var module = angular.module('xTransactionModule');

     module.service('xTransactionService', ['Restangular',function(Restangular){
		
        var base = Restangular.all('XTransaction/');

    	function create(account){
			return  base.post(account);
		}
    	
    	function remove(acc){
    		return Restangular.one("XTransaction/", acc.id).remove();
    	}
		
    	function getAll(){
    		return  base.getList(); 
    	}
    	
    	function edit(account){
    		return Restangular.one('XTransaction/', account.id).customPUT(account)
    	}
    	
		return  {
				create:create,
				getAll:getAll,
				remove:remove,
				edit:edit
		}
		
	}]);


