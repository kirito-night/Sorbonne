package pobj.motx.tme3;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.*;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.EnsembleLettre;
import pobj.motx.tme2.GrillePotentiel;

public class CroixContrainte implements IContrainte {
	int m1, m2, c1,c2;

	public CroixContrainte(int m1, int c1, int m2, int c2) {
		super();
		this.m1 = m1;
		this.m2 = m2;
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public int reduce (GrillePotentiel grille) {
		EnsembleLettre l1 = new EnsembleLettre();
		EnsembleLettre l2 = new EnsembleLettre();
		int nbMotsupp = 0;
		
		List<Dictionnaire> l_dico = grille.getMotsPot();
		Dictionnaire d1 =  l_dico.get(m1);
		Dictionnaire d2 =  l_dico.get(m2);
		
		for(int i = 0 ; i < d1.size() ; i++) {
			l1.add(d1.get(i).charAt(c1));
		}
		
		
		for(int j = 0 ; j < d2.size() ; j++) {
			l2.add(d2.get(j).charAt(c2));
		}
		
		
		EnsembleLettre s = l1.intersect(l2);
		
		if(l1.size() > s.size()) {
			nbMotsupp += d1.filtreParEnsebleLettre(s, c1);
			
		}
		
		
		if(l2.size() > s.size()) {
			nbMotsupp += d2.filtreParEnsebleLettre(s, c2);
		}
		
		return nbMotsupp;
		
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == this) {
			return true;
		}
		if(!(obj instanceof CroixContrainte)) {
			return false;
		}
		CroixContrainte other = (CroixContrainte)obj;
		return other.m1 == this.m1 && other.m2 == this.m2 && other.c1 == this.c1 && other.c2 == this.c2;
	}
	
	
	
	
}
