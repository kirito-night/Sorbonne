// On s'assure que control-C nous permet d'interrompre 
// l'ex ́ecution du programme
/*
const readline = require('readline');
readline.emitKeypressEvents(process.stdin); 
process.stdin.setRawMode(true); 
process.stdin.on('keypress', (_str, key) => {
    if (key.ctrl && key.name == "c") {
        console.log("Quitting with control-c");
        process.exit();
    
});
*/
const Pendu = require('./pendu.js')
let pendu = new Pendu("POMME" , 5);
pendu.show();
pendu.play();
/*
console.log(pendu.keypressed('p'));
pendu.show();
console.log(pendu.keypressed('O'))
pendu.show()
*/
