angular.module('CDIOFinal').controller('navbarController', ['$scope', 'CDIOFinalModel', 'navbarModel', '$location', function ($scope, CDIOFinalModel, navbarModel, $location) {
    $scope.CDIOFinalModel = CDIOFinalModel;
    $scope.navbarModel = navbarModel;

    $scope.changeLocation = function (path) {
        $location.path(path);
    };
    $scope.logout = function(){
        localStorage.removeItem('token');
        CDIOFinalModel.userToken = '';
        CDIOFinalModel.user = null;

        $scope.changeLocation("/login");
    };

}]);