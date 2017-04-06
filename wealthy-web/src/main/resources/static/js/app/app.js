var app = angular.module('myWealthy',['accountModule','ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/account',{
            templateUrl: '/views/account.html',
            controller: 'AccountController'
        })
        .otherwise(
            { templateUrl: '/'}
        );

  });