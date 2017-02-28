(function() {
	'use strict';
/**
 * Main Controller of the Expenses Application
 */
	angular.module('expensesApp').controller('ExpensesController',
			function(ExpensesService, $uibModal) {

				var vm = this;
				vm.openAddModal = openAddModal;
				vm.openEditModal = openEditModal;
				vm.closeAlert = closeAlert;
				vm.openAlert= openAlert;
				vm.showAlert = false;
				vm.alertTime=0;
				vm.alertType=null;
				vm.msg="";
				vm.addRandomExpense=addRandomExpense;
				vm.editingElement=null;
				vm.editElement=editElement;
				vm.deleteElement=deleteElement;
				vm.list=[];
				vm.i=1;
				vm.today=new Date();

				//Call the server for expenses data
				ExpensesService.getExpenses().then(function(list){
						vm.list=list;
			});
				
				function closeAlert(){
					vm.showAlert=false;
					vm.msg='';
				}
				
				/**
				 * Open a message alert 
				 */
				function openAlert(msg,type,time){
					vm.alertType=angular.isUndefined(type)?"alert-success":type;
					vm.alertTime=angular.isUndefined(time)?3000:1000;
					vm.msg=msg;
					vm.showAlert=true;
				}
				
				
				function editElement(item){
					vm.editingElement=item;
					vm.openEditModal();
				}
				
				/**
				 * Add a random expense for debugging pourpose 
				 */
				function addRandomExpense(){
					ExpensesService.createExpense({description:"Expense "+vm.i, value:vm.i++, date:vm.today}).then(function (createdObject){
						vm.list.push(createdObject);
						vm.openAlert("Expense created successfully.")
					},handleException);
					
				}
				
				/**
				 * Call the server for deleting an expense. On success, remove it from the list 
				 */
				function deleteElement(id){
					ExpensesService.deleteExpense(id).then(function(){
						vm.list=vm.list.filter(function(element){
							return element.id!==id;
						});
						vm.openAlert("Expense deleted successfully.");
					},handleException)	
				}
				
				function openAddModal() {
					var modalInstance = $uibModal.open({
						controller: 'ModalController',
					    controllerAs: '$ctrl',
					    templateUrl: 'myModalContent.html',
					    resolve: {
					        item: function () {
					          return null;
					        }}
					});
					
					//Call the server for creation, on success, add it to the list
					modalInstance.result.then(function(result){
						ExpensesService.createExpense(result).then(function (createdObject){
							vm.list.push(createdObject);
							vm.openAlert("Expense created successfully.")
						},handleException);
					});
				}
				
				function openEditModal() {
					
					
					var modalInstance = $uibModal.open({
						controller: 'ModalController',
					    controllerAs: '$ctrl',
					    templateUrl: 'myModalContent.html',
					    resolve: {
					        item: function () {
					          return vm.editingElement;
					        }}
					});
					
					//Call the server for updating, on success, add it to the list
					modalInstance.result.then(function(result){
						ExpensesService.updateExpense(vm.editingElement.id,result).then(function (updatedObject){
							angular.extend(vm.editingElement,updatedObject);
							vm.openAlert("Expense updated successfully.")
						},handleException);
					});
				}
				
				function handleException(ex){
					console.log("ex:",ex);
					vm.openAlert(ex.data.message, "alert-danger",0);
				}

			});
})();
