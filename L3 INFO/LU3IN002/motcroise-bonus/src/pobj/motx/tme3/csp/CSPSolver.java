package pobj.motx.tme3.csp;

public class CSPSolver {
    private IChoixVar startVar;
    private IChoixValeur startValeur;

    public IChoixVar getStartVar() {
        return startVar;
    }

    public void setStartVar(IChoixVar startVar) {
        this.startVar = startVar;
    }

    public void setStartValeur(IChoixValeur startValeur) {
        this.startValeur = startValeur;
    }

    public ICSP solve(ICSP problem) {
        System.out.println("Solve : \n" + problem);
        // Condition terminale : succès
        if (problem.getVars().isEmpty()) {
            System.out.println("Problème résolu.");
            return problem;
        }
        // condition terminale : échec sur cette branche
        if (!problem.isConsistent()) {
            System.out.println("Problème invalide.");
            return problem;
        } else {
            System.out.println("Problème valide.");
        }
        // On choisit une variable arbitraire, ici la première
        // On est garantis que ! getVars().isEmpty(), testé au dessus
        setStartVar(new StartMin());
        IVariable vi = getStartVar().chooseVar(problem);
        setStartValeur(new LeastFrequencyValeur());

        ICSP next = null;
        // On est garantis que toute variable a un domaine non nul
        for (String val : startValeur.orderValues(problem, vi)) {
            System.out.println("Fixe var :" + vi + " à " + val);
            next = problem.assign(vi, val);
            next = solve(next);
            if (next.isConsistent()) {
                return next;
            } else {
                System.out.println("Essai valeur suivante.");
            }
        }
        System.out.println("Backtrack sur variable " + vi);
        return next;
    }

}
