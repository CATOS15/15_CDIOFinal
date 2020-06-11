angular.module('CDIOFinal').controller('brugereController', ['$scope', 'brugereModel', 'brugereService', function ($scope, brugereModel, brugereService) {
    $scope.brugereModel = brugereModel;
    $scope.newItem = false;

    $scope.init = function(){
        brugereService.getBrugerer();
    };

    $scope.toggleItem = function(bruger){
        $scope.newItem = false;
        if(brugereModel.bruger === bruger){
            brugereModel.bruger = null;
        }
        else{
            brugereModel.bruger = bruger;
        }
    };
    $scope.toggleNewItem = function(){
        if($scope.newItem) return;
        $scope.newItem = true;
        brugereModel.bruger = {userId: "", userName: "", userIni: "", cprnummer: ""};
    };

    $scope.save = function(){
        if($scope.newItem){
            brugereService.createBruger(brugereModel.bruger);
        }else{
            brugereService.updateBruger(brugereModel.bruger);
        }
    };

    $scope.init();
}]);