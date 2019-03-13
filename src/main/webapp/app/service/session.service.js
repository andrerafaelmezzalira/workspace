(function() {

	/**
	 * api session management
	 */
	function SessionService($window) {

		return {
			getToken : getToken,
			setToken : setToken,
			removeToken : removeToken
		};

		function getToken() {
			var token = $window.sessionStorage.getItem('access_token');
			return token;
		}

		function setToken(data) {
			$window.sessionStorage.setItem('access_token', data);
		}

		function removeToken() {
			$window.sessionStorage.removeItem('access_token');
		}

	}

	SessionService.$inject = [ '$window' ];

	angular.module('app.services', [])
			.service('SessionService', SessionService);

})();
