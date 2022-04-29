module.exports = class Pendu {
    constructor(mot, max_err) {
        this.mot = mot.toLowerCase();
        this.max_err = max_err || 5;
        this.errors = 0;
        this.foundcount = 0;
        this.tab = new Array();
        for (let i = 0; i < mot.length; i++) {
            this.tab.push('_')
        }
    }

    show() {
        console.log("Erreurs :", this.errors, "/", this.max_err);
        console.log(this.tab.join(" "));
        console.log("Nombre  de mot de trouver :", this.foundcount);
    }

    keypressed(c) {
        c = c.toLowerCase();
        let i = 0;
        for (i; i < this.mot.length; i++) {
            if (c == this.mot[i]) {
                this.tab[i] = c;
                this.foundcount += 1;
            }

        }
        if (!this.mot.includes(c)) {
            this.errors += 1;
        }

        let res = undefined
        if (this.errors > this.max_err) {
            res = 0;
            console.log("etat :", res)
            return res;
        }
        if (this.foundcount > this.mot.length - 1) {
            res = 1;
            console.log("etat :", res)
            return res;
        }

        console.log("etat :", res)
    }

    play() {
        const readline = require('readline');
        readline.emitKeypressEvents(process.stdin);
        process.stdin.setRawMode(true);

        const f = (str, key) => {
            if (key.ctrl && key.name === 'c') {
                process.stdin.pause()
            }
            var res = this.keypressed(key.name)
            this.show();
            console.log('You pressed the "${str}" key');
            console.log()
            console.log(key)
            console.log()
            if (res == 1 ) {
                console.log("you win !!!")
                process.stdin.pause()
            }
            if (res == 0 ) {
                console.log("you lose !!!")
                process.stdin.pause()
            }

        }

        process.stdin.on('keypress', f);
    }
}