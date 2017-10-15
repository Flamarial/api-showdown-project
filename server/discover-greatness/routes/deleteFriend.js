'use strict';

const express = require('express');
const router = express.Router();
const deleteFriend = require('../lib/deleteFriend');

/* GET users listing. */
router.post('/deleteFriend', function(req, res, next) {
    let body = req.body;
    console.log(body);
    if (body) {
        deleteFriend(body.first_name, body.last_name, body.wallet_id, (err, results) => {
            if (err) {
                res.status(500).send('Friend not deleted.');
            } else {
                res.status(200).send('Friend deleted.')
            }
        });
    } else {
        res.send(400);
    }
});

module.exports = router;
