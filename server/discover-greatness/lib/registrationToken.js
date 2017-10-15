'use strict'

const heredoc = require('heredoc'),
    accessDb = require('./accessDb'),
    sendQuery = accessDb.sendQuery,
    db = accessDb.db;

const insertToken = heredoc(function () {/*
    INSERt INTO tokens (token, token_user)
    VALUES ($token, $token_user)
*/});

const getUserFromWalletIdQuery = heredoc(function () {/*
    SELECT * FROM users WHERE wallet_id = ?;
*/});

const getToken = heredoc(function () {/*
    SELECT * FROM tokens WHERE token = ?;
*/});

const addToken = function(registration_token, wallet_id) {

};

const getToken = function()