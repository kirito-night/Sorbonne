package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

public class EnsembleLettre {
	private List<Character> l = new ArrayList<>(); 
	
	public boolean contains(Character c) {
		return l.contains(c);
	}
	
	public void add(Character c) {
		if(!(l.contains(c))){
			l.add(c);
		}
	}
	
	public int size() {
		return l.size();
	}
	
	public EnsembleLettre intersect(EnsembleLettre e1) {
		EnsembleLettre res = new EnsembleLettre();
		
		for(Character c : e1.l) {
			if(this.l.contains(c)) {
				res.add(c);
			}
		}
		
		return res;
	}
	
	
}
