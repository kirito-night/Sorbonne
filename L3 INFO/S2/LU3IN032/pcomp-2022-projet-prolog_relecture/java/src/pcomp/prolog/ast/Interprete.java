package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

import pcomp.Gui.Question;
import pcomp.Gui.Tools;
import pcomp.prolog.ast.excep.NoSolutionException;
import pcomp.prolog.ast.excep.SolutionFound;

/**
 * Classe rassemblant les algorithmes n�cessaires � l'interpr�tation d'un arbre syntaxique abstrait
 * 
 * @author Camille Palisoc
 *
 */
public class Interprete {
	
	// Interpretes
	
	/**
	 * Interprete qui analyse un programme avec 1 but et 1 fait.
	 * Lance IllegalArgumentException si le programme ne respecte pas ces conditions
	 * @param ast : AST � interpr�ter
	 * @return environnement solution, lance l'exception NoSolutionException si le probl�me n'est pas satisfiable
	 */
	public static Environnement interprete0(Program ast) {
		// r�cup�ration des faits et buts
		List<Decl> decls = ast.getDeclarations();
		VisitorDecl v = new VisitorDecl();
		for (Decl d : decls) {
			d.accept(v);
		}
		List<Predicate> faits = v.getFaits();
		List<Predicate> buts = v.getButs();
		
		// verification que ast contient un seul fait et un seul but
		if (faits.size() != 1) {
			throw new IllegalArgumentException("Nombre de faits incorrect. Il n'en faut qu'un.");
		}
		if (buts.size() != 1) {
			throw new IllegalArgumentException("Nombre de buts incorrect. Il n'en faut qu'un");
		}
		
		// unification
		Equation eq = new Equation(new TermPredicate(faits.get(0),faits.get(0).getPosition()),
				new TermPredicate(buts.get(0),buts.get(0).getPosition()));
		Systeme s = new Systeme();
		s.addEquation(eq);
		s.unify();
		
		return s.getEnv();
	}
	
	/**
	 * Interprete qui analyse un programme avec 1 but et plusieurs faits, un par symbole de pr�dicat
	 * Lance IllegalArgumentException si le programme ne respecte pas ces conditions
	 * @param ast : AST � interpr�ter
	 * @return environnement solution, lance l'exception NoSolutionException si le probl�me n'est pas satisfiable
	 */
	public static Environnement interprete1(Program ast) {
		List<Decl> decls = ast.getDeclarations();
		
		// s�paration des buts et faits
		VisitorDecl v = new VisitorDecl();
		for (Decl d : decls) {
			d.accept(v);
		}
		List<Predicate> faits = v.getFaits();
		List<Predicate> buts = v.getButs();
		if (buts.size() != 1) {
			throw new IllegalArgumentException("Il y a trop de buts. Il n'en faut qu'un.");
		}
		// v�rification qu'il y a qu'une regle par symbole de pr�dicat
		checkSymbols(v.getRegles());
		
		// recherche du fait avec le bon pr�dicat
		for (Predicate p : faits) {
			if (p.getSymbol().equals(buts.get(0).getSymbol())) {
				
				// unification
				Equation eq = new Equation(new TermPredicate(p,p.getPosition()),
						new TermPredicate(buts.get(0),buts.get(0).getPosition()));
				Systeme s = new Systeme();
				s.addEquation(eq);
				s.unify();
				return s.getEnv();
			}
		}
		Environnement res = new Environnement();
		return res;
	}
	
