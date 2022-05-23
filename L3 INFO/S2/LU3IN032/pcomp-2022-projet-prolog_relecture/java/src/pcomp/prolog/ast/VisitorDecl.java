package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Visiteur utilisé pour séparer les faits et règles des buts et les récupérer
 * 
 * @author Camille Palisoc
 *
 */
public class VisitorDecl implements DeclVisitor<List<Predicate>> {
	
	private List<Predicate> buts = new ArrayList<>();
	private List<Predicate> faits = new ArrayList<>();
	private List<DeclAssertion> regles = new ArrayList<>();
	/** pour lancer l'exception que dans le cas des premiers interpretes. true par défaut */
	private boolean faitsOnly;
	
	public VisitorDecl(boolean faitsOnly) {
		this.faitsOnly = faitsOnly;
	}
	
	public VisitorDecl() {
		this(true);
	}

	@Override
	public List<Predicate> visit(DeclAssertion a) {
		//on ne prend que les faits
		if (a.getPredicates().isEmpty()) {
			faits.add(a.getHead());
		} else {
			if (faitsOnly) {
				throw new IllegalArgumentException("Il n'a pas que des faits.");
			}
		}
		regles.add(a);
		return faits;
	}

	@Override
	public List<Predicate> visit(DeclGoal a) {
		buts.addAll(a.getPredicates());
		return buts;
	}
	
	// Getters
	
	public List<Predicate> getFaits() {
		return faits;
	}
	
	public List<Predicate> getButs() {
		return buts;
	}
	
	public List<DeclAssertion> getRegles() {
		return regles;
	}

}
