(function() {

	var DropdownDirective = function() {
		return {
			restrict : 'C',
			link : function($scope, $element) {
				$element.dropdown();
			}
		};
	};

	angular.module('app.directives').directive('dropdownTrigger', DropdownDirective);

})();
