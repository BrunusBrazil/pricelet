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
			
			vm.tableParams = new NgTableParams({}, {
			      filterDelay: 0,
			      counts: [5, 10],
			      dataset: angular.copy(transactions)
			 });
			
			vm.create = function(xtransaction){	
				setId(xtransaction.account);
				setId(xtransaction.accSubGroup);
				xTransactionService.create(xtransaction).then(function(response){
					angular.copy(response, xtransaction);
					setId(response);
					vm.message = AppMessageService.displayDefaultMessage('CRUD1','OK', 'account')
				}, function(response){
					vm.message = AppMessageService.displayDefaultMessage('CRUD1','ERROR', 'account')
				});
			}
			
			vm.remove = function(xtransaction){
			    setId(xtransaction);
				xTransactionService.remove(xtransaction).then(function(xtransactions){
			      vm.tableParams.settings({
				        dataset: angular.copy(filterByTransactionDate(transactions, false))
			      });
	 			  var currentPage = vm.tableParams.page();
			      vm.tableParams.settings({
			        dataset: angular.copy(xtransactions)
			      });
	  	          vm.tableParams.page(currentPage);
				  xtransaction.editing = false;
				  xtransaction.creating = false;
				
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
     		  xt.editing = false;
			  xt.creating = false;
			}
			
			vm.saveEdition = function(xt){
			  setId(xt);
			  setId(xt.account);
  			  setId(xt.accSubGroup);
				  accountService.edit(xt).then(function(response){
					  xt = response; 
					  xt.editing = false;
					  xt.creating = false;
					  setId(xt);
				  }, function(response){
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
			
			vm.getTotalNumberOfTransaction = function(){
				if(transactions &&  transactions.length > 0 ){
					return filterByTransactionDate(transactions, false).length;
				}else{
					return 0;
				}
			}

			vm.getTotalDebit = function(){
				var totalDebit = 0 ;
				_.forEach(filterByTransactionDate(transactions, false), e => {
					if(e.entrada){
						totalDebit += e.valor;
					}
				})
				return totalDebit;
			}
			
			vm.getMargin = function(){
				return (vm.getTotalCredit() - vm.getTotalDebit());
			}

			vm.getTotalCredit = function(){
				var totalCredit = 0 ;
				_.forEach(filterByTransactionDate(transactions, false), e => {
					if(!e.entrada){
						totalCredit += e.valor;
					}
				})
				return totalCredit;
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
				return	_.filter(subAccounts, e => e.account.description === account.description);
			}
			
			$scope.$watch('dateRangeString', function(oldValue, newValue){
		      vm.tableParams.settings({
			        dataset: angular.copy(filterByTransactionDate(transactions, false))
		      });
			});
			
			function filterByTransactionDate(xTransaction, defaultDateRange){
				var dateRangeFilter = defaultDateRange? xTransactionService.getDefaultDateRange():  xTransactionService.getDateRangeFilter(); 
				return  _.filter(xTransaction, function (element) {
					if(moment(moment.utc(moment(element.dateTransaction).format("YYYY-MM-DD")).format())
							.isSameOrAfter
							(moment(moment.utc(dateRangeFilter.startDate.format("YYYY-MM-DD")).format()))
								&&
				   	         moment(moment.utc(moment(element.dateTransaction).format("YYYY-MM-DD")).format())
								.isSameOrBefore
							(moment(moment.utc(dateRangeFilter.endDate.format("YYYY-MM-DD")).format()))){
						return element;
					}
				});
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
