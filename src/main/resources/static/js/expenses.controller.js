(function() {
	'use strict';
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
				vm.editingElement=null;
				vm.editElement=editElement;
				vm.deleteElement=deleteElement;
				vm.list=[];

				ExpensesService.getExpenses().then(function(list){
					console.log(list);
						vm.list=list;
			});
				
				function closeAlert(){
					vm.showAlert=false;
					vm.msg='';
				}
				
				function openAlert(msg,type,time){
					
					vm.alertType=angular.isUndefined(type)?"alert-success":type;
					vm.alertTime=angular.isUndefined(time)?3000:1000;
					vm.msg=msg;
					vm.showAlert=true;
				}
				
				function editElement(item){
					console.log("element selected for edition:",item);
					vm.editingElement=item;
					vm.openEditModal();
					
				}
				
				function deleteElement(id){
					ExpensesService.deleteExpense(id).then(function(){
						vm.list=vm.list.filter(function(element){
							return element.id!==id;
						});
						vm.openAlert("Expense deleted successfully.");
					})	
				}
				
				function openAddModal() {
					console.log("calling openAddModal");
					
					var modalInstance = $uibModal.open({
						controller: 'ModalController',
					    controllerAs: '$ctrl',
					    templateUrl: 'myModalContent.html',
					    resolve: {
					        item: function () {
					          return null;
					        }}
					});
					
					modalInstance.result.then(function(result){
						console.log(result);
						ExpensesService.createExpense(result).then(function (createdObject){
							console.log("object created", createdObject);
							vm.list.push(createdObject);
							vm.openAlert("Expense created successfully.")
						},handleException);
					});
				}
				
				function openEditModal() {
					console.log("calling openAddModal");
					
					var modalInstance = $uibModal.open({
						controller: 'ModalController',
					    controllerAs: '$ctrl',
					    templateUrl: 'myModalContent.html',
					    resolve: {
					        item: function () {
					          return vm.editingElement;
					        }}
					});
					
					modalInstance.result.then(function(result){
						console.log(result);
						ExpensesService.updateExpense(vm.editingElement.id,result).then(function (updatedObject){
							console.log("object updated", updatedObject);
							angular.extend(vm.editingElement,updatedObject);
							vm.openAlert("Expense updated successfully.")
						},handleException);
					});
				}
				
				function handleException(ex){
					console.log("ex:",ex);
					vm.openAlert(ex.data.message, "alert-danger",0);
					console.log(ex.data.message);
				}

			});
})();
