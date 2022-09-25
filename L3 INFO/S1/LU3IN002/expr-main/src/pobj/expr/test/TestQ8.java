package pobj.expr.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import pobj.expr.Question8;

public class TestQ8 {

	@Test
	public void testEnv() {
		Map<String,Integer> env1 = Question8.env1();		
		assertEquals(0, env1.size());
		
		Map<String,Integer> env2 = Question8.env2();		
		assertEquals(2, env2.size());
		Integer vx = env2.get("x");
		assertNotNull(vx);
		assertEquals((Integer)10, vx);
		Integer vy = env2.get("y");
		assertNotNull(vy);
		assertEquals((Integer)20, vy);
				
		Map<String,Integer> env3 = Question8.env3();		
		assertEquals(1, env3.size());
		assertNotNull(env3.get("z"));
		assertEquals((Integer)9, env3.get("z"));		
	}

}
