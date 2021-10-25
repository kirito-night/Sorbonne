package pobj.motx.tme2;

import pobj.motx.tme1.Case;
import pobj.motx.tme1.Emplacement;
import pobj.motx.tme1.Grille;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour obtenir tous les emplacements des mots dans une grille
 *
 * @author Zhenyue FU, Zhe WANG
 */
public class GrillePlaces {

    /**
     * Tous les emplacements de mots dans la grille
     */
    private List<Emplacement> places;
    /**
     * la grille qu'on va chercher
     */
    private Grille gr;
    /**
     * le nombre d'emplacements horizontaux
     */
    private int nbHorizontal;

    /**
     * Construit la classe avec la grille donnee
     *
     * @param gr la grille qu'on va rechercher des emplacements dessus
     */
    public GrillePlaces(Grille gr) {
        places = new ArrayList<>();
        this.gr = gr;
        for (int i = 0; i < gr.nbLig(); i++) {
            cherchePlaces(getLig(i));
        }
        nbHorizontal = places.size();
        for (int j = 0; j < gr.nbCol(); j++) {
            cherchePlaces(getCol(j));
        }

    }

    /**
     * obtenir une ligne de case avec un numero indique
     *
     * @param lig le numero de ligne
     * @return une liste de cases contenant tous les case dans cette ligne
     */
    private List<Case> getLig(int lig) {
        List<Case> l = new ArrayList<>();
        for (int i = 0; i < gr.nbCol(); i++) {
            l.add(gr.getCase(lig, i));
        }
        return l;
    }

    /**
     * obtenir une colonne de case avec un numero indique
     *
     * @param col le numero de colonne
     * @return une liste de cases contenant tous les case dans cette colonne
     */
    private List<Case> getCol(int col) {
        List<Case> l = new ArrayList<>();
        for (int i = 0; i < gr.nbLig(); i++) {
            l.add(gr.getCase(i, col));
        }
        return l;
    }

    /**
     * Cherche tous les places possibles dans une ligne ou colonne de cases
     *
     * @param cases la liste des cases qu'on va faire le recherche
     */
    private void cherchePlaces(List<Case> cases) {
        Emplacement e = new Emplacement();
        for (var c : cases) {
            if (c.getChar() != '*') {
                e.add(c);
            } else {
                if (e.size() >= 2) {
                    places.add(e);
                }
                e = new Emplacement();
            }
        }
        if (e.size() >= 2) {
            places.add(e);
        }
    }

    /**
     * fixer la valeur de certains mots à un candidat donné
     *
     * @param m      indice
     * @param soluce mot
     * @return une nouvelle grille où les cases constituant l’emplacement de mot d’indice m
     */
    public GrillePlaces fixer(int m, String soluce) {
        Grille g = gr.copy();
        GrillePlaces grille = new GrillePlaces(g);
        for (int i = 0; i < grille.getPlaces().get(m).size(); i++) {
            grille.getPlaces().get(m).getCase(i).setChar(soluce.charAt(i));
        }

        return grille;
    }

    /**
     * obtenir le resultat des emplacements qu'on trouve
     *
     * @return les emplacements possibles
     */
    public List<Emplacement> getPlaces() {
        return places;
    }

    /**
     * obtenir le nombre des emplacements horizontaux
     *
     * @return le nombre des emplacements horizontaux
     */
    public int getNbHorizontal() {
        return nbHorizontal;
    }

    @Override public String toString() {
        return gr.toString();
    }

}
