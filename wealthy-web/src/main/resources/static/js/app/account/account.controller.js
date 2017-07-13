var module = angular.module('accountModule');

module.controller('AccountController', [ '$scope', 'accountService',
		function AccountController ($scope, accountService, ModalService) {
			var self = this;

			$scope.newAccount = {
					id: null, 
					description: null
			};
			
			$scope.create = function(newAccount){
				accountService.create(newAccount).then(function(account){
					$scope.accounts.push(account);
					alert("Account Created");
					newAccount.description = null;
					setId(account);
				}, function(response){
					alert('Cannot Create');
					console.log(response);
				});
			}
			
			$scope.getAccounts = function() {
				accountService.getAll().then(function(accounts) {
					$scope.accounts = accounts;
				});
			}
				
			$scope.remove = function(acc){
			    setId(acc);
				accountService.remove(acc).then(function(accounts){
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
			  accountService.edit(acc).then(function(response){
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
			
			$scope.groupedRange = $scope.getAccounts();
	}
]);
