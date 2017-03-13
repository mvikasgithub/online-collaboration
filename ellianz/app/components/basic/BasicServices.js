var BasicModule = angular.module('BasicModule', [


]);

BasicModule.service('BasicService', ['$http', '$q', 'REST_URI', function($http, $q, REST_URI)
{
    this.getGreeting = function()
    {
        // get the deferred object
        var deferred = $q.defer();

        $http.get(REST_URI + '/greeting')
            .then(

                //success callback function
                function(response)
                {
                    deferred.resolve(response.data.responseMessage);
                },

                //error callback
                function(error)
                {
                    deferred.reject(error);
                }
            );
            
            return deferred.promise;
    }


} ]);