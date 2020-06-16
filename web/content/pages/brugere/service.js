angular.module('CDIOFinal').service('brugereService', ['$http', 'brugereModel', 'CDIOFinalModel', function ($http, brugereModel, CDIOFinalModel) {
    var _this = this;

    //Roller
    this.getRoller = function(){
        brugereModel.msg = "";
        brugereModel.error = "";
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "rolle"
        }).then(function (resp) {
            brugereModel.roller = resp.data;
            _this.resetItem();
        }, function (errorResp) {
            brugereModel.error = errorResp.data;
        });
    };

    //Brugerer
    this.getBrugerer = function(){
        brugereModel.msg = "";
        brugereModel.error = "";
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "bruger"
        }).then(function (resp) {
            brugereModel.brugerer = resp.data;
            _this.resetItem();
        }, function (errorResp) {
            brugereModel.error = errorResp.data;
        });
    };
    this.getBruger = function(userId){
        brugereModel.msg = "";
        brugereModel.error = "";
        $http({
            method: "GET",
            url: CDIOFinalModel.apiURL + "bruger/" + userId
        }).then(function (resp) {
            brugereModel.bruger = resp.data;
            _this.resetItem();
        }, function (errorResp) {
            brugereModel.error = errorResp.data;
        });
    };
    this.createBruger = function(bruger){
        brugereModel.msg = "";
        brugereModel.error = "";
        $http({
            method: "POST",
            url: CDIOFinalModel.apiURL + "bruger",
            data: bruger
        }).then(function (resp) {
            brugereModel.brugerer.push(bruger);
            brugereModel.msg = "Bruger " + bruger.userName + " oprettet";
            _this.resetItem();
        }, function (errorResp) {
            brugereModel.error = errorResp.data;
        });
    };
    this.updateBruger = function(bruger){
        brugereModel.msg = "";
        brugereModel.error = "";
        $http({
            method: "PUT",
            url: CDIOFinalModel.apiURL + "bruger",
            data: bruger
        }).then(function (resp) {
            for(var i = 0;i<brugereModel.brugerer.length;i++){
                var b = brugereModel.brugerer[i];
                if(b.userId === bruger.userId){
                    brugereModel.brugerer[i] = bruger;
                    break;
                }
            }
            brugereModel.msg = "Brugeren " + bruger.userName + " blev opdateret";
            _this.resetItem();
        }, function (errorResp) {
            brugereModel.error = errorResp.data;
        });
    };
    this.deleteBruger = function(userId){
        brugereModel.msg = "";
        brugereModel.error = "";
        $http({
            method: "DELETE",
            url: CDIOFinalModel.apiURL + "bruger/" + userId
        }).then(function (resp) {
            var bruger;
            var index = -1;
            for(var i = 0;i<brugereModel.brugerer.length;i++){
                var b = brugereModel.brugerer[i];
                if(b.userId === userId){
                    index = i;
                    bruger = b;
                    bruger.tilstand = 0;
                    break;
                }
            }
            brugereModel.msg = "Brugeren " + bruger.userName + " blev deaktiveret";
            _this.resetItem();
        }, function (errorResp) {
            brugereModel.error = errorResp.data;
        });
    };

    this.resetItem = function(){
        brugereModel.newItem = false;
        brugereModel.bruger = null;
    };
}]);