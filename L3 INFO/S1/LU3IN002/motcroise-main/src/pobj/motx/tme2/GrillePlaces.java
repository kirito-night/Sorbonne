package pobj.motx.tme2;
import pobj.motx.tme1.*;
import java.util.*;
public class GrillePlaces {
	
	private Grille grille;
	private List<Emplacement> places;
	private  int nbHori; 
	
	private List<Case> getLig(int lig){
		List<Case> list = new ArrayList<>();
		for(int j = 0 ; j< grille.nbCol() ; j++) {
			Case c = grille.getCase(lig, j);
			list.add(c);
		}
		return list;
	}
	
	private List<Case> getCol(int col){
		List<Case> list = new ArrayList<>();
		for(int i = 0 ; i< grille.nbLig() ; i++) {
			Case c = grille.getCase(i, col);
			list.add(c);
		}
		return list;
	}
	
	private void cherchePlaces (List<Case> cases) {
		
		for(int i = 0 ; i < cases.size(); i++) {
			if (cases.get(i).getChar() != '*' && i+1 < cases.size() && cases.get(i+1).getChar() != '*') {
				Emplacement emp = new Emplacement();
				emp.add(cases.get(i));
				emp.add(cases.get(i+1));
				int k = i + 2;
				while (k < cases.size() && cases.get(k).getChar() != '*' ) {
				
					emp.add(cases.get(k));
					
					k++;
				}
				i = k;
				places.add(emp);
				
				
			}
		}
	}
	
	
	
	
	public GrillePlaces (Grille grille) {
		this.grille = grille;
		places = new ArrayList<>();
		
		/* pour les horizontale*/
		
		
		for(int i = 0 ; i < grille.nbLig() ; i++) {
			List<Case> lhori = getLig(i);
			cherchePlaces(lhori);
		}
		nbHori = places.size();
		
		
		
		for(int j = 0 ; j < grille.nbCol() ; j++) {
			List<Case> lverti =  getCol(j);
			cherchePlaces(lverti);
		}
	}
	
	
	public List<Emplacement> getPlaces(){
		return places;
	}
	
	public int getNbHorizontal() {
		return nbHori;
	}
	
	public GrillePlaces fixer(int m , String soluce) {
		Grille grl = this.grille.copy();
		GrillePlaces g  = new GrillePlaces(grl);
		Emplacement e = g.getPlaces().get(m);
		for(int i = 0 ; i< soluce.length() ; i++) {
			char c = soluce.charAt(i);
			e.getCase(i).setChar(c);
		}
		
		
		return g;
	}

	@Override
	public String toString() {
		return "GrillePlaces [grille=" + grille + "]";
	}
	
	
	
}
