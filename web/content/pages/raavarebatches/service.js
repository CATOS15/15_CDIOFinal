angular.module('CDIOFinal').service('raavarebatchesService', ['$http', 'raavarebatchesModel', function ($http, raavarebatchesModel) {
    this.getRaavareBatches = function(){
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "raavarebatches"
        }).then(function (resp) {
            raavarebatchesModel.raavarebatches = resp.data;
        }, function (errorResp) {
            raavarebatchesModel.error = errorResp.data;
        });
    };

    this.createRavareBatch = function(raavarebatches){
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "raavarebatches",
            data: raavarebatches
        }).then(function (resp) {
            raavarebatchesModel.raavarebatches = resp.data;
            //Todo add til liste
        }, function (errorResp) {
            raavarebatchesModel.error = errorResp.data;
        });
    };



}]);