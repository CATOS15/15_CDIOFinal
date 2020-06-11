angular.module('CDIOFinal').service('brugereService', ['$http', 'brugereModel', 'CDIOFinalModel', function ($http, brugereModel, CDIOFinalModel) {
    this.getBrugerer = function(){
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "bruger"
        }).then(function (resp) {
            brugereModel.brugerer = resp.data;
        }, function (errorResp) {
            brugereModel.error = errorResp.data;
        });
    };
    this.getBruger = function(brugerId){
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "bruger/" + brugerId
        }).then(function (resp) {
            brugereModel.bruger = resp.data;
        }, function (errorResp) {
            brugereModel.error = errorResp.data;
        });
    };
    this.createBruger = function(bruger){
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "bruger",
            data: bruger
        }).then(function (resp) {
            brugereModel.bruger = resp.data;
            //Todo add til liste
        }, function (errorResp) {
            brugereModel.error = errorResp.data;
        });
    };
    this.updateBruger = function(bruger){
        $http({
            method: "PUT",
            url: CDIOFinalModel.apiURL + "bruger",
            data: bruger
        }).then(function (resp) {
            brugereModel.bruger = resp.data;
            //Todo add til liste
        }, function (errorResp) {
            brugereModel.error = errorResp.data;
        });
    };
    this.deleteBruger = function(brugerId){
        $http({
            method: "DELETE",
            url: CDIOFinalModel.apiURL + "bruger/" + brugerId
        }).then(function (resp) {
            //Todo fjern fra liste
        }, function (errorResp) {
            brugereModel.error = errorResp.data;
        });
    };
}]);