(function($){
	'use strict'
var module = angular.module('xTransactionModule');


module.controller('xTransactionController', [ '$scope', 'accountService',
                                              'accSubgroupService', 'xTransactionService', 
                                              'subAccounts', 'accounts', 'transactions', 'NgTableParams',
                                              'AppMessageService','dataChart','CommonService',
       function xTransactionController ($scope,	accountService, 
											accSubgroupService, xTransactionService,
											subAccounts, accounts, transactions, NgTableParams, 
											AppMessageService, dataChart, CommonService) {
			var vm = this;
			vm.adding = false;
			vm.message= {display: false};
			vm.originalsubAccounts = angular.copy(subAccounts);
			vm.originalAccounts = angular.copy(accounts);
			vm.originalTransactions = angular.copy(transactions);
			vm.totalMoneyIn = 0;
			vm.totalMoneyOut = 0;
			vm.totalResult = 0;
			vm.inputDisabled = true;			
			vm.dataChart = angular.copy(dataChart);		
			vm.data =  CommonService.buildDefaultLineChart(dataChart.data.EXPENSES, dataChart.data.INCOMES, dataChart.labels)

			vm.tableParams = new NgTableParams({
			  sorting: { dateTransaction: "desc" }
			}, {
			  count: 5,
			  counts: [5, 10],
		      dataset: angular.copy(transactions)
			 });
			
			vm.create = function(xtransaction){	
				vm.inputDisabled = false;
				setId(xtransaction.account);
				setId(xtransaction.accSubGroup);
				xTransactionService.create(xtransaction).then(function(response){
					angular.copy(response, xtransaction);
					setId(response);
					xtransaction.dateTransaction = new Date(xtransaction.dateTransaction); 
					vm.adding = false;
					vm.inputDisabled = true;
					vm.message = AppMessageService.displayDefaultMessage('CRUD1','OK', 'account')
 				    vm.updateTableFacts(vm.tableParams.settings().dataset);
					return CommonService.getTransactionsChart();
				}, function(response){
					vm.message = AppMessageService.displayDefaultMessage('CRUD1','ERROR', 'account')
				}).then(function(chartDataReponse) {
					vm.data = CommonService.buildDefaultLineChart(chartDataReponse.data.EXPENSES, chartDataReponse.data.INCOMES, chartDataReponse.labels)
				});
				
			}
			vm.remove = function(transactionReq){
			    setId(transactionReq);
				xTransactionService.remove(transactionReq).then(function(transactionsResp){
				  xTransactionService.convertStringToDate(transactionsResp);
				  vm.originalTransactions = angular.copy(transactionsResp);
				  vm.tableParams.settings({
			        dataset: angular.copy(filterByTransactionDate(transactionsResp, false))
			      });				  
				  vm.updateTableFacts(filterByTransactionDate(transactionsResp, false));
				  vm.adding = false;
				  vm.message = AppMessageService.displayDefaultMessage('CRUD4','OK', 'account')
				}, function(response){
					vm.message = AppMessageService.displayDefaultMessage('CRUD4','ERROR', 'account')
				});
			}

			vm.edit = function(xtransaction){
				vm.inputDisabled = false;
				xtransaction.editing = true;
			}						
			
			vm.cancel = function(xt){
		      var currentPage = vm.tableParams.page();
		      vm.tableParams.settings({
		        dataset: angular.copy(vm.originalTransactions)
		      });
		      vm.tableParams.page(currentPage);
   			  vm.inputDisabled = true;
  			  vm.adding = false;
			}
			
			vm.saveEdition = function(transactionReq){
				setId(transactionReq);
			  	setId(transactionReq.account);
				setId(transactionReq.accSubGroup);
				xTransactionService.edit(transactionReq).then(function(transactionResp){
	  				xTransactionService.getAll(false).then(function(transactions){
  					  xTransactionService.convertStringToDate(transactions);
  					  vm.originalTransactions = angular.copy(transactions);
	  			      var currentPage = vm.tableParams.page();
	  			      vm.tableParams.settings({
	  			        dataset: angular.copy(filterByTransactionDate(transactions, false))
	  			      });
	  			      vm.tableParams.page(currentPage);
	  			      vm.updateTableFacts(filterByTransactionDate(transactions, false));
	  			      vm.inputDisabled = true;
	  				})
	  			  }, function(error){
					  vm.message = AppMessageService.displayDefaultMessage('CRUD3','ERROR', 'account')
			  });	
			}

			vm.add = function(){
				vm.inputDisabled = false;
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

			function setTotalMoneyIn(transactions){
				vm.totalMoneyIn = 0;
				_.forEach(transactions, function(e) {
					if(e.entrada){
						vm.totalMoneyIn += e.valor;
					}
				});
			};
			
			function setMargin(transactions){
				vm.totalResult = (vm.totalMoneyIn - vm.totalMoneyOut);
			};

			function setTotalMoneyOut(transactions){
				vm.totalMoneyOut = 0;
				_.forEach(transactions, function(e) {
					if(!e.entrada){
						vm.totalMoneyOut += e.valor;
					}
				});
			};
			
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
			}
			
			vm.updateTableFacts =  function(transactions){
				setTotalNumberOfTransaction(transactions);
				setTotalMoneyOut(transactions);
				setTotalMoneyIn(transactions);
				setMargin(transactions);
				vm.originalTransactions = angular.copy(transactions); 
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
				if(vm.adding){
					xt.editing = false;	
				}else{
					xt.editing = !xt.editing;
				}
			    vm.updateTableFacts(vm.tableParams.settings().dataset);
			}
			
			function filterByTransactionDate(xTransaction, defaultDateRange){
				var dateRangeFilter = defaultDateRange? xTransactionService.getDefaultDateRange():  xTransactionService.getDateRangeFilter(); 
				var transactionsByDate = [];
				function convertDate(date){
					return moment(moment.utc(moment(date).format("YYYY-MM-DD")).format());
				}
				transactionsByDate = 
					_.filter(xTransaction, function (element) {
						return convertDate(element.dateTransaction).isSameOrAfter(convertDate(dateRangeFilter.startDate)) 
						&&
						convertDate(element.dateTransaction).isSameOrBefore(convertDate(dateRangeFilter.endDate))
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
					dateTransaction: new Date(moment().format('YYYY-MM-DD HH:mm'))
				} 
			}
}
])}(window.jQuery));
