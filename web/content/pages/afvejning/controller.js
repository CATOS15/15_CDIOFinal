angular.module('CDIOFinal').controller('afvejningController', ['$scope', 'afvejningModel','produktionsbatchModel','raavarebatchesService','raavarebatchesModel','raavareModel','receptModel','afvejningService','produktionsbatchService', 'receptService', 'raavareService', 'CDIOFinalModel', function ($scope, afvejningModel,produktionsbatchModel,raavarebatchesService,raavarebatchesModel,raavareModel,receptModel,afvejningService,produktionsbatchService,receptService, raavareService, CDIOFinalModel) {
    $scope.afvejningModel = afvejningModel;
    $scope.produktionsbatchModel = produktionsbatchModel;
    $scope.raavarebatchesModel = raavarebatchesModel;
    $scope.raavareModel = raavareModel;
    $scope.receptModel = receptModel;
    $scope.afvejningRecept = null;

    $scope.$watch('afvejningModel', function(afvejningModel){
        $scope.afvejningModel.userProduktBatch = null;
        $scope.init();
    });

    $scope.existInRb = function(raavareBatches, receptRaavarer){
        var exist;
        receptRaavarer.forEach(function (receptRaavare) {
            exist = false;
            raavareBatches.forEach(function (raavareBatch) {
                if(receptRaavare.raavareId === raavareBatch.raavareId) exist = true;
            });
            if(!exist) return exist;
        });
        return exist;
    };

    $scope.availableRaavareBatches = function(raavareBatches, rbId){
        var availableRaavareBatches = [];

        var produktBatch = $scope.getProduktBatchFromId(afvejningModel.userProduktBatch.pbId);
        var recept = $scope.getReceptFromId(produktBatch.receptId);
        var raavare = $scope.getRaavaraFromId($scope.getRaavaraBatchFromId(rbId).raavareId)
        raavareBatches.forEach(function(rb){
            var raavareNavn1 = $scope.getRaavaraFromId(rb.raavareId).raavareNavn;
            var rbAvailable = true;
            if(rb.rbId !== rbId && raavare.raavareNavn !== raavareNavn1){
                afvejningModel.userProduktBatch.afvejninger.forEach(function (afvejning) {
                    var raavareNavn2 = $scope.getRaavaraFromId($scope.getRaavaraBatchFromId(afvejning.rbId).raavareId).raavareNavn;
                    if(raavareNavn1 === raavareNavn2 || afvejning.rbId === rb.rbId){
                        rbAvailable = false;
                    }
                });
                if(rbAvailable){
                    var exist = false;
                    recept.receptRaavarer.forEach(function(rr){
                        if(rr.raavareId === rb.raavareId){
                            exist = true;
                        }
                    });
                    rbAvailable = exist;
                }
            }
            if(rbAvailable) availableRaavareBatches.push(rb);
        });
        return availableRaavareBatches;
    };

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
            tolerance: receptRaavare.tolerance,
            maengde: raavareBatch.maengde,
            leverandoer: raavareBatch.leverandoer
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
        afvejningModel.msg = "";
        afvejningModel.error = "";
        if(afvejningModel.userProduktBatch === userProduktBatch){
            afvejningModel.userProduktBatch = null;
            $scope.afvejningRecept = null;
        }
        else{
            afvejningModel.userProduktBatch = userProduktBatch;
            $scope.afvejningRecept = $scope.getReceptFromId($scope.getProduktBatchFromId(afvejningModel.userProduktBatch.pbId).receptId);
        }
    };

    $scope.addAfvejning = function(){
        afvejningModel.userProduktBatch.afvejninger.push({userId: CDIOFinalModel.user.userId, rbId: $scope.availableRaavareBatches(raavarebatchesModel.raavareBatches, "")[0].rbId, tara: '', netto: '', terminal: '', newItem: true});
    };

    $scope.save = function(){
        afvejningService.createAfvejning(afvejningModel.userProduktBatch);
    };

    $scope.init();
}]);