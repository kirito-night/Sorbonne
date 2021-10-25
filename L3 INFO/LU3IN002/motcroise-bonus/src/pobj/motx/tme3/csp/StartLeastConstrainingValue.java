package pobj.motx.tme3.csp;

public class StartLeastConstrainingValue implements IChoixVar {
    @Override public IVariable chooseVar(ICSP problem) {
        IVariable choose = problem.getVars().get(0);
        int leastConstraining = choose.getContraintesNumber();
        for (IVariable var : problem.getVars()) {
            if (leastConstraining > var.getContraintesNumber()) {
                leastConstraining = var.getContraintesNumber();
                choose = var;
            }
        }
        return choose;
    }
}
