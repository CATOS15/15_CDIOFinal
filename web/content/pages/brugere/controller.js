angular.module('CDIOFinal').controller('brugereController', ['$scope', 'brugereModel', 'brugereService', function ($scope, brugereModel, brugereService) {
    $scope.brugereModel = brugereModel;

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
        brugereModel.newItem = false;
        if(brugereModel.bruger && brugereModel.bruger.userId === bruger.userId){
            brugereModel.bruger = null;
        }
        else{
            brugereModel.bruger = angular.copy(bruger);
        }
    };
    $scope.toggleNewItem = function(){
        if(brugereModel.newItem) return;
        brugereModel.newItem = true;
        brugereModel.bruger = {userId: "", userName: "", userIni: "", cprnummer: "", roller: [], tilstand: true};
    };

    $scope.save = function(){
        if(brugereModel.newItem){
            brugereService.createBruger(brugereModel.bruger);
        }else{
            brugereService.updateBruger(brugereModel.bruger);
        }
    };

    $scope.deactivateUser = function() {
        brugereService.deleteBruger(brugereModel.bruger.userId);
    };

    $scope.init();
}]);