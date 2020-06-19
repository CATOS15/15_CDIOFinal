angular.module('CDIOFinal').service('loginService',
    ['$http', 'loginModel', 'CDIOFinalModel', '$location',
        function ($http, loginModel, CDIOFinalModel, $location) {
    this.login = function(user){
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "authentication/login",
            data: user
        }).then(function (resp) {
            CDIOFinalModel.userToken = resp.data.substr(1, resp.data.length-2);
            $location.path("/");
        }, function (errorResp) {
            loginModel.error = errorResp.data;
        });
    };
}]);