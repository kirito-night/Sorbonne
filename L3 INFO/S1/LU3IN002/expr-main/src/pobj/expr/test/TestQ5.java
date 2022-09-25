package pobj.expr.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.expr.Add;
import pobj.expr.Constant;
import pobj.expr.Expression;
import pobj.expr.Mult;
import pobj.expr.Var;

public class TestQ5 {

	@Test
	public void testEval() {
		Constant ct = new Constant(3);
		Constant ct2 = new Constant(5);
		Constant ct3 = new Constant(17);
		assertEquals(3, ct.eval());;
		assertEquals(5, ct2.eval());;
		assertEquals(17, ct3.eval());;
		
		Expression var = new Var("a");
		try {
			var.eval();
			// should raise an exception
			fail();
		} catch (UnsupportedOperationException e) {
			// happy
			assertTrue(true);
		}
		
		Expression add = new Add(ct, ct2);
		assertEquals((5+3), add.eval());

		Expression mul = new Mult(ct3, add);
		assertEquals(17 * (5+3), mul.eval());
		
		Expression tot = new Mult(mul, var);
		
		try {
			tot.eval();
			// should raise an exception
			fail();
		} catch (UnsupportedOperationException e) {
			// happy
			assertTrue(true);
		}
	}

}
