package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour identifier les emplacements des mots dans la grille
 *
 * @author Zhe WANG
 */
public class Emplacement {

    /**
     * les cases pour stocker les caractere de mot
     */
    private List<Case> cases;

    /**
     * Construit un emplacement d'un mot
     */
    public Emplacement() {
        cases = new ArrayList<Case>();
    }

    /**
     * ajouter une nouvelle case dans la liste
     *
     * @param e la case suivante de ce mot
     */
    public void add(Case e) {
        cases.add(e);
    }

    /**
     * obtenir le nombre de caracteres de ce mot
     *
     * @return le nombre de cases
     */
    public int size() {
        return cases.size();
    }

    /**
     * Obtenir la case de coordonnees indiquees
     *
     * @param i le numero de la case
     * @return la case qu'on recherche
     */
    public Case getCase(int i) {
        return i < cases.size() ? cases.get(i) : null;
    }

    public boolean hasCaseVide() {
        for (Case c : cases) {
            if (c.isVide()) {
                return true;
            }
        }
        return false;
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var c : cases) {
            sb.append(c.getChar());
        }
        return sb.toString();

    }

}
