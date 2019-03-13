(function() {

	function WorkspaceService($http) {

		var url = 'api/workspace';

		return ({
			save : save,
			remove : remove,
			getByPerson : getByPerson,
			get : get
		});

		function getByPerson(idPerson) {
			return $http.get(url + '/person?idPerson=' + idPerson);
		}

		function get() {
			return $http.get(url);
		}

		function save(workspace) {
			return $http[workspace.id ? 'put' : 'post'](url, workspace);
		}

		function remove(id) {
			return $http['delete'](url, {
				params : {
					id : id
				}
			});
		}
	}

	WorkspaceService.$inject = [ '$http' ];

	angular.module('app.services')
			.service('WorkspaceService', WorkspaceService);
})();
