angular.module('CDIOFinal').controller('CDIOFinalController', ['$rootScope', '$scope', 'CDIOFinalModel', '$location', 'CDIOFinalService', function ($rootScope, $scope, CDIOFinalModel, $location, CDIOFinalService) {
    $scope.CDIOFinalModel = CDIOFinalModel;

    $scope.init = function(){
        $scope.checkLocalStorageToken();
        CDIOFinalService.getLogin();
    };

    $rootScope.$on('loginRequired', function() {
        $location.path('/login');
    });

    $scope.$on('$locationChangeSuccess', function(location) {
        $scope.init();
    });
    $scope.$watch('CDIOFinalModel.userToken', function() {
        if(!CDIOFinalModel.userToken) {
            return;
        }
        localStorage.setItem('token', CDIOFinalModel.userToken);
        $scope.init();
    });
    $scope.checkLocalStorageToken = function(){
        var token = localStorage.getItem('token');
        CDIOFinalService.setToken(token);
    };
}]);