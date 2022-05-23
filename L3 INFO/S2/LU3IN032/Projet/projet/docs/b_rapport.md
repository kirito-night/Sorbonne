# Rapport

## Jalon 1 - 28 fevrier

Tous les tests concernant le jalon 1 peuvent etre lancés avec `cd python && python -m unittest test.jalon1`.
Les tests sont implémentés en utilisant la bibliotheque native unittest.

On utilise une liste pour représenter les différentes equations, cela permet un ajout et une supression rapide et efficace de ces equations. Bien qu'elles puissent etre représentées par des simple tuples, nous avons choisi de representer les equations sous forme de classe (`Equation`) pour une meilleure évolutivité (avec term1 pour le terme de gauche et term2 pour le terme de droite).

L'environnement est représenté par une classe (`Environment`) dont les données sont stockées dans un dictionnaire. L'utilisation d'une classe permet d'effectuer des tests additionnels par exemple appeler `OccurCheck` avant d'ajouter des elements dans le dictionnaire. Les clés du dictionnaire etants représentées par des instances de la classe TermeVariable, il a été nécessaire d'ajouter une méthode `__hash__` dans cette classe pour qu'elle devienne hashable.

Pour l'implémentation de la méthode `subst`, nous avons créé une fonction auxiliaire `replace` permettant de facilement remplacer un terme par un autre.

Les exceptions spécifiques a l'interpreteur sont définies dans le fichier `exc.py`.

## Jalon 2 - 7 mars

Tous les tests concernant le jalon 2 peuvent etre lancés avec `cd python && python -m unittest test.jalon2`.
Les programmes prolog utilisés pour ces tests peuvent etre trouvés a la racine du projet dans `exemples/jalon2`.

Nous avons rajouté un mode debug pour les tests permettant d'afficher plus d'informations lors de l'execution, on peut l'activer en changeant `ENABLE = True` dans le fichier `python/test/debug.py`.
Nous avons également ajouté de la documentation pour toutes les fonctions réalisées sous forme de docstring, style google.
Le fichier `python/print_ast.py` contient des fonctions permettant d'afficher un vue détaillée d'un AST.
On a également déplacé les fonctions `subst`, `replace` et `unif` dans le fichier `python/utils.py` pour ne garder que les structures de données dans `python/data.py`.
(La fonction `OccurCheck` reste dans data pour éviter les import circulaires).

Les 3 premieres version de l'interpreteur (`interprete0`, `interprete1` et `interprete2`) sont trouvable dans le fichier `python/interp_v1.py`.

## Jalon 3 - 14 mars

Tous les tests concernant le jalon 3 peuvent etre lancés avec `cd python && python -m unittest test.jalon3`.

Pour activer **l'affichage des logs** lors des tests, modifier `ENABLE = True` dans le fichier `python/test/debug.py`.
(Il a été choisi de le desactiver par defaut pour avoir une sortie plus lisible).
On utilise `print_ast` pour avoir une representation du programme sous forme d'arbre, au lieu d'une simple expression.

Les fonctions `rename`, `choose` et `solve` se trouvent dans le fichier `python/utils.py`.
La quatrieme version de l'interpreteur se trouve dans le fichier `python/interp_v2.py`, il permet de interpreter un programme ayant un ou plusieurs buts.

Des tests ont été effectués pour les fonctions choose, solve et interprete3, on y verifie que l'environnement retourné pour l'exemple 1 du jalon 3 est correct. On a également effectué quelques correction suite aux retours des 2 rapport (ajout de doc, modification de unif et replace)

## Jalon 4 - 28 mars

Tous les tests concernant le jalon 3 peuvent etre lancés avec `cd python && python -m unittest test.jalon4`.

Pour activer **l'affichage des logs** lors des tests, modifier `ENABLE = True` dans le fichier `python/test/debug.py`.
(Il a été choisi de le desactiver par defaut pour avoir une sortie plus lisible).

implémentation d'une nouvelle version de la fonction solve appellé `solve2` se trouve dans le fichier `python/utils.py` .
implémentation de la classe `Contexte` , une structure de donnée contenant une liste des buts, une liste des règles et un environnement, se trouve dans le fichier `python/data.py`
La cinquième version de l'interpreteur se trouve dans le fichier `python/interp_v3.py`, il permet de interpreter un programme ayant un ou plusieurs buts.
correction de rename suite aux retours des rapports
Des tests ont été effectués pour les fonctions solve2 et interprete4, on verifie que l'environnement retourné pour les exemples du jalon 4 est correct dans `python/test/jalon4.py`.

## Jalon 5 - 10 avril

Le jalon 5 contient un toplevel `python/main.py` et des fonctions permettant d'afficher le parcours de l'arbre de recherche `python/trace.py`.

On se base sur les fonctions `solve2` et `interprete4` du jalon précédent. On a implémenté le toplevel directement dans le fichier main, car c'est le point d'entrée idéal pour l'utilisation de ce projet.

Pour tester le jalon 5, on peut utiliser directement le fichier main.py comme ceci: `python .\main.py "../exemples/classification2.pl"` et entrer une requete commme `animal(chat).`.

Pour activer la fonction trace, ajouter l'argument trace lors de l'appel a main.py: `python .\main.py "../exemples/classification2.pl" trace`.

Concernant la fonctionnnalité trace, nous avons préféré créer un fichier contenant les fonctions spécialement modifiées pour cette fonctionnalité (plutot que de modifier les fonctions existantes et de rajouter un argument pour activer ou non l'affichage), afin de garder un code clair.


## Temps additionnel - 04/04 au 07/04

Prise en compte des retours des jalons 4 et 5: utilisation de copie simples dans contexte, reduction du nombre de deepcopy de rename, factorisation de solve_trace et solve2, correction et nettoyage du code.

Ajout de fonctionnalités au toplevel: chargement de fichiers en mode interactif, support de l'ajout de regles, ajout d'une commande d'aide. Le toplevel est maintenant entierement interactif (voir la liste de commandes lors du lancement de celui-ci).
Il est également possible de demander toutes les solutions possibles.

Exemple d'utilisation en lancant `python main.py`:
```
> load ../exemples/inexistant.pl
programme non trouvé: ../exemples/inexistant.pl
> load ../exemples/classification2.pl
#> ?- animal(chat).
solution: {}
#> trace 1
trace activée
#> exit
> exit
```