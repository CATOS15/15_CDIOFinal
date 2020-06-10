angular.module('CDIOFinal').service('loginService', ['$http', 'loginModel', 'CDIOFinalModel', function ($http, loginModel, CDIOFinalModel) {
    this.login = function(user){
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "authentication/login",
            data: user
        }).then(function (resp) {
            debugger;
        }, function (errorResp) {
            loginModel.error = errorResp.data;
        });
    };
    this.createUser = function(user){
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "authentication/createuser",
            data: user
        }).then(function (resp) {
            debugger;
        }, function (errorResp) {
            loginModel.error = errorResp.data;
        });
    };
}]);