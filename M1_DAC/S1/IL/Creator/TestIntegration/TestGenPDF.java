package TestIntegration;

public class TestGenPDF {
    ILivres livres = ComposantFactory.createLivresBouchon();
    livres.addLivre("fairy tail" , "un livre");
    ILivre  l = livres.getLivre("fairy tail");

    @Test
    public void testGenPDF() throws Exception {
        Document doc = GenPDF.genPDF(livres.getLivre("fairy tail"));
        assertNotNull(doc);

    }
    @Test
    public void testGenPDFfail() throws Exception {
        Document doc = GenPDF.genPDF(livres.getLivre("fairy tail"));
        assertNull(doc);
    }
    
}
