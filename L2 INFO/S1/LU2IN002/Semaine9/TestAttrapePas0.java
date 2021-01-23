public class TestAttrapePas0 {
    public static void main(String[] args) {
        int [] tab = {1,2,3,4,5};
        try{
            for (int i = 0 ; i < 15 ; i++){
                System.out.println(tab[i] + " ");
    
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Exception : depassement des bornes position" + e.getMessage());



        }
        
        System.out.println("fin");


    /*
    1)
    on aura une erreur de depassement e capacite  du tableau tab 
    en effet , tan est declare et initialise comme un tableau de 5 entiers
    mais dans la boucle for() le programme essaye d'acceder jusqu'au 15 ieme element
    qui ne sont pas des inidices valide pour tab

    exception de depassement de capacite exception in thread  "main" java.lang.ArrayIndexOutOfBounds

    */
    
    }
}