'use strict';

const express = require('express');
const router = express.Router();
const updateLocation = require('../lib/updateLocation');

/* GET users listing. */
router.post('/updateLocation', function(req, res, next) {
    let body = req.body;
    console.log(body);
    updateLocation(body.latitude, body.longitude, body.wallet_id);
    res.send().status(200);
});

module.exports = router;
