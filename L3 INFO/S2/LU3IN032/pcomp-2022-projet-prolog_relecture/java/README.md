Ce répertoire contient la version Java de l'analyseur syntaxique (parseur) Prolog pour bien démarrer le projet.


# Contenu

- `src/` contient les sources Java. Il s'agit d'un package `pcomp.prolog`.
- `src/pcom/prolog/ast/` contient la structure d'AST (arbre syntaxique abstrait). Un programme est une instance de `Program`. C'est une liste de déclarations `Decl` (des buts `Goal` ou des assertions `Assertion`), contenant des termes `Term` (variables `TermVariable` ou prédicats `TermPredicate`). La classe `Predicate` encapsule la définition d'un prédicat (symbole et arguments) et est réutilisée à la fois dans les termes (`TermPredicate`) et les déclarations (`Goal`, `Assertion`). L'AST implante le motif visiteur et a un _printer_ Les nœuds d'AST ont une information de position (ligne, colonne) dans le source, encapsulée dans la classe `Position`.
- `src/pcomp/prolog/parser/` contient un parseur. Les fichiers `PrologANTLRGrammar*.java` ont été générés automatiquement par l'outil ANTLR 4 à partir du fichier de grammaire `PrologANTLRGrammar.g4`.
- `src/pcomp/prolog/Main.java` est une classe d'exemple qui lit des exemples de source Prolog, les convertit en `Program`, et réaffiche le programme à l'écran. Vous pourrez utiliser directement cette classe pour construire les objets `Program` nécessaires au projet.
- `jars/` contient les classes pour faire fonctionner l'outil ANTLR 4 à l'exécution.


# Compilation

Le répertoire `projet-java` contient un fichier de projet Eclipse `.project`.
Après un fork et un `git clone` de votre projet dans le shell, il suffira sous Eclipse de faire _File > Open Projects From Filesystem_, cliquer sur _Directory_ et choisir l'emplacement de ce répertoire.

Il ne sera pas nécessaire de modifier la grammaire `PrologANTLRGrammar.g4` fournie. Toutefois, si vous le faites, vous devrez regénérer les fichiers Java du parseur en appelant ANTLR. 
Pour cela, vous devez installer l'outil ANTLR 4, puis aller dans le répertoire `java/src/pcomp/prolog/parser/` et taper dans un shell : `antlr4 PrologANTLRGrammar.g4`.
Assurez-vous bien que les fichiers générés sont placés dans `java/src/pcomp/prolog/parser` et remplacent les fichiers actuels ; en particulier, si vous utilisez Eclipse au lieu de la ligne de commande pour générer les fichiers, il est possible qu'ils soient placés dans le mauvais répertoire (i.e., vous continuerez à compiler et utiliser une version antérieure) !