	/**
	 * Interprete qui analyse un programme avec plusieurs buts et plusieurs faits, un par symbole de pr�dicat
	 * Lance IllegalArgumentException si le programme ne respecte pas ces conditions
	 * @param ast : AST � interpr�ter
	 * @return environnement solution, lance l'exception NoSolutionException si le probl�me n'est pas satisfiable
	 */
	public static Environnement interprete2(Program ast) {
		List<Decl> decls = ast.getDeclarations();
		
		// s�paration des buts et faits
		VisitorDecl v = new VisitorDecl();
		for (Decl d : decls) {
			d.accept(v);
		}
		List<Predicate> faits = v.getFaits();
		List<Predicate> buts = v.getButs();
		// v�rification qu'il y a qu'une regle par symbole de pr�dicat
		checkSymbols(v.getRegles());
		
		//pour tous les buts, on cherche un fait de m�me symbole
		Systeme s = new Systeme();
		for (Predicate b : buts) {
			for (Predicate p : faits) {
				if (p.getSymbol().equals(b.getSymbol())) {
					Equation eq = new Equation(new TermPredicate(p,p.getPosition()),
							new TermPredicate(b,b.getPosition()));
					s.addEquation(eq);
				}
			}
		}
		s.afficherSysteme();
		s.unify();
		return s.getEnv();
	}
	
	/**
	 * Interprete qui analyse un programme avec 1 but et plusieurs r�gles, une par symbole de pr�dicat
	 * Lance IllegalArgumentException si le programme ne respecte pas ces conditions
	 * @param ast : AST � interpr�ter
	 * @return environnement solution, lance l'exception NoSolutionException si le probl�me n'est pas satisfiable
	 */
	public static Environnement interprete3(Program ast) {
		// s�paration des buts et regles
		VisitorDecl v = new VisitorDecl(false);
		List<Decl> decls = ast.getDeclarations();
		for (Decl d : decls) {
			d.accept(v);
		}
		List<Predicate> goals = v.getButs();
		List<DeclAssertion> rules = v.getRegles();
		
		// v�rification qu'il y a qu'une regle par symbole de pr�dicat
		checkSymbols(rules);
		
		// r�solution :
		return solve(goals,rules);
	}
	
	/**
	 * Interprete qui analyse un programme avec plusieurs buts et r�gles
	 * Effectue du backtracking pour trouver une solution
	 * @param ast : AST � interpr�ter
	 * @return environnement solution, lance l'exception NoSolutionException si le probl�me n'est pas satisfiable
	 */
	public static Environnement interprete4(Program ast) {
		// s�paration des Decl du Program
		VisitorDecl separator = new VisitorDecl(false);
		for (Decl d : ast.getDeclarations()) {
			d.accept(separator);
		}
		return solve(new CurrContext(separator.getButs(),separator.getRegles(),new Environnement()),
				separator.getButs(), separator.getRegles(), new Environnement());
	}
	
	/**
	 * Interprete qui analyse un programme avec plusieurs buts et r�gles
	 * Cherche plusieurs solutions si l'utilisateur le veut
	 * Effectue du backtracking pour trouver une solution
	 * @param ast : AST � interpr�ter
	 * @return la liste des environnements pouvant r�pondre au probl�me, lance l'exception NoSolutionException si le probl�me n'est pas satisfiable
	 */
	public static List<Environnement> interprete5(Program ast) {
		//s�paration des Decl
		VisitorDecl separator = new VisitorDecl(false);
		for (Decl d : ast.getDeclarations()) {
			d.accept(separator);
		}
		List<Predicate> goals = separator.getButs();
		List<DeclAssertion> rules = separator.getRegles();
		
		// r�solution
		CurrContext ch = new CurrContext(goals,rules,new Environnement());
		
		return multiSolve(ch);
	}
	
	
	// Algorithmes
	
	/**
	 * V�rifie qu'il y a un seul fait par symbole de pr�dicat
	 * Lance IllegalArgumentException si ce n'est pas le cas
	 * @param rules : liste de r�gles
	 */
	private static void checkSymbols(List<DeclAssertion> rules) {
		// v�rification qu'il y a qu'une regle par symbole de pr�dicat
		List<String> symbols = new ArrayList<>();
		for (DeclAssertion r : rules) {
			if (symbols.contains(r.getHead().getSymbol())) {
				throw new IllegalArgumentException("Il faut un fait par symbole de pr�dicat.");
			}
			symbols.add(r.getHead().getSymbol());
		}
	}
	
