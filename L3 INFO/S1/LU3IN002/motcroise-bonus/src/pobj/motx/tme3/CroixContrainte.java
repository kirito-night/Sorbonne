package pobj.motx.tme3;

import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.EnsembleLettre;
import pobj.motx.tme2.GrillePotentiel;

public class CroixContrainte implements IContrainte {
    private int m1, c1, m2, c2;

    public CroixContrainte(int m1, int c1, int m2, int c2) {
        this.m1 = m1;
        this.c1 = c1;
        this.m2 = m2;
        this.c2 = c2;
    }

    @Override public int reduce(GrillePotentiel grille) {
        Dictionnaire d1 = grille.getMotsPot().get(m1);
        EnsembleLettre l1 = d1.charAt(c1);

        Dictionnaire d2 = grille.getMotsPot().get(m2);
        EnsembleLettre l2 = d2.charAt(c2);

        EnsembleLettre s = EnsembleLettre.intersection(l1, l2);
        int cpt = 0;
        if (l1.size() > s.size()) {
            cpt += d1.filtreParLettres(s, c1);
            if (d1.size() == 0) {
                grille.setDead();
            }
        }
        if (l2.size() > s.size()) {
            cpt += d2.filtreParLettres(s, c2);
            if (d2.size() == 0) {
                grille.setDead();
            }
        }

        return cpt;

    }

    @Override public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CroixContrainte other = (CroixContrainte)obj;
        if (c1 != other.c1) {
            return false;
        }
        if (c2 != other.c2) {
            return false;
        }
        if (m1 != other.m1) {
            return false;
        }
        return m2 == other.m2;
    }
}
