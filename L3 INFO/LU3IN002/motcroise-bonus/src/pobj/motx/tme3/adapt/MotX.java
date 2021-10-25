package pobj.motx.tme3.adapt;

import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme3.GrilleContrainte;
import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.csp.IVariable;

import java.util.ArrayList;
import java.util.List;

public class MotX implements ICSP {
    private GrillePotentiel gp;
    private List<IVariable> vars = new ArrayList<>();

    public MotX(GrillePotentiel gp) {
        GrilleContrainte gc = new GrilleContrainte(gp.getGrille(), gp.getDic());
        this.gp = gc;
        for (int i = 0; i < gc.getMotsPot().size(); i++) {
            if (gp.getGrille().getPlaces().get(i).hasCaseVide()) {
                vars.add(new DicoVariable(i, gc));
            }
        }
    }

    @Override public List<IVariable> getVars() {
        return vars;
    }

    @Override public boolean isConsistent() {
        if (gp.isDead()) {
            return false;
        }
        if (gp.getMotsPot().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override public ICSP assign(IVariable vi, String val) {
        if (vi instanceof DicoVariable) {
            GrilleContrainte next = ((DicoVariable)vi).getGrilleContrainte().fixer(((DicoVariable)vi).getIndex(), val);
            return new MotX(next);
        }
        return null;
    }

    @Override public String toString() {
        return gp.getGrille().toString();
    }
}
