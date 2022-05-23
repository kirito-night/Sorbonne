/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Universit�
 * ann�e 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
 * AST : interface de visiteur des d�clarations.
 */
public interface DeclVisitor<T> {
	public T visit(DeclAssertion a);
	public T visit(DeclGoal a);
}
