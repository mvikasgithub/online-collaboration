angular.
    module('ellianzFE').
    config(['$routeProvider',
        function config($routeProvider){

            $routeProvider.
            when('/message', {
                template: <basic-connection></basic-connection>
            }).
            otherwise('/message');

    }
    ]);
