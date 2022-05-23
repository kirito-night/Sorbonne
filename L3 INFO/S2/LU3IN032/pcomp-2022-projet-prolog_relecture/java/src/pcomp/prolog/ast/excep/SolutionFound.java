package pcomp.prolog.ast.excep;

import pcomp.prolog.ast.CurrContext;

/**
 * Exception levée quand une solution a été trouvée
 * Utilisée dans le backtracking
 * 
 * @author Camille Palisoc
 *
 */
public class SolutionFound extends RuntimeException {
	private CurrContext finalChoice;
	
	public SolutionFound(CurrContext ch) {
		super("1 solution trouvée !");
		finalChoice = ch;
	}
	
	public CurrContext getFinalChoice() {
		return finalChoice;
	}

}
