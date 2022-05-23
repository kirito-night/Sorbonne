/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

import java.util.List;

/*
 * AST : interface des déclarations.
 *
 * Une déclaration est soit une assertion "head :- body.", soit un but "?- body."
 */
public interface Decl {
	
	//Ligne et colonne du début de la déclaration
	Position getPosition();
	
	//rajout FX
	List<Predicate> getPredicates();
	
	// Interface du visiteur de termes. 
	public <T> T accept(DeclVisitor<T> visitor);
}
