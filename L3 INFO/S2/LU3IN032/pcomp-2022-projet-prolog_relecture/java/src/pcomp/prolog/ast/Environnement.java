package pcomp.prolog.ast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe représentant un environnement
 * 
 * @author Camille Palisoc, François-Xavier Drouard
 *
 */
public class Environnement {
	
	private Map<TermVariable,Term> env = new HashMap<>();
	
	
	/**
	 * Ajoute l'association correspondant à l'équantion dans l'environnement
	 * @param e : Equation de la forme TermVariable = Term
	 */
	public void addEnv(Equation e) {
		TermVariable key = (TermVariable)e.getGauche();
		env.put(key, e.getDroite());
	}

	/**
	 * Getter pour l'attribut env
	 * @return l'environnement
	 */
	public Map<TermVariable,Term> getEnv() {
		return env;
	}
	
	public void clear() {
		env.clear();
	}
	
	public boolean isEmpty() {
		return env.isEmpty();
	}
	
	/**
	 * Conserve les variables de l'environnement correspondant aux variables dans la liste
	 * Remplace et efface les variables intermédiaires s'il y en a
	 * @param list : liste de TermVariables
	 */
	public void nettoieEnv(List<TermVariable> list) {
		Map<TermVariable, Term> nouvEnv = new HashMap<>();
		for (TermVariable key : env.keySet()) {
			if (list.contains(key)) {
				Term p = env.get(key);
				while (p instanceof TermVariable) {
					p = env.get(p);
				}
				nouvEnv.put(key, p);
				
			}
		}
		env = nouvEnv;
	}

	@Override
	public String toString() {
		return "Environnement : "+env.toString();
	}
	
	/**
	 * Affiche sur la sortie standard l'environnement
	 */
	public void afficherEnv() {
		for (TermVariable key : env.keySet()) {
			System.out.println(key + " -> "+ env.get(key));
		}
		if (env.isEmpty()) {
			System.out.println("Pas d'environnement");
		}
	}
	
	/**
	 * Renvoie un nouvel environnement avec les mêmes termes
	 * @return une copy de l'environnement
	 */
	public Environnement copy() {
		Environnement res = new Environnement();
		for (TermVariable var : env.keySet()) {
			res.addEnv(new Equation(var,env.get(var)));
		}
		return res;
	}
}
