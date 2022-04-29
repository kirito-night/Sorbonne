fs = require('fs')

function litSync(filepath, minlength) {
    let s = fs.readFileSync(filepath, 'utf-8');
    s = s.split('\n');
    return filterlength(s, minlength);
}

function lit(filepath, minlength) {
    return new Promise((resolve, reject) => {
        fs.readFile(filepath, 'utf-8', (err, data) => {
            if (err) {
                reject(err);
            } else {
                data = data.split('\n');
                resolve(filterlength(data, minlength));
            }
        })
    });
}

async function litAsync(filepath, minlength) {
    return new Promise((resolve, reject) => {
        fs.readFile(filepath, 'utf-8', (err, data) => {
            if (err) {
                reject(err);
            } else {
                data = data.split('\n');
                resolve(filterlength(data, minlength));
            }
        })
    });
}

function filterlength(data, minlength) {
    var res = new Array();
    for (let i = 0; i < data.length; i++) {
        if (data[i].length >= minlength) {
            res.push(data[i]);
        }
    }
    return res;
}

module.exports = {
    litSync: litSync,
    lit: lit,
    litAsync: litAsync
}

//let s = litSync(__dirname+'/../dico.txt', 5);
//console.log(s[20]);
//s = lit(__dirname+'/../dico.txt', 8);
//s.then((e) => { console.log("[1] Done", e) }).catch((e) => console.log("[1] Erreur", e));
