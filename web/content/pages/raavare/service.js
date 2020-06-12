angular.module('CDIOFinal').service('raavareService', ['$http', 'raavareModel', 'CDIOFinalModel', function ($http, raavareModel, CDIOFinalModel) {
    this.getRaavarer = function(){
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "raavare"
        }).then(function (resp) {
            raavareModel.raavarer = resp.data;
        }, function (errorResp) {
            raavareModel.error = errorResp.data;
        });
    };
    this.getRaavare = function(raavareId){
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "raavare/" + raavareId
        }).then(function (resp) {
            raavareModel.raavare = resp.data;
        }, function (errorResp) {
            raavareModel.error = errorResp.data;
        });
    };
    this.createRaavare = function(raavare){
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "raavare",
            data: raavare
        }).then(function (resp) {
            raavareModel.raavarer.push(raavare);
        }, function (errorResp) {
            raavareModel.error = errorResp.data;
        });
    };
    this.updateRaavare = function(raavare){
        $http({
            method: "PUT",
            url: CDIOFinalModel.apiURL + "raavare",
            data: raavare
        }).then(function (resp) {
        }, function (errorResp) {
            raavareModel.error = errorResp.data;
        });
    };
    this.deleteRaavare = function(raavareId){
        $http({
            method: "DELETE",
            url: CDIOFinalModel.apiURL + "raavare/" + raavareId
        }).then(function (resp) {
            //Todo fjern fra liste
        }, function (errorResp) {
            raavareModel.error = errorResp.data;
        });
    };
}]);