package pobj.motx.tme3.csp;

public class StartFirst implements IChoixVar {

    @Override public IVariable chooseVar(ICSP problem) {
        return problem.getVars().get(0);
    }
}
