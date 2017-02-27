(function(){
	'use strict';
	angular.module('expensesApp').service('ExpensesService',function($http){
		
		return {
			getExpenses: getExpenses,
			createExpense: createExpense,
			updateExpense:updateExpense,
			deleteExpense:deleteExpense
		};
		
		function getExpenses(){
			
			return $http.get("/expense").then(function(data){
				return data.data});
			
		}
		
		function createExpense(expense){
			return $http.post("/expense",
					{value:expense.value, 
					 date: expense.date, 
					 description:expense.description})
					 .then(function(response){
						 return response.data;
					 });
					}
		
		function updateExpense(id,expense){
			return $http.put("/expense/"+id,
					{value:expense.value, 
					 date: expense.date, 
					 description:expense.description})
					 .then(function(response){
						 return response.data;
					 });
					}
		function deleteExpense(id){
			return $http.delete("/expense/"+id)
					 .then(function(response){
						 return response.data;
					 });
					}		
	});
})();