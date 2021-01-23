package Entierborne;
public class TestEntierBorne {
    public static void main(String[] args){
        try {
            String s = Clavier.saisirLigne("veuillez saisir un entier");
            int i1 = Integer.parseInt(s);
            System.out.println("l'entier saisie est : " + i1);

            EntierBorne b1 = new EntierBorne(i1);
            //System.out.println(b1.getVal());


            String s1 = Clavier.saisirLigne("veuillez saisir un entier");
            int i2 = Integer.parseInt(s1);
            System.out.println("l'entier saisie est : " + i2);
           
            EntierBorne b2 = new EntierBorne(i2);
            //System.out.println(b1.getVal());
            
            EntierBorne b3 = b1.somme(b2);
            System.out.println("leur somme est : " + b3.getVal());
            

            String s2 = Clavier.saisirLigne("veuillez saisir un entier");
            int i3 = Integer.parseInt(s2);
            System.out.println("l'entier saisie est : " + i3 + " on calcule son factorielle");

            EntierBorne b4 = new EntierBorne(i3);
            EntierBorne b5 = b4.factorielle();
            System.out.println("son factorielle est : " + b5.getVal());
            
            EntierBorne b6 = b4.divPar(b3);
            System.out.println("la divisiton de " + b4.getVal() + "avec "+ b3.getVal()+ "est de : " +b6.getVal());

            
        }catch (NumberFormatException e){
            System.out.println("Le saisie n'est pas entier " + e.getMessage());  
        }
        catch(HorsBornesException e){
            System.out.println(e.getMessage());

        }
        catch(DivisionParZeroException e){
            System.out.println(e.getMessage());
        }
    


       
    }
}
