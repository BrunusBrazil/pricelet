var module = angular.module('forecastModule');
module.controller('ForecastController', [ '$scope', 'ForecastService', 'NgTableParams',
                                         'AppMessageService','forecasts','subAccounts', 'accounts',
		function ForecastController ($scope, ForecastService, NgTableParams, AppMessageService, forecasts,
									subAccounts, accounts) {
			var vm = this;
			vm.originalsubAccounts = angular.copy(subAccounts);
			vm.originalAccounts = angular.copy(accounts);
			vm.originalForecasts = angular.copy(forecasts);
			vm.adding = false;
			vm.message= {display: false};
			vm.period = new Date(); 
			
			vm.tableParams = new NgTableParams({}, {
			      filterDelay: 0,
			      counts: [5, 10],
			      dataset: angular.copy(forecasts)
			 });

			vm.edit = function(forecast){
				forecast.editing = true;
			}						
			
			vm.cancel = function(forecast){
		      var currentPage = vm.tableParams.page();
		      vm.tableParams.settings({
		        dataset: angular.copy(vm.originalForecasts)
		      });
  	          vm.tableParams.page(currentPage);
  	          forecast.editing = false;
  	          forecast.creating = false;
			}
			
			vm.saveEdition = function(forecast){
			  setId(forecast);
			  ForecastService.edit(forecast).then(function(response){
				  forecast.editing = false;
				  forecast.creating = false;
				  forecast = response; 
				  setId(forecast);
			  }, function(response){
				  vm.message = AppMessageService.displayDefaultMessage('CRUD3','ERROR', 'forecast')
			  });	
			}
			
			vm.debitOrCreditUpdate = function (forecast){
				if(forecast.adding){
					forecast.editing = false;	
				}else{
					forecast.editing = !forecast.editing;
				}
			}

			vm.setPeriod = function(date){
				vm.period = moment(date).toDate();
				ForecastService.getForecast(vm.period).then(function(response){
					  vm.originalForecasts = angular.copy(response);
					  var currentPage = vm.tableParams.page();
					  vm.tableParams.settings({
				        dataset: angular.copy(response)
				      });				  
					  vm.tableParams.page(currentPage);
				})
			}
			
			vm.createForecast = function(){
				ForecastService.create(vm.period).then(function(response){
					  vm.originalForecasts = angular.copy(response);
					  var currentPage = vm.tableParams.page();
					  vm.tableParams.settings({
				        dataset: angular.copy(response)
				      });				  
					  vm.tableParams.page(currentPage);
				},function(error){
					if(error && error.data){
						if(error.status === 400){
							vm.message = 
								AppMessageService
								.displayMessageOverrideContent(vm.message,'ERROR', error.data.description);
						}
					}
				});
			};
			
			vm.containForecast = function(){
				if(vm.originalForecasts){
					return vm.originalForecasts.length > 0;
				}else{
				 return false;	
				}
			}
						
			function setId(object){
				var link  = null;
				if(object){
					if(object.id){
						return;
					}
					if(object._links){
						if(object._links.self){
 						  link  = object._links.self.href; 
						}else{
 						  link  = object._links? object._links[0].href : object.links[0].href; 
						}
					}else{
						link  =  object.links[0].href; 
					}
				    object.id = link.substr(link.lastIndexOf('/')+1,link.length);
				}
			}			
			
	}
]);
	