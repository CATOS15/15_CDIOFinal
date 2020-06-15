angular.module('CDIOFinal').controller('afvejningController', ['$scope', 'afvejningModel','produktionsbatchModel','raavarebatchesService','raavarebatchesModel','raavareModel','receptModel','afvejningService','produktionsbatchService', 'receptService',function ($scope, afvejningModel,produktionsbatchModel,raavarebatchesService,raavarebatchesModel,raavareModel,receptModel,afvejningService,produktionsbatchService,receptService) {
    $scope.afvejningModel = afvejningModel;
    $scope.produktionsbatchModel = produktionsbatchModel;
    $scope.raavarebatchesModel = raavarebatchesModel;
    $scope.raavareModel = raavareModel;
    $scope.receptModel = receptModel;
    $scope.newItem = false;

    $scope.init = function(){
        afvejningService.getAfvejninger();
        raavarebatchesService.getRaavareBatches();
        produktionsbatchService.getProduktBatches();
        receptService.getRecepter();
    };

    $scope.toggleItem = function(userProduktBatch){
        $scope.newItem = false;
        if(afvejningModel.userProduktBatch === userProduktBatch){
            afvejningModel.userProduktBatch = null;
        }
        else{
            afvejningModel.userProduktBatch = userProduktBatch;
        }
    };

    $scope.toggleNewItem = function(){
        if($scope.newItem) return;
        $scope.newItem = true;
        afvejningModel.userProduktBatch = {pbId: "", afvejninger: []};
    };

    $scope.save = function(){
        if($scope.newItem){
            afvejningService.createAfvejning(afvejningModel.userProduktBatch);
        }
    };

    $scope.init();
}]);