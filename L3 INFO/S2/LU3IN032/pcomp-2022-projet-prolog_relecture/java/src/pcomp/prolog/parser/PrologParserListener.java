/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

/*
 * Listener passé au parseur ANTLR 4 pour construire l'AST.
 */

package pcomp.prolog.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import pcomp.prolog.ast.DeclAssertion;
import pcomp.prolog.ast.Decl;
import pcomp.prolog.ast.DeclGoal;
import pcomp.prolog.ast.Position;
import pcomp.prolog.ast.Predicate;
import pcomp.prolog.ast.TermPredicate;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.Term;
import pcomp.prolog.ast.TermVariable;
import pcomp.prolog.parser.PrologANTLRGrammarParser.AssertionContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.AtomContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.ClauseContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.GoalContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.PredContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.PredicateContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.ProgramContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.StructureContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.TermContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.VarContext;

public class PrologParserListener implements PrologANTLRGrammarListener{

	@Override public void enterEveryRule(ParserRuleContext arg0) {}
	@Override public void exitEveryRule(ParserRuleContext arg0) {}
	@Override public void visitErrorNode(ErrorNode arg0) {}
	@Override public void visitTerminal(TerminalNode arg0) {}

	@Override public void enterProgram(ProgramContext ctx) {}

	@Override
	public void exitProgram(ProgramContext ctx) {
		List<Decl> l = new ArrayList<>();
		for (ClauseContext t : ctx.clauses) {
			l.add(t.node);
		}
		ctx.node = new Program(l);
	}

	@Override public void enterGoal(GoalContext ctx) {}

	@Override public void exitGoal(GoalContext ctx) {
		Position pos = new Position(ctx.start.getLine(), ctx.start.getCharPositionInLine());
		List<Predicate> l = new ArrayList<>();
		for (PredicateContext t : ctx.body) {
			l.add(t.node);
		}
		ctx.node = new DeclGoal(l, pos);
	}

	@Override public void enterAssertion(AssertionContext ctx) {}

	@Override public void exitAssertion(AssertionContext ctx) {
		Position pos = ctx.head.node.getPosition();
		List<Predicate> l = new ArrayList<>();
		for (PredicateContext t : ctx.body) {
			l.add(t.node);
		}
		ctx.node = new DeclAssertion(ctx.head.node, l, pos);
	}

	@Override public void enterAtom(AtomContext ctx) {}

	@Override public void exitAtom(AtomContext ctx) {
		Position pos = new Position(ctx.atom.getLine(), ctx.atom.getCharPositionInLine());
		ctx.node = new Predicate(ctx.atom.getText(), pos);
	}

	@Override public void enterStructure(StructureContext ctx) {}

	@Override public void exitStructure(StructureContext ctx) {
		Position pos = new Position(ctx.atom.getLine(), ctx.atom.getCharPositionInLine());
		List<Term> l = new ArrayList<>();
		for (TermContext t : ctx.childs) {
			l.add(t.node);
		}
		ctx.node = new Predicate(ctx.atom.getText(), l, pos);
	}

	@Override public void enterVar(VarContext ctx) {}

	@Override public void exitVar(VarContext ctx) {
		Position pos = new Position(ctx.var.getLine(), ctx.var.getCharPositionInLine());
		ctx.node = new TermVariable(ctx.var.getText(), pos);
	}

	@Override public void enterPred(PredContext ctx) {}

	@Override public void exitPred(PredContext ctx) {
		Position pos = ctx.pred.node.getPosition();
		ctx.node = new TermPredicate(ctx.pred.node, pos);
	}
}
