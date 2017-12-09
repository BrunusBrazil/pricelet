var module = angular.module('xTransactionModule');

     module.service('xTransactionService', ['Restangular','$q',function(Restangular, $q){
		var base = Restangular.all('XTransaction/');

		var cachedTransactions = [];		
		var deferred = $q.defer();
    	
		function create(account){
			return  base.post(account);
		}
    	
    	function remove(acc){
    		return Restangular.one("XTransaction/", acc.id).remove();
    	}
		
    	function getAll(){
    		return $q(function(resolve, reject) {
    			if(cachedTransactions && cachedTransactions.length === 0 ){
    				base.getList().then(function(response){
        				cachedTransactions = response;
        	    		resolve(cachedTransactions);
        			},function(error){
        				reject(error)	
        			});		
    			}else{
        			resolve(cachedTransactions);
    			}
    		});  		
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


