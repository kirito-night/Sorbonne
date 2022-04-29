const e = require("express");
const { resolve } = require("path/posix");

class Users {
  constructor(db) {
    this.db = db

    // suite plus tard avec la BD
    this.get = this.get.bind(this);
  }

  create(login, password, lastname, firstname) {
    return new Promise((resolve, reject) => {

      

      this.db.users.insert({ login: login, password: password, lastname: lastname, firstname: firstname, following: [], followers: [] }, function (err, docs) {
        let userid = docs._id
        if (!userid) {
          //erreur
          reject();
        } else {
          resolve(userid);
        }
      })
    });
  }
  //get
  get(username) {
    return new Promise((resolve, reject) => {
      // const user = {
      //    login: "pikachu",
      //    password: "1234",
      //    lastname: "chu",
      //    firstname: "pika"
      // }; // À remplacer par une requête bd

      this.db.users.find({ login: username }, function (err, docs) {
        let res_user = docs
        if (!res_user) {
          reject()
        }
        else {
          resolve(res_user)
        }
      });


    });
  }



  async exists(login) {
    return new Promise((resolve, reject) => {
      this.db.users.find({ login: login }, function (err, docs) {

        if (!docs) {
          reject();
        } else {
          resolve(true)
        }
      })

    });
  }

  checkpassword(login, password) {
    return new Promise((resolve, reject) => {
      this.db.users.find({ login: login, password: password }, function (err, docs) {
        let username = docs[0].login;
        if (err) {
          reject();
        }
        else {
          resolve(username);
        }
      })


      // let login = db.users.find({ login: login, password: password }, { _id: 1 }); // À remplacer par une requête bd
      // if (false) {
      //   //erreur
      //   reject();
      // } else {
      //   resolve(userid);
      // }
    });
  }

  follow(mylogin, followinglogin) {

    return new Promise((resolve, reject) => {
      this.db.users.update({ login: mylogin }, { $push: { following: followinglogin } }, {upsert : true}, function () {});
      this.db.users.update({ login: followinglogin }, { $push: { followers: mylogin } }, {upsert : true}, function () {});
      resolve();

    });

  }

}

exports.default = Users;

