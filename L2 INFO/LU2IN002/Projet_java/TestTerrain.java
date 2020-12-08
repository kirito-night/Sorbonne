/**
 * 
 */

/**
 * @author Christophe Marsala (LU2IN002 2020oct)
 * 
 */
public class TestTerrain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Exemple de création de terrain
		Terrain t = new Terrain(5,6);
		
		// Terrain initial : il est vide
		t.affiche();
		// Informations sur le terrain
		System.out.println("Informations sur le terrain:\n"+t);		
		
		// On créé une ressource
		Ressource e1 = new Ressource("Miel",5);
		// et on la place sur le terrain
		if (t.setCase(2,3,e1))
			System.out.println("Ajout de " +e1+" valide !");
		else
			System.out.println("Ajout incorrect: problème de coordonnées !");
		
		// On créé une autre ressource
		Ressource e2 = new Ressource("Pollen",4);
		// On rajoute la ressource sur le terrain
		if (t.setCase(0,2,e2))
			System.out.println("Ajout de " +e2+" valide !");
		else
			System.out.println("Ajout incorrect: problème de coordonnées !");
		
		// On rajoute une autre ressource et on la met sur le terrain
		if (t.setCase(4,1,new Ressource("Pollen",3)))
			System.out.println("Ajout valide !");
		else
			System.out.println("Ajout incorrect: problème de coordonnées !");

		// Affichage du terrain avec les ressources ajoutées
		t.affiche();
		// Informations sur le terrain
		System.out.println("Informations sur le terrain:\n"+t);
		
		// Contenu d'une case:
		System.out.println("Dans la case (1,4): "+t.getCase(1,4));
		// Contenu d'une case:
		System.out.println("Dans la case (0,2): "+t.getCase(0,2));
		
		// Vidage d'une case:
		System.out.println("Vidage d'une case:");
		Ressource etaitDansLaCase = t.videCase(0,2);
		if (etaitDansLaCase == null)
			System.out.println("La case était déjà vide.");
		else 
			System.out.println("La case contenait : "+etaitDansLaCase);
		
		// Affichage du terrain avec les ressources ajoutées
		t.affiche();
	}

}
