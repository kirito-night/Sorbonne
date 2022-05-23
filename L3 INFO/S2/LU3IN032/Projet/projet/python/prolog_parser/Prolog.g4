/*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 */

/*
 * Fichier de grammaire ANTLR 4.
 * Permet de générer automatiquement les fichiers Java Prolog*.py
 * 
 */

grammar Prolog;

program
    : clauses += clause* EOF
;

clause
    : head = predicate ( ':-' body += predicate (',' body += predicate)* )? '.' # Assertion
    | begin = '?-'  body += predicate (',' body += predicate)* '.' # Goal
; 

predicate
    : atom = ATOM # Atom
    | atom = ATOM '('  ((childs+=term (',' childs+=term)* )?)  ')' # Structure
;

term
    : var = VAR # Var
    | pred = predicate # Pred
;

ATOM: [a-z] [a-zA-Z0-9_]* ;
VAR: [A-Z] [a-zA-Z0-9_]* ;
LINE_COMMENT : '%' (~[\r\n])* -> skip;
COMMENT : '/*' ('*' ~[/] | ~[*])* '*/' -> skip;
SPACE : [ \t\r\n]+ -> skip;
