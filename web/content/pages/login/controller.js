angular.module('CDIOFinal').controller('loginController', ['$scope', 'loginModel', 'CDIOFinalModel', 'loginService', function ($scope, loginModel, CDIOFinalModel, loginService) {
    $scope.loginModel = loginModel;
    $scope.CDIOFinalModel = CDIOFinalModel;

    $scope.loginUser = angular.copy(CDIOFinalModel.user);
    $scope.newUser = angular.copy(CDIOFinalModel.user);

    $scope.login = function(){
        loginService.login($scope.loginUser);
    };
    $scope.createUser = function(){
        loginService.createUser($scope.newUser);
    };
}]);