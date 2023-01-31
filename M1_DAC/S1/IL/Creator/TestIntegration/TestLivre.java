package TestIntegration;
import java.beans.Transient;

public class TestLivre {
    @Test
    ILivres livres = ComposantFactory.createLivres();

    public void testCreateLivre() {//creation livre reussit

        livres.addLivre("Arthur tail", "Once upon a time"); // verification lors de l'ajout
        livres.check_book("Arthur tail"); // verification lors de la creation
        assertEquals(1, livres.getLivres().size());
        assertEquals("Arthur tail", livres.getLivre("Arthur Tail").getTitre());
        assertEquals("Once upon a time", livres.getLivre("Arthur Tail").getDescription());
        
    }

    public void testCreateLivreFail(){ //creation livre echoue
        livres.addLivre("Arthur tail", "Once upon a time"); // verification lors de l'ajout
        livres.check_book("Arthur tail"); // verification lors de la creation
        assertEquals(1, livres.getLivres().size());
        assertException(IllegalArgumentException.class, ("existing title") -> livres.check_book("Arthur tail")); // nom existant
    }

    public void testLoadLivre(){//chargement livre reussit
        ILivre l = livres.getLivre("Arthur tail");
        assertFalse(l == null);

    }
    public void testLoadLivreFail(){//chargement livre echoue
        ILivre l = livres.getLivre("Arthur tail");
        assertTrue(l == null);
    }

    public void testUpdateLivre(){
        ILivre l = livres.getLivre("Arthur tail");
        l.setTitre("Arthur tail 2");
        l.setDescription("new tail of arthur");
        livres.check_book("Arthur tail 2");
        assertEquals("Arthur tail", livres.getLivre("Arthur Tail 2").getTitre());
        assertEquals("Once upon a time", livres.getLivre("Arthur Tail 2").getDescription());
    }
    public void testUpdateLivreFail(){
        livres.addLivre("Fairy tail", "tail of fairy");
        livres.check_book("Fairy tail");
        ILivre l = livres.getLivre("Arthur tail");
        l.setTitre("Fairy tail");
        l.setDescription("tail of fairy");
        assertException(IllegalArgumentException.class, ("existing title") -> livres.check_book("Fairy tail")); // nom existant
    }
    

    public void testDeleteLivre(){
        livres.removeLivre("Arthur tail");
        assertEquals(0, livres.getLivres().size());
    }
    public void testDeleteLivreFail(){
        livres.removeLivre("Arthur tail");
        assertEquals(1, livres.getLivres().size());
    }
}