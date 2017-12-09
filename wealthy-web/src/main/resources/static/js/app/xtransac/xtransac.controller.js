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
											xTransactionService,
											notificationService) {
			var self = this;

			$scope.newXTransaction = newTransaction();
			
			self.create = function(xtransaction){			
				setId(xtransaction.account);
				setId(xtransaction.accSubGroup);
				xTransactionService.create(xtransaction).then(function(response){
					angular.copy(response, xtransaction);
					setId(response);
					notificationService.success('Success!!!', 'Transaction completed');
					newXTransaction = newTransaction();
				}, function(response){
					notificationService.success('Ops! Error', 'Something happend the operation was cancelled');
				});
			}
			
			$scope.getXtransactions = function() {
				xTransactionService.getAll().then(function(xtransactions) {
					$scope.xtransactions = xtransactions;
				});
			}
				
			$scope.remove = function(xtransaction){
			    setId(xtransaction);
				xTransactionService.remove(xtransaction).then(function(xtransactions){
					$scope.xtransactions = xtransactions;
					alert("xtransaction Removed");
				}, function(response){
					notificationService.success('Ops! Error', 'Something happend the operation was cancelled');
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
					notificationService.success('Ops! Error', 'Something happend the operation was cancelled');
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
				return  $scope.xtransactions? $scope.xtransactions.length: 0;
			}

			self.getTotalDebit = function(){
				var totalDebit = 0 ;
				_.forEach($scope.xtransactions, e => {
					if(e.entrada){
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

			function newTransaction(){
				return  {
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

			//external service calls 
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
			
			$scope.getMainAccounts();
			$scope.getSubAccounts();
			$scope.getXtransactions();
			
			$scope.getSubAccountByAccount = function(account){
				return	_.filter($scope.subGroups, e=> e.account.description === account.description)
			}
	}
])}(window.jQuery));
