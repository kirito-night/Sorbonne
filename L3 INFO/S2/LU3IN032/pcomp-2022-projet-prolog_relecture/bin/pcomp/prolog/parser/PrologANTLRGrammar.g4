/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

/*
 * Fichier de grammaire ANTLR 4.
 * Permet de générer automatiquement les fichiers Java PrologANLTRGrammar*.java
 * 
 */

grammar PrologANTLRGrammar;

@header {
package pcomp.prolog.parser;
}

program returns [pcomp.prolog.ast.Program node] 
    : clauses += clause* EOF
;

clause returns [pcomp.prolog.ast.Decl node]  
    : head = predicate ( ':-' body += predicate (',' body += predicate)* )? '.' # Assertion
    | start = '?-'  body += predicate (',' body += predicate)* '.' # Goal
; 

predicate returns [pcomp.prolog.ast.Predicate node]
    : atom = ATOM # Atom
    | atom = ATOM '('  ((childs+=term (',' childs+=term)* )?)  ')' # Structure
;

term returns [pcomp.prolog.ast.Term node]
    : var = VAR # Var
    | pred = predicate # Pred
;

ATOM: [a-z] [a-zA-Z0-9_]* ;
VAR: [A-Z] [a-zA-Z0-9_]* ;
LINE_COMMENT : '%' (~[\r\n])* -> skip;
COMMENT : '/*' ('*' ~[/] | ~[*])* '*/' -> skip;
SPACE : [ \t\r\n]+ -> skip;
