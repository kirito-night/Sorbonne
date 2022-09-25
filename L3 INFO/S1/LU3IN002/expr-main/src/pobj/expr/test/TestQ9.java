package pobj.expr.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import pobj.expr.Question4;
import pobj.expr.Question8;
import pobj.expr.VisitorEvalVar;

public class TestQ9 {

	@Test
	public void testEnv() {
		Map<String,Integer> env1 = Question8.env1();		
		Map<String,Integer> env2 = Question8.env2();		

		VisitorEvalVar vev1 = new VisitorEvalVar(env1);
		VisitorEvalVar vev2 = new VisitorEvalVar(env2);
		
		assertEquals((Integer)20, Question4.e1().accept(vev1));
		assertEquals((Integer)20, Question4.e1().accept(vev2));
		
		assertEquals((Integer)182, Question4.e2().accept(vev2));
		assertEquals((Integer)240, Question4.e3().accept(vev2));
		
	}

}
