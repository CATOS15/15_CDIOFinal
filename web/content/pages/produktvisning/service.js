angular.module('CDIOFinal').service('produktvisningService', ['$http', 'produktvisningModel', 'CDIOFinalModel', function ($http, produktvisningModel, CDIOFinalModel) {
    var _this = this;

    this.getProduktVisning = function(pbId){
        produktvisningModel.msg = "";
        produktvisningModel.error = "";
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "produktvisning/" + pbId
        }).then(function (resp) {
            produktvisningModel.produktVisning = resp.data;
        }, function (errorResp) {
            produktvisningModel.error = errorResp.data;
        });
    };
}]);