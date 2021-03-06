(function () {
    'use strict';
    var goClick = function ($location) {
        return function (scope, element, attrs) {
            var path;
            attrs.$observe('goClick', function (val) {
                path = val;
            });
            element.bind('click', function () {
                scope.$apply(function () {
                    $location.path(path);
                });
            });
        };
    }
    goClick.$inject = ['$location'];
    module.exports = goClick;
})();

