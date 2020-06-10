angular.module('CDIOFinal').controller('loginController', ['$scope', 'loginModel', 'loginService', function ($scope, loginModel, loginService) {
    $scope.loginModel = loginModel;

    $scope.init = function(){
        loginService.login();
    };

    $scope.init();
}]);