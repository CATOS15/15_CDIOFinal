angular.module('CDIOFinal').controller('receptController', ['$scope', 'receptModel','receptService','raavareModel', 'raavareService', function ($scope, receptModel, receptService, raavareModel, raavareService) {
    $scope.receptModel = receptModel;
    $scope.raavareModel = raavareModel;

    $scope.validateReceptId = function(){
        if(!receptModel.newItem) return;
        var idExist = false;
        receptModel.recepter.forEach(function(recept) {
            if(recept.receptId === receptModel.recept.receptId) idExist = true;
        });
        if(idExist){
            receptModel.error = "En recept med det ID eksisterer allerede";
        }else{
            if(receptModel.error === "En recept med det ID eksisterer allerede"){
                receptModel.error = "";
            }
        }
        return idExist;
    };

    $scope.availableRaavare = function(raavarer, raavareId){
        var availableRaavare = [];
        raavarer.forEach(function(raavare) {
            var raavareAvailable = true;
            if(raavare.raavareId !== raavareId) {
                receptModel.recept.receptRaavarer.forEach(function (r) {
                    if(r.raavareId === raavare.raavareId) raavareAvailable = false;
                });
            }
            if(raavareAvailable) availableRaavare.push(raavare);
        });

        return availableRaavare;
    };

    $scope.init = function(){
        receptService.getRecepter();
        raavareService.getRaavarer();
    };
    $scope.toggleItem = function(recept){
        receptModel.newItem = false;
        receptModel.msg = "";
        receptModel.error = "";
        if(receptModel.newItem ===  recept){
            receptModel.newItem = null;
        }
        else{
            receptModel.recept = recept;
        }
    };
    $scope.addReceptRaavare = function(){
        receptModel.recept.receptRaavarer.push({
            receptId: receptModel.recept.receptId,
            raavareId: $scope.availableRaavare(raavareModel.raavarer, "")[0].raavareId,
            nonNetto: '',
            tolerance: ''
        });
    };

    $scope.removeReceptRaavare = function(receptRaavare){
        receptModel.recept.receptRaavarer.splice(receptModel.recept.receptRaavarer.indexOf(receptRaavare), 1);
    };

    $scope.toggleNewItem = function(){
        if(receptModel.newItem) return;
        receptModel.newItem = true;
        receptModel.recept = {
            receptId: '',
            receptNavn: '',
            receptRaavarer: []
        };
    };


    $scope.save = function(){
        receptService.createRecept(receptModel.recept);
    };

    $scope.init();
}]);