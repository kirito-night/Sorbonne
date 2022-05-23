/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Universit�
 * ann�e 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
 * AST : classe des termes qui sont des pr�dicats.
 *
 */
public class TermPredicate implements Term {

	// Attributs
	////////////////

	private final Position pos;
	private final Predicate pred;


	// Constructeur
	/////////////////////////

	public TermPredicate(Predicate pred, Position pos) {
		this.pred = pred;
		this.pos = pos;
	}


	// Getters
	///////////////

	public Predicate getPredicate() {
		return pred;
	}

	public Position getPosition() {
		return pos;
	}

	
	// Egalit� structurelle (r�cursive)
	///////////////////////////////////////////////////

	@Override public boolean equals(Object o) {
		// la position n'est pas prise en compte !
		if (this == o) return true;
		if (!(o instanceof TermPredicate)) return false;
		TermPredicate p = (TermPredicate)o;
		return p.pred.equals(pred);
	}

	@Override public int hashCode() {
		// la position n'est pas prise en compte !
		return pred.hashCode();
	}

	// Conversion en cha�ne
	/////////////////////////////////////

	@Override public String toString() {
		return pred.toString();
	}


	// Visiteur
	///////////////

	@Override public <T> T accept(TermVisitor<T> visitor) {
		return visitor.visit(this);
	}

	/**
	 * Remplace, si besoin, les variables dans le TermPredicate
	 * @param x : variable � remplacer
	 * @param nouv : Term par lequel remplacer la variable x
	 * @return true si la r�gle a �t� appliqu�e
	 */
	public boolean subst(TermVariable x, Term nouv) {
		return pred.subst(x, nouv);
	}
	
	/**
	 * Renomme les variables dans les arguments du pr�dicat
	 * @param n : compteur � ajouter au nom de la variable
	 * @return le nouveau TermPredicate cr�� avec les variables renomm�es
	 */
	public TermPredicate rename(int n) {
		return pred.rename(n);
	}
	
	public TermPredicate copy() {
		return new TermPredicate(this.pred.copy(),this.pos.copy());
		
	}
}
