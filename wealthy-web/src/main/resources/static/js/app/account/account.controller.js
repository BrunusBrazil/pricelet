var module = angular.module('accountModule');
module.controller('AccountController', [ '$scope', 'accountService', 'NgTableParams',
                                         'AppMessageService','accounts',
		function AccountController ($scope, accountService, NgTableParams, AppMessageService, accounts) {
			var vm = this;
			vm.add = add;
			vm.originalAccounts = angular.copy(accounts);
			vm.adding = false;
			vm.message= {display: false};
			
			vm.tableParams = new NgTableParams({}, {
			      filterDelay: 0,
			      counts: [5, 10],
			      dataset: angular.copy(accounts)
			 });
			
			function add() {
				if(vm.adding === false){
				      vm.tableParams.settings().dataset.unshift(getEmptyAccount());
				      // we need to ensure the user sees the new row we've just added.
				      // it seems a poor but reliable choice to remove sorting and move them to the first page
				      // where we know that our new item was added to
				      vm.tableParams.sorting({});
				      vm.tableParams.page(1);
				      vm.tableParams.reload();
				      vm.adding = true;
				}
		    }
			
			vm.create = function(newAccount){
				accountService.create(newAccount).then(function(accountResponse){
					angular.copy(accountResponse, newAccount);
					setId(newAccount);
					vm.message = AppMessageService.displayDefaultMessage('CRUD1','OK', 'account')
					vm.adding = false;
				}, function(response){
					vm.message = AppMessageService.displayDefaultMessage('CRUD1','ERROR', 'account')
				});
			}
				
			vm.remove = function(acc){
			    setId(acc);
				accountService.remove(acc).then(function(accounts){
			      vm.tableParams.settings({
			        dataset: angular.copy(accounts)
			      });
				  vm.message = AppMessageService.displayDefaultMessage('CRUD4','OK', 'account')
				  acc.editing = false;
				  acc.creating = false;
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
		        dataset: angular.copy(vm.originalAccounts)
		      });
  	          vm.tableParams.page(currentPage);
     		  acc.editing = false;
			  acc.creating = false;
			}
			
			vm.saveEdition = function(acc){
			  setId(acc);
			  accountService.edit(acc).then(function(response){
				  acc.editing = false;
				  acc.creating = false;
				  acc = response; 
				  setId(acc);
			  }, function(response){
				  vm.message = AppMessageService.displayDefaultMessage('CRUD3','ERROR', 'account')
			  });	
			}
			
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
			
			
			function getEmptyAccount(){
				return {
					id: null, 
					creating: true,
					description: null
				};
			}	
			
	}
]);
	