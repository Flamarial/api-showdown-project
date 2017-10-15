var request = require('request')

var auth = "Basic " + new Buffer("l7xx516deac1b17b4087b891bb381ebab8ac:359e4a885b474e00ac2712f4f8fd8032").toString('base64');

const API_KEY = 'l7xx516deac1b17b4087b891bb381ebab8ac'

request({
    url: 'https://apis.discover.com/auth/oauth/v2/token',
    method: 'POST',
    form: {
        'grant_type': 'client_credentials',
        scope: "HCE"
    },
    headers: {
        Authorization: auth,
        "Content-Type": "application/x-www-form-urlencoded",
        "Cache-Control": "no-cache"
    }
}, function(err, res) {
    if (err) {
        console.log(err);
    }

    var access_token = JSON.parse(res.body).access_token;
    getLocation(access_token);
});

function getLocation(access_token) {
  var url = 'https://api.discover.com/geo/remote/rest/location';
  var queryParams = '?' +  encodeURIComponent('requestHeader.requestId') + '=' + encodeURIComponent('123')+ '&' +  encodeURIComponent('requestHeader.version') + '=' + encodeURIComponent('3.2')+ '&' +  encodeURIComponent('requestHeader.format') + '=' + encodeURIComponent('json')+ '&' +  encodeURIComponent('requestHeader.applicationKey') + '=' + encodeURIComponent('l7xx516deac1b17b4087b891bb381ebab8ac')+ '&' +  encodeURIComponent('listControl.startIndex') + '=' + encodeURIComponent('0')+ '&' +  encodeURIComponent('listControl.segmentSize') + '=' + encodeURIComponent('10')+ '&' +  encodeURIComponent('listControl.segmentWindow') + '=' + encodeURIComponent('3')+ '&' +  encodeURIComponent('searchCriteria.filterField') + '=' + encodeURIComponent('name')+ '&' +  encodeURIComponent('searchCriteria.filterValue') + '=' + encodeURIComponent('Starbucks');
  request({
    url: url + queryParams,
    headers: { 'Authorization':'Bearer ' + access_token  },
    method: 'GET'
}, function (error, response, body) {
    console.log('Status', response.statusCode);
    console.log('Headers', JSON.stringify(response.headers));
    console.log('Reponse received', body);
});

  // request({
  //   url: 'https://api.discover.com/geo/remote/rest/location',
  //   method: 'GET',
  //   headers:{
  //     Authorization: "Bearer " + access_token
  //   },
  //   form:{
  //     requestHeader: {
  //       requestId: 12345,
  //       version: 3.2,
  //       format: 'json',
  //       applicationKey: 'l7xx516deac1b17b4087b891bb381ebab8ac'
  //     }
  //   },
  //
  //   listControl:{
  //     startIndex:0,
  //     segmentSize:1,
  //     segmentWindow:3
  //   },
  //   searchCriteria:{
  //     filterField: "name",
  //     filterValue: "Starbucks"
  //   }
  //
  // },function(err,res){
  //   if (err) {
  //       console.log(err);
  //   } else {
  //     console.log(res);
  //     console.log("it worked");
  //
  //   }
  // });
}
