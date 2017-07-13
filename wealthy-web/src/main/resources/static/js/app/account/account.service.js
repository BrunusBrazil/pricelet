var module = angular.module('accountModule');

     module.service('accountService', ['Restangular',function(Restangular){
		
        var base = Restangular.all('Account/');

    	function create(account){
			return  base.post(account);
		}
    	
    	function remove(acc){
    		return Restangular.one("Account/", acc.id).remove();
    	}
		
    	function getAll(){
    		return  base.getList(); 
    	}
    	
    	function edit(account){
    		return Restangular.one('Account/', account.id).customPUT(account)
    	}
    	
		return  {
				create:create,
				getAll:getAll,
				remove:remove,
				edit:edit
		}
		
	}]);


