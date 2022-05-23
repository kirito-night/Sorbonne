_Documentation du rendu de projet, à remplir._

# Jalon 1

## Question 1
Comme nous avons choisi le langage Java, nous allons représenter les équations par une classe `Equation` qui aura pour attributs deux `Terme`. Ainsi, on pourra représenter un système d'équations par une Collection de l'API Java. Ici, on utilisera les `List`, plus précisément les `ArrayList` afin de dynamiser le remplissage et améliorer l'efficacité.  
Nous créons donc une classe `Systeme` qui implémentera les règles d'unification. Chaque méthode correspondant à une règle est de visibilité `private` car elles sont utilisées uniquement par la méthode `unify`.  
Pour automatiser l'unification du système d'équations, nous décidons d'essayer d'appliquer chaque règle au système entier dans un ordre arbitraire.

## Question 2
Notre fonction `occur_check` est implémentée dans la classe `Equation`. Elle est de visibilité `private` car elle n'est appelée que localement dans la fonction `formatROK`. Cette fonction renvoie `true` si l'instance d'`Equation` est une bonne candidate pour appliquer la règle remplacer. C'est-à-dire que son `Term` gauche est une instance de `TermVariable`.  
`occur_check` utilise un `TermVisitor`, `VisitorVar` qui renvoie la liste des variables trouvées dans un `Term`.  
Ainsi, elle lève l'exception `NoSolutionException` dans le cas où une variable qui se trouve dans le `Term` gauche est dans le `Term` droit d'une `Equation`. Cette exception est rattrappée le plus tard possible, c'est-à-dire dans la méthode `unify` de la classe `Systeme`, ce qui nous permet de mettre fin à la boucle de la méthode.


## Question 3
Pour l'environnement, nous l'implémentons à l'aide d'une `Map` Java. Cela nous permet d'associer un `TermVariable` unique a un `Term`. Nous ajoutons cet environnement comme attribut à notre classe `Systeme` afin d'alléger l'écriture des algorithmes suivants.

## Question 4
La substitution d'une variable dans une équation est déléguée à la classe `Equation` qui prend un `TermVariable` à remplacer et le `Term` correspondant. Cette méthode secondaire est appelée pour toutes les variables contenues dans l'environnement lié au système passé en paramètre.

## Question 5
Notre algorithme `unify` est une méthode d'instance propre à un objet `Systeme` contenant des équations et un environnement. Il prend donc implicitement en paramètre ces deux éléments.  
Pour appliquer l'algorithme d'unification sur un système d'équation, il faut donc dans un premier temps instancier un objet de type `Systeme` et remplir son environnement et ses équations.


### Conclusion :
Dans ce premier jalon, nous avons implanté l'unification d'un système d'équations et le debut d'un affichage graphique.  
Dans un premier temps, nous avons choisi d'implémenter la règle `subst` (`remplacer`) sans utiliser le Design Pattern Visiteur car nous voulions changer directement les `TermVariable` sans dans les objets concernés : `Equation` et `TermPredicat` en tant qu'argument. Mais son comportement quasi similaire nous fait douter de l'implémentation de cette règle. Nous la modifierons sûrement plus tard dans le projet.  
Dans un second temps nous avons créé une interface graphique simple avec une zone d'édition de texte et un systeme de chargement/sauvegarde de la zone d'édition qui utilise un lecteur de fichier simple et utilise le parser donné dans le projet. Nous améliorerons cette interface avec le temps.  

Pour exécuter ce Jalon, il suffit d'exécuter la classe `MainUnification` qui contient tous les exemples de l'exercice 7 du TD4 concernant l'unification. La vérification du bon fonctionnement des fonctions se base sur les affichages du système d'équations et de l'environnement à chaque règle appliquée.  
Nous avons rencontré des difficultés concernant l'implantation de la règle remplacer. Comme nous l'avons dit précédemment, nous avons des doutes sur l'efficacité de nos méthodes.



