var data = require('./payload.js');
var jwt = require('json-web-token');

jwt.encode(data.secret, data.payload, function (err, token) {
    if (err) {
        console.error(err.name, err.message);
    } else {
        console.log("Encrypted Payload:");
        console.log(token);
   
      // decode 
    //   jwt.decode(secret, token, function (err_, decodedPayload, decodedHeader) {
    //     if (err) {
    //       console.error(err.name, err.message);
    //     } else {
    //       console.log(decodedPayload, decodedHeader);
    //     }
    //   });
    }
});