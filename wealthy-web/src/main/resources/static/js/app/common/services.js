var module = angular.module('common.service',['restangular']);

module.service("CommonService", ['Restangular',function(Restangular){
    var base = Restangular.all('Chart/');

    function getTransactionsChart(){
    	var dateTo = moment().toISOString(); 
    	var dateFrom  = moment().subtract(1, 'year').toISOString();
		return  base.customGET("transactions", {'dateFrom': dateFrom, 'dateTo': dateTo}); 
    }
    
    function buildDefaultLineChart(expenses, incomes, labels){
    	return {
			 labels: labels,
		         datasets: [
		          {
		        "label":"Expense",
		        "data":expenses,
				"fill":true,"backgroundColor":"rgba(255, 99, 132, 0.2)",
				"borderColor":"rgb(255, 99, 132)",
				"pointBackgroundColor":"rgb(255, 99, 132)",
				"pointBorderColor":"#fff",
				"pointHoverBackgroundColor":"#fff",
				"pointHoverBorderColor":"rgb(255, 99, 132)"},
		        {
		        "label":"Income",
		        "data":incomes,
				"fill":true,
				"backgroundColor":"rgba(54, 162, 235, 0.2)",
				"borderColor":"rgb(54, 162, 235)",
				"pointBackgroundColor":"rgb(54, 162, 235)",
				"pointBorderColor":"#fff",
				"pointHoverBackgroundColor":"#fff",
				"pointHoverBorderColor":"rgb(54, 162, 235)"}
		      ]
			};
    }
    
    return {
    	buildDefaultLineChart: buildDefaultLineChart,
    	getTransactionsChart:getTransactionsChart
    }

}]);