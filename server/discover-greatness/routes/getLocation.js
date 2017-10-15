'use strict';

const express = require('express');
const router = express.Router();
const calculateLocation = require('../lib/calculateLocation');

/* GET users listing. */
router.get('/getLocation', function(req, res, next) {
    let wallet_id = req.query.wallet_id;
    console.log(wallet_id);
    calculateLocation(wallet_id, (err, location) => {
        if (err) {
            res.status(400).send("Invalid wallet id.");
        } else {
            console.log(location);
            res.status(200).send(location);
        }
    });
});

module.exports = router;
