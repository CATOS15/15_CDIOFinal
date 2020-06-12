angular.module('CDIOFinal').controller('receptController', ['$scope', 'receptModel','receptService','raavareModel', 'raavareService', function ($scope, receptModel, receptService, raavareModel, raavareService) {
    $scope.receptModel = receptModel;
    $scope.newItem = false;

    $scope.init = function(){
        receptService.getRecepter();
        raavareService.getRaavarer();
    };
    $scope.toggleItem = function(recept){
        $scope.newItem = false;
        if(receptModel.recept ===  recept){
            receptModel.recept = null;
        }
        else{
            receptModel.recept = recept;
        }
    };
    $scope.addReceptRaavare = function(){
        receptModel.recept.receptRaavarer.push({
            receptId: receptModel.recept.receptId,
            raavareId: '',
            nonNetto: '',
            tolerance: ''
        });
    };

    $scope.toggleNewItem = function(){
        if($scope.newItem) return;
        $scope.newItem = true;
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