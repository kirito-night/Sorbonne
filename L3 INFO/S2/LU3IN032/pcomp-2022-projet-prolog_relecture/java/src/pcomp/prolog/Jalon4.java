package pcomp.prolog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pcomp.prolog.ast.CurrContext;
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
 * Classe exécutable pour le Jalon 4
 * @author Camille Palisoc
 *
 */
public class Jalon4 {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Jalon 5\n");
		
		//test de solve
		System.out.println("Test de solve :\n");
		Program prog1but = PrologParser.parseFile("tests_jalon_pl/interprete4_test1.pl");
		System.out.println(prog1but);
		try {
			VisitorDecl separator = new VisitorDecl(false);
			for (Decl d : prog1but.getDeclarations()) {
				d.accept(separator);
			}
			System.out.println("Rules : "+separator.getRegles());
			System.out.println("Goals : "+separator.getButs());
			Environnement env = Interprete.solve(new CurrContext(separator.getButs(), separator.getRegles(), new Environnement()), separator.getButs(), separator.getRegles(), new Environnement());
			System.out.println(env);
		} catch (NoSolutionException excep) {
			System.out.println(excep);
			excep.printStackTrace();
		}
		
		System.out.println("\n\ninterprete4");
		
		//tests de interprete4
		System.out.println();
		Program prog0 = PrologParser.parseFile("tests_jalon_pl/interprete4_test0.pl");
		System.out.println("Test 1\n"+prog0);
		try {
			Environnement env = Interprete.interprete4(prog0);
			System.out.println(env);
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		System.out.println();
		Program prog1 = PrologParser.parseFile("tests_jalon_pl/interprete4_test1.pl");
		System.out.println("Test 2\n"+prog1);
		try {
			Environnement env = Interprete.interprete4(prog1);
			System.out.println(env);
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		System.out.println();
		Program prog2 = PrologParser.parseFile("tests_jalon_pl/interprete4_test2.pl");
		System.out.println("Test 3\n"+prog2);
		try {
			Environnement env = Interprete.interprete4(prog2);
			System.out.println(env);
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		System.out.println();
		Program prog3 = PrologParser.parseFile("exemples/classification.pl");
		System.out.println("Test 4\n"+prog3);
		try {
			Environnement env = Interprete.interprete4(prog3);
			System.out.println(env);
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		System.out.println();
		Program prog4 = PrologParser.parseFile("exemples/genealogie.pl");
		System.out.println("Test 5\n"+prog4);
		try {
			Environnement env = Interprete.interprete4(prog4);
			System.out.println(env);
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
	}
}
