package pobj.expr.test;

import static org.junit.Assert.*;


import org.junit.Test;

import pobj.expr.Add;
import pobj.expr.Constant;
import pobj.expr.Expression;
import pobj.expr.Mult;
import pobj.expr.Question4;
import pobj.expr.Var;
import pobj.expr.VisitorSimplify;
import pobj.expr.VisitorToString;

public class TestQ11 {

	@Test
	public void testQ11() {

		VisitorToString vts = new VisitorToString();
		Expression e1 = Question4.e1() ;
		
		assertEquals("( 2 + 3 ) * 4", e1.accept(vts));
		
		Expression e2 = Question4.e2() ;
		
		assertEquals("( x + 3 ) * ( x + 4 )", e2.accept(vts));
		
		Expression e3 = Question4.e3() ;
		
		assertEquals("( x + 10 ) * ( y + -8 )", e3.accept(vts));		

		VisitorSimplify vs = new VisitorSimplify();
		Expression e1s = e1.accept(vs);
		assertEquals("20", e1s.accept(vts));
		assertEquals("( x + 3 ) * ( x + 4 )", e2.accept(vs).accept(vts));
		assertEquals("( x + 10 ) * ( y + -8 )", e3.accept(vs).accept(vts));				
		
		Constant ct = new Constant(3);
		Constant ct2 = new Constant(5);
		
		Expression add = new Add(ct, ct2);
		assertEquals("8", add.accept(vs).accept(vts));
		
		Constant ct3 = new Constant(17);
		Expression mul = new Mult(ct3, add);
		assertEquals("136", mul.accept(vs).accept(vts));
		
		// neutral element Add
		Expression n = new Add(new Var("x"),new Constant(0));
		assertEquals("x", n.accept(vs).accept(vts));
		n = new Add(new Constant(0), new Var("x"));
		assertEquals("x", n.accept(vs).accept(vts));

		// neutral element Mult
		n = new Mult(new Var("x"),new Constant(1));
		assertEquals("x", n.accept(vs).accept(vts));
		n = new Mult(new Constant(1), new Var("x"));
		assertEquals("x", n.accept(vs).accept(vts));

		// 0 absorbing for Mult
		n = new Mult(new Var("x"),new Constant(0));
		assertEquals("0", n.accept(vs).accept(vts));
		n = new Mult(new Constant(0), new Var("x"));
		assertEquals("0", n.accept(vs).accept(vts));
				
	}

}
