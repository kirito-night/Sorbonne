package pobj.motx.tme3;

import pobj.motx.tme2.GrillePotentiel;

public interface IContrainte {

    /**
     * De manière générale, une contrainte restreint le domaine potentiel des emplacements de mot, ce
     * qui réduira le nombre d’affectations de mots à tester. Nous pouvons mesurer son effet en regardant
     * combien de mots au total elle a éliminé.
     *
     * @param grille GrillePotentiel en modifiant
     * @return le nombre total de mots filtrés par son action
     */
    int reduce(GrillePotentiel grille);
}
