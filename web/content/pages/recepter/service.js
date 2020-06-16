angular.module('CDIOFinal').service('receptService', ['$http', 'receptModel', 'CDIOFinalModel','raavareService',function ($http, receptModel,CDIOFinalModel, raavareService) {

    this.getRecepter = function(){
        receptModel.error = "";
        receptModel.msg = "";
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "recept"
        }).then(function (resp) {
            receptModel.recepter = resp.data;
        }, function (errorResp) {
            receptModel.error = errorResp.data;
        });
    };

    this.createRecept = function(recept){
        receptModel.error = "";
        receptModel.msg = "";
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "recept",
            data: recept
        }).then(function () {
            receptModel.msg = "Recept " + recept.receptId + " oprettet";
            receptModel.recepter.push(recept);
            receptModel.newItem = false;
            receptModel.recept = null;
        }, function (errorResp) {
            receptModel.error = errorResp.data;
        });
    };
}]);