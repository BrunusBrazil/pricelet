var module = angular.module('accountModule');

     module.service('accountService', ['Restangular',function(Restangular){
		
        var base = Restangular.all('Account/');

    	function create(account){
			return  base.post(account);
		}
		
		return  {
				create:create
		}
		
	}]);


