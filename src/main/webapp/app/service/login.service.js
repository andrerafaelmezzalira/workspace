(function() {

	function LoginService($http) {

		var url = 'api/login';

		return ({
			login : login
		});

		function login(person) {
			return $http.post(url, person);
		}
	}

	LoginService.$inject = [ '$http' ];

	angular.module('app.services').service('LoginService', LoginService);
})();
