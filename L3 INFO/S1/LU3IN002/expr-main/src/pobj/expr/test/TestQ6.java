package pobj.expr.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.expr.Add;
import pobj.expr.Constant;
import pobj.expr.Expression;
import pobj.expr.Mult;
import pobj.expr.Question4;
import pobj.expr.Var;
import pobj.expr.VisitorToString;

public class TestQ6 {

	@Test
	public void testVisitorToString() {
		
		VisitorToString vts = new VisitorToString();
		Var var = new Var ("a");
		Constant ct = new Constant(3);		
		Add add = new Add(var, ct);
		assertEquals("( a + 3 )", add.accept(vts));
				
		Constant c2 = new Constant(5);
		Mult mul = new Mult(c2, add);
		assertEquals("5 * ( a + 3 )", mul.accept(vts));
			
		Expression e1 = Question4.e1() ;
		
		assertEquals("( 2 + 3 ) * 4", e1.accept(vts));
		
		Expression e2 = Question4.e2() ;
		
		assertEquals("( x + 3 ) * ( x + 4 )", e2.accept(vts));
		
		Expression e3 = Question4.e3() ;
		
		assertEquals("( x + 10 ) * ( y + -8 )", e3.accept(vts));		
	}

}
