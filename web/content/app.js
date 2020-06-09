var CDIOFinal = angular.module("CDIOFinal", ['ngRoute']);

CDIOFinal.config(['$routeProvider', function ($routeProvider) {
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
}]);