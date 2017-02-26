(function() {
	'use strict';
	angular.module('expensesApp').controller('ExpensesController',
			function(ExpensesService, NgTableParams, $http) {
				console.log("ExpensesController");

				var vm = this;
				vm.text = "Hola mundo";


				vm.tableParams = new NgTableParams({}, {
					getData : function(params){
						console.log("calling getData");
						return  ExpensesService.getExpenses();
					}				});
				
					vm.tableParams.reload();
				
				//console.log(vm.tableParams);
				vm.list = 1;

				ExpensesService.createExpense();
			});
})();
