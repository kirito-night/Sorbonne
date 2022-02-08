package pobj.motx.tme2.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.motx.tme2.Dictionnaire;

public class DictionnaireTest {

	@Test
	public void testAdd() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");

		System.out.println(gut.size());
		assertEquals(323422, gut.size());
		System.out.println("Succès load/add Dictionnaire");
	}

	@Test
	public void testCopy() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		// test copie
		Dictionnaire red2 = gut.copy();
		Dictionnaire red3 = gut.copy();
		assertEquals(323422, red2.size());
		assertEquals(323422, red3.size());
		System.out.println("Succès test copy Dictionnaire");
	}

	@Test
	public void testFiltreLen() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");

		// nombres de mots de longueur : 2,3,4...
		int[] expected = { 81, 427, 1807, 5916, 13945, 25577, 38343, 47671, 50254, 45851, 36343, 25313, 15462, 8530,
				4287, 2022, 894, 395, 181, 77, 32, 9, 2, 1, 0 };

		for (int len = 2; len < expected.length + 2; len++) {
			Dictionnaire red = gut.copy();
			int notX = red.filtreLongueur(len);
			// gut n'est pas modifié
			assertEquals(323422, gut.size());
			// taille attendue pour cet ensemble de mots
			assertEquals(expected[len - 2], red.size());
			// resultat attendu : nombre de mots reduits
			assertEquals(323422, red.size() + notX);
			// on ne réduit pas plus en faisant deux fois
			assertEquals(0, red.filtreLongueur(len));
		}
		System.out.println("Succès filtre par longueur");
	}

}
