'use strict';

const heredoc = require('heredoc'),
    sendQuery = require('./accessDb');

const seedUsers = heredoc(function () {/*
    INSERT INTO users ( first_name, last_name, wallet_id, info )
    VALUES
    ('Kevin', 'Mark', '1234', 'KMONEEEEEY'),
    ('Gabi', 'Gustilo', '1235', 'G-MEISTER'),
    ('Scotty', 'Chou', '1236', 'MPCHOU'),
    ('Hajin', 'Lee', '1237', 'HAJINI');
*/});

const seedLocations = heredoc(function () {/*
    INSERT INTO location ( longitude, latitude, user_id )
    VALUES
    (0.0, 0.0, 1),
    (0.0, 0.0, 2),
    (0.0, 0.0, 3),
    (0.0, 0.0, 4);
*/});

const updateUsersWithLocation = function() {
    let locations;
    const getAllLocations = heredoc(function () {/*
        SELECT * FROM location;
    */});

    const updateUserWithLocation = heredoc(function () {/*
        UPDATE users
        SET location = $location_key
        WHERE key = $user_id;
    */});

    sendQuery(getAllLocations, [], (err, rows) => {
        if (err) {
            throw (err);
        } else {
            locations = rows;
            locations.forEach((locationObj) => {
                sendQuery(updateUserWithLocation, {
                    $location_key: locationObj.key,
                    $user_id: locationObj.user_id
                }, (err) => {
                    if (err) {
                        throw (err);
                    }
                })
            })
        }
    })
}

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

    sendQuery(seedLocations, [], (err) => {
        if (err) {
            throw (err) 
        } else {
            updateUsersWithLocation();
        }
    });
}


