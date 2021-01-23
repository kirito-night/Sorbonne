public class TestMenagerie {
    public static void main(String[] args) {
        Animal a1 = new Animal_Apatte("vache",1, 4);
        Animal a2 = new Animal_Apatte("canards",1, 2);

        Animal a3 = new Animal_Apatte("millespattes",1, 4);

        Animal a4 = new Animal_SansPatte("saumon",1);
        Animal a5 = new Animal_SansPatte("boas",1);



        Menagerie m1 = new Menagerie();
        
         m1.ajouter(a1);
         m1.ajouter(a2);

         m1.ajouter(a3);

         m1.ajouter(a4);

         m1.ajouter(a5);
         System.out.println(m1.toString());
         
         for(Animal a : m1.tab){
             System.out.println(a.getNom() +" : age  " +a.getAge());
         }

         m1.midi();
         m1.vieillirTous();

         for(Animal a : m1.tab){
            System.out.println(a.getNom() +" : age  " +a.getAge());
        }
       
    }
    
}
