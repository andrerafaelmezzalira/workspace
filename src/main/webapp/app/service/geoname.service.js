(function() {

	function GeonameService($http) {

		var url = 'http://www.geonames.org/childrenJSON?geonameId=';

		return ({
			getStates : getStates,
			getCities : getCities,
			getNeighborhoods : getNeighborhoods
		});

		function getStates() {
			//3469034 is brazil code 
			return $http.get(url + '3469034');
		}

		function getCities(state) {
			return $http.get(url + state);
		}

		function getNeighborhoods(city) {
			return $http.get(url + city);
		}

	}

	GeonameService.$inject = [ '$http' ];

	angular.module('app.services').service('GeonameService', GeonameService);
})();
