angular
    .module('event-list')
    .config(route);

function route($routeProvider) {

    $routeProvider
        .when('/events', {
            templateUrl: '/event-list/event-list.html',
            controllerAs: 'vm',
            controller: 'EventListController',
            resolve: {
                productPage: function($routeParams, eventService) {
                    return eventService.query($routeParams);
                }
            }
        });
}