angular.module('CDIOFinal').service('receptService', ['$http', 'receptModel', 'CDIOFinalModel','raavareService',function ($http, receptModel,CDIOFinalModel, raavareService) {
    var _this = this;

    this.getRecepter = function(){
        receptModel.error = "";
        receptModel.msg = "";
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "recept"
        }).then(function (resp) {
            receptModel.recepter = resp.data;
            _this.resetItem();
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
            _this.resetItem();
        }, function (errorResp) {
            receptModel.error = errorResp.data;
        });
    };


    this.resetItem = function(){
        receptModel.newItem = false;
        receptModel.recept = null;
    };
}]);