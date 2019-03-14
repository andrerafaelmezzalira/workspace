(function() {

	function LoginController($scope, LoginService, SessionService) {

		var vm = this;

		// methods
		vm.login = login;
		vm.logout = logout;

		init();

		function init() {
			// get session and person object
			vm.person = JSON.parse(SessionService.getToken());
		}

		function login() {
			if (!vm.person.name) {
				return;
			}
			LoginService.login(vm.person).then(function(response) {
				vm.person = response.data;
				// set token session
				SessionService.setToken(JSON.stringify(vm.person));
				M.toast({
					html : vm.person.name + ' logado com sucesso!'
				})

				$scope.$broadcast('login', {
					person : vm.person
				});
			});
		}

		function logout() {
			// destroyng session and person object
			SessionService.removeToken();
			delete vm.person;
		}
	}

	LoginController.$inject = [ '$scope', 'LoginService', 'SessionService' ];

	angular.module('app.controllers').controller('LoginController',
			LoginController);

})();
