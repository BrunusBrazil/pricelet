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
 		
 		if(!(user && user.userName && user.fullName && user.email)) return;
 				
 		userService.create(user).then(function(response){
 			if(response.status = 200){
 				var message = 'Welcome! Your account was successfully created. The password was sent to your e-mail';
 				$state.go('ui.login', {obj: message});
 			}
 		}, function(error){
 			$scope.message = error.data.description;
 		}); 
 		
 	}	
 	
 }]);

module.controller('userRecoverController', ['$scope', '$state', 'userService',
                                            function userController($scope, $state, userService){
  		$scope.user  =  new User();
  		var vm = this; 
  		function User(){
  			this.userName = '',
  			this.fullName = '',
  			this.password = '',
  			this.email = '',
  			this.term = false,
  			this.newsletter= true
  		}	
  		
  		$scope.recover = function(user){
  			$scope.$broadcast('show-errors-check-validity');
  			if(!(user && user.email)) return;
  			userService.recover(user).then(function(response){
  				$scope.message = 'Your passord was sent to your email';
  			}, function(error){
  				$scope.message = error.data.description;
  			}); 
  		}	
  		
  	}]);

module.controller('userPasswordResetController', ['$scope', '$state', 'userService',
                                          function userController($scope, $state, userService, currentUser){
		$scope.user  =  new User();
		var vm = this; 
		function User(){
			this.userName = '',
			this.fullName = '',
			this.password = '',
			this.newPassord = ''
		}	
		
		$scope.resetPassword = function(user){
			$scope.$broadcast('show-errors-check-validity');
			if(!(user && user.email)) return;
			userService.resetPassword(user).then(function(response){
				$scope.message = 'Your passord was sent to your email';
			}, function(error){
				$scope.message = error.data.description;
			}); 
		}	
		
	}]);