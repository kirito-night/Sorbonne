package pobj.motx.tme3.adapt;

import pobj.motx.tme3.GrilleContrainte;
import pobj.motx.tme3.csp.IVariable;

import java.util.List;

public class DicoVariable implements IVariable {

    private int index;
    private GrilleContrainte gp;

    public DicoVariable(int index, GrilleContrainte gp) {
        this.index = index;
        this.gp = gp;
    }

    public GrilleContrainte getGrilleContrainte() {
        return gp;
    }

    public int getIndex() {
        return index;
    }

    @Override public String[] getDomain() {
        List<String> mots = gp.getMotsPot().get(index).getMots();
        return mots.toArray(new String[0]);
    }

    @Override public int getContraintesNumber() {
        return gp.getContraintesNumber(index);
    }

    @Override public String toString() {
        return "DicoVariable{" + "index=" + index + ", mots potentiel=" + gp.getMotsPot().get(index).getMots() + '}';
    }
}
