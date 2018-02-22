var module = angular.module('userModule', ['ui.router']);

module.controller('userController', ['$scope', '$state', 'userService',
                                    function userController($scope, $state, userService){
	$scope.user  =  new User();
	
	function User(){
		this.userName = '',
		this.fullName = '',
		this.password = '',
		this.email = '',
		this.term = false,
		this.newsletter= true
	}	
	
	$scope.register = function(user){
		$scope.$broadcast('show-errors-check-validity');
		
		if(!user.term){
			$scope.message = 'If you want to register, you must accept our terms of service';
			return;
		}
		
		if(!(user && user.userName && user.fullName && user.email && user.password)) return;
				
		userService.create(user).then(function(response){
			if(response.status = 200){
				$state.go('ui.login');
			}
		}, function(error){
			$scope.message = error.data.description;
		}); 
		
	}	
	
}]);