const assert = require("assert")
const dico = require("../src/dico.js")
const path = require('path')
const dicopath = path.join(path.dirname(__dirname), "dico.txt")
console.log(dicopath)
describe("Lire un dictionnaire", () => {
    it("sync", () => {
        let words = dico.litSync(dicopath, 5)
        assert.strictEqual(words[0], "angle")
        assert.strictEqual(words[20], "meuble")
    })
    it("promise", async () => {
        let words = await dico.lit(dicopath, 5)
        assert.strictEqual(words[0], "angle")
        assert.strictEqual(words[20], "meuble")
    })
    it("async", async () => {
        let words = await dico.litAsync(dicopath, 5)
        assert.strictEqual(words[0], "angle")
        assert.strictEqual(words[20], "meuble")
    })
})