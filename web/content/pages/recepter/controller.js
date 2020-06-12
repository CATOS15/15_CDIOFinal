angular.module('CDIOFinal').controller('receptController', ['$scope', 'receptModel','receptService','raavareModel', function ($scope, receptModel, receptController, raavareModel) {
    $scope.recepterModel = recepterModel;
    $scope.newItem = false;

    $scope.init = function(){
        receptService.getRecept();
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
    $scope.toggleNewItem = function(){
        if($scope.newItem) return;
        $scope.newItem = true;
        receptModel.recept = {receptId: "", receptNavn: "", raavareId: "", nonNetto: "", tolerance: ""};
    };

    $scope.save = function(){
            receptService.createRecept(receptModel.recept);
    };

    $scope.init();
}]);