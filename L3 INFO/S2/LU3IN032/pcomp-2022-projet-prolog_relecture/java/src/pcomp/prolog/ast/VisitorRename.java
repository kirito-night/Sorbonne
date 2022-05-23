package pcomp.prolog.ast;

/**
 * Visiteur utilisé pour renommer les variables dans les termes
 * 
 * @author Camille Palisoc
 *
 */
public class VisitorRename implements TermVisitor<Term> {
	
	private int n;
	
	public VisitorRename(int n) {
		this.n = n;
	}

	@Override
	public Term visit(TermVariable termVariable) {
		return new TermVariable(termVariable.getName()+n,termVariable.getPosition());
	}

	@Override
	public Term visit(TermPredicate termPredicate) {
		return termPredicate.rename(n);
	}

}
