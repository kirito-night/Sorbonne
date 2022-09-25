package pobj.expr.test;

import static org.junit.Assert.*;


import org.junit.Test;

import pobj.expr.Add;
import pobj.expr.Constant;
import pobj.expr.Expression;
import pobj.expr.Mult;
import pobj.expr.Question10;
import pobj.expr.Question4;

public class TestQ10 {

	@Test
	public void testQ10() {

		assertTrue(Question10.isConstant(Question4.e1()));
		assertFalse(Question10.isConstant(Question4.e2()));
		assertFalse(Question10.isConstant(Question4.e3()));
		
		Constant ct = new Constant(3);
		Constant ct2 = new Constant(5);
		Constant ct3 = new Constant(17);
		assertTrue(Question10.isConstant(ct));
		assertEquals(3, Question10.evalConstantExpression(ct));
		assertEquals(5, Question10.evalConstantExpression(ct2));
		assertEquals(17, Question10.evalConstantExpression(ct3));
		
		Expression add = new Add(ct, ct2);
		assertTrue(Question10.isConstant(add));
		assertEquals((5+3), Question10.evalConstantExpression(add));

		Expression mul = new Mult(ct3, add);
		assertTrue(Question10.isConstant(mul));
		assertEquals(17 * (5+3), Question10.evalConstantExpression(mul));		
	}

}
