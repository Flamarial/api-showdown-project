'use strict';

const heredoc = require('heredoc'),
    sendQuery = require('./accessDb');

const createTables = heredoc(function() {/*
    CREATE TABLE friendships (
        user_id integer,
        friend_id integer,
        key integer primary key,
        foreign key(user_id) references users(key),
        foreign key(friend_id) references users(key)
    );

    CREATE TABLE location (
        key integer primary key,
        longitude float,
        latitude float,
        user_id integer,
        foreign key(user_id) references users(key)
    );
    CREATE TABLE users (
        key integer primary key,
        last_name text,
        first_name text,
        wallet_id text,
        info text,
        image_url text
        location integer,
        foreign key(location) references locations(key)
    );
    CREATE TABLE transactions (
        key integer primary key,
        merchant text,
        description text,
        amount float,
        transaction_user integer,
        foreign key(transaction_user) references users(key)
    );
*/});

const seedUsers = heredoc(function () {/*
    INSERT INTO users ( first_name, last_name, wallet_id, info, image_url )
    VALUES
    ('Kevin', 'Mark', '1234', 'KMONEEEEEY', 'https://media-exp2.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAz5AAAAJDgwZDg4NzM1LWJlYTMtNGI1OC04NTJlLThmMWE5M2QxMjE0Yw.jpg'),
    ('Gabi', 'Gustilo', '1235', 'G-MEISTER', 'https://media-exp2.licdn.com/media/AAEAAQAAAAAAAAcZAAAAJGIwN2E2Y2UwLTZiYmEtNDgzOC1hYmRiLTI3MTVmZGYwNjQzYg.jpg'),
    ('Scotty', 'Chou', '1236', 'MPCHOU', 'https://media-exp2.licdn.com/media/AAEAAQAAAAAAAAUkAAAAJGE3MjQ3OWYxLTU1NWEtNDI3OC04NzI3LTY0Mzk4MmQ3ZWI2Zg.jpg (12kB)'),
    ('Hajin', 'Lee', '1237', 'HAJINI', 'https://media-exp2.licdn.com/media/p/7/000/1d1/25c/1975557.jpg');
*/});

const seedLocations = heredoc(function () {/*
    INSERT INTO locations ( longitude, latitude, user_id )
    VALUES
    (41.876742, -87.624386, 1),
    (41.892689, -87.672056, 2),
    (41.948485, -87.663251, 3),
    (41.915427, -87.635601, 4);
*/});

const updateUsersWithLocation = function() {
    let locations;
    const getAllLocations = heredoc(function () {/*
        SELECT * FROM locations;
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
    sendQuery(createTables, [], (err) => {
        if (err) {
            throw (err) 
        } 
    });

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


