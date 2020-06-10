angular.module('CDIOFinal').service('loginModel', function () {
    this.loginUser = {
        userName: '',
        password: ''
    };
    this.newUser = {
        userId: '',
        userName: '',
        userIni: '',
        cprnummer: '',
        password: ''
    };
    this.error = '';
    this.msg = '';
});