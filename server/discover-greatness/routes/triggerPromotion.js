'use strict';

const express = require('express');
const router = express.Router();
const location = require('../lib/getLocation');

/* GET users listing. */
router.get('/triggerPromotion', function(req, res, next) {
    location.getLocationAuthenticated();
    res.send().status(200);
});

router.get('/triggerSecondPromotion', function(req, res, next) {
    location.secondExample();
    res.send().status(200);
});

module.exports = router;
