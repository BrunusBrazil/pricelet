var module = angular.module('userModule');

module.service('userService', ['Restangular',function(Restangular){
	
    var base = Restangular.all('User/');

	function create(user){
		return  base.post(user);
	}
	
	function recover(user){
		return Restangular.all('User/recover/').post(user);
	}
		
	function resetPassword(user){
		return Restangular.all('User/resetPassword/').post(user);
	}
	
	return {
			create:create,
			recover: recover,
			resetPassword:resetPassword
	}
}]);