angular.module('CDIOFinal').controller('raavareController', ['$scope', 'raavareModel', function ($scope, raavareModel) {
    $scope.raavareModel = raavareModel;

    $scope.orderByMe = function(x) {
        $scope.myOrderBy = x;
    }
}]);