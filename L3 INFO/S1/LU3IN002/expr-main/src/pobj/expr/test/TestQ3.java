package pobj.expr.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.expr.Add;
import pobj.expr.Constant;
import pobj.expr.Expression;
import pobj.expr.Mult;
import pobj.expr.Var;

public class TestQ3 {

	@Test
	public void testOperators() {
		Var var = new Var ("a");
		Constant ct = new Constant(3);
		
		assertTrue(var instanceof Expression);
		assertTrue(ct instanceof Expression);
		
		Add add = new Add(var, ct);
		assertEquals("( a + 3 )", add.toString());
		assertTrue(add instanceof Expression);
		assertEquals(add.getLeft(), var);
		assertEquals(add.getRight(), ct);
				
		Constant c2 = new Constant(5);
		Mult mul = new Mult(c2, add);
		assertTrue(mul instanceof Expression);
		assertEquals("5 * ( a + 3 )", mul.toString());
		assertEquals(mul.getLeft(), c2);
		assertEquals(mul.getRight(), add);
	}

}
