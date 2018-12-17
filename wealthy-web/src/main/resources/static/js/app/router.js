var app = angular.module('webapp',['accountModule', 'bouncerModule', 'serviceUtils',
                                   'userModule', 'commonDirective','serviceUtils',
                                   'accSubgroupModule','ui.router','ngResource','xTransactionModule',
                                   'forecastModule', 'pascalprecht.translate', 'common.service']);

app.config(function($stateProvider, $urlRouterProvider,$locationProvider) {
	$locationProvider.hashPrefix('');
	$stateProvider
		.state('ui', {
	        url: '/ui',
	        abstract: true,
	        templateUrl: '/index.html'
	    })
	    .state('ui.login', {
	        url: '/login',
	        templateUrl: '/login.html',
	        params: {
	            obj: null
	        }
	    }).state('ui.register', {
	        url: '/register',
	        templateUrl: '/register.html',
	        controller : 'userController'
	       	 
	    })
	    .state('ui.password-recover', {
	        url: '/password-recover',
	        templateUrl: '/password-recover.html',
	        controller : 'userRecoverController'
	       	 
	    })
	    .state('ui.password-reset', {
	        url: '/password-reset',
	        templateUrl: '/password-reset.html',
	        controller : 'userPasswordResetController'
	       	 
	    })
	    .state('ui.home', {
	        url: '/home',
	        abstract: true,
	        templateUrl: '/home.html',
	    })
	    .state('ui.page-not-found', {
	        url: '/page-not-found',
	        abstract: true,
	        templateUrl: '/page-not-found.html',
	    })
	    .state('ui.access-denied', {
	        url: '/access-denied',
	        abstract: true,
	        templateUrl: '/access-denied.html',
	    })
	    .state('ui.home.account-group', {
	        url: '/account-group',
	        templateUrl: '/js/app/account/account.html',
	        controller : 'AccountController',
	        controllerAs: 'vm',
	        resolve: {
	            accounts: function(accountService, $q) {
            		var deferred = $q.defer();
	            		accountService.getAll().then(function(response) {
	            			deferred.resolve(response);
		            	})
		            	
	            	return deferred.promise;
	            }
	        }
	    })
	    .state('ui.home.account-subgroup', {
	        url: '/account-subgroup',
	        templateUrl: '/js/app/acc-subgroup/acc-subgroup.html',
	        controller : 'AccSubgroupController',
        	controllerAs: 'vm',
	        resolve: {
	            subAccounts: function(accSubgroupService, $q) {
            		var deferred = $q.defer();
            			accSubgroupService.getAll().then(function(response) {
	            			deferred.resolve(response);
		            	})
		            	
	            	return deferred.promise;
	            },
	            accounts: function(accountService, $q) {
            		var deferred = $q.defer();
	            		accountService.getAll().then(function(response) {
	            			deferred.resolve(response);
		            	})
		            	
	            	return deferred.promise;
	            }
	        }
	    })
	    .state('ui.home.xtransac', {
	        url: '/transaction',
	        templateUrl: '/js/app/xtransac/xtransac.html',
	        controller : 'xTransactionController',
	      	controllerAs: 'vm',
	        resolve: {
	            subAccounts: function(accSubgroupService, $q) {
            		var deferred = $q.defer();
        			accSubgroupService.getAll().then(function(response) {
            			deferred.resolve(response);
	            	})
		            return deferred.promise;
	            },
	            accounts: function(accountService, $q) {
            		var deferred = $q.defer();
	            		accountService.getAll().then(function(response) {
	            			deferred.resolve(response);
	            	})
		            return deferred.promise;
	            },
	            transactions: function(xTransactionService, $q) {
            		var deferred = $q.defer();
            		xTransactionService.getAll(false).then(function(response) {
	            			deferred.resolve(response);
		            })
		            return deferred.promise;
	            },
	            dataChart: function(CommonService, $q) {
            		var deferred = $q.defer();
            		CommonService.getTransactionsChart()				
            		.then(function(response) {
	            			deferred.resolve(response);
		            })
		            return deferred.promise;
	            }
	            
	        }
	    })
    .state('ui.home.forecast', {
        url: '/forecast',
        templateUrl: '/js/app/forecast/forecast.html',
        controller : 'ForecastController',
      	controllerAs: 'vm',
        resolve: {
            subAccounts: function(accSubgroupService, $q) {
        		var deferred = $q.defer();
        			accSubgroupService.getAll().then(function(response) {
            			deferred.resolve(response);
	            	})
	            	
            	return deferred.promise;
            },
            accounts: function(accountService, $q) {
        		var deferred = $q.defer();
            		accountService.getAll().then(function(response) {
            			deferred.resolve(response);
	            	})
	            	
            	return deferred.promise;
            },
            forecasts: function(ForecastService, $q) {
        		var deferred = $q.defer();
        			var period  = new Date();
        			ForecastService.getForecast(period).then(function(response) {
            			deferred.resolve(response);
	            	})
	            	
            	return deferred.promise;
            }
        }
    });
});

//the following method will run at the time of initializing the module. That
//means it will run only one time.
app.run(function(AuthService, $rootScope, $state, $window) {
	
	// For implementing the authentication with ui-router we need to listen the
	// state change. For every state change the ui-router module will broadcast
	// the '$stateChangeStart'.
	$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
		// checking the user is logged in or not
		if (!AuthService.user) {
			// To avoiding the infinite looping of state change we have to add a
			// if condition.
			if (toState.name != 'ui.login' && toState.name != 'ui.register') {
				event.preventDefault();
				$state.go('ui.login');
			}
		} else {
			// checking the user is authorized to view the states
			if (toState.data && toState.data.role) {
				var hasAccess = false;
				for (var i = 0; i < AuthService.user.roles.length; i++) {
					var role = AuthService.user.roles[i];
					if (toState.data.role == role) {
						hasAccess = true;
						break;
					}
				}
				if (!hasAccess) {
					event.preventDefault();
					$state.go('access-denied');
				}

			}
		}
	});
});

app.config(ConfigureTranslator);

function ConfigureTranslator($translateProvider){
	$translateProvider.useUrlLoader('translation/labels.json');
	$translateProvider.preferredLanguage(' ');
	$translateProvider.useSanitizeValueStrategy('escapeParameters');
}

app.controller('MainCtrl',function ($state, $scope, $translate) {
	$scope.user = {
			username: ''
	};
	
	$state.transitionTo('ui.login');
	
	$scope.selectCountry = selectCountry;
	
	function selectCountry(code){
		$translate.use(code);
	}
});