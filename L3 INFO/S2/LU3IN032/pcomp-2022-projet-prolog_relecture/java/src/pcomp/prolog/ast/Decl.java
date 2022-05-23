/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Universit�
 * ann�e 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

import java.util.List;

/*
 * AST : interface des d�clarations.
 *
 * Une d�claration est soit une assertion "head :- body.", soit un but "?- body."
 */
public interface Decl {
	
	//Ligne et colonne du d�but de la d�claration
	Position getPosition();
	
	//rajout FX
	List<Predicate> getPredicates();
	
	// Interface du visiteur de termes. 
	public <T> T accept(DeclVisitor<T> visitor);
}
