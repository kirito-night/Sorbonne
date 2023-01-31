package TestIntegration;
import java.beans.Transient;

public class TestAnalyse {
    
    ILivres livres = ComposantFactory.createLivresBouchon();
    livres.addLivre("fairy tail", "un livre");
    ILivre l = livres.getLivre("fairy tail");

    
    @Test
    public void testCreerAnalyseReussite() throws Exception {
        boolean t = Analyse.analyse(l);
        assertTrue(t);
    }

    @Test
    public void testCreerAnalyseEchec() throws Exception {
        boolean t = Analyse.analyse(l);
        assertFalse(t);
    }
}
