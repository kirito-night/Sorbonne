package Exo54;
import java.util.ArrayList;
public class Mer {
   private Arraylist<Submarine> tab; 

    public Mer(){
        tab = new Arraylist<Submarine>();
    }

    public void ajoutElem(Submarine s1){
        tab.add(s1);

    }

    public void deplacer(){
        for (Submarine s : tab){
            s.seDeplarcer();
        }
    }
}
