package pobj.expr.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.expr.Add;
import pobj.expr.Constant;
import pobj.expr.Expression;
import pobj.expr.Mult;
import pobj.expr.Question4;
import pobj.expr.Var;
import pobj.expr.VisitorEval;

public class TestQ7 {

	@Test
	public void testVisitorEval() {
		VisitorEval ve = new VisitorEval();
		Constant ct = new Constant(3);
		Constant ct2 = new Constant(5);
		Constant ct3 = new Constant(17);
		assertEquals((Integer)3, ct.accept(ve));
		assertEquals((Integer)5, ct2.accept(ve));;
		assertEquals((Integer)17, ct3.accept(ve));;
		
		Expression var = new Var("a");
		try {
			var.accept(ve);
			// should raise an exception
			fail();
		} catch (Exception e) {
			// happy
			assertTrue(true);
		}
		
		Expression add = new Add(ct, ct2);
		assertEquals((Integer)(5+3), add.accept(ve));

		Expression mul = new Mult(ct3, add);
		assertEquals((Integer)(17 * (5+3)), mul.accept(ve));
		
		Expression tot = new Mult(mul, var);
		
		try {
			tot.accept(ve);
			// should raise an exception
			fail();
		} catch (Exception e) {
			// happy
			assertTrue(true);
		}
		
		assertEquals((Integer)20, Question4.e1().accept(ve));
	}

}
