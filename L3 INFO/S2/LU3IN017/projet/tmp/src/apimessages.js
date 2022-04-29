const express = require("express");
const Users = require("./entities/users.js");
const Message = require("./entities/messages.js");

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
    const messages = new Message.default(db);

    // createMessage
    router.post("/message", async (req, res) => {

        const { message } = req.body;
        if (!message) {
            res.status(400).send("Missing fields");
        } else if (!req.session.username) {
            res.status(401).send("Vous n'êtes pas autorisé à poster un message");
        } else {
            console.log(req.session.username)
            messages.create(message, req.session.username)
                .then((message_id) => res.status(201).send({ id: message_id }))
                .catch((err) => res.status(500).send(err));
        }
    });


    //get allMessage of user
    router.get("/:login/getUserMessage", async (req, res) => {
        const { login } = req.params.login;
        if (!login) {
            res.status(400).send("login problem")
        } else if (! await users.exists(login)) {
            res.status(402).send("Utilisateur inconnue")
        } else {
            try {
                uMess = await messages.getUserMessage(login)
                if (!uMess) {
                    res.sendStatus(400)
                } else {
                    res.send(uMess)
                }
            } catch (e) {
                console.log(e);
                res.status(500).send(e)
            }


        }
    });
    //avoir ses propre message
    router.get("/getSelfMessage", async (req, res) => {
        //const { login } = req.params.login;
        if (!req.session.username) {
            res.status(400).send("login problem")
        } else if (! await users.exists(req.session.username)) {
            res.status(402).send("Utilisateur inconnue")
        } else {
            try {
                uMess = await messages.getUserMessage(req.session.username)
                if (!uMess) {
                    res.sendStatus(400)
                } else {
                    res.send(uMess)
                }
            } catch (e) {
                console.log(e);
                res.status(500).send(e)
            }


        }
    });


    //get message by id
    router.get("/message/:id", async (req, res) => {
        try {
            res.send({
                status: 200, message: await messages.get(
                    req.params.id
                )
            });
        }
        catch (e) {
            console.error(e);
            res.status(500).send({ status: 500, message: "internal server error" });
        }
    })

    router.get("/allmessage", async (req, res) => {
        try {
            allmess = await messages.getAllMessage();

            if (!allmess) {
                res.sendStatus(400)
            } else {
                res.send({ allmess })
            }

        } catch (e) {
            console.log(e);
            res.status(500).send({ status: 500, message: "internal server error" });

        }
    })

    router.get("/findmessage", async (req, res) => {
        let content = req.body
        try {
            res.status(200).send(await messages.findMessage(content))
        } catch (e) {
            console.log(e)
            res.status(500).send({ status: 500, message: "internal server error" });
        }

    })
    // router.post("/user/:user_id(\\d+)/message", (req, res) => {
    //     const { user_id } = req.params.user_id;
    //     const { message } = req.body;
    //     if (!message) {
    //         res.status(400).send("Missing fields");
    //     } else {
    //         users.addMessage(user_id, message)
    //             .then(() => res.sendStatus(201))
    //             .catch((err) => res.status(500).send(err));
    //     }
    // });

    // router.post("user/user_id/message", async (req, res) => {
    //     let tab = []
    //     const user = await users.get(req.params.user_id);
    //     var message = req.body;
    //     tab.push(message)
    //     res.send(message)


    // });

    return router;
}
exports.default = init;

