'use strict';

const express = require('express');
const router = express.Router();
const updateLocation = require('../lib/sendPushNotifications');

/* GET users listing. */
router.post('/registrationToken', function(req, res, next) {
    let body = req.body;
    console.log(body.gcm_registration_token);
<<<<<<< Updated upstream
    res.status(200).send({"error": "Registration was not successful hajin. :("});
=======
    res.status(200).send();
>>>>>>> Stashed changes
});

module.exports = router;
