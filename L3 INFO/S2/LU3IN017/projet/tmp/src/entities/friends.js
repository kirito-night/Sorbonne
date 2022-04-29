class Friend {
    constructor(db) {
      this.db = db
    }

    async follow( login){
      return new Promise((resolve, reject) => {
      

        
          this.db.friends.insert({login: login }, function (err, docs) {
            try{
              let messageid = docs._id
              if (!messageid) {
                  //erreur
                  reject();
              } else {
                  resolve(messageid);
              }
            }catch(err){
              reject(err)
            }
              
          })
      });
  }

}exports.default = Friend;