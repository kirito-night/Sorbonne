package pobj.motx.tme2.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme2.GrillePlaces;

public class GrillePlacesTest {

	@Test
	public void testGrilleEasy() {
		// 3 mots
		Grille gr = GrilleLoader.loadGrille("data/easy.grl");

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		assertEquals(2, grille.getNbHorizontal());
		assertEquals(3, grille.getPlaces().size());

		// Ce bloc génère les valeurs attendues pour les tailles de mots.
		// System.out.print("{");
		// for (int i = 0 ; i < grille.getMots().size() ; i++) {
		// System.out.print(grille.getMots().get(i).size());
		// if (i < grille.getMots().size()-1) {
		// System.out.print(",");
		// }
		// }
		// System.out.println("}");

		int[] expected = { 5, 5, 5 };
		assertEquals(expected.length, grille.getPlaces().size());

		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], grille.getPlaces().get(i).size());
		}
		System.out.println("Succès tests sur GrillePlaces : easy.grl");
	}

	@Test
	public void testGrilleEnonce() {
		Grille gr = GrilleLoader.loadGrille("data/enonce.grl");

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		assertEquals(7, grille.getNbHorizontal());
		assertEquals(12, grille.getPlaces().size());

		int[] expected = { 9, 5, 14, 8, 5, 9, 10, 10, 10, 6, 4, 8 };
		assertEquals(expected.length, grille.getPlaces().size());

		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], grille.getPlaces().get(i).size());
		}
		System.out.println("Succès tests sur GrillePlaces : enonce.grl");
	}

	@Test
	public void testGrilleEasy2() {
		// avec des lettres placees
		Grille gr = GrilleLoader.loadGrille("data/easy2.grl");

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		assertEquals(2, grille.getNbHorizontal());
		assertEquals(3, grille.getPlaces().size());
		System.out.println("Succès tests sur Grille Easy2 ");
	}

	@Test
	public void testGrilleSplit() {
		// grille 6x5, mots sans croisement
		Grille gr = GrilleLoader.loadGrille("data/split.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(6, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		assertEquals(4, grille.getNbHorizontal());
		assertEquals(4, grille.getPlaces().size());
		System.out.println("Succès tests sur Grille split ");
	}

	@Test
	public void testGrilleMedium() {
		Grille gr = GrilleLoader.loadGrille("data/medium.grl");

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		assertEquals(3, grille.getNbHorizontal());
		assertEquals(6, grille.getPlaces().size());
		System.out.println("Succès tests sur Grille medium ");
	}

	@Test
	public void testGrilleHard() {
		Grille gr = GrilleLoader.loadGrille("data/hard.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		assertEquals(5, grille.getNbHorizontal());
		assertEquals(10, grille.getPlaces().size());
		System.out.println("Succès tests sur Grille hard ");
	}

	@Test
	public void testGrilleLarge() {
		Grille gr = GrilleLoader.loadGrille("data/large.grl");

		assertEquals(20, gr.nbCol());
		assertEquals(20, gr.nbLig());

		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		assertEquals(63, grille.getNbHorizontal());
		assertEquals(124, grille.getPlaces().size());

		int[] expected = { 6, 5, 6, 5, 4, 2, 5, 9, 7, 2, 5, 8, 4, 2, 5, 3, 2, 6, 3, 7, 2, 7, 5, 3, 4, 3, 4, 6, 5, 2, 3,
				3, 6, 7, 3, 7, 3, 3, 6, 2, 6, 10, 5, 2, 2, 8, 3, 6, 3, 3, 6, 13, 2, 4, 2, 5, 3, 7, 2, 2, 7, 9, 9, 6, 4,
				4, 3, 4, 9, 3, 4, 6, 4, 6, 6, 9, 2, 2, 2, 8, 2, 2, 2, 6, 2, 4, 6, 5, 2, 7, 6, 3, 2, 11, 4, 3, 5, 5, 3,
				4, 3, 6, 6, 2, 2, 11, 6, 6, 3, 3, 9, 3, 2, 8, 5, 2, 7, 4, 2, 7, 12, 3, 3, 5 };

		assertEquals(expected.length, grille.getPlaces().size());

		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], grille.getPlaces().get(i).size());
		}
		System.out.println("Succès tests sur Grille large ");
	}

	@Test
	public void testGrilleLarge2() {
		Grille gr = GrilleLoader.loadGrille("data/large2.grl");

		GrillePlaces grille = new GrillePlaces(gr);

		assertEquals(63, grille.getNbHorizontal());
		assertEquals(124, grille.getPlaces().size());

		int[] expected = { 6, 5, 6, 5, 4, 2, 5, 9, 7, 2, 5, 8, 4, 2, 5, 3, 2, 6, 3, 7, 2, 7, 5, 3, 4, 3, 4, 6, 5, 2, 3,
				3, 6, 7, 3, 7, 3, 3, 6, 2, 6, 10, 5, 2, 2, 8, 3, 6, 3, 3, 6, 13, 2, 4, 2, 5, 3, 7, 2, 2, 7, 9, 9, 6, 4,
				4, 3, 4, 9, 3, 4, 6, 4, 6, 6, 9, 2, 2, 2, 8, 2, 2, 2, 6, 2, 4, 6, 5, 2, 7, 6, 3, 2, 11, 4, 3, 5, 5, 3,
				4, 3, 6, 6, 2, 2, 11, 6, 6, 3, 3, 9, 3, 2, 8, 5, 2, 7, 4, 2, 7, 12, 3, 3, 5 };

		assertEquals(expected.length, grille.getPlaces().size());

		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], grille.getPlaces().get(i).size());
		}
		System.out.println("Succès tests sur Grille large 2");
	}
}
