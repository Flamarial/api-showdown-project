const express = require('express');
const router = express.Router();
const calculateLocation = require('../lib/calculateLocation');

/* GET users listing. */
router.get('/getLocation', function(req, res, next) {
    let wallet_id = req.query.wallet_id;
    console.log(wallet_id);
    calculateLocation(wallet_id, (err, location) => {
        if (err) {
            res.send("Invalid wallet id.").status(400);
        } else {
            res.send(location).status(200);
        }
    });
});

module.exports = router;
