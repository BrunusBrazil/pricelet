var module = angular.module('accSubgroupModule');

module.controller('AccSubgroupController', [ '$scope', 'accSubgroupService','accountService',
		function AccountController ($scope, accSubgroupService, accountService) {
			var self = this;

			$scope.newSubgroupaccount = {
					id: null, 
					description: null,
					account: {
							description:null
					}
			};
			
			$scope.create = function(newSubgroupaccount){
				setId(newSubgroupaccount.account);	
				accSubgroupService.create(newSubgroupaccount).then(function(account){
					$scope.accounts.push(account);
					alert("Account Created");
					newSubgroupaccount.description = null;
					setId(account);
				}, function(response){
					alert('Cannot Create');
					console.log(response);
				});
			}
			
			$scope.getAccounts = function() {
				accSubgroupService.getAll().then(function(accounts) {
					$scope.accounts = accounts;
				});
			}
				
			$scope.remove = function(acc){
			    setId(acc);
				accSubgroupService.remove(acc).then(function(accounts){
					$scope.accounts = accounts;
					alert("Account Removed");
					
				}, function(response){
					alert('connot delete');		
				});
			}

			$scope.edit = function(acc){
	          acc.editing = true;
			}						
			
			$scope.saveEdition = function(acc){
			  setId(acc);
			  accSubgroupService.edit(acc).then(function(response){
				   acc.editing = false;
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
			
			//services 
			$scope.getMainAccounts = function() {
				accountService.getAll().then(function(accounts) {
					$scope.mainGroups  = accounts;
				});
			}
			
			$scope.getMainAccounts();
			
			$scope.groupedRange = $scope.getAccounts();
	}
]);
