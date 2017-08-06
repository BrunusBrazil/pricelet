var module = angular.module('accSubgroupModule');

module.controller('AccSubgroupController', [ '$scope', 'accSubgroupService','accountService',
		function AccountController ($scope, accSubgroupService, accountService) {
			var self = this;


			$scope.newSubgroupaccount =  {
					id: null, 
					description: null,
					account: {
							description:null
						}
				};

			self.create = function(newSubgroupaccount){
				setId(newSubgroupaccount.account);	
				accSubgroupService.create(newSubgroupaccount).then(function(account){
					newSubgroupaccount = account;
					newSubgroupaccount.editing = false;
					newSubgroupaccount.creating = false;
					alert('Success');
					setId(newSubgroupaccount);
				}, function(response){
					alert('Cannot Create');
					console.log(response);
				});
			}
			
			self.getAccounts = function() {
				accSubgroupService.getAll().then(function(accounts) {
					$scope.accounts = accounts;
				});
			}
				
			self.remove = function(acc){
			    setId(acc);
				accSubgroupService.remove(acc).then(function(accounts){
					$scope.accounts = accounts;
					alert("Account Removed");
					
				}, function(response){
					alert('connot delete');		
				});
			}

			self.edit = function(acc){
	          acc.editing = true;
			}		
			
			
			self.cancel = function(xt){
				xt.editing = false;
			}
			
			self.saveEdition = function(acc){
			  setId(acc);
			  setId(acc.account);
			  accSubgroupService.edit(acc).then(function(response){
				  acc = response; 	
				  acc.editing = false;
				  acc.creating = false;
				  setId(acc);
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
			
			$scope.groupedRange = self.getAccounts();
	}
]);
