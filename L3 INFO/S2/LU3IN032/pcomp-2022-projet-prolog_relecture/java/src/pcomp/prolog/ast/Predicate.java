/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * AST : classe des prédicats.
 *
 * Un prédicat a la forme f(term1,...,termN).
 * f est son symbole, et term1 à termN sont ses arguments
 */
public class Predicate {

	// Attributs
	////////////////

	private final Position pos; // position du début du prédicat
	private final String symbol; // symbole du prédicat
	private final List<Term> args; // arguments du prédicat


	// Constructeurs
	/////////////////////////

	// constructeur "symbol(args)"
	public Predicate(String symbol, List<Term> args, Position pos) {
		this.symbol = symbol;
		this.args = args;
		this.pos = pos;
	}

	// constructeur d'une constante, i.e., un prÃ©dicat sans argument
	public Predicate(String symbol, Position pos) {
		this(symbol, Collections.emptyList(), pos);
	}


	// Getters
	///////////////

	public String getSymbol() {
		return symbol;
	}

	public List<Term> getArguments() {
		return args;
	}

	public Position getPosition() {
		return pos;
	}
	
	// Egalité structurelle (récursive)
	///////////////////////////////////////////////////

	@Override public boolean equals(Object o) {
		// la position n'est pas prise en compte !
		if (this == o) return true;
		if (!(o instanceof Predicate)) return false;
		Predicate p = (Predicate)o;
		if (!symbol.equals(p.symbol)) return false;
		if (args.size() != p.args.size()) return false;
		for (int i = 0; i < args.size(); i++) {
			if (!args.get(i).equals(p.args.get(i))) return false;
		}
		return true;
	}

	@Override public int hashCode() {
		// la position n'est pas prise en compte !
		int hash = symbol.hashCode();
		for (Term t : args) {
			hash = hash * 75 + t.hashCode();
		}
		return hash;
	}

	// Conversion en chaîne
	/////////////////////////////////////

	@Override public String toString() {
		if (args.isEmpty()) return symbol;
		StringBuffer buf = new StringBuffer();
		buf.append(symbol);
		buf.append("(");
		boolean first = true;
		for (Term t : args) {
			if (first) first = false; else buf.append(", ");
			buf.append(t.toString());
		}
		buf.append(")");
		return buf.toString();
	}
	
	// Autres méthodes
	
	/**
	 * Remplace, si présente, la variable x dans ses arguments
	 * @param x : TermVariable à remplacer
	 * @param nouv : Term par lequel remplacer x
	 * @return true si la règle remplacer a été appliquée, false sinon
	 */
	public boolean subst(TermVariable x, Term nouv) {
		boolean replaced = false;
		for (int i=0;i<args.size();i++) {
			Term t = args.get(i);
			if (t instanceof TermVariable) {
				if (((TermVariable)t).equals(x)) {
					// on a trouvé une variable x, on la remplace
					replaced = true;
					args.set(i, nouv);
				}
			} else if (t instanceof TermPredicate) {
				// on va chercher en profondeur
				Predicate tmp = ((TermPredicate)t).getPredicate();
				replaced = replaced || tmp.subst(x, nouv);
			}
		}
		return replaced;
	}
	
	/**
	 * Renomme les variables dans les arguments du prédicat
	 * @param n : compteur à ajouter au nom de la variable
	 * @return le nouveau TermPredicate créé avec les variables renommées
	 */
	public TermPredicate rename(int n) {
		VisitorRename v = new VisitorRename(n);
		List<Term> nouvArgs = new ArrayList<>();
		for (int i=0;i<args.size();i++) {
			nouvArgs.add(i, args.get(i).accept(v));
		}
		return new TermPredicate(new Predicate(symbol,nouvArgs,pos),pos);
	}
	
	/**
	 * Fait une copie de l'objet
	 * @return un Predicate avec les mêmes arguments et la même position
	 */
	public Predicate copy() {
		List<Term> res=new ArrayList<>();
		for (int i=0;i<args.size();i++) {
			res.add(args.get(i).copy());
		}
		return new Predicate(symbol, res, pos.copy());
	}
}
