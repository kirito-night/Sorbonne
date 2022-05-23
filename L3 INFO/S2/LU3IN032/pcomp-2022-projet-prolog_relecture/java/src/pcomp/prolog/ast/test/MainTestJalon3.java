package pcomp.prolog.ast.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pcomp.prolog.ast.Decl;
import pcomp.prolog.ast.DeclAssertion;
import pcomp.prolog.ast.Environnement;
import pcomp.prolog.ast.Interprete;
import pcomp.prolog.ast.Predicate;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.VisitorDecl;
import pcomp.prolog.ast.excep.NoSolutionException;
import pcomp.prolog.parser.PrologParser;

/**
 * Classe de tests pour surveiller, à travers l'affichage sur la sortie standard, le renommage des variables
 * 
 * @author Camille Palisoc
 *
 */
public class MainTestJalon3 {
	
	public static void main(String[] args) throws IOException {
		Program prog1 = PrologParser.parseFile("tests_jalon_pl/interprete3_test1.pl");
		List<Decl> decls = prog1.getDeclarations();
		
		// test de rename
		List<Decl> renamed = new ArrayList<>();
		int n = 1;
		for (Decl d : decls) {
			if (d instanceof DeclAssertion) {
				renamed.add(((DeclAssertion) d).rename(n++));
			}
		}
		System.out.println(renamed);
		
		// test de choose
		List<Predicate> nouvGoals = new ArrayList<>();
		VisitorDecl v = new VisitorDecl(false);
		for (Decl d : decls) {
			d.accept(v);
		}
		
		Environnement e = new Environnement();
		try {
			Environnement env = Interprete.choose(n, e, v.getButs().get(0), v.getRegles(), nouvGoals);
			System.out.println("goals : "+nouvGoals);
			env.afficherEnv();
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		
		//solve
		try {
			Environnement env = Interprete.solve(v.getButs(), v.getRegles());
			System.out.println(env);
		} catch (NoSolutionException excep) {
			System.out.println(prog1);
			System.out.println(excep);
		}
		
	}

}
