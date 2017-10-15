'use strict';

const heredoc = require('heredoc'),
sendQuery = require('./helpers/accessDb');

const getUserQuery = heredoc(function () {/*
    SELECT key
    FROM users
    WHERE wallet_id = ?;
*/});

const updateLocationQuery = heredoc(function () {/*
    UPDATE location
    SET latitude = $latitude, longitude = $longitude
    WHERE user_id = $user_id;
*/});

const updateUserWithLocationQuery = heredoc(function () {/*
    UPDATE users
    SET location = $location_key
    WHERE key = $user_id;
*/});

const getAllLocations = heredoc(function () {/*
    SELECT key
    FROM location
    WHERE user_id = ?;
*/});

const checkLocationAndUpdateUser = function(user_id) {
    let locationKey;
    sendQuery(getAllLocations, user_id, (err, rows) => {
        if (err) {
            throw err;
        } else {
            locationKey = rows[0];
            if (locationKey) {
                sendQuery(updateUserWithLocationQuery, {
                    $location_key: locationKey.key,
                    $user_id: user_id
                }, (err) => {
                    if (err) {
                        throw err
                    } 
                })
            }
        }
    })
}

const updateLocation = function(latitude, longitude, user_wallet_id) {
    let user;
    if (!(sendQuery instanceof Error)) {
        sendQuery(getUserQuery, user_wallet_id, (err, rows) => {
            if (err) {
                throw err;
            } else {
                user = rows[0];
                if (user) {
                    sendQuery(updateLocationQuery, {
                        $latitude: latitude,
                        $longitude: longitude,
                        $user_id: user.key
                    }, (err) => {
                        if (err) {
                            throw err;
                        } else {
                            checkLocationAndUpdateUser(user.key);
                        }
                    });
                }
            }
        });

    }
}

module.exports = updateLocation;

