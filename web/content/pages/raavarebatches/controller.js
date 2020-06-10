angular.module('CDIOFinal').controller('raavarebatchesController', ['$scope', 'raavarebatchesModel', function ($scope, raavarebatchesModel) {
    $scope.raavarebatchesModel = raavarebatchesModel;



    $scope.orderByMe = function(x) {
        $scope.myOrderBy = x;
    }
}]);