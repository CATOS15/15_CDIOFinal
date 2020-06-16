angular.module('CDIOFinal').controller('raavareController', ['$scope', 'raavareModel', 'raavareService', function ($scope, raavareModel, raavareService) {
    $scope.raavareModel = raavareModel;

    $scope.init = function(){
        raavareService.getRaavarer();
    };

    $scope.toggleItem = function(raavare){
        raavareModel.newItem = false;
        if(raavareModel.raavare && raavareModel.raavare.raavareId === raavare.raavareId){
            raavareModel.raavare = null;
        }
        else{
            raavareModel.raavare = angular.copy(raavare);
        }
    };
    $scope.toggleNewItem = function(){
        if(raavareModel.newItem) return;
        raavareModel.newItem = true;
        raavareModel.raavare = {raavareId: "", raavareNavn: ""};
    };

    $scope.save = function(){
        if(raavareModel.newItem){
            raavareService.createRaavare(raavareModel.raavare);
        }else{
            raavareService.updateRaavare(raavareModel.raavare);
        }
    };

    $scope.init();
}]);