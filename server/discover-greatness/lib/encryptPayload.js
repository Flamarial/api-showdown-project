var data = require('./payload.js');
var jwt = require('json-web-token');
var sha256 = require('sha256');
var jose = require('node-jose');

// console.log('getMethods');
// console.log(jwt.getAlgorithms());

// jwt.encode(data.secret, data.payload, function (err, token) {
//     if (err) {
//         console.error(err.name, err.message);
//     } else {
//         console.log("Encrypted Payload:");
//         console.log(token);

//       // decode
//     //   jwt.decode(secret, token, function (err_, decodedPayload, decodedHeader) {
//     //     if (err) {
//     //       console.error(err.name, err.message);
//     //     } else {
//     //       console.log(decodedPayload, decodedHeader);
//     //     }
//     //   });
//     }
// });

var jweHeader = "";
var contentEncryptionKey = "";
var contentEncryptionIV = "";
var encryptedContent = "";
var authenticationTag = "";

var kid = base64(sha256(`MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwYY1cHydBfrGWHQwr0WU
K2SC+nKTm2RKDV5lz5W/oMQYsSuu34rnY+e1+i//qpnshL98brjrcbnsVIaQBZH6
P6OmlP5w50Ata9aWbUDtoMomZ23dhK5bxwSqYl17rwiESY9ADWFAW0WZVMc02hUR
9GOIpr2wbzzZeQfUaoN5rctT4NfZXcQeBSWzfPp4Bg7BttBd4L9AGaVFQ3/lGQRA
tcK4gXztfHXUr0ADUAnEpnfilBZ4AO6PeWOeXx7jF8m0SpNsPsxhtdzWW+UZKU2+
bEggAQncTZkIBZ/g05E/QdsAErjkHW0Qx4TOMw9GKT4CAti8q9i5jQXLIRIanXMC
pQIDAQAB`));
kid = kid.split("==").join("");

jweHeader = JSON.stringify({"alg":"RSA_5",
"enc":"A128CBC-HS256",
"kid":"LRGSx453Ccok3sKZ11hdC0d-S5BQjkr4M7MQb2xKNh9"});

var buffer = jose.util.randomBytes(16);
contentEncryptionIV = jose.util.base64url.encode(buffer);
console.log(contentEncryptionIV);

console.log("jweheader:");
console.log(base64(jweHeader));


var jwePayload = base64(jweHeader) + "." + base64(contentEncryptionKey) + "." +
    base64(contentEncryptionIV) + "." + base64(encryptedContent) + "." + base64(authenticationTag);

function base64(stringToEncode) {
    return new Buffer(stringToEncode).toString('base64');
}
