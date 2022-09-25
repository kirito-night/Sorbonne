package pobj.motx.tme3.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme3.GrilleContrainte;

import static pobj.motx.tme2.test.GrillePotentielTest.testNombrePot;

public class GrilleContrainteTest2 {

	@Test
	public void testMedium() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/medium.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);
		
		GrillePotentiel g = new GrillePotentiel(grille, gut);

		GrilleContrainte gp = new GrilleContrainte(grille, gut);

		assertTrue(!gp.isDead());

		assertEquals(9, gp.getContraintes().size());

		int[] expected = { 5916, 5916, 5320, 5916, 5916, 5320 };

		testNombrePot(gp, expected);

		System.out.println("Succès test GrillePotentiel : medium.");

	}

	@Test
	public void testEasy() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/easy.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);
		
		GrilleContrainte gp = new GrilleContrainte(grille, gut);
		

		assertTrue(!gp.isDead());

		assertEquals(2, gp.getContraintes().size());

		int[] expected = { 5916, 5688 , 5916};

		testNombrePot(gp, expected);

		System.out.println("Succès test GrillePotentiel : easy.");

	}
	
	@Test
	public void testHard() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/hard.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrilleContrainte gp = new GrilleContrainte(grille, gut);

		assertTrue(!gp.isDead());

		assertEquals(25, gp.getContraintes().size());

		int[] expected = { 5916, 5916, 5916, 5916, 5185, 5916, 5916, 5916, 5916, 5185 };

		testNombrePot(gp, expected);

		System.out.println("Succès test GrillePotentiel : hard.");
	}

}
