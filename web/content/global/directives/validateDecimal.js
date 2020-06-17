angular.module('CDIOFinal').directive('validateDecimal', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            setTimeout(function(){
                element.val(element.val().replace(",", ""));
                element.val(element.val().replace("-", ""));
            }, 100);
        });
    };
});