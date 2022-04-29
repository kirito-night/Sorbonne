fs = require('fs')

// Attends ms millisecondes (non asynchrone)
function wait(ms) {
    var start = +(new Date());
    while (new Date() - start < ms);
    console.log("Wait is over....")
}

// Planifie un message dans 100ms
setTimeout(() => {
    console.log("Message 1")
}, 100)

wait(1000)

// Planifie un nouveau message dans 100ms
setTimeout(() => {
    console.log('Message 2')
}, 100)

// Appel synchrone
let s = fs.readFileSync(__filename)
console.log("Sync file %s", __filename)

// Appel asynchrone
fs.readFile(__filename, () => {
    console.log("Async file read")
})