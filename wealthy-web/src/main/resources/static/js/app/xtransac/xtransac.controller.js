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
				xTransactionService.create(xtransaction).then(function(xtransaction){
					$scope.xtransactions.push(xtransaction);
					alert("Account Created");
					newXTransaction = newTransaction();
					setId(xtransaction);
					setId(xtransaction.account);
					setId(xtransaction.accSubGroup);
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
			
			$scope.saveEdition = function(xtransaction){
			  setId(xtransaction);
			  xTransactionService.edit(xtransaction).then(function(response){
				  xtransaction.editing = false;
					alert('Success');		
			  }, function(response){
					alert('connot edit');		
		      });	
			}
			
			function setId(object){
				var link  = null;
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
					create: new Date()
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
	}
]);
