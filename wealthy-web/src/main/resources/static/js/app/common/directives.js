(function($){
	'use strict'
var module = angular.module('commonDirective', ['xTransactionModule','bouncerModule']);

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

module.directive('showErrors', function(){
	return {
		restrict: 'A',
		require: '^form',
		link: function(scope, el, attrs, formCtrl){
			
			// find the text box element, which has the 'name' attribute
	        var inputEl   = el[0].querySelector("[name]");
	        
	        // convert the native text box element to an angular element
	        var inputNgEl = angular.element(inputEl);

	        // get the name on the text box so we know the property to check
	        // on the form controller
	        var inputName = inputNgEl.attr('name');

	        // only apply the has-error class after the user leaves the text box
	        inputNgEl.bind('blur', function() {
	          el.toggleClass('has-error', formCtrl[inputName].$invalid);
	        })
	        
	        scope.$on('show-errors-check-validity', function() {
	          el.toggleClass('has-error', formCtrl[inputName].$invalid);
	        });
		}
	}	
});

module.directive('toggleTheToogle', function(){
	return {
		restrict: 'A',
		  link: function(scope, element, attrs) {
	            element.bind('click', function() {
	                if(element.attr("class") == "dropdown open") {
	                    element.removeClass("dropdown open");
	                    element.addClass(attrs.toggleClass);
	                } else {
	                    element.removeClass("dropdown");
	                    element.addClass("dropdown open");
	                }
	            });
		  }
	}
});

module.directive('displayUser', function(AuthService, $state){
	return {
	    restrict: 'AE',
	    replace: 'true',
	    scope: {
	    	_user: '=user'
	    },
//	    template: '<span>'+scope._user.username+'</span>',
	    link: function(scope, $location){
	    	if(AuthService && 
	    			AuthService.user && 
	    			AuthService.user.username)	{
	        	scope._user.username = AuthService.user.username;
	    	}else{
	    			$state.go('ui.login');
	    	}
	    }
	  };
});



module.directive('buttonNavigateToLogin', function($state){
	return {
		templateUrl: '/js/app/common/templates/buttonToLogin.html',
	    restrict: 'E',
	    replace: 'true',
      	link: function($scope, element, attrs) {
	            $scope.goToLogin= function() {
	    			$state.go('ui.login');
	          }
		}
	}
});

module.directive('showMessage', function($state, $timeout){
	return {
		templateUrl: '/js/app/common/templates/message.html',
	    restrict: 'EA',
	    scope: {
	    	message: '='
	    },
	    link: function(scope) {
	    	  scope.$watch('message', function(newValue, oldValue) {
	    		    if (newValue !== oldValue) {
	                	$timeout(hideMessage, 10000);
	                }
	               
	                function hideMessage(newValue){
	                	scope.message.display = false;
	                	scope.message.type = undefined;
	  	    	    }
	           }, true);
    	   	  
	    }
	}
});

module.directive('switcher', function(){
	return {
	    restrict: 'A',
	    link:  function($scope, element, attrs) {
	          // Trigger when number of children changes,
            // including by directives like ng-repeat
			var elems = $('.switchery');
			for (var i = 0; i < elems.length; i++) {
				var switchery = new Switchery(elems[i]);
			}
			if($(".switch").length !== 0){
				$(".switch").bootstrapSwitch();
			}

//	    	var watch = $scope.$watch(function() {
//            	return element.children().length;
//            }, function() {
//                // Wait for templates to render
//                $scope.$evalAsync(function() {
//                    // Finally, directives are evaluated
//                    // and templates are renderer here
//               	
//                	var children = element.children();
//                    console.log(children);
//                });
//            });
	    }
	}
})

}(window.jQuery));
