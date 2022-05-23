package pcomp.prolog;

import java.io.IOException;

import pcomp.prolog.ast.*;
import pcomp.prolog.ast.excep.NoSolutionException;
import pcomp.prolog.parser.PrologParser;

/**
 * Classe ex�cutable pour le Jalon 1 et 2
 * @author Fran�ois-Xavier Drouard, Camille Palisoc
 *
 */
public class Jalon1 {

	public static void main(String[] args) throws IOException {
		
		// Jalon 1
		// Tests de l'unification avec les �quations du TD
		System.out.println("Jalon 1");
		System.out.println("Tests Unification");
		System.out.println("\nEq 1\n");
		// 1 :
		Program p1 = PrologParser.parseString("?- p(Z,h(Z,W),f(W)).");
		System.out.println(p1);
		Program p2 = PrologParser.parseString("?- p(f(X),h(Y,f(a)),Y).");
		System.out.println(p2);
		Predicate pred1 = p1.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred1);
		Predicate pred2 = p2.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred2);
		Equation eq1 = new Equation(new TermPredicate(pred1,pred1.getPosition()), new TermPredicate(pred2,pred2.getPosition()));
		Systeme s1 = new Systeme();
		s1.addEquation(eq1);
		s1.afficherSysteme();
		s1.unify();
		s1.afficherSysteme();
		
		System.out.println("\n\nEq 2\n");
		// 2 :
		Program p21 = PrologParser.parseString("?- p(a,X,f(g(Y))).");
		System.out.println(p21);
		Program p22 = PrologParser.parseString("?- p(Z,f(Z),f(U)).");
		System.out.println(p22);
		Predicate pred21 = p21.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred21);
		Predicate pred22 = p22.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred22);
		Equation eq2 = new Equation(new TermPredicate(pred21,pred21.getPosition()), new TermPredicate(pred22,pred22.getPosition()));
		Systeme s2 = new Systeme();
		s2.addEquation(eq2);
		s2.afficherSysteme();
		try {
			s2.unify();
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		
		
		System.out.println("\n\nEq 3\n");
		// 3 :
		Program p31 = PrologParser.parseString("?- q(f(a),g(X)).");
		System.out.println(p31);
		Program p32 = PrologParser.parseString("?- q(Y,Y).");
		System.out.println(p32);
		Predicate pred31 = p31.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred31);
		Predicate pred32 = p32.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred32);
		Equation eq3 = new Equation(new TermPredicate(pred31,pred31.getPosition()), new TermPredicate(pred32,pred32.getPosition()));
		Systeme s3 = new Systeme();
		s3.addEquation(eq3);
		s3.afficherSysteme();
		try {
			s3.unify();
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		
		System.out.println("\nEq 4\n");
		// 4 : 
		Program p41 = PrologParser.parseString("?- p(X,f(X),f(f(X))).");
		System.out.println(p41);
		Program p42 = PrologParser.parseString("?- p(f(f(Y)),Y,f(Y)).");
		System.out.println(p42);
		Predicate pred41 = p41.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred41);
		Predicate pred42 = p42.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred42);
		Equation eq4 = new Equation(new TermPredicate(pred41,pred41.getPosition()), new TermPredicate(pred42,pred42.getPosition()));
		Systeme s4 = new Systeme();
		s4.addEquation(eq4);
		s4.afficherSysteme();
		try {
			s4.unify();
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		
		
		System.out.println("\nEq 5\n");
		// 5 : 
		Program p51 = PrologParser.parseString("?- q(x, q(y, z)).");
		System.out.println(p51);
		Program p52 = PrologParser.parseString("?- q(x, g(h(k(x)))).");
		System.out.println(p52);
		Predicate pred51 = p51.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred51);
		Predicate pred52 = p52.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred52);
		Equation eq5 = new Equation(new TermPredicate(pred51,pred51.getPosition()), new TermPredicate(pred52,pred52.getPosition()));
		Systeme s5 = new Systeme();
		s5.addEquation(eq5);
		s5.afficherSysteme();
		try {
			s5.unify();
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}	
		
		System.out.println("\nEq 6\n");
		// 6 : 
		Program p61 = PrologParser.parseString("?-  q(X, r(U, X)).");
		System.out.println(p61);
		Program p62 = PrologParser.parseString("?-  q(r(Y, a), r(Z, r(b, Z))).");
		System.out.println(p62);
		Predicate pred61 = p61.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred61);
		Predicate pred62 = p62.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred62);
		Equation eq6 = new Equation(new TermPredicate(pred61,pred61.getPosition()), new TermPredicate(pred62,pred62.getPosition()));
		Systeme s6 = new Systeme();
		s6.addEquation(eq6);
		s6.afficherSysteme();
		try {
			s6.unify();
			s6.getEnv().afficherEnv();
			
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
				
		System.out.println("\nEq 7\n");
		// 7 : 
		Program p71 = PrologParser.parseString("?-  p(X, f(X), r(f(X), X)).");
		System.out.println(p71);
		Program p72 = PrologParser.parseString("?-  p(z, f(f(a)), r(f(r(a, Z)), v)).");
		System.out.println(p72);
		Predicate pred71 = p71.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred71);
		Predicate pred72 = p72.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred72);
		Equation eq7 = new Equation(new TermPredicate(pred71,pred71.getPosition()), new TermPredicate(pred72,pred72.getPosition()));
		Systeme s7 = new Systeme();
		s7.addEquation(eq7);
		s7.afficherSysteme();
		try {
			s7.unify();
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}	
				
			
		System.out.println("\n Eq 8\n");
		// 8 : 
		Program p81 = PrologParser.parseString("?-  p(f(r(X, Y)), r(V, W), Y).");
		System.out.println(p81);
		Program p82 = PrologParser.parseString("?-  p(f(Z), X, f(X)).");
		System.out.println(p82);
		Predicate pred81 = p81.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred81);
		Predicate pred82 = p82.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred82);
		Equation eq8 = new Equation(new TermPredicate(pred81,pred81.getPosition()), new TermPredicate(pred82,pred82.getPosition()));
		Systeme s8 = new Systeme();
		s8.addEquation(eq8);
		s8.afficherSysteme();
		try {
			s8.unify();
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		
		System.out.println("\n Eq 9\n");
		// 9 : 
		Program p91 = PrologParser.parseString("?-  p(f(Y), f(Z), f(T), f(X)).");
		System.out.println(p91);
		Program p92 = PrologParser.parseString("?-  p(g(Z), g(X), g(Y), g(Z)).");
		System.out.println(p92);
		Predicate pred91 = p91.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred91);
		Predicate pred92 = p92.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred92);
		Equation eq9 = new Equation(new TermPredicate(pred91,pred91.getPosition()), new TermPredicate(pred92,pred92.getPosition()));
		Systeme s9 = new Systeme();
		s9.addEquation(eq9);
		s9.afficherSysteme();
		try {
			s9.unify();
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		
		// Jalon2
		System.out.println("\n\n\nJalon 2");
		System.out.println("\n\nTests interpretes :");
		Program prog_err = PrologParser.parseFile("tests_jalon_pl/interprete_err.pl");
		
		Program prog1 = PrologParser.parseFile("tests_jalon_pl/interprete0_test0.pl");
		Program prog2 = PrologParser.parseFile("tests_jalon_pl/interprete0_test1.pl");
		Program prog3 = PrologParser.parseFile("tests_jalon_pl/interprete0_test2.pl");
		
		Program prog11 = PrologParser.parseFile("tests_jalon_pl/interprete1_test1.pl");
		Program prog12 = PrologParser.parseFile("tests_jalon_pl/interprete1_test2.pl");
		Program prog13 = PrologParser.parseFile("tests_jalon_pl/interprete1_test_excep1.pl");
		
		Program prog21 = PrologParser.parseFile("tests_jalon_pl/interprete2_test0.pl");
		Program prog22 = PrologParser.parseFile("tests_jalon_pl/interprete2_test1.pl");
		Program prog23 = PrologParser.parseFile("tests_jalon_pl/interprete2_test2.pl");
		Program prog24 = PrologParser.parseFile("tests_jalon_pl/interprete2_test3.pl");
		//interprete0
		System.out.println("\ninterprete0");
		try {
			System.out.println("\nTest 1");
			System.out.println(prog1);
			try {
				Environnement env1 = Interprete.interprete0(prog1);
				env1.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
			
			System.out.println("\nTest 2");
			System.out.println(prog2);
			try {
				Environnement env2 = Interprete.interprete0(prog2);
				env2.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
			System.out.println("\nTest 3");
			System.out.println(prog3);
			try {
				Environnement env3 = Interprete.interprete0(prog3);
				env3.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		System.out.println("\n\ncas de lev�e d'exception : ");
		try {
			System.out.println("\nTest 1");
			System.out.println(prog11);
			try {
				Environnement env1 = Interprete.interprete0(prog11);
				env1.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("\nTest 2");
			System.out.println(prog22);
			try {
				Environnement env1 = Interprete.interprete0(prog22);
				env1.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("\nTest 3");
			System.out.println(prog_err);
			try {
				Environnement env1 = Interprete.interprete0(prog_err);
				env1.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		
		//interprete1
		System.out.println("\n\ninterprete1");
		try {
			System.out.println("\nTest 1");
			System.out.println(prog11);
			try {
				Environnement env11 = Interprete.interprete1(prog11);
				env11.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
			System.out.println("\nTest 2");
			System.out.println(prog12);
			try {
				Environnement env12 = Interprete.interprete1(prog12);
				env12.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		System.out.println("\ncas de lev�e d'exception : ");
		try {
			System.out.println("\nTest 1");
			System.out.println(prog13);
			try {
				Environnement env13 = Interprete.interprete1(prog13);
				env13.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("\nTest 2");
			System.out.println(prog_err);
			try {
				Environnement env1 = Interprete.interprete1(prog_err);
				env1.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("\nTest 3");
			System.out.println(prog24);
			try {
				Environnement env1 = Interprete.interprete1(prog24);
				env1.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		
		//interprete2
		System.out.println("\n\ninterprete2");
		try {
			System.out.println("\nTest 1");
			System.out.println(prog21);
			try {
				Environnement env21 = Interprete.interprete2(prog21);
				env21.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
			System.out.println("\nTest 2");
			System.out.println(prog22);
			try {
				Environnement env22 = Interprete.interprete2(prog22);
				env22.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
			System.out.println("\nTest 3");
			System.out.println(prog23);
			try {
				Environnement env23 = Interprete.interprete2(prog23);
				env23.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
			System.out.println("\nTest 4");
			System.out.println(prog24);
			try {
				Environnement env24 = Interprete.interprete2(prog24);
				env24.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		System.out.println("\ncas de lev�e d'exception : ");
		try {
			System.out.println("\nTest 1");
			System.out.println(prog_err);
			try {
				Environnement env1 = Interprete.interprete2(prog_err);
				env1.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("\nTest 2");
			System.out.println(prog13);
			try {
				Environnement env1 = Interprete.interprete2(prog13);
				env1.afficherEnv();
			} catch (NoSolutionException excep) {
				System.out.println(excep);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
	}

}
