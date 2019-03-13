(function() {

	function FooterController(Version) {

		var vm = this;

		init();

		function init() {
			// set version api
			vm.version = Version;
		}
	}

	FooterController.$inject = [ 'Version' ];

	angular.module('app.controllers').controller('FooterController',
			FooterController);

})();
