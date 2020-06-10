angular.module('CDIOFinal').directive('globalLoading', function () {
    return {
        restrict: 'E',
        template: '<div class="loading"><div class="spinnerContainer">Indlæser...<div class="spinner"></div></div></div>'
    };
});