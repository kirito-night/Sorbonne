package pobj.motx.tme3.csp;

import java.util.List;

public interface IChoixValeur {
    List<String> orderValues(ICSP problem, IVariable v);
}
