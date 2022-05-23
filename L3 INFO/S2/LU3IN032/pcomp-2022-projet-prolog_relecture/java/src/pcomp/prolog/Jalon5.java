package pcomp.prolog;

import java.io.IOException;
import java.util.List;

import pcomp.prolog.ast.Environnement;
import pcomp.prolog.ast.Interprete;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.excep.NoSolutionException;
import pcomp.prolog.parser.PrologParser;

/**
 * Classe exécutable pour le Jalon 5
 * @author Camille Palisoc
 *
 */
public class Jalon5 {
	
	public static void main(String[] args) throws IOException {
		Program prog0 = PrologParser.parseFile("tests_jalon_pl/interprete5_test0.pl");
		System.out.println(prog0);
		try {
			List<Environnement> env = Interprete.interprete5(prog0);
			System.out.println(env);
		} catch (NoSolutionException excep) {
			System.out.println("Jalon 5 "+excep);
		}
	}
}
