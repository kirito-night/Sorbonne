/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
 * AST : interface de visiteur des termes.
 */
public interface TermVisitor<T> {
	public T visit(TermVariable termVariable);
	public T visit(TermPredicate termPredicate);
}
