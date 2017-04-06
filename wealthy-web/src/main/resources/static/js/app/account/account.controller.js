var module = angular.module('accountModule');

module.controller('AccountController',['$scope','accountService',function AccountController($scope, accountService){
    
	$scope.getPersonById  = function (){
		accountService.getPerson('12').then(function (account) {
			$scope.account = account;
		});
	}
	
	$scope.create = function (account){
		accountService.create(account).then(function (account) {
			$scope.account = account;
		});
		
	}
	
}]);


