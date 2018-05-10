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
<<<<<<< HEAD
			vm.totalMoneyIn = 0;
			vm.totalMoneyOut = 0;
			vm.totalResult = 0;
			vm.numberTransactions = 0;
			  
||||||| merged common ancestors
			
=======
			vm.totalDebit = 0;
			vm.totalCredit = 0;
			vm.totalResult = 0;
			vm.numberTransactions = 0;
			  
>>>>>>> c7c1d78dfcc3991ced6cc09a9fb3cc46ce6eee7e
			vm.tableParams = new NgTableParams({}, {
<<<<<<< HEAD
				  filterDelay: 0,
				  count: 5,
			      counts: [5],
||||||| merged common ancestors
			      filterDelay: 0,
			      counts: [5, 10],
=======
			      filterDelay: 0,
			      counts: [5],
>>>>>>> c7c1d78dfcc3991ced6cc09a9fb3cc46ce6eee7e
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

<<<<<<< HEAD
			function setTotalMoneyIn(transactions){
				vm.totalMoneyIn = 0;
				_.forEach(transactions, function(e) {
||||||| merged common ancestors
			vm.getTotalDebit = function(){
				var totalDebit = 0 ;
				_.forEach(filterByTransactionDate(transactions, false), e => {
=======
			function setTotalDebit(transactions){
				vm.totalDebit = 0;
				_.forEach(transactions, function(e) {
>>>>>>> c7c1d78dfcc3991ced6cc09a9fb3cc46ce6eee7e
					if(e.entrada){
<<<<<<< HEAD
						vm.totalMoneyIn += e.valor;
||||||| merged common ancestors
						totalDebit += e.valor;
=======
						vm.totalDebit += e.valor;
>>>>>>> c7c1d78dfcc3991ced6cc09a9fb3cc46ce6eee7e
					}
				});
			}
			
<<<<<<< HEAD
			function setMargin(transactions){
				vm.totalResult = (vm.totalMoneyIn - vm.totalMoneyOut);
||||||| merged common ancestors
			vm.getMargin = function(){
				return (vm.getTotalCredit() - vm.getTotalDebit());
=======
			function setMargin(transactions){
				vm.totalResult = (vm.totalCredit - vm.totalDebit);
>>>>>>> c7c1d78dfcc3991ced6cc09a9fb3cc46ce6eee7e
			}

<<<<<<< HEAD
			function setTotalMoneyOut(transactions){
				vm.totalMoneyOut = 0;
				_.forEach(transactions, function(e) {
||||||| merged common ancestors
			vm.getTotalCredit = function(){
				var totalCredit = 0 ;
				_.forEach(filterByTransactionDate(transactions, false), e => {
=======
			function setTotalCredit(transactions){
				vm.totalCredit = 0;
				_.forEach(transactions, function(e) {
>>>>>>> c7c1d78dfcc3991ced6cc09a9fb3cc46ce6eee7e
					if(!e.entrada){
<<<<<<< HEAD
						vm.totalMoneyOut += e.valor;
||||||| merged common ancestors
						totalCredit += e.valor;
=======
						vm.totalCredit += e.valor;
>>>>>>> c7c1d78dfcc3991ced6cc09a9fb3cc46ce6eee7e
					}
<<<<<<< HEAD
				});
			}
			
			vm.updateTableFacts =  function(transactions){
				setTotalNumberOfTransaction(transactions);
				setTotalMoneyOut(transactions);
				setTotalMoneyIn(transactions);
				setMargin(transactions);
			} 
			
			function resetTransactionState(transaction){
				transaction.editing = false;
				transaction.creating = false;
			    vm.adding = false;
||||||| merged common ancestors
				})
				return totalCredit;
=======
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
>>>>>>> c7c1d78dfcc3991ced6cc09a9fb3cc46ce6eee7e
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
<<<<<<< HEAD

			vm.debitOrCreditUpdate = function (xt){
				xt.editing = !xt.editing;
				
			    vm.updateTableFacts(vm.tableParams.settings().dataset);
//				vm.message = AppMessageService.displayDefaultMessage('UNSAVED','WARN', 'transaction');
			}
||||||| merged common ancestors
=======

			vm.debitOrCreditUpdate = function (xt){
					xt.creating = false;
					xt.editing = !xt.editing;
				
			    vm.updateTableFacts(vm.tableParams.settings().dataset);
//				vm.message = AppMessageService.displayDefaultMessage('UNSAVED','WARN', 'transaction');
			}
>>>>>>> c7c1d78dfcc3991ced6cc09a9fb3cc46ce6eee7e
			
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
