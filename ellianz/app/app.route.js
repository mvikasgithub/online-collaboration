window.routes = {

    "/home":{
        templateUrl: 'app/components/basic/home.html',
        controller: 'BasicController',
        controllerAs: 'basicCtrl',
        requireLogin: false,
        roles: ['GUEST', 'ADMIN', 'STAFF', 'STUDENT']            


    },
    "/about":{
        templateUrl: 'app/components/basic/about.html',
        controller: 'BasicController',
        controllerAs: 'basicCtrl',
        requireLogin: false,
        roles: ['GUEST', 'ADMIN', 'STAFF', 'STUDENT']            
    
    },
    "/login":{
        templateUrl: 'app/components/authentication/login.html',
        controller: 'AuthenticationController',
        controllerAs: 'authCtrl',
        requireLogin: false,
        roles: ['GUEST', 'ADMIN', 'STAFF', 'STUDENT']            
    
    }

};

//specify the backend url fromwhere you are going to get the value
app.constant('REST_URI', 'http://localhost:8080/onlinecollaboration/');

app.config(['$routeProvider', function($routeProvider){

    for(var path in window.routes)
    {
        $routeProvider.when(path, window.routes[path]);           
    }

    $routeProvider.otherwise({redirectTo: '/login'});

}]);