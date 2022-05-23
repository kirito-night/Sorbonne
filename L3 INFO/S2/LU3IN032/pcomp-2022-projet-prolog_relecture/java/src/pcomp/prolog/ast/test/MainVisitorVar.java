package pcomp.prolog.ast.test;

import pcomp.prolog.ast.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe de tests pour surveiller, à travers l'affichage sur la sortie standard, le comportement de VisitorVar
 * 
 * @author Camille Palisoc
 *
 */
public class MainVisitorVar {

	public static void main(String[] args) {
		
		// p(f(X, Y, g(O)), q(a))
		TermPredicate a = new TermPredicate(new Predicate("a", null), null);
		List<Term> argsQ = new ArrayList<>();
		argsQ.add(a);
		TermPredicate q = new TermPredicate(new Predicate("q",argsQ,null),null);
		TermVariable x = new TermVariable("X",null);
		TermVariable y = new TermVariable("Y",null);
		TermVariable o = new TermVariable("O",null);
		List<Term> argsG = new ArrayList<>();
		argsG.add(o);
		TermPredicate g = new TermPredicate(new Predicate("g",argsG, null),null);
		List<Term> argsF = new ArrayList<>();
		argsF.add(x);
		argsF.add(y);
		argsF.add(g);
		TermPredicate f = new TermPredicate(new Predicate("f",argsF, null),null);
		List<Term> argsP = new ArrayList<>();
		argsP.add(f);
		argsP.add(q);
		TermPredicate p = new TermPredicate(new Predicate("p",argsP, null),null);
		
		// Test VisitorVar
		VisitorVar visitor = new VisitorVar();
		List<TermVariable> vars = p.accept(visitor);
		
		// affichage du resultat
		for (TermVariable v : vars) {
			System.out.println(v);
		}

	}

}
