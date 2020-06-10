angular.module('CDIOFinal').service('loginService', ['$http', 'loginModel', 'CDIOFinalModel', function ($http, loginModel, CDIOFinalModel) {
    this.login = function(user){
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "authentication/login",
            data: user
        }).then(function (resp) {
            CDIOFinalModel.userToken = resp.data.substr(1, resp.data.length-2);
            $http.defaults.headers.common.Authorization = 'Bearer ' + CDIOFinalModel.userToken;
            loginModel.msg = "Successfuldt logget ind!";
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
            loginModel.msg = "Bruger oprettet " + resp.data.userName;
        }, function (errorResp) {
            loginModel.error = errorResp.data;
        });
    };
}]);