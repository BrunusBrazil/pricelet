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
			
			$scope.create = function(xtransaction){
				setId(xtransaction.account);
				setId(xtransaction.accSubGroup);
				xTransactionService.create(xtransaction).then(function(response){
					xtransaction.editing = false;
					xtransaction.creating = false;
					cloneXtransaction(xtransaction, response);
					$scope.xtransactions.push(xtransaction);
					alert("Account Created");
					newXTransaction = newTransaction();
				}, function(response){
					alert('Cannot Create');
					console.log(response);
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
					alert('connot delete');		
				});
			}

			$scope.edit = function(xtransaction){
				xtransaction.editing = true;
			}						
			
			$scope.saveEdition = function(xt){
			  setId(xt);
			  setId(xt.account);
  			  setId(xt.accSubGroup);
			  xt.editing = false;
			  xTransactionService.edit(xt).then(function(response){
				  $scope.getXtransactions();
				  alert('Success');		
			  }, function(response){
					alert('connot edit');		
		      });	
			}

			$scope.addNewXT = function(){
				var newXt = newTransaction();
				newXt.creating = true;
				$scope.xtransactions.unshift(newXt);
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
			
			
			function cloneXtransaction(xt, response){
					xt.id =  response.id; 
					xt.description = response.description;
					xt.account = response.account;
					xt.accSubGroup =  response.accSubGroup;
					xt.type = response.type;
					xt.valor = response.valor;
					xt.entrada =  response.entrada;
					xt.create = response.create;
					xt.dateTransaction = response.dateTransaction;
			}
	}
]);
