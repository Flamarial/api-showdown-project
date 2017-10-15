'use strict';

const express = require('express');
const router = express.Router();
const getAllFriends = require('../lib/getAllFriends');

/* GET users listing. */
router.get('/getAllFriends', function(req, res, next) {
    let wallet_id = req.query.wallet_id;
    console.log(wallet_id);
    calculateAllFriends(wallet_id, (err, friends) => {
        if (err) {
            res.status(400).send("Invalid wallet id.");
        } else {
            console.log(friends);
            res.status(200).send(friends);
        }
    });
});

module.exports = router;
