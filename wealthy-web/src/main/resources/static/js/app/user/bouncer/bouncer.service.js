var module = angular.module('bouncerModule');

module.service('loginService', ['Restangular',function(Restangular){
    	 var isUserValid = false;
    	 function authenticate(user){
    		
		 $scope.$broadcast('show-errors-check-validity');
		  if ($scope.userForm.$invalid) { return; }
    		  
    		 if(user.name && user.password){
   			  	isUserValid = true; 	
    		 }else{
    			isUserValid = false;
    		 }
    		 return isUserValid;
    	 }
    	 
    	 function getUserState(){
    		 return isUserValid;
    	 }
    	 
		return  {
			authenticate: authenticate,
			getUserState:getUserState
		}
		
	}]);

