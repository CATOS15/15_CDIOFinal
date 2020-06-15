angular.module('CDIOFinal').service('produktionsbatchService', ['$http', 'produktionsbatchModel','CDIOFinalModel', function ($http, produktionsbatchModel,CDIOFinalModel) {
    this.getProduktBatches = function(){
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "produktbatch"
        }).then(function (resp) {
            produktionsbatchModel.produktBatches = resp.data;
        }, function (errorResp) {
            produktionsbatchModel.error = errorResp.data;
        });
    };

    this.createProduktBatch = function(produktBatch){
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "produktbatch",
            data: produktBatch
        }).then(function (resp) {
            produktionsbatchModel.produktBatches.push(resp.data);
        }, function (errorResp) {
            produktionsbatchModel.error = errorResp.data;
        });
    };
}]);