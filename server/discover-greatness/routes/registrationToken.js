'use strict';

const express = require('express');
const router = express.Router();
const updateLocation = require('../lib/sendPushNotifications');

/* GET users listing. */
router.post('/registrationToken', function(req, res, next) {
    let body = req.body;
    console.log(body.gcm_registration_token);
    res.send().status(404);
});

module.exports = router;
