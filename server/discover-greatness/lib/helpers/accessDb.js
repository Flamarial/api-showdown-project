'use strict';

const sqlite3 = require('sqlite3').verbose();
const path = require('path');
console.log(__dirname);
const db = new sqlite3.Database(path.join(__dirname, '../../../../db/pal.db'), (err) => {
    if (err) {
        throw new Error("There was an error accessing the database: " + err)
    } else {
        console.log("Successfully connected to the sqlite database.")
    }
});

const sendQuery = function(query, options, cb) {
    db.all(query, options, (err, rows) => {
        if (err) {
            cb(new Error("There was an error running your query: " + err ));
        } else {
            console.log("Query ran succesfully.");
            cb(null, rows);
        }
    });
}

module.exports.sendQuery = sendQuery;
module.exports.db = db;
