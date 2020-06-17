angular.module('CDIOFinal').service('raavarebatchesService', ['$http', 'raavarebatchesModel','CDIOFinalModel','raavareService', function ($http, raavarebatchesModel,CDIOFinalModel,raavareService) {
    var _this = this;
    this.getRaavareBatches = function(){
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "raavarebatch"
        }).then(function (resp) {
            raavarebatchesModel.raavareBatches = resp.data;
            _this.resetItem();
        }, function (errorResp) {
            raavarebatchesModel.error = errorResp.data;
        });
    };

    this.createRavareBatch = function(raavareBatch){
        raavarebatchesModel.error = "";
        raavarebatchesModel.msg = "";
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "raavarebatch",
            data: raavareBatch
        }).then(function () {
            raavarebatchesModel.msg = "Råvarebatch " + raavareBatch.rbId + " oprettet";
            raavarebatchesModel.raavareBatches.push(raavareBatch);
            _this.resetItem();
        }, function (errorResp) {
            raavarebatchesModel.error = errorResp.data;
        });
    };


    this.resetItem = function(){
        raavarebatchesModel.newItem = false;
        raavarebatchesModel.raavareBatch = null;
    };



}]);