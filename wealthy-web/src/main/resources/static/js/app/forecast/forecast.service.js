var module = angular.module('forecastModule');

     module.service('ForecastService', ['Restangular',function(Restangular){
		
        var base = Restangular.all('forecasts/');

        function getForecast(date){
    		return  base.customGET("", {period: date}); 
    	}
    	
    	function edit(forecast){
    		return Restangular.one('forecasts/', forecast.id).customPUT(forecast);
    	}
    	
    	function create(date){
    		return Restangular.all('forecasts/period').post({period: date});
    	}
    	
		return  {
			getForecast:getForecast,
			edit: edit,
			create: create
		}
		
	}]);


