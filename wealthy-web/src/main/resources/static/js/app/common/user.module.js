var module = angular.module('userModule', ['ui.router']);

module.controller('userController', ['$scope', '$state', 'userService',
                                    function userController($scope, $state, userService){
	$scope.user  =  new User();
	
	function User(){
		this.name = '',
		this.password = '',
		this.email = ''
	}	
	
	$scope.register = function(user){
		userService.create(user).then(function(response){
			if(response.status = 200){
				$state.go('ui.login');
			}
		}, function(error){
			alert(error);
		}); 
		
	}	
	
}]);