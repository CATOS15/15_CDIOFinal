angular.module('CDIOFinal').service('produktionsbatchService', ['$http', 'produktionsbatchModel','CDIOFinalModel', function ($http, produktionsbatchModel,CDIOFinalModel) {
    var _this = this;

    this.getProduktBatches = function(){
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "produktbatch"
        }).then(function (resp) {
            produktionsbatchModel.produktBatches = resp.data;
            _this.resetItem();
        }, function (errorResp) {
            produktionsbatchModel.error = errorResp.data;
        });
    };

    this.createProduktBatch = function(produktBatch){
        produktionsbatchModel.error = "";
        produktionsbatchModel.msg = "";
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "produktbatch",
            data: produktBatch
        }).then(function (resp) {
            produktionsbatchModel.msg = "produktbatch " + produktBatch.pbId + " oprettet";
            produktionsbatchModel.produktBatches.push(resp.data);
            _this.resetItem();
        }, function (errorResp) {
            produktionsbatchModel.error = errorResp.data;
        });
    };

    this.resetItem = function(){
        produktionsbatchModel.newItem = false;
        produktionsbatchModel.produktBatch = null;
    };
}]);