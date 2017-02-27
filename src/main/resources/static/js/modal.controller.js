(function() {
	'use strict';
	/**
	 * Controller for the expense modal
	 */
	angular.module('expensesApp').controller('ModalController',
			function($uibModalInstance, item) {

				var $ctrl = this;
				if (item != null) {
					$ctrl.expense = {
						description : item.description,
						date : new Date(item.date),
						value : item.value
					};
				} else {
					$ctrl.expense = {
						description : "",
						date : new Date(),
						value : 0.0
					};
				}

				$ctrl.format = 'yyyy-MM-dd';

				$ctrl.openDp = function() {
					$ctrl.popup.opened = true;
				}

				$ctrl.popup = {
					opened : false
				};

				$ctrl.dateOptions = {
					formatYear : 'yy',
					startingDay : 1
				};

				$ctrl.ok = function() {
					$uibModalInstance.close($ctrl.expense);
				};

				$ctrl.cancel = function() {
					$uibModalInstance.dismiss('cancel');
				};

			});
})();
