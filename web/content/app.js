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
            controller: "recepterController"
        })
        .when("/brugere", {
            templateUrl: "content/pages/brugere/page.html",
            controller: "brugereController"
        })
        //Hvis siden ikke eksistester
        .otherwise({
            templateUrl: "content/pages/404/404.html"
        });

        $httpProvider.interceptors.push(['$rootScope', '$q', function($rootScope, $q) {
            return {
                'responseError': function(response) {
                    var status = response.status;
                    if (status === 401) {
                        $rootScope.$emit('loginRequired');
                    }
                    return $q.reject(response);
                }
            };
        }]);
}]);

CDIOFinal.run(['$http', 'CDIOFinalModel', function($http, CDIOFinalModel) {
    var token = localStorage.getItem('token');
    if(token) {
        CDIOFinalModel.userToken = token;
        $http.defaults.headers.common.Authorization = 'Bearer ' + CDIOFinalModel.userToken;
    }
}]);