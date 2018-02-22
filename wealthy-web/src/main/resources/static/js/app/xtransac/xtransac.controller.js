(function($){
	'use strict'
var module = angular.module('xTransactionModule');

module.controller('xTransactionController', [ '$scope', 
                                              'accountService',
                                              'accSubgroupService',
                                              'xTransactionService',
       function xTransactionController ($scope,
											accountService, 
											accSubgroupService,
											xTransactionService) {
			var self = this;
			$scope.newXTransaction = newTransaction();
			
			self.create = function(xtransaction){	
				setId(xtransaction.account);
				setId(xtransaction.accSubGroup);
				xTransactionService.create(xtransaction).then(function(response){
					angular.copy(response, xtransaction);
					xtransaction.filtered = true;
					setId(response);
				}, function(response){
					alert("Error");
				});
			}
			
			$scope.getXtransactions = function() {
				xTransactionService.getAll().then(function(xtransactions) {
					$scope.xtransactions =	xtransactions;
					filterByTransactionDate($scope.xtransactions, true);
				});
			}
				
			$scope.remove = function(xtransaction){
			    setId(xtransaction);
				xTransactionService.remove(xtransaction).then(function(xtransactions){
					$scope.xtransactions = xtransactions;
					filterByTransactionDate($scope.xtransactions, false);
				}, function(response){
					alert("Error");
				});
			}

			$scope.edit = function(xtransaction){
				xtransaction.editing = true;
			}						
			
			self.cancel = function(xt){
				if(xt && !xt.id){
					$scope.xtransactions.splice($scope.xtransactions.length -1,1);
				}else{
					xt.editing = false;
					xt.creating = false;
				}
			}
			
			
			self.saveEdition = function(xt){
			  setId(xt);
			  setId(xt.account);
  			  setId(xt.accSubGroup);
			  xt.editing = false;
			  xTransactionService.edit(xt).then(function(response){
				  $scope.getXtransactions();
				  alert('Success');		
			  }, function(response){
					alert("Error");
		      });	
			}

			$scope.addNewXT = function(){
				if(_.filter($scope.xtransactions, a => a.creating === true).length === 0){
					var newXt = newTransaction();
					newXt.creating = true;
					$scope.xtransactions.push(newXt);
				}
			} 
			
			self.getTotalNumberOfTransaction = function(){
				if($scope && $scope.xtransactions && $scope.xtransactions.length > 0 ){
					return _.filter($scope.xtransactions, e => e.filtered).length;
				}else{
					return 0;
				}
			}

			self.getTotalDebit = function(){
				var totalDebit = 0 ;
				_.forEach($scope.xtransactions, e => {
					if(e.entrada && e.filtered){
						totalDebit += e.valor;
					}
				})
				return totalDebit;
			}
			
			self.getMargin = function(){
				return (self.getTotalCredit() - self.getTotalDebit());
			}

			self.getTotalCredit = function(){
				var totalCredit = 0 ;
				_.forEach($scope.xtransactions, e => {
					if(!e.entrada  && e.filtered){
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

			$scope.getMainAccounts = function() {
				accountService.getAll().then(function(accounts) {
					$scope.mainGroups  = accounts;
				});
			}
			
			$scope.getSubAccounts = function() {
				accSubgroupService.getAll().then(function(subGroups) {
					$scope.subGroups  = subGroups;
				});
			}
			
			$scope.getSubAccountByAccount = function(account){
				return	_.filter($scope.subGroups, e=> e.account.description === account.description)
			}
			
			$scope.$watch('dateRangeString', function(oldValue, newValue){
				filterByTransactionDate($scope.xtransactions, false);
			});
			
			function filterByTransactionDate(xTransaction, defaultDateRange){
				var dateRangeFilter = defaultDateRange? xTransactionService.getDefaultDateRange():  xTransactionService.getDateRangeFilter(); 
				_.forEach(xTransaction, function(element){
						if(moment(moment.utc(moment(element.dateTransaction).format("YYYY-MM-DD")).format())
							.isSameOrAfter
							(moment(moment.utc(dateRangeFilter.startDate.format("YYYY-MM-DD")).format()))
							&&
							moment(moment.utc(moment(element.dateTransaction).format("YYYY-MM-DD")).format())
							.isSameOrBefore
							(moment(moment.utc(dateRangeFilter.endDate.format("YYYY-MM-DD")).format()))
							
						){	
							element.filtered=true;		
						 }else{
							element.filtered=false;		
						 }
				});					
			}
			
			function newTransaction(){
				return  {
					id: null, 
					filtered: true,
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

			
			
			$scope.getMainAccounts();
			$scope.getSubAccounts();
			$scope.getXtransactions();

	}
])}(window.jQuery));
