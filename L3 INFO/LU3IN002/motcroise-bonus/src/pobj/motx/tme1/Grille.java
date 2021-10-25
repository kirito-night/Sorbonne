package pobj.motx.tme1;

/**
 * Classe de grille qui est constitue d'une matrice de cases
 *
 * @author Zhe WANG
 */
public class Grille {

    /**
     * les cases
     */
    private Case[][] cases;

    /**
     * Construit une grille avec les tailles donnees
     *
     * @param hauteur hauteur de grille
     * @param largeur largeur de grille
     */
    public Grille(int hauteur, int largeur) {
        cases = new Case[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                cases[i][j] = new Case(i, j, ' ');
            }
        }
    }

    /**
     * Recherche la case de coordonnees donnees
     *
     * @param lig nombre de lignes de la grille
     * @param col nombre de colonnes de la grille
     * @return case indiquee
     */
    public Case getCase(int lig, int col) {
        if (lig >= 0 && lig < cases.length && col >= 0 && col < cases[0].length) {
            return cases[lig][col];
        }
        return null;
    }

    @Override public String toString() {
        return GrilleLoader.serialize(this, false);
    }

    /**
     * obtenir le nombre de lignes
     *
     * @return nombre de lignes
     */
    public int nbLig() {
        return cases.length;
    }

    /**
     * obtenir le nombre de colonnes
     *
     * @return nombre de colonnes
     */
    public int nbCol() {
        return cases[0].length;
    }

    /**
     * Copier coller la grille dans une nouvelle grille
     *
     * @return la nouvelle grille
     */
    public Grille copy() {
        Grille g = new Grille(this.cases.length, this.cases[0].length);
        for (int i = 0; i < this.cases.length; i++) {
            for (int j = 0; j < this.cases[0].length; j++) {
                g.cases[i][j].setChar(cases[i][j].getChar());
            }
        }
        return g;
    }

}
