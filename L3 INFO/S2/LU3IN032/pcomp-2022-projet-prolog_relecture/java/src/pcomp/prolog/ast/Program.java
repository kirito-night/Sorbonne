/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

import java.util.List;

/*
 * AST de programmes Prolog.
 *
 * Un programme Prolog est une liste de déclarations.
 */
public class Program {

	// Attribut
	//////////////

	private List<Decl> decls;


	// Constructeur
	///////////////////////

	public Program(List<Decl> decls) {
		this.decls = decls;
	}


	// Getter
	//////////////

	public List<Decl> getDeclarations() {
		return decls;
	}


	// Conversion en chaîne
	/////////////////////////////////////

	@Override public String toString() {
		StringBuffer buf = new StringBuffer();
		for (Decl c : decls) {
			buf.append(c.toString());
			buf.append("\n");
		}
		return buf.toString();
	}
}
