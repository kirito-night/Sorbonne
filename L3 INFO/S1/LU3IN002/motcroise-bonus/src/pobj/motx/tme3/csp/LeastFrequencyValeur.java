package pobj.motx.tme3.csp;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class LeastFrequencyValeur implements IChoixValeur {
    private static final int[] SCORELIST =
        {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 10, 1, 2, 1, 1, 3, 8, 1, 1, 1, 1, 4, 10, 10, 10, 10};

    private int calScore(String s) {
        int score = 0;
        for (int i = 0; i < s.length(); i++) {
            score += SCORELIST[(int)s.charAt(i) - 97];
        }
        return score;
    }

    @Override public List<String> orderValues(ICSP problem, IVariable v) {
        List<Pair<String, Integer>> toSort = new ArrayList<>();
        for (String s : v.getDomain()) {
            toSort.add(Pair.of(s, calScore(s)));
        }
        toSort.sort(new PairValueComparator());
        List<String> ordered = new ArrayList<>();
        for (Pair<String, Integer> pair : toSort) {
            ordered.add(pair.getKey());
        }
        return ordered;
    }
}
