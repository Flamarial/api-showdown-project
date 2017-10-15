var request = require('request')

var auth = "Basic " + new Buffer("l7xx516deac1b17b4087b891bb381ebab8ac:359e4a885b474e00ac2712f4f8fd8032").toString('base64');
const API_KEY = 'l7xx516deac1b17b4087b891bb381ebab8ac'

function authenticatedRequest(methodToRun, cb) {
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
        methodToRun(access_token, cb);
    });
}

module.exports = {"authenticatedRequest": authenticatedRequest};