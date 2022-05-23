/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

import java.util.List;

/*
 * AST : classe des buts.
 *
 * Un but est une déclaration de la forme "?- pred, ..., pred."
 */
public class DeclGoal implements Decl {

	// Attributs
	/////////////////

	private final Position pos;
	private final List<Predicate> preds; // prédicats, à droite de ?-


	// Constructeur
	/////////////////////////

	public DeclGoal(List<Predicate> preds, Position pos) {
		this.preds = preds;
		this.pos = pos;
	}


	// Getters
	//////////////

	public List<Predicate> getPredicates() {
		return preds;
	}
	
	public Position getPosition() {
		return pos;
	}


	// Conversion en chaîne
	/////////////////////////////////////

	@Override public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(" ?- ");
		boolean first = true;
		for (Predicate t : preds) {
			if (first) first = false; else buf.append(", ");
			buf.append(t.toString());
		}
		buf.append(".");
		return buf.toString();
	}


	// Interface du visiteur
	//////////////////////////////////

	@Override public <T> T accept(DeclVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
