var ClientOAuth2 = require('client-oauth2')

var auth = new ClientOAuth2({
    clientId: 'l7xx516deac1b17b4087b891bb381ebab8ac',
    clientSecret: '359e4a885b474e00ac2712f4f8fd8032',
    accessTokenUri: 'https://apis.discover.com/auth/oauth/v2/token',
    scopes: ['HCE']
})

var token = auth.createToken();

// token.sign({
//     method: 'get',
//     url: ''
//   })

console.log(token);
console.log("TESTING!");