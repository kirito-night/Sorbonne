package pcomp.prolog.ast;

import java.util.List;

import pcomp.prolog.ast.excep.NoSolutionException;

/**
 * Classe représentant une équation
 * 
 * @author Camille Palisoc, François-Xavier Drouard
 *
 */
public class Equation {
	
	private Term gauche, droite;
	
	
	/**
	 * Constructeur
	 * @param g : membre gauche
	 * @param d : membre droit
	 */
	public Equation(Term g, Term d) {
		gauche = g;
		droite = d;
	}
	
	// Regles d'unification
	
	/**
	 * Applique, si possible, la règle effacer de l'unification
	 * @param s : système dans lequel appliquer la règle
	 * @return true si la règle d'effacement a été appliquée, false sinon
	 */
	public boolean effacer(Systeme s) {
		if (gauche.equals(droite)) {
			s.removeEquation(this);
			return true;
		}
		return false;
	}
	
	/**
	 * Applique, si possible, la règle orienter de l'unification
	 * Crée une nouvelle équation et se supprime du système
	 * @param s : système dans lequel appliquer la règle
	 * @return true si la règle d'orientation a été appliquée, false sinon
	 */
	public boolean orienter(Systeme s) {
		if (droite instanceof TermVariable && gauche instanceof TermPredicate) {
			Equation nouv = new Equation(droite, gauche);
			s.addEquation(nouv);
			s.removeEquation(this);
			return true;
		}
		return false;
	}
	
	/**
	 * Applique, si possible, la règle décomposer de l'unification
	 * Lance l'exception NoSolutionException en précisant l'équation
	 * si on les prédicats de l'équation sont de symboles différents
	 * donc la résolution n'est pas possible
	 * @param s : système dans lequel appliquer la règle
	 * @return true si la règle de décomposition a été appliquée, false sinon
	 */
	public boolean decomposer(Systeme s) {
		//comparaison des symboles si on a des predicats a gauche et a droite
		if (gauche instanceof TermPredicate && droite instanceof TermPredicate) {
			Predicate p1 = ((TermPredicate)gauche).getPredicate();
			Predicate p2 = ((TermPredicate)droite).getPredicate();
			if (p1.getSymbol().equals(p2.getSymbol())) {
				// Décomposition possible!
				List<Term> args1 = p1.getArguments();
				List<Term> args2 = p2.getArguments();
				// ajout des nouvelles équations à résoudre
				for (int i = 0;i<args1.size();i++) {
					s.addEquation(new Equation(args1.get(i),args2.get(i)));
				}
				s.removeEquation(this);
				return true;
			} else {
				throw new NoSolutionException("Décomposition impossible sur l'Equation "+toString());
			}
		}
		return false;
	}
	
	/**
	 * Applique, si possible, la règle remplacer de l'unification sur l'équation courant avec e
	 * @param s : système dans lequel appliquer la règle
	 * @param x : variable à remplacer dans l'équation
	 * @param nouv : le terme par lequel remplacer la variable x
	 * @return true si la règle d'effacement a été appliquée, false sinon
	 */
	public boolean subst(Systeme s, TermVariable x, Term nouv) {
		// vérification occur_check
		Equation e = new Equation(x,nouv);
		e.occur_check();
		boolean replaced = false;
		if (droite instanceof TermVariable) {
			if (droite.equals(x)) {
				// on remplace
				replaced = true;
				droite = nouv;
			}
		} else {
			// c'est un TermPredicate
			replaced = ((TermPredicate)droite).subst((TermVariable)x, nouv);
		}
		if (gauche instanceof TermVariable) {
			if (gauche.equals(x)) {
				// on remplace
				replaced = true;
				gauche = nouv;
			}
		} else {
			// c'est un TermPredicate
			replaced = ((TermPredicate)gauche).subst((TermVariable)x, nouv);
		}
		return replaced;
	}
	
	
	/**
	 * Vérifie que le TermVariable à gauche n'est pas présent dans le terme droit
	 * Appelée avant d'être utilisée pour la règle remplacer,
	 * l'équation est de forme TermVariable = Term
	 * @return false si l'équation passe le test, lance l'exception NoSolutionException sinon
	 */
	private boolean occur_check() {
		VisitorVar v = new VisitorVar();
		List<TermVariable> vars = droite.accept(v);
		if (vars.contains(gauche)) {
			throw new NoSolutionException("OccurCheck true : "+this.toString());
		}
		return false;
	}
	
	/**
	 * Vérifie que l'équation peut être mise dans l'environnement
	 * @return true si l'équation peut être mise dans l'environnement, lance l'exception NoSolutionException sinon.
	 */
	public boolean formatROK() {
		return gauche instanceof TermVariable && !occur_check();
	}
	
	
	//Getters
	
	public Term getDroite() {
		return droite;
	}
	
	public Term getGauche() {
		return gauche;
	}
	

	// Méthodes usuelles
	
	@Override
	public String toString() {
		return gauche.toString() + " = " + droite.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Equation) {
			Equation other = (Equation)o;
			return (droite.equals(other.droite)) && (gauche.equals(other.gauche));
		}
		return false;
	}
	
	/**
	 * Renvoie une copie de l'équation
	 * @return une copie de l'équation courante
	 */
	public Equation copy() {
		return new Equation(this.gauche.copy(),this.droite.copy());
	}

}
