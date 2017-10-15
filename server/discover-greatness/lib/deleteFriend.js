'use strict';

const heredoc = require('heredoc'),
    sendQuery = require('./helpers/accessDb').sendQuery;

const getUserFromWalletIdQuery = heredoc(function () {/*
    SELECT * FROM users WHERE wallet_id = ?;
*/});

const getUserFriend = heredoc(function () {/*
    SELECT * FROM users
    WHERE last_name = $last AND first_name = $first;
*/});

const deleteFriendship = heredoc(function () {/*
    DELETE FROM friendships
    WHERE user_id = $user_id AND friend_id = $friend_id
*/});

const getUser = function(wallet_id, cb) {
    sendQuery(getUserFromWalletIdQuery, wallet_id, (err, rows) => {
        if (err) {
            cb(err);
        } else {
            cb(null, rows[0]);
        }
    });
};

const getFriend = function(first, last, cb) {
    sendQuery(getUserFriend, {
        $first: first,
        $last: last
    }, (err, rows) => {
        if (err) {
            cb(err);
        } else {
            cb(null, rows[0]);
        }
    });
}

const deleteFriend = function(first, last, wallet_id, cb) {
    let user, friend;

    getUser(wallet_id, (err, user) => {
        if (err) {
            cb(err);
        } else {
            user = user;
            getFriend(first, last, (err, friend) => {
                if (err) {
                    cb(err);
                } else {
                    friend = friend;
                    console.log(user);
                    console.log(friend);
                    if (user && friend) {
                        console.log("here");
                        sendQuery(deleteFriendship, {
                            $user_id: user.key,
                            $friend_id: friend.key
                        }, (err, results) => {
                            if (err) {
                                cb(err);
                            } else {
                                sendQuery(deleteFriendship, {
                                    $user_id: friend.key,
                                    $friend_id: user.key
                                }, (err, results) => {
                                    if (err) {
                                        cb(err);
                                    } else {
                                        cb(null, results);
                                    }
                                })
                            }
                        });
                    }
                }
            });
        }
    });

    

    
}

module.exports = deleteFriend;