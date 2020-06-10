angular.module('CDIOFinal').controller('loginController', ['$scope', 'loginModel', 'loginService', function ($scope, loginModel, loginService) {
    $scope.loginModel = loginModel;
    $scope.login = function(){
        loginService.login($scope.loginModel.loginUser);
    };
    $scope.createUser = function(){
        loginService.createUser($scope.loginModel.newUser);
    };
}]);