package pobj.motx.tme3.csp;

import java.util.*;

public interface ICSP {
	List<IVariable> getVars();
	boolean isConsistent();
	ICSP assign(IVariable vi, String val);
}
