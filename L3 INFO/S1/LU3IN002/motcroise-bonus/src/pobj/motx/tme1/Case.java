package pobj.motx.tme1;

/**
 * Classe de case dans une grille
 * @author Zhe WANG
 */
public class Case {

	/** ligne de la case */
	private int lig;
	/** colonne de la case */
	private int col;
	/** caractere de la case */
	private char c;

	/** 
	 * Construit une case de coordonnees donnees
	 * @param lig ligne de la case
	 * @param col colonne de la case
	 * @param c caractere de la case
	 */
	public Case(int lig, int col, char c) {
		this.lig = lig;
		this.col = col;
		this.c = c;
	}
	
	/**
	 * Accede au numero de ligne de cette case
	 * @return le numero de ligne de la case
	 */
	public int getLig() {
		return lig;
	}

	/**
	 * Accede au numero de colonne de cette case
	 * @return le numero de colonne de la case
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Accede a la caractere de cette case
	 * @return la caractere de la case
	 */
	public char getChar() {
		return c;
	}

	/**
	 * Modifie la caractere de la case
	 * @param c la nouvelle caractere
	 */
	public void setChar(char c) {
		this.c = c;
	}

	/**
	 * Verifie si la case est vide
	 * @return la case est vide ou pas
	 */
	public boolean isVide() {
		return (c == ' ');
	}

	/**
	 * Verifie si la case est pleine
	 * @return la case est pleine ou pas
	 */
	public boolean isPleine() {
		return (c == '*');
	}
	
	
}
