(function() {

	function AdminController($scope, WorkspaceService, SessionService,
			GeonameService, DayOfTheWeek, DescriptionWorkspace) {

		var vm = this;

		// methods
		vm.save = save;
		vm.edit = edit;
		vm.remove = remove;
		vm.getCities = getCities;
		vm.getNeighborhoods = getNeighborhoods;
		vm.getDescriptionState = getDescriptionState;

		init();

		function init() {

			$scope.$on('login', function(event, data) {
				vm.person = data.person;
				save();
			});

			vm.person = JSON.parse(SessionService.getToken());

			vm.dayOfTheWeeks = DayOfTheWeek;
			vm.descriptionsWorkspace = DescriptionWorkspace;

			if (!vm.states) {
				GeonameService.getStates().then(function(response) {
					vm.states = response.data.geonames;
				});
			}

			if (!vm.person) {
				return;
			}
			delete vm.workspaces;
			WorkspaceService.getByPerson(vm.person.id).then(function(response) {
				vm.workspaces = response.data;
				delete vm.workspace;
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

		function getDescriptionState(state) {
			if (!state) {
				return;
			}
			for (var i = 0; i < vm.states.length; i++) {
				if (vm.states[i].geonameId == state) {
					return vm.states[i].adminName1;
				}
			}
		}

		function save() {

			if (!vm.workspace) {
				return;
			}

			setAvaliablePeriods();
			setAvaliableHours();

			vm.workspace.person = vm.person;
			WorkspaceService.save(vm.workspace).then(function(response) {
				vm.workspaces = response.data;
				delete vm.workspace;
				M.toast({
					html : 'Espaço de trabalho salvo com sucesso!'
				})
			});
		}

		function setAvaliablePeriods() {
			vm.workspace.avaliablePeriods = '';
			for ( var i in vm.workspace.dayOfTheWeeks) {
				if (vm.workspace.dayOfTheWeeks[i]) {
					if (vm.workspace.avaliablePeriods !== '') {
						vm.workspace.avaliablePeriods += ',';
					}
					vm.workspace.avaliablePeriods += i;
				}
			}
			if (vm.workspace.avaliablePeriods === '') {
				delete vm.workspace.avaliablePeriods;
			}
				
			delete vm.workspace.dayOfTheWeeks;
		}

		function setAvaliableHours() {
			if (vm.workspace.startingTime) {
				vm.workspace.avaliableHours = vm.workspace.startingTime;
			}

			if (vm.workspace.finalHour) {
				if (vm.workspace.avaliableHours) {
					vm.workspace.avaliableHours += ',' + vm.workspace.finalHour;
				} else {
					vm.workspace.avaliableHours = vm.workspace.finalHour;
				}
			}
			delete vm.workspace.startingTime;
			delete vm.workspace.finalHour;
		}

		function edit(workspace) {
			// copy person object to grid
			vm.workspace = angular.copy(workspace);

			var avaliableHours = vm.workspace.avaliableHours.split(',');
			vm.workspace.startingTime = avaliableHours[0];
			vm.workspace.finalHour = avaliableHours[1];
			getCities();
			getNeighborhoods();
			var avaliablePeriods = vm.workspace.avaliablePeriods.split(',');
			vm.workspace.dayOfTheWeeks = {};
			for (var i = 0; i < avaliablePeriods.length; i++) {
				vm.workspace.dayOfTheWeeks[avaliablePeriods[i]] = true;
			}
		}

		function remove(id) {
			WorkspaceService.remove(id).then(function(response) {
				vm.workspaces = response.data;
				M.toast({
					html : 'Espaço de trabalho excluído com sucesso!'
				})
			});
		}
	}

	AdminController.$inject = [ '$scope', 'WorkspaceService', 'SessionService',
			'GeonameService', 'DayOfTheWeek', 'DescriptionWorkspace' ];

	angular.module('app.controllers').controller('AdminController',
			AdminController);

})();