# Jalon 2
Nous avons renommé notre classe `MainUnification` qui testait les unifications des équations du TD par `Jalon1` pour correspondre à l'énoncé.  
Nous avons également modifié la classe `MainTestRegles` qui avait pour but de tester les règles d'unification une à une et de contrôler leurs effets sur le système par l'affichage. Ces mêmes affichages, pour ce Jalon 2, ont été commentés et supprimés afin de ne pas alourdir l'affichage des interprètes.  
Une classe `Environnement` a été créée afin de ne pas surcharger la classe `Systeme` et aléger la signature des méthodes créées pour ce Jalon.  

Nous plaçons les méthodes des différents interprètes une classe "statique" `Interprete`.  
Dans nos méthodes, nous vérifions que les programmes vérifient bien les conditions de l'énoncé. Si ce n'est pas le cas, nous levons l'exception `IllegalArgumentException`.
On lève cette exception dans :
- `interprete0` quand :
	- une `DeclAssertion` n'est pas un fait
	- il n'y a pas qu'un seul fait
	- il n'y a pas qu'un seul but
- `interprete1` et `interprete2` quand :
	- les `DeclAssertion` ne sont pas que des faits
	- il y a deux faits avec le même symbole de prédicat

`interprete1` lance en plus l'exception quand il y a trop de buts.


Nos fichiers tests `.pl` se trouvent dans le dossier nommé `tests_jalon_pl`. Dans la classe `Jalon1` (du package `pcomp.prolog.ast.test`), nous avons ajouté les tests avec tous les fichiers `.pl` que nous fournissons.  
Nous testons aussi les cas où l'interprete rejetterait le `Program` passé en paramètres.

Dans les méthodes d'interprètes, la manière que nous avons de séparer les faits et les buts n'était pas très optimale et était plutôt redondante. Nous avons donc décidé d'implanter un `DeclVisitor` que l'on appelle `VisitorDecl`. Il sépare les faits et buts et construit les listes. La valeur de retour ne nous sert pas encore donc nous avons mis des `List<Predicate>` par défaut.  
Ce visiteur pourra être étoffé s'il faut à l'avenir traiter les `DeclAssertion` qui ne seraient pas des faits.



# Jalon 3
Nous avons déplacé les classes exécutables correspondant aux Jalons dans le package principal `pcomp.prolog.ast` afin de les mettre davantage en évidence.  
Celui correspondant à ce Jalon est `Jalon3`. Dans le main de cette classe, nous testons notre méthode `interprete3` avec les fichiers tests `.pl` que l'on avons mis avec les autres, dans le dossier `tests_jalon_pl`.

Pour la méthode `rename` que l'on place dans la classe `DeclAssertion`, nous implémentons un autre `TermVisitor` qui nous renvoie un nouveau `Term` avec les variables renommées. La méthode principale délègue le renommage des variables au `Predicate` qui utilise le visiteur décrit précédemment.  
Pour la substitution des variables, nous pouvons faire un visiteur similaire. Il sera sûrement implanté au cours d'un Jalon ultérieur.

Nos méthodes `choose` et `solve`, pour le moment `public`, sont placées dans notre classe statique `Interprete`. Ces méthodes peuvent aussi être de visibilité `private` car elles ne servent qu'à l'implantation de la méthode `interprete3`. Néanmoins, les premiers tests effectués sur ces méthodes se trouvant dans une autre classe `MainTestJalon3`, nous les conservons donc de visibilité `public`.  
Comme nous pensons que l'`interprete3` doit pouvoir résoudre les AST qui ne contiennent pas que des Assertion(head, body) avec body non vide, nous généralisons la résolution à tous les `DeclAssertion`, c'est pour cela que nous passons une `List<DeclAssertion>` pour le paramètre `rules`, qui contiendra par la suite toutes les `DeclAssertion` de l'AST.

Notre `VisitorDecl` a été modifié pour pouvoir l'utiliser même dans le cas où l'AST contiendrait des règles conditionnelles Assertion (head,body). Pour ne pas changer l'algorithme des interprètes implémantés précédemment, c'est-à-dire pour qu'ils lèvent toujours `IllegalArgumentException` dans le cas où l'AST passé en paramètre ne contient pas seulement des faits et des buts, nous surchargeons le constructeur du Visiteur avec l'initialisation d'un `boolean` qui indiquera si on prend, ou non, les Assertion(head,body) avec body non vide.

