'use strict';

const express = require('express');
const router = express.Router();
const updateLocation = require('../lib/hce.js');

/* GET users listing. */
router.post('/provision', function(req, res, next) {
    let body = req.body;
    console.log(body.gcm_registration_token);
    res.status(200).send({"error": "Registration was not successful hajin. :("});
});

module.exports = router;
