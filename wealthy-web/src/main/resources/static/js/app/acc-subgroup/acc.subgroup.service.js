var module = angular.module('accSubgroupModule');

     module.service('accSubgroupService', ['Restangular',function(Restangular){
		
        var base = Restangular.all('AccSubGroup/');

    	function create(account){
			return  base.post(account);
		}
    	
    	function remove(acc){
    		return Restangular.one("AccSubGroup/", acc.id).remove();
    	}
		
    	function getAll(){
    		return  base.getList(); 
    	}
    	
    	function edit(account){
    		return Restangular.one('AccSubGroup/', account.id).customPUT(account)
    	}
    	
		return  {
				create:create,
				getAll:getAll,
				remove:remove,
				edit:edit
		}
		
	}]);
