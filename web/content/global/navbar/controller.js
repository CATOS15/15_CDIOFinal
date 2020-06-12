angular.module('CDIOFinal').controller('navbarController', ['$rootScope', '$scope', 'CDIOFinalModel', 'navbarModel', '$location', '$location', function ($rootScope, $scope, CDIOFinalModel, navbarModel, $location) {
    $scope.CDIOFinalModel = CDIOFinalModel;
    $scope.navbarModel = navbarModel;

    $scope.changeLocation = function (path) {
        $location.path(path);
    };
    $scope.logout = function(){
        localStorage.removeItem('token');
        CDIOFinalModel.userToken = '';
        CDIOFinalModel.user = null;
        $rootScope.$emit('loginRequired');
    };

}]);