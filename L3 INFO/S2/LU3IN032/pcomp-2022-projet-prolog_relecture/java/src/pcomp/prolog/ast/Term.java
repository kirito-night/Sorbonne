/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
 * AST : interface des termes.
 *
 * Un terme est soit une variable, soit un prédicat f(term1,...,termN).
 */
public interface Term {

	// Ligne et colonne du dÃ©but du terme.
	Position getPosition();

	// Interface du visiteur de termes.
	public <T> T accept(TermVisitor<T> visitor);
	
	public Term copy();
}
