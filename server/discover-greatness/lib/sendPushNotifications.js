'use strict';

const config = require('config');
const gcm = require('node-gcm');

const sendPushNotifications = function(registrationToken){
  // const serverKey = config.get("pushServerKey");

  // Set up the sender with your GCM/FCM API key (declare this once for multiple messages)
  const sender = new gcm.Sender('AAAAABsy6qE:APA91bEy0uEDjKw5UKSsY159CHnJbhIvDPWejuWV5hxBMMbKnt8VwLAi75WPf3F9tiveh384UMsvLELJmAWwlQYUL8BeVkTaYE62ulxEWU1HTc_eLF-gM2yShH0zZHhfniR5KIcXqpFH');

  // Prepare a message to be sent
  // let message = new gcm.Message({
  //    data: { key1: 'msg1' }
  // });

  var message = new gcm.Message({
      collapseKey: 'demo',
      priority: 'high',
      contentAvailable: true,
      delayWhileIdle: true,
      timeToLive: 3,
      dryRun: true,
      data: {
          key1: 'message1',
          key2: 'message2'
      },
      notification: {
          title: "Hello, World",
          icon: "ic_launcher",
          body: "This is a notification that will be displayed if your app is in the background."
      }
  });
  message.addData({
      key1: 'Hey, check out this restaurant nearby!',
      key2: 'Go with a friend and receive a sick deal!'
  });

  // Specify which registration IDs to deliver the message to
  const regTokens = [];
  regTokens.push(registrationToken);

  sender.send(message, { registrationTokens: regTokens }, function(err, response) {
    if(err) console.error(err);
    else    console.log(response);
  });

}

module.exports = sendPushNotifications;
