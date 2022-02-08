package pobj.motx.tme1.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.motx.tme1.Case;

public class CaseTest {

	@Test
	public void testIsPleine() {
		Case cv = new Case(0, 1, ' ');
		Case cp = new Case(1, 0, '*');
		Case cc = new Case(7, 5, 'z');
		assertEquals(false, cv.isPleine());
		assertEquals(true, cp.isPleine());
		assertEquals(false, cc.isPleine());
		System.out.println("Succès tests sur Case : isPleine");
	}

	@Test
	public void testIsVide() {
		Case cv = new Case(0, 1, ' ');
		Case cp = new Case(1, 0, '*');
		Case cc = new Case(7, 5, 'z');
		assertEquals(true, cv.isVide());
		assertEquals(false, cp.isVide());
		assertEquals(false, cc.isVide());
		System.out.println("Succès tests sur Case : isVide");
	}

	@Test
	public void testGetChar() {
		Case cv = new Case(0, 1, ' ');
		Case cp = new Case(1, 0, '*');
		Case cc = new Case(7, 5, 'z');
		assertEquals(' ', cv.getChar());
		assertEquals('*', cp.getChar());
		assertEquals('z', cc.getChar());
		System.out.println("Succès tests sur Case : getChar");
	}

	@Test
	public void testGetLig() {
		Case cv = new Case(0, 1, ' ');
		Case cp = new Case(1, 0, '*');
		Case cc = new Case(7, 5, 'z');
		assertEquals(0, cv.getLig());
		assertEquals(1, cp.getLig());
		assertEquals(7, cc.getLig());
		System.out.println("Succès tests sur Case : getLig");
	}

	@Test
	public void testGetCol() {
		Case cv = new Case(0, 1, ' ');
		Case cp = new Case(1, 0, '*');
		Case cc = new Case(7, 5, 'z');
		assertEquals(1, cv.getCol());
		assertEquals(0, cp.getCol());
		assertEquals(5, cc.getCol());
		System.out.println("Succès tests sur Case : getCol");
	}

	@Test
	public void testSetChar() {
		Case cv = new Case(0, 1, ' ');
		Case cp = new Case(1, 0, '*');
		Case cc = new Case(7, 5, 'z');
		assertEquals(' ', cv.getChar());
		assertEquals('*', cp.getChar());
		assertEquals('z', cc.getChar());
		cv.setChar('a');
		cp.setChar(' ');
		cc.setChar('*');
		assertEquals('a', cv.getChar());
		assertEquals(' ', cp.getChar());
		assertEquals('*', cc.getChar());
		System.out.println("Succès tests sur Case : setChar");
	}

}
