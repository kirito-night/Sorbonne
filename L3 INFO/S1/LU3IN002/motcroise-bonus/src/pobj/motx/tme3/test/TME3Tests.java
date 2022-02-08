package pobj.motx.tme3.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	CroixContrainteTest.class, 
	GrilleContrainteTest1.class, GrilleContrainteTest2.class, GrilleContrainteTest3.class,
	GrilleSolverTest.class
})
public class TME3Tests {

}
