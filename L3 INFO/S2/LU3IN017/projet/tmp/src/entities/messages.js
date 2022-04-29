class Message{
    constructor(db){
        this.db = db;
    }

    async create(message, login){
        return new Promise((resolve, reject) => {
            this.db.messages.insert({ message: message, login: login }, function (err, docs) {
                let messageid = docs._id
                if (!messageid) {
                    //erreur
                    reject();
                } else {
                    resolve(messageid);
                }
            })
        });
    }

    async delete(messageid){
        return new Promise((resolve, reject) => {
            this.db.messages.remove({ _id: messageid }, function (err, docs) {
                if (err) {
                    reject();
                }
                else {
                    resolve(docs);
                }
            })
        });
    }
    async getUserMessage(username){ 
        return new Promise((resolve  , reject) => {
            this.db.messages.find({login : username} , function(err,docs){
                let mess = docs
                if (!mess){
                    reject();
                }else {
                    resolve(mess);
                }
            })
        })
    }

    async getAllMessage(){ 
        return new Promise((resolve  , reject) => {
            this.db.messages.find({} , function(err,docs){
                let mess = docs
                if (!mess){
                    reject();
                }else {
                    resolve(mess);
                }
            })
        })
    }

    async findMessage(content){
        return new Promise((resolve  , reject) => {
            this.db.messages.find({message : /content/ } , function(err,docs){
                let message = docs
                if (!message){
                    reject();
                }else {
                    resolve(messsage);
                }
            })
        })
    }



    
}

exports.default = Message;