	// Utile pour le nettoyage de l'environnement
	/**
	 * R�colte les variables des buts
	 * @param goals : liste de buts
	 * @return la liste de variables � r�soudre dans les buts
	 */
	private static List<TermVariable> vars(List<Predicate> goals) {
		VisitorVar v = new VisitorVar();
		List<TermVariable> res = new ArrayList<>();
		TermPredicate tmp;
		for (Predicate p : goals) {
			tmp = new TermPredicate(p, p.getPosition());
			res.addAll(tmp.accept(v));
		}
		return res;
	}
	
	/**
	 * Choisit la bonne r�gle pour r�soudre le but
	 * @param n : compteur pour le renommage
	 * @param v : environnement
	 * @param but : but � r�soudre
	 * @param rules : liste des r�gles
	 * @param nouvGoals : liste des nouveaux buts � r�soudre
	 * @return l'environnement solution, lance NoSolutionException si le probl�me n'est pas satisfiable
	 */
	public static Environnement choose(int n, Environnement v, Predicate but, List<DeclAssertion> rules, List<Predicate> nouvGoals) {
		//choose fait aussi l'unification pour les faits
		for (DeclAssertion d : rules) {
			if (d.getHead().getSymbol().equals(but.getSymbol())) {
				// on match, donc on renomme
				DeclAssertion renamed = d.rename(n);
				Systeme s = new Systeme();
				s.setEnv(v.copy()); // copie de l'environnement pour ne pas modifier celui pass� en param�tre
				s.addEquation(new Equation(
						new TermPredicate(renamed.getHead(),renamed.getPosition()),
						new TermPredicate(but,but.getPosition())));
				s.unify();
				nouvGoals.addAll(renamed.getPredicates());
				return s.getEnv();
			}
		}
		throw new NoSolutionException("pas d'environnement correspondant pour le but "+but);
	}
	
	/**
	 * R�sout le probl�me d�crit par les param�tres
	 * @param goals : liste de buts � r�soudre
	 * @param rules : liste de r�gles
	 * @return l'environnement solution, lance NoSolutionException si le probl�me n'est pas satisfiable
	 */
	public static Environnement solve(List<Predicate> goals, List<DeclAssertion> rules) {
		Environnement res = new Environnement();
		int cpt = 1;
		List<TermVariable> vars = vars(goals);
		while (!goals.isEmpty()) {
			Tools.addText("Buts � v�rifier : "+goals);
			res = choose(cpt,res,goals.get(0),rules,goals);
			goals.remove(0);
			cpt++;
		}
		res.nettoieEnv(vars);
		return res;
	}
	
