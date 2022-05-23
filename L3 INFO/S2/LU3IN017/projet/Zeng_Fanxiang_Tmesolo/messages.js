const defaultimage = "http://localhost:4000/public/images/";
class Message {
    constructor(db) {
        this.db = db;

        this.getMessage = this.getMessage.bind(this);
    }

    async create(message, login, images) {
        return new Promise((resolve, reject) => {
            this.db.messages.insert({ message: message, login: login, date: Date.now(), comments: [], images: images , fakenumber : 0, votedUser : []}, function (err, docs) {
                let messageid = docs._id
                if (!messageid) {
                    reject();
                } else {
                    resolve(messageid);
                }
            })
        });
    }

    delete(messageid) {
        return new Promise((resolve, reject) => {
            this.db.messages.remove({ _id: messageid }, function (err, numRemoved) {
                if (err) {
                    reject();
                }
                else {
                    resolve(numRemoved);
                }
            })
        });
    }
    async getUserMessage(username) {
        return new Promise((resolve, reject) => {
            this.db.messages.find({ login: username }).sort({ date: -1 }).exec(function (err, docs) {
                let mess = docs
                if (!mess) {
                    reject();
                } else {
                    resolve(mess);
                }
            })
        })
    }

    async getAllMessage() {
        return new Promise((resolve, reject) => {
            this.db.messages.find({}).sort({ date: -1 }).exec(function (err, docs) {
                let mess = docs
                if (!mess) {
                    reject();
                } else {
                    resolve(mess);
                }
            })
        })

    }

    async findMessage(content) {
        return new Promise((resolve, reject) => {
            this.db.messages.find({ message: new RegExp(content) }, function (err, docs) {

                if (!docs) {
                    reject();
                } else {
                    resolve(docs);
                }
            })
        })
    }


    async getMessage(id) {
        return new Promise((resolve, reject) => {
            this.db.messages.find({ _id: id }).sort({ date: -1 }).exec(function (err, docs) {
                if (!docs) {
                    reject();
                } else {
                    resolve(docs);
                }
            })
        })
    }


    updateMessageImage(username, filename) {
        return new Promise((resolve, reject) => {

            this.db.messages.update({ login: username }, { $set: { ImagePhoto: defaultimage + filename } }, function (err, docs) {

                resolve()
            });
        })
    }

    async addComment(id, comment) {
        return new Promise((resolve, reject) => {
            this.db.messages.update({ _id: id }, { $push: { comments: comment } }, function (err, docs) {
                resolve()
            });
        })
    }


    async isFakeNews(isFakeNews , userid) {
        return new Promise((resolve, reject) => {
            console.log("isFakeNews",isFakeNews)
            console.log("userid",userid)
            let mess = this.getMessage(isFakeNews)
            let fakenumber = mess.isFakeNews + 1;
            let votedUser = mess.votedUser;
            votedUser.push(userid)

            console.log(fakenumber, votedUser)

            this.db.messages.update({ _id: isFakeNews }, { $set: { fakenumber:  fakenumber , votedUser : votedUser} }, function (err, docs) {
                resolve()
            });
        })
    }
}

exports.default = Message;