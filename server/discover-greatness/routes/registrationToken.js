'use strict';

const express = require('express');
const router = express.Router();
const updateLocation = require('../lib/sendPushNotifications');

/* GET users listing. */
router.post('/registrationToken', function(req, res, next) {
    let body = req.body;
    console.log('registration token');
    console.log(body.gcm_registration_token);
    res.status(200).send({"error": "Registration was not successful hajin. :("});
});

module.exports = router;
