angular.module('CDIOFinal').controller('CDIOFinalController', ['$rootScope', '$scope', 'CDIOFinalModel', '$location', 'CDIOFinalService', function ($rootScope, $scope, CDIOFinalModel, $location, CDIOFinalService) {
    $scope.CDIOFinalModel = CDIOFinalModel;

    $scope.onlyNumber = function(model){
        try{
            var newModel = model.toString().replace(",", "");
            return parseFloat(newModel.replace(".", ""));
        }
        catch(ex){
            return model;
        }
    };
    $scope.changeCommaToDot = function(model){
        try{
            return parseFloat(model.toString().replace(",", ""));
        }
        catch(ex){
            return model;
        }
    };

    $scope.init = function(){
        $scope.checkLocalStorageToken();
        CDIOFinalService.getLogin();
    };

    $scope.checkRoleAccess = function(accessIds){
        if(!CDIOFinalModel.user) return false;
        var access = false;
        CDIOFinalModel.user.roller.forEach(function(role){
            if(accessIds.indexOf(role.roleId) !== -1) access = true;
        });
        return access;
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