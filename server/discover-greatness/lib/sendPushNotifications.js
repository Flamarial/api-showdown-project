'use strict';

const config = require('config');
const gcm = require('node-gcm');
// const serverKey = config.get("pushServerKey");

// Set up the sender with your GCM/FCM API key (declare this once for multiple messages)
const sender = new gcm.Sender('456321697');

// Prepare a message to be sent
let message = new gcm.Message({
   data: { key1: 'msg1' }
});

// Specify which registration IDs to deliver the message to
const regTokens = ['YOUR_REG_TOKEN_HERE'];

// Actually send the message
// sender.send(message, { registrationTokens: regTokens }, function (err, response) {
//    if (err) console.error(err);
//    else console.log(response);
// });