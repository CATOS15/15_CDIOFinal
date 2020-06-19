angular.module('CDIOFinal').service('loginModel',
    function () {
    this.loginUser = {
        userName: '',
        password: ''
    };
    this.error = '';
    this.msg = '';
});