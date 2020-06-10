angular.module('CDIOFinal').controller('CDIOFinalController', ['$rootScope', '$scope', 'CDIOFinalModel', '$location', 'CDIOFinalService', function ($rootScope, $scope, CDIOFinalModel, $location, CDIOFinalService) {
    $scope.CDIOFinalModel = CDIOFinalModel;

    $scope.init = function(){
        CDIOFinalService.getLogin();
    };

    $rootScope.$on('loginRequired', function() {
        $location.path('/login');
    });

    $scope.$watch('CDIOFinalModel.userToken', function() {
        if(!CDIOFinalModel.userToken) return;
        localStorage.setItem('token', CDIOFinalModel.userToken);
        CDIOFinalService.getLogin();
    });

    $scope.init();
}]);