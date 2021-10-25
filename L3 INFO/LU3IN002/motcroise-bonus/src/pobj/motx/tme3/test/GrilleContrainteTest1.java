package pobj.motx.tme3.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme3.CroixContrainte;
import pobj.motx.tme3.GrilleContrainte;
import pobj.motx.tme3.IContrainte;

public class GrilleContrainteTest1 {

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

		

		int[][] expected = { {0,2,2,0},
				{1,2,2,4}};

		checkContraintes(gp, expected);

		System.out.println("Succès test GrillePotentiel : easy.");

	}

	private void checkContraintes(GrilleContrainte gp, int[][] expected) {
		List<IContrainte> exp = new ArrayList<>();
		for (int []e : expected) {
			// (m1,c1,m2,c2) dans cet ordre.
			exp.add(new CroixContrainte(e[0], e[1], e[2], e[3]));
		}
		
		assertEquals(expected.length, gp.getContraintes().size());
		
		for (IContrainte c : gp.getContraintes()) {
			// penser à définir public boolean equals(Object o) dans CroixContrainte.
			assertTrue(exp.contains(c));
		}
	}
	
	@Test
	public void testMedium() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/medium.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrilleContrainte gp = new GrilleContrainte(grille, gut);

		assertTrue(!gp.isDead());

//		for (IContrainte c: gp.getContraintes()) {
//			System.out.println(c+",");
//		}
		int[][] expected = { {0,0,3,0},
				{0,2,4,0},
				{0,4,5,0},
				{1,0,3,2},
				{1,2,4,2},
				{1,4,5,2},
				{2,0,3,4},
				{2,2,4,4},
				{2,4,5,4}};

		checkContraintes(gp, expected);

		System.out.println("Succès test GrillePotentiel : medium.");

	}

	
	@Test
	public void testEnonce() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/enonce.grl");

		assertEquals(16, gr.nbCol());
		assertEquals(12, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrilleContrainte gp = new GrilleContrainte(grille, gut);

		assertTrue(!gp.isDead());

		int[][] expected = { {0,1,8,0},
				{1,1,11,1},
				{2,0,8,3},
				{2,2,9,1},
				{2,10,11,3},
				{3,4,11,5},
				{4,0,7,4},
				{4,2,8,6},
				{4,4,9,4},
				{5,0,9,5},
				{5,6,10,0},
				{5,8,11,7},
				{6,4,10,2}};

		checkContraintes(gp, expected);

		System.out.println("Succès test GrillePotentiel : medium.");

	}

}
