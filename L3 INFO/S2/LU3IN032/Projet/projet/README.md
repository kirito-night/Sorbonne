# PCOMP - Projet : Interprète Prolog

Le projet est un interprète Prolog, à réaliser au choix en OCaml, Java ou Python.

L'énoncé est disponible sur Moodle.

Vous devez faire un _fork_ individuel et privé de ce squelette de projet, y ajouter les enseignants du cours (Carlos Agon, Emmanuel Chailloux, Basile Pesin, Antoine Miné, Christine Tasson) comme _maintainer_, puis ajouter les fichiers de votre projet (`git add`, `git commit`, `git push`).
N'oubliez pas de remplir le fichier `RENDU.md`.
Il est conseillé de faire des commit réguliers.

Les répertoires `java`, `ocaml`, `python` de ce squelette contiennent une structure d'AST et un analyseur syntaxique dans chacun des langages proposés.
Le répertoire `exemples`, que vous pouvez enrichir, contient quelques exemples de programmes Prolog.
Référez-vous au fichier `README.md` de chaque répertoire pour plus d'informations sur la structure des sources et sur la procédure de compilation et de test de l'analyseur syntaxique.


# Commentaires (Antoine Miné)


# Jalon 1

## exécution et tests
* (+) Le code s'exécute sans erreur. Mais ce serait bien d'afficher le log !
* (+) Nombreux tests unitaires avec unittest, y compris des cas d'erreur.

## qualité du code
* (+) Code bien organisé, exceptions et tests dans des fichiers séparés.
* (+) Code bien documenté avec des doc-string (mais pas pour toutes les méthodes).
* print_ast, etc. sont-ils utiles ? L'AST redéfinit déjà `__str__`.
* Attention : replace modifie le terme en place au lieu de retourner une copie indépendante de l'argument. Cela pourrait causer des soucis par la suite. (idem pur rename)
* Attention : unif itère sur une liste qui est modifiée dans la boucle, ce qui peut causer des soucis. La nécessité d'un argument First n'est pas claire.


## rapport de Jalon
* (+) Description succincte de l'implantation et des choix faits.
* (=) Discussion sur l'efficacité incomplète.
* (=) Pas de description des tests effectués.
* (-) Pas de commentaire sur l'unification, qui est pourtant la fonction la plus complexe à implanter.


# Jalon 2

## exécution et tests
* (+) Le code s'exécute sans erreur. Aucun log n'est affiché cependant,
* (=) Tous les tests donnent le même résultat ? Un peu plus de variété serait bienvenue.

## qualité du code
* (+) Code bien organisé, avec séparation des structures de données, des algorithmes et des tests.
* (+) Code toujours bien documenté.
* (-) Attention, interprete2 mélange les prédicats de tous les buts, y compris s'ils sont dans différentes déclarations.

## rapport de Jalon
* Rapport succinct, qui décrit surtout la structure du code. Plus de détails sur les tests seraient bienvenus.


# Jalon 3

## exécution et tests
* (+) Tests unitaires bien détaillés.
* (=) Un seul test (non unitaire) de interprete3 sur un programme Prolog. Pas d'exemple complexe (avec plusieurs assertions). On aurait aimé plus d'exemples. 

## qualité du code
* (+) Code toujours bien organisé et documenté.
* (-) Attention : deux DeclGoal différents doivent être résolus dans environnements séparés.
* (-) Attention : rename devrait retourner un nouveau terme et pas modifier son argument en place.
* (+) Prise en compte du précédent retour pour corriger les erreurs.

## rapport de Jalon
* (+) Rapport un peu plus détaillé, qui tient compte du retour sur les jalons précédents (logs).



# Jalon 4

## exécution et tests
* (+) Le code s'exécute sans erreur et affiche le résultat attendu.
* (-) Il faudra tester plus d'exemples. En particulier, les exemples du répertoire exemples ne sont pas tous exécutés (ce qui est pourtant demandé dans l'énoncé, Q4.3).

## qualité du code, structures de données et algorithmes
* (+) Code bien documenté. _docstring_ pour toutes les méthodes. Il aurait néanmoins intéressant de documenter le code des fonctions les plus complexes (`choose`, `solve2`) en expliquant les étapes importantes (e.g., ce que fait chaque boucle).
* (=) Modification de `rename` suite au retour du Jalon 3.  Cependant, effectuer un `deepcopy` à chaque appel récursif est inutile et trop coûteux. Une des deux solutions suivantes est suffisante: 1) chaque appel récursif retourne un nouveau nœud (sans copie récursive, puisque la fonction `rename` est déjà récursive), ou 2) un appel unique à `deepcopy` est effectué sur l'argument avant un appel à `rename` (dont tous les appels récursifs modifient la _même_ copie profonde de l'argument original).
* (-) `deepcopy` également inutile dans `Contexte` ! Une copie simple devrait suffire.
* (-) Attention : dans `solve2`, `del goals[i]` modifie la liste `goals` sur laquelle nous sommes en train d'itérer (`for i in range(len(goals))`), ce qui est généralement une erreur.

## rapport de Jalon
* (=) Rapport succinct, qui ne décrit pas toutes les structures de données, e.g. le journal (Q.4.1).



# Jalon 5

**Attention :** le résultat du toplevel est incorrect.
Par exemple, dans `classification2.pl` échoue sur `?-animal(X).` (il devrait retourner une valeur de `X` qui fonctionne) ; `?-animal(a).` retourne une solution `X0=a` (il devrait indiquer qu'il m'y a pas de solution).

* (=) Implantation de trace, pas de fonctionnalité pour énumérer toutes les solutions (Q.5.2).
* (=) toplevel très basique, qui ne permet pas de charger de nouveaux fichiers depuis la ligne de commande, d'ajouter des règles, ni même de résoudre plusieurs but (retour au _shell_ après un seul but).
* (-) Factorisation nécessaire entre `solve_trace` et `solve2`.
* (+) Rapport explicatif.
