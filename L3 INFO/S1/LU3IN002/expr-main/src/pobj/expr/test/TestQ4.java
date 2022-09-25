package pobj.expr.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.expr.Expression;
import pobj.expr.Question4;

public class TestQ4 {

	@Test
	public void testToString() {
		Expression e1 = Question4.e1() ;
		
		assertEquals("( 2 + 3 ) * 4", e1.toString());
		
		Expression e2 = Question4.e2() ;
		
		assertEquals("( x + 3 ) * ( x + 4 )", e2.toString());
		
		Expression e3 = Question4.e3() ;
		
		assertEquals("( x + 10 ) * ( y + -8 )", e3.toString());
		
	}
	
}
