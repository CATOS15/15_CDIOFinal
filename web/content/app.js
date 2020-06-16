var CDIOFinal = angular.module("CDIOFinal", ['ngRoute']);

CDIOFinal.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        .when("/login", {
            templateUrl: "content/pages/login/page.html",
            controller: "loginController"
        })
        .when("/", {
            templateUrl: "content/pages/hjem/page.html",
            controller: "hjemController"
        })
        .when("/afvejning", {
            templateUrl: "content/pages/afvejning/page.html",
            controller: "afvejningController"
        })
        .when("/produktionsbatch", {
            templateUrl: "content/pages/produktionsbatch/page.html",
            controller: "produktionsbatchController"
        })
        .when("/raavare", {
            templateUrl: "content/pages/raavare/page.html",
            controller: "raavareController"
        })
        .when("/raavarebatches", {
            templateUrl: "content/pages/raavarebatches/page.html",
            controller: "raavarebatchesController"
        })
        .when("/recepter", {
            templateUrl: "content/pages/recepter/page.html",
            controller: "receptController"
        })
        .when("/brugere", {
            templateUrl: "content/pages/brugere/page.html",
            controller: "brugereController"
        })
        .when("/produktvisning", {
            templateUrl: "content/pages/produktvisning/page.html",
            controller: "produktvisningController"
        })
        //Hvis siden ikke eksistester
        .otherwise({
            templateUrl: "content/pages/404/404.html"
        });

        $httpProvider.interceptors.push(['$rootScope', '$q', 'CDIOFinalModel', '$timeout', function($rootScope, $q, CDIOFinalModel, $timeout) {
            return {
                'request': function(config) {
                    $timeout(function(){
                        CDIOFinalModel.loadingCounter++;
                    }, 200);
                    return config;
                },
                'requestError': function(rejection) {
                    $timeout(function(){
                        CDIOFinalModel.loadingCounter++;
                    }, 200);
                    return $q.reject(rejection);
                },
                'response': function(response) {
                    CDIOFinalModel.loadingCounter--;
                    return response;
                },
                'responseError': function(response) {
                    CDIOFinalModel.loadingCounter--;
                    var status = response.status;
                    if (status === 401) {
                        $rootScope.$emit('loginRequired');
                    }
                    return $q.reject(response);
                }
            };
        }]);
}]);