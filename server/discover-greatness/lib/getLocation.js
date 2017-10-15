var oauth = require("./oauth.js");
var request = require('request')

function getLocation(access_token) {
    var url = 'https://api.discover.com/geo/remote/rest/location';
    var location = {longitude: };
    var queryParams = '?' + encodeURIComponent('requestHeader.requestId') + '=' + 
        encodeURIComponent('123')+ '&' + encodeURIComponent('requestHeader.version') + '=' + 
        encodeURIComponent('3.2')+ '&' + encodeURIComponent('requestHeader.format') + '=' + 
        encodeURIComponent('json')+ '&' + encodeURIComponent('requestHeader.applicationKey') + '=' + 
        encodeURIComponent('l7xx516deac1b17b4087b891bb381ebab8ac')+ '&' +  
        encodeURIComponent('listControl.startIndex') + '=' + encodeURIComponent('0')+ '&' +  
        encodeURIComponent('listControl.segmentSize') + '=' + encodeURIComponent('10')+ '&' +  
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
    
            console.log('Reponse received', JSON.parse(body));
        }
    );
}

oauth.authenticatedRequest(getLocation);

