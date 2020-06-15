angular.module('CDIOFinal').service('raavareService', ['$http', 'raavareModel', 'CDIOFinalModel', function ($http, raavareModel, CDIOFinalModel) {
    var _this = this;

    this.getRaavarer = function(){
        raavareModel.msg = "";
        raavareModel.error = "";
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "raavare"
        }).then(function (resp) {
            raavareModel.raavarer = resp.data;
            _this.resetItem();
        }, function (errorResp) {
            raavareModel.error = errorResp.data;
        });
    };
    this.getRaavare = function(raavareId){
        raavareModel.msg = "";
        raavareModel.error = "";
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "raavare/" + raavareId
        }).then(function (resp) {
            raavareModel.raavare = resp.data;
            _this.resetItem();
        }, function (errorResp) {
            raavareModel.error = errorResp.data;
        });
    };
    this.createRaavare = function(raavare){
        raavareModel.msg = "";
        raavareModel.error = "";
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "raavare",
            data: raavare
        }).then(function (resp) {
            raavareModel.msg = "Råvaren " + raavare.raavareNavn + " blev oprettet";
            raavareModel.raavarer.push(raavare);
            _this.resetItem();
        }, function (errorResp) {
            raavareModel.error = errorResp.data;
        });
    };
    this.updateRaavare = function(raavare){
        raavareModel.msg = "";
        raavareModel.error = "";
        $http({
            method: "PUT",
            url: CDIOFinalModel.apiURL + "raavare",
            data: raavare
        }).then(function (resp) {
            for(var i = 0;i<raavareModel.raavarer.length;i++){
                var r = raavareModel.raavarer[i];
                if(r.raavareId === raavare.raavareId){
                    raavareModel.raavarer[i] = raavare;
                    break;
                }
            }
            raavareModel.msg = "Råvaren " + raavare.raavareNavn + " blev opdateret";
            _this.resetItem();
        }, function (errorResp) {
            raavareModel.error = errorResp.data;
        });
    };

    this.resetItem = function(){
        raavareModel.newItem = false;
        raavareModel.raavare = null;
    };
}]);