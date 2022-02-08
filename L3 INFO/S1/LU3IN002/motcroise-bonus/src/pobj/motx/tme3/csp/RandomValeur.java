package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomValeur implements IChoixValeur {

    @Override public List<String> orderValues(ICSP problem, IVariable v) {
        List<String> ordered = new ArrayList<>();
        Collections.addAll(ordered, v.getDomain());
        Collections.shuffle(ordered);
        return ordered;
    }
}
