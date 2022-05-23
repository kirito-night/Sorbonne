package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Classe représentant le contexte d'un choix après avoir été exploré
 * Les choix sont liés entre eux de manière arborescente
 * 
 * @author Camille Palisoc
 *
 */
public class CurrContext {
	/**	règle choisie */
	private DeclAssertion choice;
	/** liste des buts à résoudre */
	private List<Predicate> goals;
	/** liste des règles à explorer */
	private List<DeclAssertion> toExplore;
	/** environnement engendré */
	private Environnement env;
	/** liste des choix suivants */
	private List<CurrContext> nextChoices;
	/** choix précédent */
	private CurrContext pere;
	
	/**
	 * Constructeur d'un contexte de choix fils
	 * @param choice : règle choisie
	 * @param goals : liste des buts
	 * @param rules : liste des règles
	 * @param env : environnement résultat
	 * @param p : choix précédent
	 */
	public CurrContext(DeclAssertion choice, List<Predicate> goals, List<DeclAssertion> rules, Environnement env, CurrContext p) {
		this.choice = choice;
		//pas besoin de copier parce que par construction, le goals passé en paramètre est une nouvelle liste (dans solve du Jalon4)
		this.goals = goals;
		toExplore = rules;
		this.env = env;
		nextChoices = new ArrayList<>();
		pere = p;
	}
	
	/**
	 * Constructeur d'un contexte de choix racine
	 * @param goals : liste des buts
	 * @param rules : liste des règles
	 * @param env : environnement résultat
	 */
	public CurrContext(List<Predicate> goals, List<DeclAssertion> rules, Environnement env) {
		this.choice = null;
		this.goals = goals;
		toExplore = rules;
		this.env = env;
		nextChoices = new ArrayList<>();
		pere = null;
	}
	
	// Getters
	
	/**
	 * Getter pour l'attribut choice
	 * @return choix
	 */
	public DeclAssertion getChoice() {
		return choice;
	}
	
	/**
	 * Getter pour l'attribut goals
	 * @return liste de buts
	 */
	public List<Predicate> getGoals() {
		return goals;
	}
	
	/**
	 * Getter pour l'attribut rules
	 * @return liste de règles
	 */
	public List<DeclAssertion> getRules() {
		return toExplore;
	}
	
	/**
	 * Getter pour l'attribut env
	 * @return environnement du contexte
	 */
	public Environnement getEnv() {
		return env;
	}
	
	/**
	 * Getter pour l'attribut nextChoices
	 * @return la liste des choix effectués exactement après le choix courant
	 */
	public List<CurrContext> getNextChoices() {
		return nextChoices;
	}
	
	/**
	 * Ajoute choice dans la liste des choix effectués après
	 * @param choice : choix fils
	 */
	public void addNextChoice(CurrContext choice) {
		nextChoices.add(choice);
	}
	
	/**
	 * @param next : règle candidate pour être un prochain fils
	 * @return true si cette règle a déjà été choisie à partir de ce noeud courant, false sinon.
	 */
	public boolean inNextChoices(DeclAssertion next) {
		for (CurrContext c : nextChoices) {
			if (next.equals(c.getChoice())) {
				return true;
			}
		}
		return false;
	}
	
	// Fonctions d'affichages
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner("\n");
		if (pere != null) {
			sj.add(pere.toString());
		}
		if (choice == null) {
			sj.add("Racine :");
		} else {
			sj.add("Choix : "+choice.toString()+" à la position "+choice.getPosition());
		}
		sj.add("    Buts à résoudre : "+goals.toString());
		sj.add("    "+env.toString());
		return sj.toString();
	}
	
	public static void afficheChoice(CurrContext choix) {
		System.out.println(choix.toString());
	}
}
