public class TestEnchainement {
    private ILivres livres;
    private ILivre livre;
    private ISection section1;
    private ISection section2;
    private IEnchainement enchainement;

    @Before
    public void setup() {
        this.livres = ComposantFactory.createLivresBouchon();
    }

    @Test
    public void testCreateEnchainement() {
        this.enchainement = livre.addEnchainement(section1, section2, "get out");

        assertNotNull(this.enchainement);
        assertEquals(section1, this.enchainement.getDepart());
        assertEquals(section2, this.enchainement.getArrivee());
        assertEquals("get out", this.enchainement.getDescription());
    }

    @Test
    public void testDeleteEnchainement() {
        this.enchainement = livre.addEnchainement(section1, section2, "get out");
        livre.deleteEnchainement(enchainement);

        assertNull(this.enchainement);
    }
}
