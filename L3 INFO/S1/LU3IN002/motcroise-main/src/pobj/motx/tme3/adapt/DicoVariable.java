package pobj.motx.tme3.adapt;

import java.util.List;

import pobj.motx.tme3.GrilleContrainte;
import pobj.motx.tme3.csp.IVariable;

public class DicoVariable implements  IVariable {
	private int index;
	GrilleContrainte gp;
	
	
	
	@Override
	public String toString() {
		return "DicoVariable [index=" + index + ", gp=" + gp + "]";
	}



	public DicoVariable(int index, GrilleContrainte gp) {
		super();
		this.index = index;
		this.gp = gp;
	}



	public List<String> getDomain(){
		return gp.getMotsPot().get(index).getMots();
	}
	
	
	public GrilleContrainte getGrilleContrainte() {
        return gp;
    }
	
	public int getIndex() {
        return index;
    }
}
