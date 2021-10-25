package pobj.motx.tme3.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme3.CroixContrainte;

public class CroixContrainteTest {

	@Test
	public void test1() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/easy2.grl");

		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);
		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		CroixContrainte c1 = new CroixContrainte(0,3,2,0);
		assertEquals(242, c1.reduce(gp));

		CroixContrainte c2 = new CroixContrainte(1,3,2,4);
		assertEquals(236, c2.reduce(gp));
	}

	@Test
	public void test2() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/hard.grl");

		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);
		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		CroixContrainte c = new CroixContrainte(0,0,5,0);
		assertEquals(0, c.reduce(gp));
	}

}
