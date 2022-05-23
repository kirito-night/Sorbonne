 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
 * AST : représentation d'une position (line,colonne) dans le source.
 */
public class Position {

	private final int line;
	private final int column;
	
	public Position(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
	
	@Override 
	public String toString() {
		return line + ":" + column;
	}
	
	public Position copy() {
		return new Position(this.line, this.column);
	}
}
