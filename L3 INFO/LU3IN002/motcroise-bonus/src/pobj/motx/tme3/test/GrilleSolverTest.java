package pobj.motx.tme3.test;

import org.junit.Test;
import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme3.GrilleContrainte;
import pobj.motx.tme3.adapt.MotX;
import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.ICSP;

public class GrilleSolverTest {

    @Test public void testHard() {
        Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
        Grille gr = GrilleLoader.loadGrille("data/easy2.grl");

        System.out.println(gr);

        GrillePlaces grille = new GrillePlaces(gr);
        GrilleContrainte gp = new GrilleContrainte(grille, gut);

        ICSP problem = new MotX(gp);
        CSPSolver solver = new CSPSolver();

        long timestamp = System.currentTimeMillis();
        ICSP solution = solver.solve(problem);

        System.out.println(
            "Solution \n" + solution + " \nCalculée en " + (System.currentTimeMillis() - timestamp) + " ms ");
    }

}
