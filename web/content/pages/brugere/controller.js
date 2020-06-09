angular.module('CDIOFinal').controller('brugereController', ['$scope', 'brugereModel', 'brugereService', function ($scope, brugereModel, brugereService) {
    $scope.brugereModel = brugereModel;

    $scope.init = function(){
        brugereService.getBrugere();
    };

    $scope.init();
}]);