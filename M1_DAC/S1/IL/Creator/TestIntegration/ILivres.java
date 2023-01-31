package TestIntegration;

import java.util.ArrayList;
import java.util.List;

public class ILivres {

    private List<ILivre> livres = new ArrayList<ILivre>();

    void addLivre(String titre, String description){
        livres.add(new ILivre(titre, description));
    }

    ILivre getLivre(String titre){
        try{
            for (ILivre l : livres) {
                if (l.getTitre().equals(titre)) {
                    return l;
                }
            }
        }catch (Exception e){
            System.out.println("Livre introuvable");
        }
        return null;
    }
}
