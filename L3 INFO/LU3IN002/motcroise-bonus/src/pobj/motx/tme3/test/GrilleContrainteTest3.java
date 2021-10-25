package pobj.motx.tme3.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme3.GrilleContrainte;

import static pobj.motx.tme2.test.GrillePotentielTest.testNombrePot;

public class GrilleContrainteTest3 {

	@Test
	public void testLarge() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large.grl");

		assertEquals(20, gr.nbCol());
		assertEquals(20, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrilleContrainte gp = new GrilleContrainte(grille, gut);

		assertTrue(!gp.isDead());

		assertEquals(274, gp.getContraintes().size());

		int[] expected = { 13757, 5762, 13756, 5850, 1665, 81, 5324, 45881, 25035, 80, 5896, 35900, 1779, 81, 5815, 427,
				81, 12848, 427, 24238, 81, 22995, 5768, 427, 1763, 413, 1762, 13676, 5915, 81, 424, 427, 13945, 25577,
				420, 24343, 427, 425, 13694, 81, 13642, 43454, 5829, 81, 80, 34749, 427, 13262, 427, 418, 11973, 24819,
				81, 1715, 81, 5757, 425, 24511, 79, 81, 23777, 39932, 40978, 13945, 1756, 1791, 424, 1807, 38958, 367,
				1807, 13643, 1771, 13847, 12968, 41979, 81, 81, 81, 33153, 81, 66, 80, 13314, 80, 1801, 13474, 5659, 81,
				25343, 13677, 421, 81, 44354, 1805, 427, 5769, 5473, 424, 1782, 427, 9849, 13210, 81, 81, 42704, 13573,
				13429, 400, 426, 40832, 427, 81, 36642, 5828, 81, 25126, 1769, 81, 23195, 29819, 273, 400, 4023 };

		testNombrePot(gp, expected);

		System.out.println("Succès test GrillePotentiel : large.");
	}

	@Test
	public void testLarge3() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large3.grl");

		assertEquals(20, gr.nbCol());
		assertEquals(20, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrilleContrainte gp = new GrilleContrainte(grille, gut);

		assertTrue(!gp.isDead());

		assertEquals(19, gp.getContraintes().size());

		assertEquals(124, gp.getMotsPot().size());
		int[] expected = { 746, 1, 1, 1081, 2, 1, 1, 44, 1, 1, 312, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				2122, 1, 1, 1, 1515, 1, 1, 1419, 1, 1, 27, 1, 1, 1, 1, 1, 1, 1, 1, 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

		testNombrePot(gp, expected);

		System.out.println("Succès test GrillePotentiel : large3.");
	}

	@Test
	public void testLarge2() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large2.grl");

		assertEquals(20, gr.nbCol());
		assertEquals(20, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrilleContrainte gp = new GrilleContrainte(grille, gut);

		assertTrue(!gp.isDead());

		assertEquals(72, gp.getContraintes().size());

		int[] expected = { 13757, 1028, 1, 5850, 924, 5, 1, 19653, 1, 1, 5896, 1, 1, 81, 1, 1, 1, 11635, 4, 1, 79, 24,
				4, 410, 1103, 19, 1, 11012, 12, 1, 1, 424, 1, 1, 334, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 13945, 1756, 1, 1, 1807, 202, 1, 1807, 445, 1, 13847, 1, 41977, 1, 1,
				1, 464, 1, 1, 80, 1, 1, 66, 20, 1, 1, 33, 1, 1, 81, 1, 1, 1, 17, 8, 69, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 11, 1 };

		testNombrePot(gp, expected);

		System.out.println("Succès test GrillePotentiel : large2.");

	}

	@Test
	public void testLarge4() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large4.grl");

		assertEquals(20, gr.nbCol());
		assertEquals(20, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrilleContrainte gp = new GrilleContrainte(grille, gut);

		// System.out.println(gp);

		assertTrue(gp.isDead());

		assertEquals(64, gp.getContraintes().size());

		assertEquals(124, gp.getMotsPot().size());

		System.out.println("Succès test GrillePotentiel : large 4.");

	}
}
