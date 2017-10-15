'use strict';

const express = require('express');
const router = express.Router();
const updateLocation = require('../lib/hce.js');

/* GET users listing. */
router.post('/provision', function(req, res, next) {
    let body = req.body;
    successCallback(res);
});

module.exports = router;


function successCallback(res) {
    res.status(200).send({"Success": "Provisioned approppriately."});
}