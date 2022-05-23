/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
* AST : Classe des termes qui sont des variables.
*/

public class TermVariable implements Term {

	// Attribut
	///////////////

	private final Position pos;
	private final String name;


	// Constructeur
	////////////////////////

	public TermVariable(String name, Position pos) {
		this.name = name;
		this.pos = pos;
	}


	// Getter
	////////////

	public String getName() {
		return name;
	}

	public Position getPosition() {
		return pos;
	}

	
	// Egalité (de contenu)
	///////////////////////////////////

	@Override public boolean equals(Object o) {
		// la position n'est pas prise en compte !
		if (!(o instanceof TermVariable)) return false;
		return name.equals(((TermVariable)o).name);
	}

	@Override public int hashCode() {
		// la position n'est pas prise en compte !
		return name.hashCode();
	}


	// Conversion en chaîne
	/////////////////////////////////////

	@Override public String toString() {
		return name;
	}


	// Visiteur
	///////////////

	@Override public <T> T accept(TermVisitor<T> visitor) {
		return visitor.visit(this);
	}


	@Override
	public TermVariable copy() {
		return new TermVariable(this.name,this.pos.copy());

	}
}
