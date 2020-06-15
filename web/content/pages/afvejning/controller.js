angular.module('CDIOFinal').controller('afvejningController', ['$scope', 'afvejningModel','produktionsbatchModel','raavarebatchesService','raavarebatchesModel','raavareModel','receptModel','afvejningService','produktionsbatchService', 'receptService', 'raavareService', 'CDIOFinalModel', function ($scope, afvejningModel,produktionsbatchModel,raavarebatchesService,raavarebatchesModel,raavareModel,receptModel,afvejningService,produktionsbatchService,receptService, raavareService, CDIOFinalModel) {
    $scope.afvejningModel = afvejningModel;
    $scope.produktionsbatchModel = produktionsbatchModel;
    $scope.raavarebatchesModel = raavarebatchesModel;
    $scope.raavareModel = raavareModel;
    $scope.receptModel = receptModel;

    $scope.getRaavaraFromId = function(raavareId){
        var raavare = {};
        raavareModel.raavarer.forEach(function(r){
            if(r.raavareId === raavareId) raavare = r;
        });
        return raavare;
    };

    $scope.getRaavaraBatchFromId = function(rbId){
        var raavareBatch = {};
        raavarebatchesModel.raavareBatches.forEach(function(rb){
            if(rb.rbId === rbId) raavareBatch = rb;
        });
        return raavareBatch;
    };

    $scope.getProduktBatchFromId = function(pbId){
        var produktBatches = {};
        produktionsbatchModel.produktBatches.forEach(function(pb){
            if(pb.pbId === pbId) produktBatches = pb;
        });
        return produktBatches;
    };

    $scope.getReceptFromId = function(receptId){
        var recept = {};
        receptModel.recepter.forEach(function(r){
            if(r.receptId === receptId) recept = r;
        });
        return recept;
    };

    $scope.getInformation = function(raavareBatchId){
        var raavareBatch = $scope.getRaavaraBatchFromId(raavareBatchId);

        var produktBatch = $scope.getProduktBatchFromId(afvejningModel.userProduktBatch.pbId);
        var recept = $scope.getReceptFromId(produktBatch.receptId);
        var raavare = {};
        var receptRaavare = {};
        recept.receptRaavarer.forEach(function(rr){
            if(rr.raavareId === raavareBatch.raavareId){
                receptRaavare = rr;
                raavare = $scope.getRaavaraFromId(rr.raavareId);
            }
        });

        return {
            receptNavn: recept.receptNavn,
            raavareNavn: raavare.raavareNavn,
            nonNetto: receptRaavare.nonNetto,
            tolerance: receptRaavare.tolerance
        };
    };

    $scope.init = function(){
        afvejningService.getAfvejninger();
        raavarebatchesService.getRaavareBatches();
        produktionsbatchService.getProduktBatches();
        receptService.getRecepter();
        raavareService.getRaavarer();
    };

    $scope.toggleItem = function(userProduktBatch){
        if(afvejningModel.userProduktBatch === userProduktBatch){
            afvejningModel.userProduktBatch = null;
        }
        else{
            afvejningModel.userProduktBatch = userProduktBatch;
        }
    };

    $scope.addAfvejning = function(){
        afvejningModel.userProduktBatch.afvejninger.push({userId: CDIOFinalModel.user.userId, rbId: '', tara: '', netto: '', terminal: ''});
        console.log($scope);
    };

    $scope.save = function(){
        afvejningService.createAfvejning(afvejningModel.userProduktBatch);
    };

    $scope.init();
}]);