package TestIntegration;
public class TestObjet {

    //ILivres livres = ComposantFactory.createLivresBouchon();
    ILivre l = ComposantFactory.createLivreBouchon("fairy tail", "tail of fairy");
    
    @Test
    public void testCreerObjetReussite() throws Exception {
        l.createObjet("clé de donjon", "clé");
        assertEquals("clé de donjon", l.getObjet("clé de donjon").getNom());
        assertEquals("clé", l.getObjet("clé de donjon").getCategorie());
    }

    @Test
    public void testCreerObjetEchec() throws Exception {
        try {
            l.createObjet("clé de donjon", "clé");
            fail("L'objet existe déjà");
        } catch (Exception e) {
            assertEquals("L'objet existe déjà", e.getMessage());
        }
    }

    @Test
    public void testGererObjet() throws Exception {
        l.createObjet("clé de donjon", "clé");
        l.getObjet("clé de donjon").setCategorie("new type");
        assertEquals("new type", l.getObjet("clé de donjon").getCategorie());
    }

    @Test
    public void testSupprimerObjet() throws Exception {
        l.createObjet("clé de donjon", "clé");
        l.removeObjet("clé de donjon");
        assertNull(l.getObjet("clé de donjon"));
    }

}