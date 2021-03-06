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
        raavarebatchesModel.newItem = false;
        raavarebatchesModel.msg = "";
        raavarebatchesModel.error = "";
        if(raavarebatchesModel.raavareBatch === raavareBatch){
            raavarebatchesModel.raavareBatch = null;
        }
        else{
            raavarebatchesModel.raavareBatch = raavareBatch;
        }
    };
    $scope.toggleNewItem = function(){
        if(raavarebatchesModel.newItem) return;
        raavarebatchesModel.newItem = true;
        raavarebatchesModel.raavareBatch = {rbId: "", raavareId: "", maengde: "", leverandoer: ""};
    };
    $scope.save = function(){
        raavarebatchesService.createRavareBatch(raavarebatchesModel.raavareBatch);
    };

    $scope.init();
}]);