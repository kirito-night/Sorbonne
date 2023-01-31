package TestIntegration;

import java.util.ArrayList;
import java.util.List;

public class ILivre {
    
    private String titre;
    private String description;
    private List<IObjet> objets;
    
    public ILivre(String titre, String description) {
        this.titre = titre;
        this.description = description;
        this.objets = new ArrayList<IObjet>();
    }
    
    public String getTitre() {
        return titre;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public List<IObjet> getObjets() {
        return objets;
    }

    public void addObjet(String nom, String description) {
        IObjet objet = new IObjet(nom, description);
        this.objets.add(objet);
    }

    public void removeObjet(String nom) {
        IObjet objet = getObjet(nom);
        if (objet != null) {
            this.objets.remove(objet);
        }
    }

    public IObjet getObjet(String nom) {
        for (IObjet objet : objets) {
            if (objet.getNom().equals(nom)) {
                return objet;
            }
        }
        return null;
    }
}