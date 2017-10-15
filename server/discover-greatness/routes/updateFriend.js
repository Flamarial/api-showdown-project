const express = require('express');
const router = express.Router();
const updateFriend = require('../lib/updateFriend');

/* GET users listing. */
router.post('/updateFriend', function(req, res, next) {
    res.send('hello');
});

module.exports = router;
