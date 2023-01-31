package TestIntegration;

import java.beans.Transient;

public class TestSection {
  
    ILivre l = ComposantFactory.createLivreBouchon("Arthur tail", "un livre");
    IObjet o = l.createObjet("bannane","description of banane");

    @Test 
    public void testCreateSection(){
        
        l.addSection("s1", o,2, "arthur a trouve son epee");
        l.check_section("s1");
        assertEquals(1, l.getSections().size());
        assertEquals("s1", l.getSection("s1").getTitre());
        assertEquals("banane", l.getSection("s1").getCondition().getNom());
        assertEquals(2, l..getSection("s1").getCondition().getQuantite());
        assertEquals("arthur a trouve son epee", l.getSection("s1").getDescription());
    }

    @Test
    public void testCreateSectionFail(){
        l.addSection("s1", o ,2, "arthur a trouve son epee");
        l.check_section("s1");
        assertEquals(1, l.getSections().size());
        assertException(IllegalArgumentException.class, ("existing title") -> l.check_section("s1")); // nom existant
    }



    @Test
    public void testUpdateSection(){ //update section reussit 
        ISection s = l.getSection("s1");
    
        s.setTitre("s2");
        s.setCondition(o, 3);
        s.setDescription("arthur a trouve une pomme");
        l.check_section("s2");
        assertEquals("s2", l.getSection("s2").getTitre());
        assertEquals("pomme", l.getSection("s2").getCondition().getNom());
        assertEquals(3, l.getSection("s2").getCondition().getQuantite());
        assertEquals("arthur a trouve une pomme", l.getSection("s2").getDescription());
    }

    @Test
    public void testUpdateSectionFail(){ //update section echoue
        l.addSection("s2", "pomme", 3, "arthur a trouve une pomme");
        l.check_section("s2");
        ISection s = l.getSection("s1");
        s.setTitre("s2");
        s.setCondition(o, 3);
        s.setDescription("arthur a trouve une pomme");
        assertException(IllegalArgumentException.class, ("existing title") -> l.check_section("s2")); // nom existant
    }

    @Test
    public void testDeleteSectionFail(){
        l.removeSection("s1");
        assertEquals(1, l.getSections().size());
    }
    

    @Test
    public void testDeleteSection(){
        l.removeSection("s1");
        assertEquals(0, l.getSections().size());
    }


    
    


}

