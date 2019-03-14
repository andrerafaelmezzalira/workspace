(function() {

	function UserController(WorkspaceService, DescriptionWorkspace,
			GeonameService) {

		var vm = this;
		vm.getCities = getCities;
		vm.getNeighborhoods = getNeighborhoods;
		
		init();

		function init() {

			vm.descriptionsWorkspace = DescriptionWorkspace;

			if (!vm.states) {
				GeonameService.getStates().then(function(response) {
					vm.states = response.data.geonames;
				});
			}

			delete vm.workspaces;
			WorkspaceService.get().then(function(response) {
				vm.workspaces = response.data;
			});
		}

		function getCities() {
			GeonameService.getCities(vm.workspace.state).then(
					function(response) {
						vm.cities = response.data.geonames;
					});
		}

		function getNeighborhoods() {
			GeonameService.getNeighborhoods(vm.workspace.city).then(
					function(response) {
						vm.neighborhoods = response.data.geonames;
					});
		}

	}

	UserController.$inject = [ 'WorkspaceService', 'DescriptionWorkspace',
			'GeonameService' ];

	angular.module('app.controllers').controller('UserController',
			UserController);

})();
