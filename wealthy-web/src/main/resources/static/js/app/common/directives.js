/*(function($){
	'use strict'
var module = angular.module('commonDirective', []);
module.directive('rangeDatePicker', function($window){
	return {
			scope:{
				transactions: '=' 
			},
			template:"<input type='text' name='transactionDate' />",
			controller: function($scope){
				console.log($scope);
			},
			link: function(scope, element, attrs){
	
				$('input[name="transactionDate"]')
				.daterangepicker(
								{
								    locale: {
								      format: 'YYYY-MM-DD'
								    },
								    startDate: '2013-01-01',
								    endDate: '2013-12-31'
								}, 
								function(start, end, label) {
								    alert("A new date range was chosen: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
								});
			
				scope.$watch('transactions', function(transactions) {
				})
			}
		}	
  });
}(window.jQuery));
*/