'use strict';

const heredoc = require('heredoc'),
    sendQuery = require('./accessDb');

    // CREATE TABLE users (
    //     key INTEGER PRIMARY KEY,
    //     first_name text,
    //     last_name text,
    //     wallet_id text,
    //     info text);

    // CREATE TABLE transactions (
    //     key integer primary key,
    //     merchant text,
    //     description text,
    //     amount float,
    //     transaction_user integer,
    //     foreign key(transaction_user) references users(key));

    // CREATE TABLE friendships (
    //     user_id integer,
    //     friend_id integer,
    //     key integer primary key,
    //     foreign key(user_id) references users(key),
    //     foreign key(friend_id) references users(key));

const seedUsers = heredoc(function () {/*
    INSERT INTO users ( first_name, last_name, wallet_id, info )
    VALUES
    ('Kevin', 'Mark', '1234', 'KMONEEEEEY'),
    ('Gabi', 'Gustilo', '1235', 'G-MEISTER'),
    ('Scotty', 'Chou', '1236', 'MPCHOU'),
    ('Hajin', 'Lee', '1237', 'HAJINI');
*/});

const makeFriendships = function() {
    let allUsers;
    const getAllUsers = heredoc(function () {/*
        SELECT users.key FROM users;
    */});

    const seedFriendships = heredoc(function () {/*
        INSERT INTO friendships ( user_id, friend_id )
        VALUES ($user, $friend1), ($user, $friend2), ($user, $friend3);
    */});

    sendQuery(getAllUsers, [], (err, rows) => {
        if (err) {
            throw (err);
        } else {
            allUsers = rows;
            // [ { key: 1 }, { key: 2 }, { key: 3 }, { key: 4 } ]

            if (allUsers && allUsers.length > 0) {
                for (let i = 0; i < allUsers.length; i++) {
                    let friends = allUsers.filter((userObj) => { return userObj.key !== (i+1); });
                    console.log(friends);
                    sendQuery(seedFriendships, {
                        $user: allUsers[i].key,
                        $friend1: friends.pop().key,
                        $friend2: friends.pop().key,
                        $friend3: friends.pop().key
                    }, (err) => {
                        if (err) {
                            throw (err);
                        }
                    })
                }
            }
        }
    });

    
}

if (!(sendQuery instanceof Error)) {
    sendQuery(seedUsers, [], (err) => {
        if (err) {
            throw (err) 
        } else {
            makeFriendships();
        }
    });
}


