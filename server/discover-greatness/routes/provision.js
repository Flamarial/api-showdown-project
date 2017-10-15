'use strict';

const express = require('express');
const router = express.Router();
const hce = require('../lib/hce.js');

/* GET users listing. */
router.post('/provision', function(req, res, next) {
    let body = req.body;
    console.log('provisioning card...');
    hce.provisionRequestToServer((err, results) => {
        if (err) {
            console.log('Not provisioned');
            res.status(500).send({"error": 'Not provisioned.'});
        } else {
            console.log('Provisioned');
            res.status(200).send(results);
        }
    });
});

module.exports = router;


function successCallback(res) {
    res.status(200).send({"Success": "Provisioned approppriately."});
}