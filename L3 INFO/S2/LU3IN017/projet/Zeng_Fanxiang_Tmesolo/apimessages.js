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

        const { message, images } = req.body;
        if (!message) {
            res.status(400).send("Missing fields");
        } else if (!req.session.username) {
            res.status(401).send("Vous n'êtes pas autorisé à poster un message");
        } else {
            console.log(req.session.username)
            console.log(req.body);
            messages.create(message, req.session.username, images)
                .then((message_id) => res.status(201).send({ id: message_id }))
                .catch((err) => res.status(500).send(err));
        }
    });


    //get allMessage of user
    router.get("/:login/getUserMessage", async (req, res) => {
        const { login } = req.params;
        console.log(login)
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
            console.log("id", req.params.id)
            const { id } = req.params;
            if (!id) {
                res.status(400).send("Missing fields");
            } else {
                try {
                    mess = await messages.getMessage(id)
                    if (!mess) {
                        res.sendStatus(400)
                    } else {
                        console.log("message trouver : ", mess)
                        res.send(mess)
                    }
                } catch (e) {
                    console.log(e);
                    res.status(500).send(e)
                }
            }
        } catch (e) {
            console.log(e);
            res.status(500).send(e)
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
    //find message with content
    router.get("/findmessage", async (req, res) => {
        let { content } = req.query
        console.log(req.body)
        console.log(content);
        try {
            res.status(200).send(await messages.findMessage(content))
        } catch (e) {
            console.log(e)
            res.status(500).send({ status: 500, message: "internal server error" });
        }

    })


    router.delete("/:id", async (req, res) => {
        try {
            const { id } = req.params;
            console.log(id)
            if (!id) {
                res.status(505).send("message doesn't exist");
            } else {

                let num = await messages.delete(id);
                console.log("nbmessage deleted" + num);
                res.status(200).send(num + "message deleted");
            }
        } catch (e) {
            res.status(500).send(e, " internal server error");
        }

    })


    router.post("/comment/:id", async (req, res) => {
        const { id } = req.params;
        const { comment } = req.body;
        if (!id) {
            res.status(400).send("Missing fields");
        } else if (!comment) {
            res.status(401).send("Missing fields");
        } else {
            try {
                await messages.addComment(id, comment);
                res.status(201).send("comment added");
            } catch (e) {
                res.status(500).send(e);
            }
        }
    });

    router.post("/isFakeNews", async (req, res) => {
        const { isFakeNews } = req.body;
        console.log("id : " , isFakeNews)
        if (!isFakeNews) {
            res.status(401).send("Missing fields");
        } else {
            try {
                console.log("===========: " , isFakeNews)
                await messages.isFakeNews(isFakeNews , req.session.username);
                res.status(201).send("isFakeNews added");
            } catch (e) {
                res.status(500).send(e);
            }
        }
    });

        


    return router;
}
exports.default = init;

