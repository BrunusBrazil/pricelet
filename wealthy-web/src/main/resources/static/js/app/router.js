var app = angular.module('webapp',['accountModule','accSubgroupModule','ui.router','ngResource','xTransactionModule']);

app.config(function($stateProvider, $urlRouterProvider) {
		
		$urlRouterProvider.otherwise('/index.html');

		$stateProvider
		.state('account-group', {
	        url: '/account-group',
	        templateUrl: '/js/app/account/account.html',
	        controller : 'AccountController'
	    })
	    .state('account-subgroup', {
	        url: '/account-subgroup',
	        templateUrl: '/js/app/acc-subgroup/acc-subgroup.html',
	        controller : 'AccSubgroupController'
	    })
	    .state('xtransac', {
	        url: '/transaction',
	        templateUrl: '/js/app/xtransac/xtransac.html',
	        controller : 'xTransactionController'
	    });
		
});