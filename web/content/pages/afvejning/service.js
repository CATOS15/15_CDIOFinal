angular.module('CDIOFinal').service('afvejningService', ['$http', 'afvejningModel','CDIOFinalModel', 'produktionsbatchService', function ($http, afvejningModel,CDIOFinalModel, produktionsbatchService) {
    var _this = this;

    this.getAfvejninger = function(){
        afvejningModel.error = "";
        afvejningModel.msg = "";
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "afvejning"
        }).then(function (resp) {
            afvejningModel.userProduktBatches = resp.data;
            _this.resetItem();
        }, function (errorResp) {
            afvejningModel.error = errorResp.data;
        });
    };

    this.createAfvejning = function(userProduktBatch){
        var newItems = [];
        afvejningModel.error = "";
        afvejningModel.msg = "";
        for(var i = 0;i<userProduktBatch.afvejninger.length;i++){
            if(userProduktBatch.afvejninger[i].newItem){
                newItems.push(i);
                delete(userProduktBatch.afvejninger[i].newItem)
            }
        }
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "afvejning",
            data: userProduktBatch
        }).then(function (resp) {
            afvejningModel.msg = "Afvejning " + userProduktBatch.rbId + " oprettet";
            produktionsbatchService.getProduktBatches();
            afvejningModel.userProduktBatches.push(resp.data);
            _this.resetItem();
        }, function (errorResp) {
            afvejningModel.error = errorResp.data;
            newItems.forEach(function (item) {
                userProduktBatch.afvejninger[item].newItem = true;
            });
        });
    };


    this.resetItem = function(){
        afvejningModel.newItem = false;
        afvejningModel.userProduktBatch = null;
    };
}]);