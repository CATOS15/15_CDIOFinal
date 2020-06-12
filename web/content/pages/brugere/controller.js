angular.module('CDIOFinal').controller('brugereController', ['$scope', 'brugereModel', 'brugereService', function ($scope, brugereModel, brugereService) {
    $scope.brugereModel = brugereModel;
    $scope.newItem = false;

    $scope.init = function(){
        brugereService.getBrugerer();
        brugereService.getRoller();
    };

    $scope.getRolleIndex = function(rolle){
        var index = -1;
        var i = 0;
        brugereModel.bruger.roller.forEach(function(brugerRolle){
            if(brugerRolle.roleId === rolle.roleId){
                index = i;
                return;
            }
            i++;
        });
        return index;
    };

    $scope.toggleRolle = function(rolle){
        var rolleIndex = $scope.getRolleIndex(rolle);
        if(rolleIndex !== -1){
            brugereModel.bruger.roller.splice(rolleIndex, 1);
        }else{
            brugereModel.bruger.roller.push(rolle);
        }
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
        brugereModel.bruger = {userId: "", userName: "", userIni: "", cprnummer: "", roller: []};
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