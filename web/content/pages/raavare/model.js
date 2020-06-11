angular.module('CDIOFinal').service('raavareModel', function () {
    this.raavare = null;
    this.raavarer = [{
        raavareId: 1, raavareName: "ting"
    },{
        raavareId: 2, raavareName: "og"
    },{
        raavareId: 3, raavareName: "sager"
    }];

    this.error = "";
});