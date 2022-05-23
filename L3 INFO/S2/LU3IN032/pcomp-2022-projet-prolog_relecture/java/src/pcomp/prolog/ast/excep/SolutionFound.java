package pcomp.prolog.ast.excep;

import pcomp.prolog.ast.CurrContext;

/**
 * Exception lev�e quand une solution a �t� trouv�e
 * Utilis�e dans le backtracking
 * 
 * @author Camille Palisoc
 *
 */
public class SolutionFound extends RuntimeException {
	private CurrContext finalChoice;
	
	public SolutionFound(CurrContext ch) {
		super("1 solution trouv�e !");
		finalChoice = ch;
	}
	
	public CurrContext getFinalChoice() {
		return finalChoice;
	}

}
