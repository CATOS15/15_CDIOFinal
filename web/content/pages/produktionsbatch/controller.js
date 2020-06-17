angular.module('CDIOFinal').controller('produktionsbatchController', ['$scope', 'produktionsbatchModel','receptModel', 'produktionsbatchService','receptService',function ($scope, produktionsbatchModel,receptModel,produktionsbatchService,receptService) {
    $scope.produktionsbatchModel = produktionsbatchModel;
    $scope.receptModel = receptModel;

    $scope.init = function(){
        produktionsbatchService.getProduktBatches();
        receptService.getRecepter();
    };

    $scope.toggleItem = function(produktBatch){
        produktionsbatchModel.newItem = false;
        if(produktionsbatchModel.produktBatch === produktBatch){
            produktionsbatchModel.produktBatch = null;
        }
        else{
            produktionsbatchModel.produktBatch = produktBatch;
        }
    };
    $scope.toggleNewItem = function(){
        if(produktionsbatchModel.newItem) return;
        produktionsbatchModel.newItem = true;
        produktionsbatchModel.produktBatch = {pbId: "", receptId: ""};
    };

    $scope.save = function(){
        if(produktionsbatchModel.newItem){
            produktionsbatchService.createProduktBatch(produktionsbatchModel.produktBatch);
        }
    };

    $scope.init();
}]);