package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;


/**
 *  Visiteur qui permet de lister toutes les variables dans un Term
 * 
 * @author Camille Palisoc
 *
 */
public class VisitorVar implements TermVisitor<List<TermVariable>> {

	@Override
	public List<TermVariable> visit(TermVariable termVariable) {
		List<TermVariable> res = new ArrayList<>();
		res.add(termVariable);
		return res;
	}

	@Override
	public List<TermVariable> visit(TermPredicate termPredicate) {
		List<TermVariable> res = new ArrayList<>();
		Predicate p = termPredicate.getPredicate();
		for (Term t : p.getArguments()) {
			res.addAll(t.accept(this));
		}
		return res;
	}

}
