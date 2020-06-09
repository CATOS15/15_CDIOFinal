angular.module('CDIOFinal').service('brugereService', ['$http', 'brugereModel', 'CDIOFinalModel', function ($http, brugereModel, CDIOFinalModel) {
    this.getBrugere = function(){
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "brugere"
        }).then(function (resp) {
            brugereModel.bruger = resp.data;
        }, function (errorResp) {
            console.error(errorResp);
        });
    };
}]);