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
        for(var i = 0;i<userProduktBatch.afvejninger.length;i++){
            if(userProduktBatch.afvejninger[i].newItem){
                delete(userProduktBatch.afvejninger[i].newItem)
            }
        }
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "afvejning",
            data: userProduktBatch
        }).then(function (resp) {
            afvejningModel.userProduktBatches.push(resp.data);
        }, function (errorResp) {
            afvejningModel.error = errorResp.data;
        });
    };
}]);