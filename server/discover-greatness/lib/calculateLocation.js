'use strict';

const heredoc = require('heredoc'),
    sendQuery = require('./helpers/accessDb'),
    getAllFriends = require('./getAllFriends');

const getUserQuery = heredoc(function () {/*
    SELECT * FROM users WHERE key = ?;
*/});

const getUserFromWalletIdQuery = heredoc(function () {/*
    SELECT * FROM users WHERE wallet_id = $wallet_id;
*/});

const getUserLocation = heredoc(function () {/*
    SELECT * FROM location WHERE user_id = ?;
*/});

const getRandomIntInclusive = function(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

const getLocationForRandomUser = function(friendsArray, cb) {
    let randomNum = getRandomIntInclusive(0, friendsArray.length - 1);
    let randomUser = friendsArray[randomNum];
    let location;
    sendQuery(getUserLocation, randomUser.key, (err, rows) => {
        if (err) {
            cb(err);
        } else {
            cb(null, location);
        }
    })
}

const calculateLocation = function(userInfo, cb) {
    if (userInfo instanceof Object) {
        getLocation(userInfo, cb);
    } else {
        sendQuery(getUserFromWalletIdQuery, {$wallet_id: userInfo}, (err, rows) => {
            if (err) {
                cb(err);
            } else {
                getLocation(rows[0], cb);
            }
        })
    }
}


const getLocation = function(userObj, cb) {
    console.log(userObj);
    let user, friends;
    sendQuery(getUserQuery, userObj.key, (err, rows) => {
        if (err) {
            cb(err);
        } else {
            user = rows[0];
            getAllFriends(userObj, (err, results) => {
                if (err) {
                     cb(err);
                } else {
                    friends = results;
                    if (friends && friends.length > 0) {
                        friends.push(user);
                        getLocationForRandomUser(friends, cb);
                    }
                }
            });
        }
    });
}

module.exports = calculateLocation;