/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Universit�
 * ann�e 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
 * AST : interface des termes.
 *
 * Un terme est soit une variable, soit un pr�dicat f(term1,...,termN).
 */
public interface Term {

	// Ligne et colonne du début du terme.
	Position getPosition();

	// Interface du visiteur de termes.
	public <T> T accept(TermVisitor<T> visitor);
	
	public Term copy();
}
