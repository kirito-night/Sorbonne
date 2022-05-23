package pcomp.prolog.ast;

import java.util.List;

import pcomp.prolog.ast.excep.NoSolutionException;

/**
 * Classe repr�sentant une �quation
 * 
 * @author Camille Palisoc, Fran�ois-Xavier Drouard
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
	 * Applique, si possible, la r�gle effacer de l'unification
	 * @param s : syst�me dans lequel appliquer la r�gle
	 * @return true si la r�gle d'effacement a �t� appliqu�e, false sinon
	 */
	public boolean effacer(Systeme s) {
		if (gauche.equals(droite)) {
			s.removeEquation(this);
			return true;
		}
		return false;
	}
	
	/**
	 * Applique, si possible, la r�gle orienter de l'unification
	 * Cr�e une nouvelle �quation et se supprime du syst�me
	 * @param s : syst�me dans lequel appliquer la r�gle
	 * @return true si la r�gle d'orientation a �t� appliqu�e, false sinon
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
	 * Applique, si possible, la r�gle d�composer de l'unification
	 * Lance l'exception NoSolutionException en pr�cisant l'�quation
	 * si on les pr�dicats de l'�quation sont de symboles diff�rents
	 * donc la r�solution n'est pas possible
	 * @param s : syst�me dans lequel appliquer la r�gle
	 * @return true si la r�gle de d�composition a �t� appliqu�e, false sinon
	 */
	public boolean decomposer(Systeme s) {
		//comparaison des symboles si on a des predicats a gauche et a droite
		if (gauche instanceof TermPredicate && droite instanceof TermPredicate) {
			Predicate p1 = ((TermPredicate)gauche).getPredicate();
			Predicate p2 = ((TermPredicate)droite).getPredicate();
			if (p1.getSymbol().equals(p2.getSymbol())) {
				// D�composition possible!
				List<Term> args1 = p1.getArguments();
				List<Term> args2 = p2.getArguments();
				// ajout des nouvelles �quations � r�soudre
				for (int i = 0;i<args1.size();i++) {
					s.addEquation(new Equation(args1.get(i),args2.get(i)));
				}
				s.removeEquation(this);
				return true;
			} else {
				throw new NoSolutionException("D�composition impossible sur l'Equation "+toString());
			}
		}
		return false;
	}
	
	/**
	 * Applique, si possible, la r�gle remplacer de l'unification sur l'�quation courant avec e
	 * @param s : syst�me dans lequel appliquer la r�gle
	 * @param x : variable � remplacer dans l'�quation
	 * @param nouv : le terme par lequel remplacer la variable x
	 * @return true si la r�gle d'effacement a �t� appliqu�e, false sinon
	 */
	public boolean subst(Systeme s, TermVariable x, Term nouv) {
		// v�rification occur_check
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
	 * V�rifie que le TermVariable � gauche n'est pas pr�sent dans le terme droit
	 * Appel�e avant d'�tre utilis�e pour la r�gle remplacer,
	 * l'�quation est de forme TermVariable = Term
	 * @return false si l'�quation passe le test, lance l'exception NoSolutionException sinon
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
	 * V�rifie que l'�quation peut �tre mise dans l'environnement
	 * @return true si l'�quation peut �tre mise dans l'environnement, lance l'exception NoSolutionException sinon.
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
	

	// M�thodes usuelles
	
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
	 * Renvoie une copie de l'�quation
	 * @return une copie de l'�quation courante
	 */
	public Equation copy() {
		return new Equation(this.gauche.copy(),this.droite.copy());
	}

}
