'use strict';

const express = require('express');
const router = express.Router();
const addToken = require('../lib/registrationToken').addToken;

/* GET users listing. */
router.post('/registrationToken', function(req, res, next) {
    let body = req.body;
    addToken(body.gcm_registration_token, body.wallet_id, (err, results) => {
        if (err) {
            res.status(400).send({});
        } else {
            console.log(results);
            res.status(200).send({});
        }
    });
});

module.exports = router;
