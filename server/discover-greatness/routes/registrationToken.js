'use strict';

const express = require('express');
const router = express.Router();
const sendPushNotifications = require('../lib/sendPushNotifications');

/* GET users listing. */
router.post('/registrationToken', function(req, res, next) {
    let body = req.body;
    console.log('registration token');
    console.log(body.gcm_registration_token);
    sendPushNotifications(body.gcm_registration_token);
    res.status(200).send();
});

module.exports = router;
