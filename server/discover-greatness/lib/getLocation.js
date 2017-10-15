var oauth = require("./oauth.js");
var request = require('request')

var jsUcfirst = function(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function getLocation(access_token) {
    var url = 'https://api.discover.com/geo/remote/rest/location';
    var queryParams = '?' + encodeURIComponent('requestHeader.requestId') + '=' +
        encodeURIComponent('123')+ '&' + encodeURIComponent('requestHeader.version') + '=' +
        encodeURIComponent('3.2')+ '&' + encodeURIComponent('requestHeader.format') + '=' +
        encodeURIComponent('json')+ '&' + encodeURIComponent('requestHeader.applicationKey') + '=' +
        encodeURIComponent('l7xx516deac1b17b4087b891bb381ebab8ac')+ '&' +
        encodeURIComponent('listControl.startIndex') + '=' + encodeURIComponent('0')+ '&' +
        encodeURIComponent('listControl.segmentSize') + '=' + encodeURIComponent('3')+ '&' +
        encodeURIComponent('listControl.segmentWindow') + '=' + encodeURIComponent('3')+ '&' +
        encodeURIComponent('searchCriteria.filterField') + '=' + encodeURIComponent('name')+ '&' +
        encodeURIComponent('searchCriteria.filterValue') + '=' + encodeURIComponent('Starbucks');
    request({
        url: url + queryParams,
        headers: { 'Authorization':'Bearer ' + access_token  },
        method: 'GET'
        },
        function (error, response, body) {
            if (error) { console.log(error); }

            // console.log('Reponse received', JSON.parse(body));
            var sample = JSON.parse(body).location[2];

            //just hard coding/taking 3rd store for now
            var restaurant = {
              name: sample.name,
              address: sample.geoLocation,
              eligibilityIndicators: sample.mid[0],
              category: sample.category[0].parent.name
            }
            console.log(restaurant);
            var formattedRestaurantData = {
                name: jsUcfirst(restaurant.name.toLowerCase()),
                address: restaurant.address,
                offerMessage: "Save $2 on your next cup of coffee!",
                expirationMessage: "Expires on October 16th, 2017"
            }
            console.log(formattedRestaurantData);
            // Send push note here...

        }
    );
}

function getLocationAuthenticated() {
    oauth.authenticatedRequest(getLocation);
}

module.exports = {getLocationAuthenticated: getLocationAuthenticated}
