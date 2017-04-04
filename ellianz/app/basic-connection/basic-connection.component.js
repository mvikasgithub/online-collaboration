'use strict'

//Register 'basicConnection' component along with its associated controller and template
angular.
    module('basicConnection').
        component('basicConnection', {
            templateUrl: 'basic-connection/basic-connection.template.html',
            controller: [ function BasicConnectionController() {
                this.message = "Hello World";

            }]

        });