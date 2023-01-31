package TestIntegration;

import java.beans.Transient;

public class TestGenHTML {
    ILivre livre = ComposantFactory.createLivreBouchon("fairy tail", "un livre");

    
    @Test
    public void testGenHTML() throws Exception {
        Document doc = GenHTML.genHTML(livre);
        assertNotNull(doc);

    }
    @Test
    public void testGenHTMLfail() throws Exception {
        Document doc = GenHTML.genHTML(livre);
        assertNull(doc);
    }

}