'use strict'

const heredoc = require('heredoc'),
    accessDb = require('./helpers/accessDb'),
    sendQuery = accessDb.sendQuery,
    db = accessDb.db;

const insertToken = heredoc(function () {/*
    INSERT INTO tokens (token, token_user)
    VALUES ($token, $token_user)
*/});

const getUserFromWalletIdQuery = heredoc(function () {/*
    SELECT * FROM users WHERE wallet_id = ?;
*/});

const fetchToken = heredoc(function () {/*
    SELECT * FROM tokens WHERE token_user = ?;
*/});

const fetchUserId = function(wallet_id, cb) {
    sendQuery(getUserFromWalletIdQuery, wallet_id, (err, rows) => {
        if (err) {
            cb(err);
        } else {
            cb(null, rows[0])
        }
    });
};

const addToken = function(registration_token, wallet_id, cb) {
    fetchUserId(wallet_id, (err, userObj) => {
        if (err) {
            throw err;
        } else {
            sendQuery(insertToken, {
                $token: registration_token,
                $token_user: userObj.key
            }, (err, results) => {
                if (err) {
                    cb(err);
                } else {
                    cb(null, "Token stored.")
                }
            })
        }
    })
};

const getToken = function(user_id, cb) {
    sendQuery(fetchToken, user_id, (err, tokenObj) => {
        if (err) {
            cb(err)
        } else {
            cb(null, tokenObj[0]);
        }
    })
}

module.exports.addToken = addToken;
module.exports.getToken = getToken;