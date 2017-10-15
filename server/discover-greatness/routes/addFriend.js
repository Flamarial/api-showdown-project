const express = require('express');
const router = express.Router();
const addFriend = require('../lib/addFriend');

/* GET users listing. */
router.post('/addFriend', function(req, res, next) {
    let body = req.body;
    if (body != {}) {
        addFriend(body.first_name, body.last_name, body.wallet_id, (err, results) => {
            if (err) {
                res.status(500).send('Friend not set.');
            } else {
                res.status(200).send('Friend set.')
            }
        });
    } else {
        res.send(400);
    }
});

module.exports = router;
