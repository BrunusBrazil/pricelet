var module = angular.module('accSubgroupModule');

module.controller('AccSubgroupController', [ '$scope', 'accSubgroupService',
                                             'accountService', 'NgTableParams', 
                                             'subAccounts', 'accounts', 'AppMessageService',
		function AccountController ($scope, accSubgroupService, accountService, NgTableParams,
									subAccounts, accounts, AppMessageService) {
			var vm = this;
			vm.originalAccounts = angular.copy(accounts);
			vm.originalSubAccounts = angular.copy(subAccounts);
			vm.message= {display: false};
			vm.adding = false;
			
			vm.tableParams = new NgTableParams({}, {
			      filterDelay: 0,
			      counts: [5, 10],
			      dataset: angular.copy(subAccounts)
			 });
			
			vm.create = function(subGroupAccount){
				$scope.$broadcast('show-errors-check-validity');
				setId(subGroupAccount.account);	
				accSubgroupService.create(subGroupAccount).then(function(subGroupAccountResponse){
					angular.copy(subGroupAccountResponse, subGroupAccount);
					setId(subGroupAccount);
					vm.message = AppMessageService.displayDefaultMessage('CRUD1','OK', 'account')
					vm.adding = false;
				}, function(response){
					vm.message = AppMessageService.displayDefaultMessage('CRUD1','ERROR', 'account')
				});
			}
			

			vm.add  = function(){
				if(vm.adding === false){
				      vm.tableParams.settings().dataset.unshift(newSubGrupAccount());
				      // we need to ensure the user sees the new row we've just added.
				      // it seems a poor but reliable choice to remove sorting and move them to the first page
				      // where we know that our new item was added to
				      vm.tableParams.sorting({});
				      vm.tableParams.page(1);
				      vm.tableParams.reload();
				      vm.adding = true;
				}
			}
			
			vm.remove = function(acc){
			    setId(acc);
				accSubgroupService.remove(acc).then(function(accounts){
			      vm.tableParams.settings({
			        dataset: angular.copy(accounts)
			      });
				  acc.editing = false;
				  acc.creating = false;
				  acc.adding = false;
 			  	  vm.message = AppMessageService.displayDefaultMessage('CRUD4','OK', 'account')
				
				}, function(response){
				
					vm.message = AppMessageService.displayDefaultMessage('CRUD4','ERROR', 'account')
				});
			}

			vm.edit = function(acc){
	          acc.editing = true;
			}		
			
			
			vm.cancel = function(acc){
			 var currentPage = vm.tableParams.page();
		      vm.tableParams.settings({
		        dataset: angular.copy(vm.originalSubAccounts)
		      });
  	          vm.tableParams.page(currentPage);
     		  acc.editing = false;
			  acc.creating = false;
			}
			
			vm.saveEdition = function(acc){
			  setId(acc);
			  setId(acc.account);
			  accSubgroupService.edit(acc).then(function(response){
				  acc.editing = false;
				  acc.creating = false;
				  acc = response; 	
				  setId(acc);
			  }, function(response){
				  vm.message = AppMessageService.displayDefaultMessage('CRUD3','ERROR', 'account')
		      });	
			}
			
			function newSubGrupAccount(){
				return {
					id: null, 
					description: null,
					creating: true,
					account: {
						description:null
					}
				}
			};

			function setId(object){
				var link  = null;
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
			
	}
]);
