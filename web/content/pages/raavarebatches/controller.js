angular.module('CDIOFinal').controller('raavarebatchesController', ['$scope', 'raavarebatchesModel','raavarebatchesService','raavareModel','raavareService', function ($scope, raavarebatchesModel,raavarebatchesService,raavareModel,raavareService) {
    $scope.raavarebatchesModel = raavarebatchesModel;
    $scope.showAccordian = false;
    $scope.raavareModel = raavareModel;

    $scope.orderByMe = function(x) {
        $scope.myOrderBy = x;
    }

    $scope.init = function(){
        raavarebatchesService.getRaavareBatches();
        raavareService.getRaavarer();
    };

    $scope.save = function(){
        raavarebatchesService.createRavareBatch(raavarebatchesModel.raavareBatch);
    };

    $scope.init();
}]);