(function($){
	'use strict'
var module = angular.module('xTransactionModule');

module.controller('xTransactionController', [ '$scope', 'accountService',
                                              'accSubgroupService', 'xTransactionService', 
                                              'subAccounts', 'accounts',
                                              'transactions', 'NgTableParams', 'AppMessageService',
       function xTransactionController ($scope,	accountService, 
											accSubgroupService, xTransactionService,
											subAccounts, accounts, transactions, NgTableParams, 
											AppMessageService) {
			var vm = this;
			vm.adding = false;
			vm.message= {display: false};
			vm.originalsubAccounts = angular.copy(subAccounts);
			vm.originalAccounts = angular.copy(accounts);
			vm.originalTransactions = angular.copy(transactions);
			vm.totalDebit = 0;
			vm.totalCredit = 0;
			vm.totalResult = 0;
			vm.numberTransactions = 0;
			  
			vm.tableParams = new NgTableParams({}, {
			      filterDelay: 0,
			      counts: [5],
			      dataset: angular.copy(transactions)
			 });

			
			
			vm.create = function(xtransaction){	
				setId(xtransaction.account);
				setId(xtransaction.accSubGroup);
				xTransactionService.create(xtransaction).then(function(response){
					angular.copy(response, xtransaction);
					setId(response);
					vm.adding = false;
					vm.message = AppMessageService.displayDefaultMessage('CRUD1','OK', 'account')
 				    vm.updateTableFacts(vm.tableParams.settings().dataset);
				}, function(response){
					vm.message = AppMessageService.displayDefaultMessage('CRUD1','ERROR', 'account')
				});
			}
			
			vm.remove = function(transactionReq){
			    setId(transactionReq);
				xTransactionService.remove(transactionReq).then(function(transactionsResp){
				  vm.originalTransactions = angular.copy(transactionsResp);

				  var currentPage = vm.tableParams.page();
				  vm.tableParams.settings({
			        dataset: angular.copy(transactionsResp)
			      });
				  
				  vm.tableParams.page(currentPage);
				
				  vm.updateTableFacts(transactionsResp);

				  vm.adding = false;
				  vm.message = AppMessageService.displayDefaultMessage('CRUD4','OK', 'account')
				}, function(response){
					vm.message = AppMessageService.displayDefaultMessage('CRUD4','ERROR', 'account')
				});
			}

			vm.edit = function(xtransaction){
				xtransaction.editing = true;
			}						
			
			vm.cancel = function(xt){
		      var currentPage = vm.tableParams.page();
		      vm.tableParams.settings({
		        dataset: angular.copy(vm.originalTransactions)
		      });
		      vm.tableParams.page(currentPage);
			}
			
			vm.saveEdition = function(transactionReq){
				setId(transactionReq);
			  	setId(transactionReq.account);
				setId(transactionReq.accSubGroup);
				xTransactionService.edit(transactionReq).then(function(transactionResp){
	  				xTransactionService.getAll(false).then(function(transactions){
	  				  vm.originalTransactions = angular.copy(transactions);
	  			      var currentPage = vm.tableParams.page();
	  			      vm.tableParams.settings({
	  			        dataset: angular.copy(transactions)
	  			      });
	  			      vm.tableParams.page(currentPage);
	  			      vm.updateTableFacts(transactions);
	  				})
	  			  }, function(error){
					  vm.message = AppMessageService.displayDefaultMessage('CRUD3','ERROR', 'account')
			  });	
			}

			vm.add = function(){
				if(vm.adding === false){
				      vm.tableParams.settings().dataset.unshift(newTransaction());
				      // we need to ensure the user sees the new row we've just added.
				      // it seems a poor but reliable choice to remove sorting and move them to the first page
				      // where we know that our new item was added to
				      vm.tableParams.sorting({});
				      vm.tableParams.page(1);
				      vm.tableParams.reload();
				      vm.adding = true;
				}
			} 
			
			function setTotalNumberOfTransaction(transactions){
				if(transactions &&  transactions.length > 0 ){
					vm.numberTransactions = transactions.length;
				}else{
					vm.numberTransactions =  0;
				}
			}

			function setTotalDebit(transactions){
				vm.totalDebit = 0;
				_.forEach(transactions, function(e) {
					if(e.entrada){
						vm.totalDebit += e.valor;
					}
				});
			}
			
			function setMargin(transactions){
				vm.totalResult = (vm.totalCredit - vm.totalDebit);
			}

			function setTotalCredit(transactions){
				vm.totalCredit = 0;
				_.forEach(transactions, function(e) {
					if(!e.entrada){
						vm.totalCredit += e.valor;
					}
				});
			}
			
			vm.updateTableFacts =  function(transactions){
				setTotalNumberOfTransaction(transactions);
				setTotalCredit(transactions);
				setTotalDebit(transactions);
				setMargin(transactions);
			} 
			
			function resetTransactionState(transaction){
				transaction.editing = false;
				transaction.creating = false;
			    vm.adding = false;
			}
			
			function setId(object){
				var link  = null;
				
				if(object && object.id)
				return;
				
				if(object){
					if(object.id){
						return;
					}
					if(object._links){
						if(object._links.self){
 						  link  = object._links.self.href; 
						}else{
 						  link  = object._links? object._links[0].href : object.links[0].href; 
						}
					}else{
						link  =  object.links[0].href; 
					}
				    object.id = link.substr(link.lastIndexOf('/')+1,link.length);
				}
			}

			vm.getSubAccountByAccount = function(account){
				return _.filter(vm.originalsubAccounts, function(e){
						return e.account.description === account.description
				});
			}
			
			$scope.$watch('dateRangeString', function(oldValue, newValue){
		      vm.tableParams.settings({
			        dataset: angular.copy(filterByTransactionDate(transactions, false))
		      });
			    vm.updateTableFacts(vm.tableParams.settings().dataset);
			});

			vm.debitOrCreditUpdate = function (xt){
					xt.creating = false;
					xt.editing = !xt.editing;
				
			    vm.updateTableFacts(vm.tableParams.settings().dataset);
//				vm.message = AppMessageService.displayDefaultMessage('UNSAVED','WARN', 'transaction');
			}
			
			function filterByTransactionDate(xTransaction, defaultDateRange){
				var dateRangeFilter = defaultDateRange? xTransactionService.getDefaultDateRange():  xTransactionService.getDateRangeFilter(); 
				var transactionsByDate = [];
				function convertDate(date){
					return moment(moment.utc(moment(date).format("YYYY-MM-DD")).format());
				}
				transactionsByDate = 
					_.filter(xTransaction, function (element) {
						return convertDate(element.dateTransaction) 
						.isSameOrAfter(convertDate(dateRangeFilter.startDate)) 
					/*	&&
						convertDate(element.dateTransaction) 
						.isSameOrBefore(convertDate(dateRangeFilter.startDate))
					*/	
			    	 });				
				return transactionsByDate;
			}
			
			function newTransaction(){
				return  {
					creating: true,
					id: null, 
					description: null,
					account: {
						description:null
					},
					accSubGroup:{
						description:null
					},
					type: null,
					valor: null,
					entrada: false,
					create:null,
					dateTransaction:null
				} 
			}
			
}
])}(window.jQuery));
