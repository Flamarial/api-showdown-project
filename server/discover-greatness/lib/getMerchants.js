server.route({
    method: 'GET',
    path: '/location/{location}',
    handler: function (request, reply) {
        const teamID = encodeURIComponent(request.params.location);
 
        Request.get(`https://api.discover.com/geo/remote/rest`, function (error, response, body) {
            if (error) {
                throw error;
            }
 
            const result = JSON.parse(body);
 
            console.log("Made a successful GET request to api.discover.com/geo/remote/rest");
        });
    }
});