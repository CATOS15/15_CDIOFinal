angular.module('CDIOFinal').controller('raavarebatchesController', ['$scope', 'raavarebatchesModel','raavarebatchesService','raavareModel','raavareService', function ($scope, raavarebatchesModel,raavarebatchesService,raavareModel,raavareService) {
    $scope.raavarebatchesModel = raavarebatchesModel;
    $scope.raavareModel = raavareModel;

    $scope.orderByMe = function(x) {
        $scope.myOrderBy = x;
    }

    $scope.init = function(){
        raavarebatchesService.getRaavareBatches();
        raavareService.getRaavarer();
    };
    $scope.toggleItem = function(raavareBatch){
        $scope.newItem = false;
        if(raavarebatchesModel.raavareBatch === raavareBatch){
            raavarebatchesModel.raavareBatch = null;
        }
        else{
            raavarebatchesModel.raavareBatch = raavareBatch;
        }
    };
    $scope.toggleNewItem = function(){
        if($scope.newItem) return;
        $scope.newItem = true;
        raavarebatchesModel.raavareBatch = {rbId: "", raavareId: "", maengde: "", leverandoer: ""};
    };
    $scope.save = function(){
        raavarebatchesService.createRavareBatch(raavarebatchesModel.raavareBatch);
    };

    $scope.init();
}]);