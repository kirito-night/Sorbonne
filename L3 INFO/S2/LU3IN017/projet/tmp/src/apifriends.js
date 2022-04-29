const express = require("express");
const Users = require("./entities/users.js");
const Friends = require("./entities/friends.js");
function init(db) {
    const router = express.Router();
    // On utilise JSON
    router.use(express.json());
    // simple logger for this router's requests
    // all requests to this router will first hit this middleware
    router.use((req, res, next) => {
        console.log('API: method %s, path %s', req.method, req.path);
        console.log('Body', req.body);
        next();
    });
    const users = new Users.default(db);
    const friends = new Friends.default(db);



    return router;
}
exports.default = init;