	/**
	 * Choisit la r�gle � appliquer.
	 * M�thode pour le backtracking
	 * L�ve l'exception SolutionFound si une solution est trouv�e,
	 * si toutes les r�gles sont parcourues et aucune solution n'est trouv�e, NoSolutionException est lev�e
	 * @param cpt : compteur
	 * @param ch : choix courant, sous forme arborescente
	 * @param goals : liste de buts
	 * @param rules : liste de r�gles
	 * @param env : environnement du choix
	 */
	private static void choose(int cpt, CurrContext ch, List<Predicate> goals, List<DeclAssertion> rules, Environnement env) {
		//si goals non vide :
		if (goals.isEmpty()) {
			throw new SolutionFound(ch);
		}
		//r�solution du premier but
		Predicate but = goals.get(0);
		Tools.addText("R�solution de "+but+" � la position "+but.getPosition());
		for (DeclAssertion r : rules) {
			Predicate head = r.getHead();
			if (head.getSymbol().equals(but.getSymbol()) && !ch.inNextChoices(r)) {
				//on choisit la r�gle r pour le CurrContext � empiler dans ch
				//on renomme
				DeclAssertion renamed = r.rename(cpt);
				cpt++;
				head = renamed.getHead();
				//unification
				Systeme s = new Systeme();
				s.setEnv(env.copy());
				s.addEquation(new Equation(
						new TermPredicate(head, head.getPosition()),
						new TermPredicate(but, but.getPosition())));
				try {
					s.unify();
				} catch (NoSolutionException excep) {
					Tools.addText(excep.getMessage());
					continue;
				}
				
				//il y a une solution :
				//cr�ation du contexte avec les nouveaux buts, dans une nouvelle liste
				List<Predicate> nouvGoals = new ArrayList<>();
				nouvGoals.addAll(goals);
				//on retire le but qui vient d'�tre partiellement r�solu
				nouvGoals.remove(but);
				nouvGoals.addAll(renamed.getPredicates());
				CurrContext choix = new CurrContext(r, nouvGoals, rules, s.getEnv(), ch);
				ch.addNextChoice(choix);
				try {
					choose(cpt++, choix, choix.getGoals(), choix.getRules(), choix.getEnv());
				} catch (NoSolutionException excep) {
					//le dernier choix effectu� n'aboutit pas donc on le d�pile
					ch.getNextChoices().remove(choix); //� commenter si on veut garder les choix inutiles
					//on continue le parcours des r�gles
					continue;
				}
			}
		}
		
		//on n'a pas pu trouver de match, on lance une exception pour revenir � l'appel pr�c�dent
		//si on est au tout premier appel, l'exception n'est pas rattrap�e
		throw new NoSolutionException("probleme non satisfiable");
	}
	
	/**
	 * R�sout le probl�me d�crit par les param�tres
	 * @param ch : choix courant, initialement une racine
	 * @param goals : liste des buts � r�soudre
	 * @param rules : liste des r�gles
	 * @param env : environnement, initialement vide
	 * @return environnement solution, l�ve l'exception NoSolutionException si le probl�me n'est pas satisfiable
	 */
	public static Environnement solve(CurrContext ch, List<Predicate> goals, List<DeclAssertion> rules, Environnement env) {
		try {
			choose(0, ch, goals, rules, env);
		} catch (SolutionFound sol) {
			Tools.addText(sol.getMessage());
			Tools.addText("Journal des choix :");
			Tools.addText(sol.getFinalChoice().toString());
			Environnement res = sol.getFinalChoice().getEnv();
			res.nettoieEnv(vars(goals));
			return res;
		}
		env.nettoieEnv(vars(goals));
		return env;
	}
	
	/**
	 * Cherche autant de solutions que veut l'utilisateur, autant qu'il y en a
	 * @param ch : racine des choix
	 * @return la liste d'environnements pouvant r�soudre le probl�me d�crit par les attributs de ch
	 */
	public static List<Environnement> multiSolve(CurrContext ch) {
		List<Environnement> res = new ArrayList<>();
		boolean more = true;
		try {
			while (more) {
				List<TermVariable> var = vars(ch.getGoals());
				try {
					choose(0,ch,ch.getGoals(),ch.getRules(),ch.getEnv());
				} catch (SolutionFound sol) {
					//afficher solution
					sol.getFinalChoice().getEnv().nettoieEnv(var);
					res.add(sol.getFinalChoice().getEnv());
					Tools.addText(sol.getFinalChoice().toString());
					Tools.addText(sol.getFinalChoice().getEnv().toString());
					//demander si utilisateur veut chercher d'autres solutions
					more = Question.choix("Solution trouv�e :\n"+
							sol.getFinalChoice().toString()+"\n"+
							"\n\nVoulez-vous chercher d'autres solutions?");
				} 
			}
		} catch (NoSolutionException excep) {
			if (!res.isEmpty()) {
				Tools.addText("Pas d'autres solutions");
			} else {
				throw new NoSolutionException(excep.getMessage());
			}
		}
		
		return res;
	}
}
