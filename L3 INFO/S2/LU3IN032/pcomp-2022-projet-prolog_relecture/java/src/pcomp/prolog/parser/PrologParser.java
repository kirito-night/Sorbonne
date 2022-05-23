 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

package pcomp.prolog.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pcomp.prolog.ast.Program;

/*
 * Points d'entrée pour appeler le parseur ANTLR 4 sur une chaîne ou un fichier
 * et retourner un AST Prolog (instance de Program).
 */

public class PrologParser {

    public static Program parseString(String s) {
    	 ANTLRInputStream i = new ANTLRInputStream(s);
    	 PrologANTLRGrammarLexer lexer = new PrologANTLRGrammarLexer(i);
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         PrologANTLRGrammarParser parser = new PrologANTLRGrammarParser(tokens);
         PrologANTLRGrammarParser.ProgramContext tree = parser.program();
         ParseTreeWalker walker = new ParseTreeWalker();
         PrologParserListener extractor = new PrologParserListener();
         walker.walk(extractor, tree);
         return tree.node;
    }

    public static Program parseFile(String filename) throws IOException {
    	byte[] encoded = Files.readAllBytes((new File(filename)).toPath());
    	String s = new String(encoded, Charset.forName("UTF-8"));
   	 	return parseString(s);
    }
}
