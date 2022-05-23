package pcomp.prolog.ast.excep;

import pcomp.prolog.ast.Equation;


/**
 * Exception levee quand on trouve une Equation candidate pour appliquer la règle remplacer
 * 
 * @author Camille Palisoc
 *
 */
public class FoundRException extends RuntimeException {
	
	private Equation e;
	
	public FoundRException(Equation e) {
		super("Equation a injecter!");
		this.e = e;
	}
	
	public Equation getEquation() {
		return e;
	}

}
