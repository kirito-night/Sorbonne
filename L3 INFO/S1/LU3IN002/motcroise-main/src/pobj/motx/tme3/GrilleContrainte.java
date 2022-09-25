package pobj.motx.tme3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import pobj.motx.tme1.Emplacement;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme2.GrillePotentiel;

public class GrilleContrainte extends GrillePotentiel {
	
	private List<IContrainte> contraintes ;
	

	

	private void init(GrillePlaces grille, List<Emplacement> l) {
		for(int i = 0 ; i <grille.getNbHorizontal() ; i++) {
			for(int j = 0 ; j < l.get(i).size() ; j ++) {
				if(!(l.get(i).getCase(j).isVide())) {
					continue;
				}
				else {
					for(int k  = grille.getNbHorizontal() ; k < l.size() ; k++) {
						for(int m = 0 ; m < l.get(k).size() ; m++) {
							if(l.get(i).getCase(j).equals(l.get(k).getCase(m))) {
								CroixContrainte c = new CroixContrainte(i, j, k, m);
								contraintes.add(c);
							}
						}
					}
				}
			}
				
			
		}
	}
	
	
	
	public GrilleContrainte(GrillePlaces grille, Dictionnaire dicoComplet ) {
		super(grille, dicoComplet);
		contraintes = new ArrayList<>();
		List<Emplacement> l = grille.getPlaces();
		 
		
		init(grille, l);
		
		propage();
	}
	
	public GrilleContrainte(GrillePlaces grille, Dictionnaire dicoComplet , List<Dictionnaire> motPots ) {
		super(grille, dicoComplet, motPots);
		contraintes = new ArrayList<>();
		List<Emplacement> l = grille.getPlaces();
		 
		
		
		init(grille, l);
		
		propage();
	}
	
	

	public List<IContrainte> getContraintes() {
		return contraintes;
	}

	public void setContraintes(List<IContrainte> contraintes) {
		this.contraintes = contraintes;
	}
	
	
	public GrilleContrainte fixer(int m, String soluce) {
        GrillePlaces grille = this.getGrille().fixer(m, soluce);
        List<Dictionnaire> newMots = new ArrayList<>();
        for (Dictionnaire d : this.getMotsPot()) {
            newMots.add(d.copy());
        }
        newMots.get(m).fixer(soluce); 
        
        return new GrilleContrainte(grille, this.getDicoComplet(), newMots);
    }
	
	private boolean propage() {
        while (!isDead()) {
            int cpt = 0;
            for (var c : contraintes) {
                cpt += c.reduce(this);
            }
            if (cpt == 0) {
                return true;
            }
        }
        return false;
    }

	@Override
	public String toString() {
		return "GrilleContrainte [getGrille()=" + getGrille() + "]";
	}
	
	
	
}
