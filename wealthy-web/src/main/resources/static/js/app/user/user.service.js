var module = angular.module('userModule');

module.service('userService', ['Restangular',function(Restangular){
	
    var base = Restangular.all('User/');

	function create(account){
		return  base.post(account);
	}
		
	return {
			create:create
	}
}]);