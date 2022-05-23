package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Classe repr�sentant le contexte d'un choix apr�s avoir �t� explor�
 * Les choix sont li�s entre eux de mani�re arborescente
 * 
 * @author Camille Palisoc
 *
 */
public class CurrContext {
	/**	r�gle choisie */
	private DeclAssertion choice;
	/** liste des buts � r�soudre */
	private List<Predicate> goals;
	/** liste des r�gles � explorer */
	private List<DeclAssertion> toExplore;
	/** environnement engendr� */
	private Environnement env;
	/** liste des choix suivants */
	private List<CurrContext> nextChoices;
	/** choix pr�c�dent */
	private CurrContext pere;
	
	/**
	 * Constructeur d'un contexte de choix fils
	 * @param choice : r�gle choisie
	 * @param goals : liste des buts
	 * @param rules : liste des r�gles
	 * @param env : environnement r�sultat
	 * @param p : choix pr�c�dent
	 */
	public CurrContext(DeclAssertion choice, List<Predicate> goals, List<DeclAssertion> rules, Environnement env, CurrContext p) {
		this.choice = choice;
		//pas besoin de copier parce que par construction, le goals pass� en param�tre est une nouvelle liste (dans solve du Jalon4)
		this.goals = goals;
		toExplore = rules;
		this.env = env;
		nextChoices = new ArrayList<>();
		pere = p;
	}
	
	/**
	 * Constructeur d'un contexte de choix racine
	 * @param goals : liste des buts
	 * @param rules : liste des r�gles
	 * @param env : environnement r�sultat
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
	 * @return liste de r�gles
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
	 * @return la liste des choix effectu�s exactement apr�s le choix courant
	 */
	public List<CurrContext> getNextChoices() {
		return nextChoices;
	}
	
	/**
	 * Ajoute choice dans la liste des choix effectu�s apr�s
	 * @param choice : choix fils
	 */
	public void addNextChoice(CurrContext choice) {
		nextChoices.add(choice);
	}
	
	/**
	 * @param next : r�gle candidate pour �tre un prochain fils
	 * @return true si cette r�gle a d�j� �t� choisie � partir de ce noeud courant, false sinon.
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
			sj.add("Choix : "+choice.toString()+" � la position "+choice.getPosition());
		}
		sj.add("    Buts � r�soudre : "+goals.toString());
		sj.add("    "+env.toString());
		return sj.toString();
	}
	
	public static void afficheChoice(CurrContext choix) {
		System.out.println(choix.toString());
	}
}
