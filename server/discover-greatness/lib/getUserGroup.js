'use strict';

const heredoc = require('heredoc'),
    sendQuery = require('./helpers/accessDb');

const getFriendsQuery = heredoc(function () {/*
    SELECT friend_id FROM friendships WHERE user_id = ?;
*/});

const getUserQuery = heredoc(function () {/*
    SELECT * FROM users WHERE key = ?;
*/});

const getAllFriends = function(user_key) {
    let userFriends = [], friends = [];
    
    if (!(sendQuery instanceof Error)) {
        sendQuery(getFriendsQuery, user_key, (err, rows) => {
            if (err) {
                throw (err);
            } else {
                rows.forEach((obj) => {
                    friends.push(obj);
                    console.log(obj.friend_id);
                })
            }
            console.log(friends);
        });
        
        // if (friends && friends.length > 0) {
            friends.forEach((id) => {
                sendQuery(getUserQuery, id, (err, rows) => {
                    if (err) {
                        throw (err);
                    } else {
                        console.log(rows);
                        rows.forEach((obj) => {
                            userFriends.push(obj);
                        })
                    }
                })
            })
        // }
        
    } else {
        console.log("There was an issue accessing the database.");
    }
    console.log("Final results: ");
    console.log(userFriends);
    return userFriends;
}

getAllFriends(1);

