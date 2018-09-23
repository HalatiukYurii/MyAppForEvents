var app = angular.module('app', [
    'ngRoute',
    'event-list',
    'settings',
    'events',
    'ngResource'
]);

app.config(function($routeProvider) {
    $routeProvider
        .otherwise({
            redirectTo: '/'
        });
});
