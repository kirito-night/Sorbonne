import java.util.ArrayList;
import java.util.Arrays;

public class Menagerie {
    ArrayList<Animal> tab;

    public Menagerie() {
        tab = new ArrayList<Animal>();
    }

    public void ajouter(Animal a){
        tab.add(a);
    }

    @Override
    public String toString() {
        String [] list_ani = new String[tab.size()];

        for (int i  = 0 ; i < tab.size(); i++){
             list_ani[i] = tab.get(i).nom;

        }
        String s = ""; 
        for (int i  = 0 ; i < tab.size(); i++){
            s += list_ani[i]+ "     ";
        }
        return s;
    }
    public void midi(){
        for(Animal a : tab){
            a.crier();
        }
    }

    public void vieillirTous(){
        for(Animal a : tab){
            a.viellir();
        }
    }



    
   
    
    
}
