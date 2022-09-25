package pobj.expr.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.expr.Expression;
import pobj.expr.Var;

public class TestQ2 {
	
	
	@Test
	public void testVar() {
		Var var = new Var("a");
		Var var2 = new Var("a");
		Var var3 = new Var("b");
		
		// test definition equals
		assertTrue(var2.equals( var) );
		assertTrue(var2.equals( (Object) var) );
		
		assertFalse(var3.equals( var) );
		assertFalse(var3.equals( var2) );
		
		assertEquals("a", var.getName() );
		assertEquals("b", var3.getName() );
	
		assertEquals("a", var.toString() );
		assertEquals("a", var2.toString() );
		assertEquals("b", var3.toString() );
		
		assertTrue(var instanceof Expression);
	}
	
}

