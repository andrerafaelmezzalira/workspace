(function() {

	var SliderDirective = function() {
		return {
			restrict : 'C',
			link : function($scope, $element) {
				$element.slider();
			}
		};
	};

	angular.module('app.directives').directive('slider', SliderDirective);

})();
