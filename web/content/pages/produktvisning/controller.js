angular.module('CDIOFinal').controller('produktvisningController', ['$scope', 'produktvisningModel', 'produktvisningService', 'produktionsbatchService', 'produktionsbatchModel', function ($scope, produktvisningModel, produktvisningService, produktionsbatchService, produktionsbatchModel) {
    $scope.produktionsbatchModel = produktionsbatchModel;
    $scope.produktvisningModel = produktvisningModel;
    $scope.pbId = "";
    $scope.now = "";


    $scope.init = function(){
        produktionsbatchService.getProduktBatches();
    };

    $scope.onProduktionBatchSelect = function(pbId){
        produktvisningService.getProduktVisning(pbId);
        var date = new Date();
        $scope.now = date.getDate() + "-" + (date.getMonth()+1) + "-" + date.getFullYear();
    };

    $scope.print = function(){
        window.print();
    };

    $scope.init();
}]);