// Generated from PrologANTLRGrammar.g4 by ANTLR 4.4

package pcomp.prolog.parser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PrologANTLRGrammarParser}.
 */
public interface PrologANTLRGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Goal}
	 * labeled alternative in {@link PrologANTLRGrammarParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterGoal(@NotNull PrologANTLRGrammarParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Goal}
	 * labeled alternative in {@link PrologANTLRGrammarParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitGoal(@NotNull PrologANTLRGrammarParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Var}
	 * labeled alternative in {@link PrologANTLRGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterVar(@NotNull PrologANTLRGrammarParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link PrologANTLRGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitVar(@NotNull PrologANTLRGrammarParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Pred}
	 * labeled alternative in {@link PrologANTLRGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterPred(@NotNull PrologANTLRGrammarParser.PredContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Pred}
	 * labeled alternative in {@link PrologANTLRGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitPred(@NotNull PrologANTLRGrammarParser.PredContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assertion}
	 * labeled alternative in {@link PrologANTLRGrammarParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterAssertion(@NotNull PrologANTLRGrammarParser.AssertionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assertion}
	 * labeled alternative in {@link PrologANTLRGrammarParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitAssertion(@NotNull PrologANTLRGrammarParser.AssertionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrologANTLRGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull PrologANTLRGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologANTLRGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull PrologANTLRGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Structure}
	 * labeled alternative in {@link PrologANTLRGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterStructure(@NotNull PrologANTLRGrammarParser.StructureContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Structure}
	 * labeled alternative in {@link PrologANTLRGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitStructure(@NotNull PrologANTLRGrammarParser.StructureContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Atom}
	 * labeled alternative in {@link PrologANTLRGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull PrologANTLRGrammarParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Atom}
	 * labeled alternative in {@link PrologANTLRGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull PrologANTLRGrammarParser.AtomContext ctx);
}