(function(){
	'use strict';
	angular.module('expensesApp').service('ExpensesService',function($http){
		
		return {
			getExpenses: getExpenses,
			createExpense: createExpense
		};
		
		function getExpenses(){
			
			return $http.get("/expense").then(function(data){
				console.log(data);
				return data.data});
			
		}
		
		function createExpense(){
			$http.post("/expense",{value:6.6, date: "2015-10-11", description:"tren"});
		}
		
	});
})();