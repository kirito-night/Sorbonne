package pobj.motx.tme1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class GrilleLoader {

	/**
	 * Permet de charger une grille depuis un fichier au format "grl". La
	 * première ligne du fichier contient deux entiers : nblig nbcol qui donnent
	 * la taille de la grille. Les lignes suivantes donnent le contenu de la
	 * grille, '*' désignant une case pleine, ' ' une case vide, ou une lettre
	 * 'a-z'.
	 * 
	 * @param path
	 *            chemin d'accès au fichier grl
	 * @return une grille chargée depuis le fichier ou null en cas de problème
	 *         d'accès au fichier.
	 */
	public static Grille loadGrille(String path) {
		// la grille qu'on va construire
		Grille gc = null;

		// try-with-resource : cette syntaxe permet d'accéder au contenu du
		// fichier ligne par ligne.
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			// un compteur pour retenir le numero de la ligne qu'on charge
			// actuellement
			int lig = 0;

			// On itere sur toutes les lignes du fichier
			for (String line = br.readLine(); line != null; line = br.readLine()) {

				if (gc == null) {
					// la grille n'est pas encore construite : c'est donc la
					// première ligne du fichier
					// elle contient deux entiers : nblig nbcol

					// séparer et parser les entiers
					String[] digits = line.split("\\s+"); // couper la string
															// sur les espaces
					int haut = Integer.parseInt(digits[0]);
					int larg = Integer.parseInt(digits[1]);
					System.out.println("Chargement grille " + haut + " lignes x " + larg + " colonnes \n");

					// initialiser la grille avec les bonnes hauteurs et largeur
					gc = new Grille(haut, larg);
					// NB: si le fichier est trop court on aura donc des cases
					// vides en bas, vu que ce constructeur initialise à vide.

				} else {
					// la grille est déjà construite, on y ajoute une ligne

					// éviter les problèmes de majuscule/minuscule
					String linelc = line.toLowerCase();
					// on itere sur les caractères, si la ligne est trop longue,
					// on ignore la fin.
					for (int col = 0; col < line.length() && col < gc.nbCol(); col++) {
						// mise à jour du contenu de la case
						gc.getCase(lig, col).setChar(linelc.charAt(col));
					}
					// on a lu une ligne de plus
					lig++;
					if (lig == gc.nbLig()) {
						// si le fichier est trop long, on ignore les lignes
						// restantes.
						break;
					}
				}
			}
		} catch (IOException e) {
			// Problème d'accès au fichier.
			e.printStackTrace();
		}
		return gc;
	}

	public static void saveGrille(Grille g, String path) {
		try {
			PrintWriter pw = new PrintWriter(path);
			pw.print(serialize(g,true));
			pw.close();
		} catch (IOException e) {
			System.err.println("Save Grille raised an IOException :" + e);
			e.printStackTrace();
		}
	}

	/**
	 * Fournit une représentation de la grille comme une String. L'affichage est
	 * contrôlé par le style, cf. méthode setStyle().
	 * 
	 * @param g
	 *            une grille
	 * @return une String représentant g.
	 */
	public static String serialize(Grille g, boolean isGrlFormat) {
		StringBuilder sb = new StringBuilder();

		String espace;
		if (! isGrlFormat)
			espace = " "; // version aérée
		else
			espace = ""; // version grl

		// Préface
		if (isGrlFormat) {
			sb.append(g.nbLig() + " " + g.nbCol() + "\n");
		} else {
			for (int i = 0; i < g.nbCol(); i++)
				sb.append("===");
			sb.append("\n");
		}

		// La grille
		for (int lig = 0; lig < g.nbLig(); lig++) {
			for (int col = 0; col < g.nbCol(); col++) {
				sb.append(espace + Character.toUpperCase(g.getCase(lig, col).getChar()) + espace);
			}
			sb.append("\n");
		}

		// post face
		if (! isGrlFormat) {
			for (int i = 0; i < g.nbCol(); i++)
				sb.append("===");
			sb.append("\n");
		}

		return sb.toString();
	}

}
