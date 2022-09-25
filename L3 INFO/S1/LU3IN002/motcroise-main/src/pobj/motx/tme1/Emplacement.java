package pobj.motx.tme1;
import java.util.*;

/**
 * Classe de representation de Emplacement dans une mot grille 
 * @author 28600693
 *
 */

public class Emplacement {
	/**
	 *  les cases contigus de la grille qui composent l’emplacement du mot
	 */
	private List<Case> cases;
	
	/**
	 * @param cases les cases contigus de la grille
	 */
	
	public Emplacement() {
		cases = new  ArrayList<>();
	}
	
	public void add(Case e) {
		cases.add(e);
	}
	
	public int size() {
		return cases.size();
		
	}
	
	public Case getCase(int i) {
		return cases.get(i);
	}
	
	@Override
	public String toString() {
		String str = "";
		for(Case c : cases) {
			str += c.getChar();
		}
		return str;
	}
	
	public boolean hasCaseVide() {
		for(Case c : cases) {
			if(c.isVide()) {
				return true;
			}
		}
		return false;
	}
}
