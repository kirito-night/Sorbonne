package pobj.motx.tme2.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme2.GrillePotentiel;

public class GrillePotentielTest3 {

	@Test
	public void testMakeEasy2() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		// grille 6x5, mots sans croisement
		Grille gr = GrilleLoader.loadGrille("data/easy.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);

		assertTrue(gp.getMotsPot().get(2).size() > 1);

		GrillePotentiel gp2 = gp.fixer(2, "chats");

		assertTrue(gp.getMotsPot().get(2).size() > 1);

		int[] expected = { 245, 302, 1 };
		GrillePotentielTest.testNombrePot(gp2, expected);

		// System.out.println(gp2.getGrillePlaces().getGrille());

		System.out.println("Succès test GrillePotentiel : make easy 2.");
	}

}
