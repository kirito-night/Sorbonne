# PCOMP (LU3IN032), Licence 3, Sorbonne Université
# année 2021-2022
#
# Projet Prolog
#

# Ce module contient les classes pour représenter un arbre syntaxique
# abstrait (AST) de programme Prolog.
# La classe principale est Program.
#
# Pour mieux documenter le code, celui-ci utilise les annotations
# de type Python pour les attributs et les méthodes.

from typing import Optional, List


# Classe pour représenter une position (ligne, colonne) dans le source.
# Utile pour les messages d'erreur.
class Pos:

    line: int
    column: int

    def __init__(self, line: int, column: int):
        self.line = line
        self.column = column

    def __str__(self):
        return str(self.line) + ":" + str(self.column)

    def __repr__(self):
        return self.__str__()


# Classe abstraite pour les termes.
class Term:

    # les termes ont une position optionnelle (qui peut être None)
    pos: Pos

    def __init__(self, pos: Optional[Pos] = None):
        self.pos = pos


# Un prédicat est de la forme "symbol(arg1,..., argN)".
# - le symbole est une chaîne
# - arg1,...,argN est une liste d'arguments (des termes)
# - la position, de type Pos, est optionnelle (peut être None)
class Predicate:

    pos:    Pos
    symbol: str
    args:   List[Term]
    
    def __init__(self, symbol: str, args: List[Term], pos: Optional[Pos] = None):
        self.symbol = symbol
        self.args = args
        self.pos = pos

    # conversion en chaîne
    def __str__(self):
        if self.args:
            return self.symbol + '(' + ', '.join(map(str, self.args)) + ')'
        else:
            return self.symbol

    def __repr__(self):
        return self.__str__()

    # comparaison d'égalité
    # la position est ignorée
    def __eq__(self, other):
        if isinstance(other,Predicate):
            return self.symbol == other.symbol and self.args == other.args
        return False


# Un terme peut être une variable, le nom est une chaîne.
class TermVariable(Term):

    name: str
    
    def __init__(self, name: str, pos: Optional[Pos] = None):
        super().__init__(pos)
        self.name = name

    # conversion en chaîne
    def __str__(self):
        return self.name

    def __repr__(self):
        return self.name

    # comparaison d'égalité
    # la position est ignorée
    def __eq__(self, other):
        if isinstance(other,TermVariable):
            return self.name == other.name
        return False

    # rendre la classe hashable pour pouvoir l'utiliser dans un dictionnaire
    def __hash__(self):
        return hash(self.name)


# Un terme peut aussi être un prédicat
class TermPredicate(Term):

    pred: Predicate
    
    def __init__(self, pred: Predicate, pos: Optional[Pos] = None):
        super().__init__(pos)
        self.pred = pred

    # conversion en chaîne
    def __str__(self):
        return str(self.pred)

    def __repr__(self):
        return self.__str__()

    # comparaison d'égalité
    # la position est ignorée
    def __eq__(self, other):
        if isinstance(other,TermPredicate):
            return self.pred == other.pred
        return False


# Classe abstraite pour les déclarations.
class Decl:

    # les déclarations ont une position optionnelle (qui peut être None)
    pos: Pos

    def __init__(self, pos: Optional[Pos] = None):
        self.pos = pos


# Une assertion est une déclaration de la forme "head :- pred1,...,redN."
# head et chaque pred sont des prédicats
class DeclAssertion(Decl):

    head: Predicate
    preds: List[Predicate]

    def __init__(self, head: Predicate, preds: List[Predicate], pos: Optional[Pos] = None):
        super().__init__(pos)
        self.head = head
        self.preds = preds

    # conversion en chaîne
    def __str__(self):
        if self.preds:
            return str(self.head) + ' :- ' + ', '.join(map(str, self.preds)) + '.'
        else:
            return str(self.head) + '.'

    def __repr__(self):
        return self.__str__()

    # comparaison d'égalité
    # la position est ignorée
    def __eq__(self, other):
        if isinstance(other,DeclAssertion):
            return self.head == other.head and self.preds == other.preds
        return False


# Un but est une déclaration de la forme "?- pred1,...,predN."
# Similare à une assertion, mais sans prédicat de tête.
class DeclGoal(Decl):

    preds: List[Predicate]
    
    def __init__(self, preds: List[Predicate], pos: Optional[Pos] = None):
        super().__init__(pos)
        self.preds = preds

    # conversion en chaîne
    def __str__(self):
        return ' ?- ' + ', '.join(map(str, self.preds)) + '.'

    def __repr__(self):
        return self.__str__()

    # comparaison d'égalité
    # la position est ignorée
    def __eq__(self, other):
        if isinstance(other,DeclGoal):
            return self.preds == other.preds
        return False


# Un programme est une liste de déclarations.
class Program:

    decls: List[Decl]
    
    def __init__(self, decls: List[Decl]):
        self.decls = decls

    # conversion en chaîne
    def __str__(self):
        return '\n'.join(map(str, self.decls))

    # permet à la conversion en chaîne de fonctionner aussi dans les
    # conteneurs (listes, dictionnaires)
    def __repr__(self):
        return self.__str__()
