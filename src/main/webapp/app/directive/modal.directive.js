(function() {

	var ModalDirective = function() {
		return {
			restrict : 'C',
			link : function($scope, $element) {
				// enable modal materializecss
				$element.modal();
			}
		};
	};

	angular.module('app.directives').directive('modal', ModalDirective);

})();
