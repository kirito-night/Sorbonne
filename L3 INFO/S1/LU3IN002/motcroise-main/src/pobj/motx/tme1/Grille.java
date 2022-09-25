package pobj.motx.tme1;

public class Grille {
	private Case[][] matrice;
	
	public Grille (int hauteur, int largeur) {
		matrice= new Case[hauteur][largeur];
		
		for(int i = 0 ; i< hauteur ; i++) {
			for(int j = 0 ; j < largeur ; j++) {
				matrice[i][j] = new Case(i, j, ' ');
			}
		}
	}
	
	public Case getCase(int lig, int col) {
		return matrice[lig][col];
	}
	
	
	@Override 
	public String toString() {
		return GrilleLoader.serialize(this, false);
	}
	
	public int nbLig() {
		return matrice.length;
	}
	
	public int nbCol() {
		return matrice[0].length;
	}
	
	public Grille copy() {
		Grille g = new Grille(this.nbLig(),this.nbCol());
		for(int i = 0  ; i < g.nbLig(); i++) {
			for(int j = 0 ;  j < g.nbCol() ; j++) {
				g.getCase(i, j).setChar(this.getCase(i, j).getChar());
				
			}
		}
		return g;
	}
	
}
