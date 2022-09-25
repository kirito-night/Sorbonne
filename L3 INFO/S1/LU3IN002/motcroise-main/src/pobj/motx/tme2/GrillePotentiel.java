package pobj.motx.tme2;
import java.util.*;

import pobj.motx.tme1.Case;
import pobj.motx.tme1.Emplacement;
public class GrillePotentiel {
	private GrillePlaces grille;
	private Dictionnaire dicoComplet;
	
	private boolean dead = false;
	
	private List<Dictionnaire> motsPot;
	
	
	public GrillePotentiel(GrillePlaces grille , Dictionnaire dicoComplet) {
		this.grille = grille; 
		this.dicoComplet = dicoComplet;
		motsPot = new ArrayList<>();
		
		List<Emplacement> l = grille.getPlaces();
		for( int i = 0 ; i < l.size() ; i++) {
			int tailleEmplacement =  l.get(i).size();
			Dictionnaire dicoTmp = dicoComplet.copy();
			dicoTmp.filtreLongueur(tailleEmplacement);
			motsPot.add(i, dicoTmp);
		}
		
		for(int j = 0 ; j < l.size() ; j++) {
			Emplacement e = l.get(j);
			for(int c = 0 ; c < e.size() ; c++) {
				if(!(e.getCase(c).isVide())){
					char lettre = e.getCase(c).getChar();
					motsPot.get(j).filtreParLettre(lettre, c);
				}
			}
		}
	}
	
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet, List<Dictionnaire> motsPot) {
        this.grille = grille;
        this.dicoComplet = dicoComplet;
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
	 * nouvelle version de isDead , car cela va etre verifier dans le constructeur
	 * @return
	 */
	/*
	public boolean isDead() {
		return this.dead;
	}*/
	
	/*anciennce version isDead*/
	public boolean isDead() {
		for(Dictionnaire d : motsPot) {
			if (d.size() == 0) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	public void setDead(boolean set) {
        dead = set;
    }
	public List<Dictionnaire> getMotsPot() {
		return motsPot;
	}
	
	
	/**
	 * ancienne version de fixer 
	public GrillePotentiel fixer(int m , String soluce) {
		GrillePotentiel grp= new GrillePotentiel(this.grille.fixer(m, soluce), this.dicoComplet);
		return grp;
	}*/
	
	/**
	 * nouvelle version de fixer
	 * @param m
	 * @param soluce
	 * @return
	 */
	
	public GrillePotentiel fixer(int m, String soluce) {
        GrillePlaces grille = this.grille.fixer(m, soluce);
        List<Dictionnaire> newMots = new ArrayList<>();
        
        for (Dictionnaire d : motsPot) {
            newMots.add(d.copy());
        }
        
        newMots.get(m).fixer(soluce);
        return new GrillePotentiel(grille, dicoComplet, newMots);
    }
	

	public GrillePlaces getGrille() {
		return grille;
	}

	public Dictionnaire getDicoComplet() {
		return dicoComplet;
	}

	
	
	
	
}
