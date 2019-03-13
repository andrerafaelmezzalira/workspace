(function() {

	function PersonService($http) {

		var url = 'api/person';

		return ({
			login : login
		});

		function login(person) {
			return $http.post(url + '/login', person);
		}
	}

	PersonService.$inject = [ '$http' ];

	angular.module('app.services').service('PersonService', PersonService);
})();
