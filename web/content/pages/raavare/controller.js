angular.module('CDIOFinal').controller('raavareController', ['$scope', 'raavareModel', 'raavareService', function ($scope, raavareModel, raavareService) {
    $scope.raavareModel = raavareModel;

    $scope.init = function(){
        //raavareService.getRaavarer();
    };

    $scope.init();
}]);