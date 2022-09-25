package pobj.motx.tme2.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme2.GrillePotentiel;

public class GrillePotentielTest {

	public static void testNombrePot(GrillePotentiel gp, int[] expected) {
		assertEquals(expected.length, gp.getMotsPot().size());
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], gp.getMotsPot().get(i).size());
		}
		
	}

	@Test
	public void testSplit() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		// grille 6x5, mots sans croisement
		Grille gr = GrilleLoader.loadGrille("data/split.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(6, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);

		assertTrue(!gp.isDead());

		// Ce bloc permet de générer des valeurs attendues pour d'autres tests
		// System.out.print("{");
		// for (int i = 0 ; i < gp.getMotsPot().size() ; i++) {
		// System.out.print(gp.getMotsPot().get(i).size());
		// if (i < gp.getMotsPot().size()-1) {
		// System.out.print(",");
		// }
		// }
		// System.out.println("}");

		int[] expected = { 5916, 427, 81, 81 };

		testNombrePot(gp, expected);

		System.out.println("Succès test GrillePotentiel : split.");
	}

}
