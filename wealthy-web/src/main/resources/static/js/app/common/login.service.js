var module = angular.module('loginModule');

     module.service('loginService', ['Restangular',function(Restangular){
    	 var isUserValid = false;
    	 
    	 function authenticate(user){
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

