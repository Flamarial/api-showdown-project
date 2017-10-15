'use strict';

const heredoc = require('heredoc'),
    sendQuery = require('./helpers/accessDb'),
    getAllFriends = require('./getAllFriends');

const getUserQuery = heredoc(function () {/*
    SELECT * FROM users WHERE key = ?;
*/});

const getUserLocation = heredoc(function () {/*
    SELECT * FROM location WHERE user_id = ?;
*/});

function getRandomIntInclusive(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

var getLocationForRandomUser = function(friendsArray, cb) {
    let randomNum = getRandomIntInclusive(0, friendsArray.length - 1);
    console.log("FRIENDS:");
    console.log(friendsArray);
    let randomUser = friendsArray[randomNum];
    let location;
    if (randomUser) {
        sendQuery(getUserLocation, randomUser.key, (err, rows) => {
            if (err) {
                cb(err);
            } else {
                location = rows[0];
                cb(null, location);
            }
        })
    }
}

var calculateLocation = function(userObj, cb) {
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

// module.exports = calculateLocation;

calculateLocation({key: 1}, (err, location) => {
    if (err) {
        console.log(err)
    } else {
        console.log(location);
    }
});