angular.module('CDIOFinal').service('afvejningService', ['$http', 'afvejningModel','CDIOFinalModel', function ($http, afvejningModel,CDIOFinalModel) {
    this.getAfvejninger = function(){
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "afvejning"
        }).then(function (resp) {
            afvejningModel.userProduktBatches = resp.data;
        }, function (errorResp) {
            afvejningModel.error = errorResp.data;
        });
    };

    this.createAfvejning = function(userProduktBatch){
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "userProduktBatch",
            data: userProduktBatch
        }).then(function (resp) {
            afvejningModel.userProduktBatches.push(resp.data);
        }, function (errorResp) {
            afvejningModel.error = errorResp.data;
        });
    };
}]);