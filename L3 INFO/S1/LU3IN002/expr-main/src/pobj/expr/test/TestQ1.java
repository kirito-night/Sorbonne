package pobj.expr.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.expr.Constant;
import pobj.expr.Expression;

public class TestQ1 {

	@Test
	public void testConst() {
		testConstante(10);
		testConstante(-1);
		testConstante(0);
		testConstante(65536);
		// ctor par defaut : cree la constante 0
		Constant cZero = new Constant();
		assertEquals(0, cZero.getValue());
		
		assertTrue(cZero instanceof Expression);
	}

	private void testConstante(int k) {
		Constant c = new Constant(k);
		assertEquals(k, c.getValue());
		assertEquals(Integer.toString(k), c.toString());
	}

}
