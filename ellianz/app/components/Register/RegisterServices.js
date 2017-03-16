var RegisterModule = angular.module('RegisterModule', [


]);

BasicModule.service('RegisterService', ['$http', '$q', 'REST_URI', function ($http, $q, REST_URI) {

    var REST_SERVICE_URI = 'http://localhost:8080/user/all';

    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
            );
        return deferred.promise;
    }
}]);