var module = angular.module('xTransactionModule');

     module.service('xTransactionService', ['Restangular','$q',function(Restangular, $q){
		var base = Restangular.all('XTransaction/');
		var dateRangeFilter = {startDate: null, endDate: null};
		var cachedTransactions = [];		
		var deferred = $q.defer();
    	
		function create(account){
			return  base.post(account);
		}
    	
    	function remove(acc){
    		return Restangular.one("XTransaction/", acc.id).remove();
    	}
		
    	
    	function getAll(fromCache){
    		return $q(function(resolve, reject) {
    			if(fromCache && cachedTransactions && cachedTransactions.length > 0 ){
    				resolve(cachedTransactions);
    			}else{
    				base.getList().then(function(response){
    					convertStringToDate(response)
    					cachedTransactions = response;
    					resolve(cachedTransactions);
    				},function(error){
    					reject(error)	
    				});		
    			}
    		});  		
    	}
    	
    	function edit(account){
    		return Restangular.one('XTransaction/', account.id).customPUT(account)
    	}
    	
    	function setDateRangeFilter(start, end){
    		dateRangeFilter.startDate = start;
    		dateRangeFilter.endDate = end;
    	}
    	
    	function getDateRangeFilter(){
    		return dateRangeFilter;
    	}    
    	
    	function getDefaultDateRange(){
    		setDateRangeFilter(moment().subtract(30, 'day'), moment());
    		return getDateRangeFilter();
    	}

    	function convertStringToDate(transactions){
			_.forEach(transactions, function(e) {
				e.dateTransaction = new Date(e.dateTransaction);
			});
		}
    	
    	return  {
				create:create,
				getAll:getAll,
				remove:remove,
				edit:edit,
				setDateRangeFilter:setDateRangeFilter,
				getDateRangeFilter:getDateRangeFilter,
				getDefaultDateRange: getDefaultDateRange,
				convertStringToDate: convertStringToDate
		}
			
	}]);


