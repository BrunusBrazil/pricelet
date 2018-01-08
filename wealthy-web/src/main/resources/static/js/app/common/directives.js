(function($){
	'use strict'
var module = angular.module('commonDirective', ['xTransactionModule']);
module.directive('rangeDatePicker', function(xTransactionService){
	return {
			templateUrl: '/js/app/common/templates/dateRange.html',
			controller: function($scope, xTransactionService){
				setDefaultRangeDate($scope);
				$('button[name="transactionDate"]').daterangepicker({
									locale: {
							            applyClass: 'btn-green',
							            applyLabel: "Apply",
							            fromLabel: "From",
							            format: "YYYY-MM-DD",
							            toLabel: "To",
							            cancelLabel: 'Cancel',
							            customRangeLabel: 'Custom range'
							        },
							        ranges: {
							            'Last 7 Days': [moment().subtract(6, 'days'), moment()],
							            'Last 30 Days': [moment().subtract(29, 'days'), moment()]
							        }
								  
								},function(start, end, label) {
									$scope.$apply(function(){
										xTransactionService.setDateRangeFilter(start, end);
										$scope.dateRangeString =  start.format('LL')+' - '+ end.format('LL');
									});
								
								});
			moment().subtract(30, 'day'), moment()
			}
		}
		
		function setDefaultRangeDate($scope){
			xTransactionService.setDateRangeFilter(moment().subtract(30, 'day'), moment());
			$scope.dateRangeString =  moment().subtract(30, 'day').format('LL')+' - '+ moment().format('LL');
		}	
  });
}(window.jQuery));
