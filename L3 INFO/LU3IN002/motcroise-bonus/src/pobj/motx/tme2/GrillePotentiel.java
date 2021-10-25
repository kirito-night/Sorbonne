package pobj.motx.tme2;

import pobj.motx.tme1.Emplacement;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour correspondre les mots existes avec le dictionnaire
 *
 * @author Zhenyue FU, Zhe WANG
 */
public class GrillePotentiel {

    /**
     * la grille avec laquelle on a trouve les emplacements possibles
     */
    private GrillePlaces grille;
    /**
     * le dictionnaire avec lequel on va rechercher
     */
    private Dictionnaire dic;
    /**
     * les resultats apres rechercher tous les emplacements dans le dictionnaire
     */
    private List<Dictionnaire> motsPot;
    /**
     * si il existe des emplacements ayant un domaine potentiel vide
     */
    private boolean dead = false;

    /**
     * Constuit la grille potentiele avec la grille, le resultat dans le
     * dictionnaire
     *
     * @param grille      la grille avec laquelle on a trouve les emplacements
     *                    possibles
     * @param dicoComplet le dictionnaire avec lequel on va rechercher
     */
    public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
        this.grille = grille;
        this.dic = dicoComplet;
        motsPot = new ArrayList<>();
        for (var e : this.grille.getPlaces()) {
            Dictionnaire d = dic.copy();
            d.filtreLongueur(e.size());
            if (d.size() == 0) {
                dead = true;
                break;
            }
            for (int i = 0; i < e.size(); i++) {
                if (e.getCase(i).getChar() != ' ') {
                    d.filtreParLettre(e.getCase(i).getChar(), i);
                }
            }
            motsPot.add(d);
        }
    }

    public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet, List<Dictionnaire> motsPot) {
        this.grille = grille;
        this.dic = dicoComplet;
        this.motsPot = motsPot;
        List<Emplacement> places = this.grille.getPlaces();
        for (int i = 0; i < places.size(); i++) {
            motsPot.get(i).filtreLongueur(places.get(i).size());
            if (motsPot.get(i).size() == 0) {
                dead = true;
                break;
            }
            for (int j = 0; j < places.get(i).size(); j++) {
                char c = places.get(i).getCase(j).getChar();
                if (c != ' ') {
                    motsPot.get(i).filtreParLettre(c, j);
                }
            }
        }
    }

    /**
     * verifier s'il n'y a aucun possible
     *
     * @return vrai si et seulement si au moins un emplacement a un domaine
     * potentiel vide.
     */
    public boolean isDead() {
        return dead;
    }

    public void setDead() {
        dead = true;
    }

    public GrillePotentiel fixer(int m, String soluce) {
        GrillePlaces grille = this.grille.fixer(m, soluce);
        List<Dictionnaire> newMots = new ArrayList<>();
        for (Dictionnaire d : motsPot) {
            newMots.add(d.copy());
        }
        newMots.get(m).fixer(soluce);
        return new GrillePotentiel(grille, dic, newMots);
    }

    public GrillePlaces getGrille() {
        return grille;
    }

    public Dictionnaire getDic() {
        return dic;
    }

    /**
     * obtenir le resultat qu'on a trouve
     *
     * @return le resultat
     */
    public List<Dictionnaire> getMotsPot() {
        return motsPot;
    }

}
