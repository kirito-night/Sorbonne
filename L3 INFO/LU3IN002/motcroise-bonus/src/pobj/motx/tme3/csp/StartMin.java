package pobj.motx.tme3.csp;

public class StartMin implements IChoixVar {
    @Override public IVariable chooseVar(ICSP problem) {
        IVariable choose = problem.getVars().get(0);
        int shortest = choose.getDomain().length;
        for (IVariable var : problem.getVars()) {
            if (shortest > var.getDomain().length) {
                shortest = var.getDomain().length;
                choose = var;
            }
        }
        return choose;
    }
}
