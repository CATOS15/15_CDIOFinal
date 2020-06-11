angular.module('CDIOFinal').directive('accordian', function () {
    return {
        restrict: 'E',
        transclude: true,
        scope:{},
        template: '<div ng-transclude></div>',
        link: function(scope,element){
            element.class().find('accordian_panel').css('display','none')
            element.find('button').on('click', function(event){
                element.class().find('accordian_panel').css('display','none');
                var panel = angular.element(event.target).next();
                panel.css('display','block')
            })
        }
    };
});