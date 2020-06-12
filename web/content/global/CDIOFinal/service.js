angular.module('CDIOFinal').service('CDIOFinalService', ['$http', 'CDIOFinalModel', '$rootScope', function ($http, CDIOFinalModel, $rootScope) {
    this.getLogin = function(){ //For at tjekke om man er logget ind og hent bruger data
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "authentication/getLogin"
        }).then(function (resp) {
            CDIOFinalModel.user = resp.data;
        }, function (errorResp) {
            localStorage.removeItem('token');
            CDIOFinalModel.userToken = null;
            $rootScope.$emit('loginRequired');
        });
    };

    this.setToken = function(token){
        if(token) {
            CDIOFinalModel.userToken = token;
            $http.defaults.headers.common.Authorization = 'Bearer ' + CDIOFinalModel.userToken;
        }
    }
}]);