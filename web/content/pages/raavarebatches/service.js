angular.module('CDIOFinal').service('raavarebatchesService', ['$http', 'raavarebatchesModel','CDIOFinalModel','raavareService', function ($http, raavarebatchesModel,CDIOFinalModel,raavareService) {
    this.getRaavareBatches = function(){
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "raavarebatch"
        }).then(function (resp) {
            raavarebatchesModel.raavareBatches = resp.data;
        }, function (errorResp) {
            raavarebatchesModel.error = errorResp.data;
        });
    };

    this.createRavareBatch = function(raavareBatch){
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "raavarebatch",
            data: raavareBatch
        }).then(function () {
            raavarebatchesModel.raavareBatches.push(raavareBatch);
        }, function (errorResp) {
            raavarebatchesModel.error = errorResp.data;
        });
    };



}]);