Nous remarquons que l'`Environnement` renvoyé contient beaucoup de variables à cause du renommage. Nous nous demandons donc s'il ne serait pas meilleur de "nettoyer" l'`Environnement` avant son affichage en faisant les substitutions possibles pour ne conserver que les variables qui seraient pertinentes.


# Jalon 4

Pour représenter un choix, nous implémentons la classe `CurrContext`. Dans une instance, nous conservons l'`Environnement` résultant du choix, les règles à explorer et les buts qu'il reste à résoudre. Dans l'attribut choix, nous précisons la règle choisie.  
Une suite de choix sera donc représentée par une `List<CurrContext>` se comportant comme une pile. Le choix le plus récent sera à la position `size()-1` de la liste et le plus ancien sera à la position `0`.  
Pour afficher une suite de choix, nous implémentons la méthode statique `afficheListChoices` dans la classe `CurrContext` qui utilise la méthode `afficheChoice` qui affiche le choix effectué.  
Néanmoins, ces méthodes d'affichages ont été modifiées par la suite pour l'implémentation d'un interprete qui se sert d'un journal de choix de forme arborescente.

La méthode `solve` demandée dans ce Jalon est implantée dans la classe `Interprete`. L'algorithme décrit est implémenté par la méthode `private` `choose` qui est une méthode récursive. La méthode `public` `solve` se contente donc de rattraper les exceptions lancées par choose.  
On peut se passer de cette division de l'algorithme si on modifie `choose` pour qu'elle soit récursive terminale.



# Jalon 5

Dans le Jalon 3, nous avons évoqué le nombre important de variables non-pertinentes contenues dans l'`Environnement` à cause du renommage.  
Nous avons donc implémenté une méthode `nettoieEnv` dans la classe `Environnement` qui prend en paramètre une liste de variables qui sont celles des buts initiaux. Ainsi, l'`Environnement` renvoyé dans les interprètes des deux Jalons précédents ne contient strictement que les variables qui servent à résoudre les buts.

L'implémentation du toplevel se fait dans les packages `pcomp.Gui` et `pcomp.IO`. Elle est lancée en exécutant la classe `Gui` dans le package `pcomp.Gui`. L'interprète utilisé pour l'affichage dans l'interface est le dernier interprète donnant l'option de chercher plusieurs solutions. Le suivi de l'exécution par l'affichage est récolté par la classe "statique" et ne peut être affichée qu'à la fin de l'exécution de l'interprète. On affiche donc la dernière solution trouvée à travers la fenêtre qui demande à l'utilisateur s'il veut chercher d'autres solutions.

Pour l'algorithme nous servant à donner plusieurs solutions, nous alons modifier la méthode `choose` implémentée dans le Jalon précédent.  
Notre algorithme se repose sur la sauvegarde des contextes dans un journal de choix. Nous remontons chaque choix, en commençant par le choix le plus récent, pour voir si on ne pouvait pas choisir autrement à ce moment-là.  
Nous ajoutons donc un attribut `List` à `CurrContext` qui nous sert à conserver les choix effectués juste après le choix courant. Il sera ajouté lors de la création du contexte du choix suivant. La liste est initialisée à vide.  
Dans notre méthode `choose`, nous allons vérifier, lors du choix de la règle à unifier, qu'il n'a pas été effectué avant. On remonte ainsi à chaque demande de solutions différentes.  
Pour faire cela, nous avons modifié notre classe `CurrContext` et notre manière de représenter un journal de choix. Le nouvel attribut `nextChoices` est une liste de `CurrContext`, ce qui donne donc une représentation arborescente à notre journal de choix qui sera désormais le `CurrContext` racine où l'attribut choice sera `null`.  
Nous modifions donc la fonction d'affichage pour qu'elle ne montre qu'un seul chemin : on part donc d'une feuille.
