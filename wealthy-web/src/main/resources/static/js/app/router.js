var app = angular.module('webapp',['accountModule','ui.router','ngResource']);

app.config(function($stateProvider, $urlRouterProvider) {
		
		$urlRouterProvider.otherwise('/index.html');

		$stateProvider.state('account-group', {
	        url: '/account-group',
	        templateUrl: '/js/app/account/account.html',
	        controller : 'AccountController'
	    });

});