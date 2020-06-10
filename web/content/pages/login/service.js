angular.module('CDIOFinal').service('loginService', ['$http', 'loginModel', 'CDIOFinalModel', function ($http, loginModel, CDIOFinalModel) {
    this.login = function(){
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "login",
            data: {username: 'kage', password: 'lokumspapir'}
        }).then(function (resp) {
            debugger;
        }, function (errorResp) {
            console.error(errorResp);
        });
    };
}]);