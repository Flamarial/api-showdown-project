var data = require('./payload.js');
var jwt = require('json-web-token');
var sha256 = require('sha256');
var jose = require('jose');
var nodejose = require('node-jose');
var keystore = nodejose.JWK.createKeyStore();
var fs = require('fs');
var rsaPemToJwk = require('rsa-pem-to-jwk');
var fs = require('fs');

const PUB_KEY = `MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwYY1cHydBfrGWHQwr0WU
K2SC+nKTm2RKDV5lz5W/oMQYsSuu34rnY+e1+i//qpnshL98brjrcbnsVIaQBZH6
P6OmlP5w50Ata9aWbUDtoMomZ23dhK5bxwSqYl17rwiESY9ADWFAW0WZVMc02hUR
9GOIpr2wbzzZeQfUaoN5rctT4NfZXcQeBSWzfPp4Bg7BttBd4L9AGaVFQ3/lGQRA
tcK4gXztfHXUr0ADUAnEpnfilBZ4AO6PeWOeXx7jF8m0SpNsPsxhtdzWW+UZKU2+
bEggAQncTZkIBZ/g05E/QdsAErjkHW0Qx4TOMw9GKT4CAti8q9i5jQXLIRIanXMC
pQIDAQAB`;
const PEM = `-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwYY1cHydBfrGWHQwr0WU
K2SC+nKTm2RKDV5lz5W/oMQYsSuu34rnY+e1+i//qpnshL98brjrcbnsVIaQBZH6
P6OmlP5w50Ata9aWbUDtoMomZ23dhK5bxwSqYl17rwiESY9ADWFAW0WZVMc02hUR
9GOIpr2wbzzZeQfUaoN5rctT4NfZXcQeBSWzfPp4Bg7BttBd4L9AGaVFQ3/lGQRA
tcK4gXztfHXUr0ADUAnEpnfilBZ4AO6PeWOeXx7jF8m0SpNsPsxhtdzWW+UZKU2+
bEggAQncTZkIBZ/g05E/QdsAErjkHW0Qx4TOMw9GKT4CAti8q9i5jQXLIRIanXMC
pQIDAQAB
-----END PUBLIC KEY-----`


function generateEncryptedKey() {
    var props = {
        alg: 'A128CBC-HS256',
        use: 'enc'
      };
    keystore.generate("oct", 256, props).
        then(function(result) {
        key = result;
        generateJwePayload(key.kid);
    });
}

function generateJwePayload(encryptionKey) {
    console.log('hellodfadf');
    var jweHeader = "";
    var contentEncryptionKey = "";
    var contentEncryptionIV = "";
    var encryptedContent = "";
    var authenticationTag = "";
    
    var kid = base64(sha256(PUB_KEY));
    kid = kid.split("==").join("");
    
    jweHeader = JSON.stringify({"alg":"RSA1_5",
    "enc":"A128CBC-HS256",
    "kid":"LRGSx453Ccok3sKZ11hdC0d-S5BQjkr4M7MQb2xKNh9"});

    var jwk = rsaPemToJwk(PEM,{}, 'public');
    var buffer = new Buffer(encryptionKey, "base64")
    console.log('buffer');
    console.log(buffer);
    console.log('jwk');
    console.log(jwk);
    contentEncryptionKey = encryptionKey;
    console.log('encryptionKey...');
    console.log(contentEncryptionKey);

    console.log("PUB_KEY");
    console.log(PUB_KEY);
    ////TODO: complete contentEncryptionKey;

    var buffer = nodejose.util.randomBytes(16);
    contentEncryptionIV = nodejose.util.base64url.encode(buffer);
      
    var jwePayload = base64(jweHeader) + "." + base64(contentEncryptionKey) + "." +
        base64(contentEncryptionIV) + "." + base64(encryptedContent) + "." + base64(authenticationTag);
}

function base64(stringToEncode) {
    return new Buffer(stringToEncode).toString('base64');
}

generateEncryptedKey();
