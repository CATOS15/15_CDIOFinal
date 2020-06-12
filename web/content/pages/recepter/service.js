angular.module('CDIOFinal').service('receptService', ['$http', 'receptModel', 'CDIOFinalModel','raavareService',function ($http, receptModel,CDIOFinalModel, raavareService) {

    this.getRecept = function(){
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "recept"
        }).then(function (resp) {
            receptModel.recept = resp.data;
        }, function (errorResp) {
            receptModel.error = errorResp.data;
        });
    };

    this.createRecept= function(recept){
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "recept",
            data: recept
        }).then(function () {

            receptModel.recept.push(recept);
        }, function (errorResp) {
            receptModel.error = errorResp.data;
        });
    };
}]);