'use strict';

const heredoc = require('heredoc'),
    sendQuery = require('./helpers/accessDb');

const getFriendsQuery = heredoc(function () {/*
    SELECT friend_id FROM friendships WHERE user_id = ?;
*/});

const getUserQuery = heredoc(function () {/*
    SELECT * FROM users WHERE key = ?;
*/});

const getUserObjectsForFriends = function(friendIds, cb) {
    let userFriends = [];
    friendIds.forEach((id) => {
        sendQuery(getUserQuery, id, (err, rows) => {
            if (err) {
                cb(err);
            } else {                        
                rows.forEach((obj) => {
                    userFriends.push(obj);
                })
                if(userFriends.length === friendIds.length) {
                    cb(null, userFriends);
                }
            }
        })
    });
};

const calculateAllFriends = function(userInfo, cb) {
    if (userInfo instanceof Object) {
        getAllFriends(userInfo, cb);
    } else {
        sendQuery(getUserFromWalletIdQuery, {$wallet_id: userInfo}, (err, rows) => {
            if (err) {
                cb(err);
            } else {
                getAllFriends(rows[0], cb);
            }
        })
    }
}

const getAllFriends = function(user, cb) {
    let friends = [], friendObjs = [];
    
    if (!(sendQuery instanceof Error)) {
        sendQuery(getFriendsQuery, user.key, (err, rows) => {
            if (err) {
                cb(err);
            } else {
                rows.forEach((obj) => {
                    friends.push(obj.friend_id);
                })
                if (friends && friends.length > 0) {
                    getUserObjectsForFriends(friends, cb);
                }
            }
        });
    } else {
        cb("There was an issue accessing the database.");
    }
}

module.exports = calculateAllFriends;

