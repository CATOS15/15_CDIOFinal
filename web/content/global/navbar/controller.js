angular.module('CDIOFinal').controller('navbarController', ['$scope', 'navbarModel', '$location', function ($scope, navbarModel, $location) {
    $scope.navbarModel = navbarModel;

    $scope.changeLocation = function (path) {
        $location.path(path);
    }

}]);