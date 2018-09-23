angular
    .module('events')
    .service('eventService', service);

function service($resource, SERVER_URL) {

    this.get = get;
    this.query = query;
    this.create = create;
    this.update = update;
    this.remove = remove;

    var eventResource = $resource(SERVER_URL + '/events/:id', {}, {
        query: {
            method: 'GET',
            isArray: false,
            url: SERVER_URL + '/events'
        },
        update: {
            method: 'PUT'
        }
    });

    function get(id) {
        return eventResource.get({
            'id': id
        }).$promise;
    }

    function query(params) {
        return eventResource.query(params).$promise;
    }

    function create(event) {
        return eventResource.save(null, eventt).$promise;
    }

    function update(event) {
        return eventResource.update({
            'id': event.id
        }, product).$promise;
    }

    function remove(id) {
        return eventResource.remove({
            'id': id
        }).$promise;
    }
}