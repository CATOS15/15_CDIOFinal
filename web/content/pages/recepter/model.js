angular.module('CDIOFinal').service('receptModel', function () {
    this.recept = null;
    this.recepter = [];
    this.newItem = false;

    this.msg = "";
    this.error = "";
});