angular
    .module('event-list')
    .controller('EventListController', controller);

function controller(eventPage, $routeParams, $route, $location) {
    var vm = this;

    vm.search = search;
    vm.editProduct = editProduct;
    vm.changePage = changePage;

    vm.params = $routeParams;
    vm.events = eventPage.content;

    function search() {
        $route.updateParams(vm.params);
        $route.reload();
    }

    function editProduct(events) {
        $location.path('/events/edit/' + events.id);
    }

    function changePage(isPrevious) {
        var newPage = eventPage.number;
        if (isPrevious && !eventPage.first) {
            newPage--;
        } else if (!isPrevious && !eventPage.last) {
            newPage++;
        }
        vm.params.page = newPage;
        search();
    }
}