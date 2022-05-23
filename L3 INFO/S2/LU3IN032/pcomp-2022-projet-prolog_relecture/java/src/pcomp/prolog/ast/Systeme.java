package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe repr�sentant un syst�me d'�quations
 * 
 * @author Camille Palisoc, Fran�ois-Xavier Drouard
 *
 */
public class Systeme {
	
	private List<Equation> eqs = new ArrayList<>();
	private Environnement env = new Environnement();
	
	// M�thodes pour g�rer la liste d'�quations
	
	/**
	 * Ajoute e dans la liste d'�quations
	 * @param e : Equation � ajouter
	 */
	public void addEquation(Equation e) {
		eqs.add(e);
	}
	
	/**
	 * Retire l'�quation du syst�me
	 * @param e : Equation � retirer
	 */
	public void removeEquation(Equation e) {
		eqs.remove(e);
	}
	
	/**
	 * Getter de l'Equation � l'indice index
	 * @param index : position de l'�quation dans la liste
	 * @return Equation � l'indice index
	 */
	public Equation getEq(int index) {
		return eqs.get(index);
	}
	
	/**
	 * Renvoie une copie en surface de la liste d'Equation
	 * Utilis�e pour le parcours de la liste d'�quations dans l'application des r�gles d'unification
	 * @return copie de la liste d'�quations
	 */
	private List<Equation> copie() {
		List<Equation> res = new ArrayList<>();
		for (Equation e : eqs) {
			res.add(e);
		}
		return res;
	}
	
	public int size() {
		return eqs.size();
	}
	
	/**
	 * Affiche sur la sortie standard les �quations du syst�me et l'environnement
	 */
	public void afficherSysteme() {
		System.out.println("Affichage du systeme :");
		for (Equation e : eqs) {
			System.out.println(e);
		}
		if (eqs.isEmpty()) {
			System.out.println("Pas d'�quations");
		}
		env.afficherEnv();
	}
	
	/**
	 * Getter pour l'attribut env
	 * @return environnement associ�
	 */
	public Environnement getEnv() {
		return env;
	}
	
	/**
	 * Setter pour l'attribut env
	 * @param e : nouvel environnement
	 */
	public void setEnv(Environnement e) {
		env = e;
	}
	
	// R�gles d'unification
	
	/**
	 * Applique, si possible, la r�gle d�composer sur une �quation du syst�me
	 * @return true si la r�gle a pu �tre appliqu�e
	 */
	private boolean decomposer() {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		for (Equation e : oldSys) {
			replaced = replaced || e.decomposer(this);
		}
		return replaced;
	}
	
	/**
	 * Applique, si possible, la r�gle effacer sur une �quation du syst�me
	 * @return true si la r�gle a pu �tre appliqu�e
	 */
	private boolean effacer() {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		for (Equation e : oldSys) {
			replaced = replaced || e.effacer(this);
		}
		return replaced;
	}
	
	/**
	 * Applique, si possible, la r�gle orienter sur une �quation du syst�me
	 * @return true si la r�gle a pu �tre appliqu�e
	 */
	private boolean orienter() {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		for (Equation e : oldSys) {
			replaced = replaced || e.orienter(this);
		}
		return replaced;
	}
	
	/**
	 * R�ccup�re et stocke dans l'environnement les �quations de la forme TermVariable = Term
	 * @return true si la r�gle a pu �tre appliqu�e
	 */
	private boolean remplacer() {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		for (Equation e : oldSys) {
		// si e est de la forme TermVariable = Term, on peut l'ajouter � l'environnement
			if (e.formatROK()) {
				// ajout dans l'environnement
				env.addEnv(e);
				replaced = true;
			}
		}
		return replaced;
	}
	
	/**
	 * Applique la r�gle remplacer sur les �quations du syst�me avec le contenu de l'environnement
	 */
	private void subst() {
		// regleapp sert de condition d'arret de notre boucle d'unification
		boolean replaced = true;
		List<Equation> oldSys;
		while (replaced) {
			replaced = false;
			oldSys = copie();
			for (TermVariable key : env.getEnv().keySet()) {
				for (Equation e : oldSys) {
					replaced = e.subst(this, key, env.getEnv().get(key)) || replaced;
				}
			}
		}
	}
	
	/**
	 * Applique les r�gles d'unification sur le syst�me d'�quations
	 */
	public void unify() {
		// regleapp sert de condition d'arret de notre boucle d'unification
		boolean regleapp = true;
		
		while (regleapp && !eqs.isEmpty()) {
			regleapp = false; //aucune regle n'a ete appliquee sur le systeme pendant ce tour
			// Application des regles
			subst();
			regleapp = regleapp || effacer();
			regleapp = regleapp || orienter();
			regleapp = regleapp || decomposer();
			regleapp = regleapp || remplacer();
		}
		
	}

}
