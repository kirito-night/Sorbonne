package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un système d'équations
 * 
 * @author Camille Palisoc, François-Xavier Drouard
 *
 */
public class Systeme {
	
	private List<Equation> eqs = new ArrayList<>();
	private Environnement env = new Environnement();
	
	// Méthodes pour gérer la liste d'équations
	
	/**
	 * Ajoute e dans la liste d'équations
	 * @param e : Equation à ajouter
	 */
	public void addEquation(Equation e) {
		eqs.add(e);
	}
	
	/**
	 * Retire l'équation du système
	 * @param e : Equation à retirer
	 */
	public void removeEquation(Equation e) {
		eqs.remove(e);
	}
	
	/**
	 * Getter de l'Equation à l'indice index
	 * @param index : position de l'équation dans la liste
	 * @return Equation à l'indice index
	 */
	public Equation getEq(int index) {
		return eqs.get(index);
	}
	
	/**
	 * Renvoie une copie en surface de la liste d'Equation
	 * Utilisée pour le parcours de la liste d'équations dans l'application des règles d'unification
	 * @return copie de la liste d'équations
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
	 * Affiche sur la sortie standard les équations du système et l'environnement
	 */
	public void afficherSysteme() {
		System.out.println("Affichage du systeme :");
		for (Equation e : eqs) {
			System.out.println(e);
		}
		if (eqs.isEmpty()) {
			System.out.println("Pas d'équations");
		}
		env.afficherEnv();
	}
	
	/**
	 * Getter pour l'attribut env
	 * @return environnement associé
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
	
	// Règles d'unification
	
	/**
	 * Applique, si possible, la règle décomposer sur une équation du système
	 * @return true si la règle a pu être appliquée
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
	 * Applique, si possible, la règle effacer sur une équation du système
	 * @return true si la règle a pu être appliquée
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
	 * Applique, si possible, la règle orienter sur une équation du système
	 * @return true si la règle a pu être appliquée
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
	 * Réccupère et stocke dans l'environnement les équations de la forme TermVariable = Term
	 * @return true si la règle a pu être appliquée
	 */
	private boolean remplacer() {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		for (Equation e : oldSys) {
		// si e est de la forme TermVariable = Term, on peut l'ajouter à l'environnement
			if (e.formatROK()) {
				// ajout dans l'environnement
				env.addEnv(e);
				replaced = true;
			}
		}
		return replaced;
	}
	
	/**
	 * Applique la règle remplacer sur les équations du système avec le contenu de l'environnement
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
	 * Applique les règles d'unification sur le système d'équations
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
