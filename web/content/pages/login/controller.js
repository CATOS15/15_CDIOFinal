angular.module('CDIOFinal').controller('loginController',
    ['$scope', 'loginModel', 'loginService',
    function ($scope, loginModel, loginService) {
    $scope.loginModel = loginModel;
    $scope.login = function(){
        loginService.login($scope.loginModel.loginUser);
    };
}]);