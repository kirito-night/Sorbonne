package pobj.motx.tme3.adapt;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme3.GrilleContrainte;
import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.csp.IVariable;

public class MotX implements ICSP{
	private List<IVariable> vars;
	private GrilleContrainte gc;
	
	public MotX(GrilleContrainte gc) {
		// TODO Auto-generated constructor stub
		this.gc = gc;
		vars = new ArrayList<>();
		
		for(int i = 0 ; i <gc.getMotsPot().size(); i++) {
			if(gc.getGrille().getPlaces().get(i).hasCaseVide()) {
				vars.add(new DicoVariable(i, gc));
			}
		}

	}
	
	
	
	
	@Override
	public List<IVariable> getVars() {
		// TODO Auto-generated method stub
		return vars;
	}

	@Override
	public boolean isConsistent() {
		if (gc.isDead()) {
            return false;
        }
        if (gc.getMotsPot().isEmpty()) {
            return false;
        }
        return true;
	}

	@Override
	public ICSP assign(IVariable vi, String val) {
        if (vi instanceof DicoVariable) {
            GrilleContrainte next = ((DicoVariable)vi).getGrilleContrainte().fixer(((DicoVariable)vi).getIndex(), val);
            return new MotX(next);
        }
        return null;
	}




	@Override
	public String toString() {
		return "MotX [gc=" + gc + "]";
	}
	
	
	
}
