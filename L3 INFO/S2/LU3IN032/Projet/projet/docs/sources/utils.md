#


## Contexte
```python 
Contexte(
   goals: DeclGoal, rules: list, env: Environment
)
```


---
Structure de donne arbonrescent permettant de faire le backtracking


**Attributes**

* **goals**  : une liste de buts a prouver
* **rules**  : une liste de regles
* **env**  : Environnement a utiliser
* **Lcontext**  : liste de contexte fils du noeud
* **choix**  : regle choisie



**Methods:**


### .backtrack
```python
.backtrack(
   numbut, goal: Predicate
)
```

---
Fonction permettant de faire l'unification avec choose

**Args**

* **numbut**  : numero servant pour renomer les variables des regles, incremente enfonction des recursion
* **goal**  : but


**Returns**

* **env**  : l'environnement
* **body**  : liste de regles


----


### subst
```python
.subst(
   term: Term, env: Environment
)
```

---
Fonction de substitution
Effectue toutes les substitutions possible pour un terme associé a un environnement


**Args**

* **term**  : le terme dans lequel on souhaite faire des substitutions
* **env**  : l'environnement associé


**Returns**

le terme avec les substitutions effectuées

----


### replace
```python
.replace(
   term: Term, old: Term, new: Term
)
```

---
Fonction utilitaire permettant de remplacer un terme par un autre dans un terme


**Args**

* **term**  : le terme dans lequel remplacer des éléments
* **old**  : le terme a remplacer
* **new**  : le nouveau terme remplacant l'ancien


**Returns**

le terme avec les replacements effectués

----


### unif
```python
.unif(
   env: Environment, equ: List[Equation], first = True
)
```

---
Fonction d'unification


**Args**

* **env**  : l'environnement de base
* **equ**  : une liste d'equations a resoudre
* **first**  : booléen premettant de différencier un premier appel d'un appel récursif


**Returns**

un environnement permettant de resoudre le systeme d'equations


**Raises**

* **NotUnifiable**  : si il est impossible de résoudre le systeme d'equations


----


### rename
```python
.rename(
   n: int, res, first = True
)
```

---
Fonction de renommage
Ajoute la valeur de n en suffixe a toutes les noms de variables


**Args**

* **n**  : la valeur a ajouter en suffixe
* **res**  : l'element dans lequel rechercher des variables
* **first**  : argument permettant de distinguer un premier appel d'un appel recursif


**Returns**

l'element passé en argument (r) avec les noms de variables remplacés

----


### choose
```python
.choose(
   n: int, env: Environment, goal: Predicate, rules
)
```

---
Fonction permettant de trouver des regles et un environnement permettant d'unifier un goal


**Args**

* **n**  : la valeur du compteur (pour le renommage)
* **env**  : l'environnement a utiliser
* **goal**  : le goal a unifier
* **rules**  : une liste de regles


**Returns**

* **env**  : l'environnement
* **body**  : liste de regles


----


### solve
```python
.solve(
   buts: DeclGoal, rules: list
)
```

---
Fonction permettant de prouver successivement chaque but de la liste goals à  l'aide d'une suite de regles rules


**Args**

* **goals**  : une liste de buts a prouver
* **rules**  : une liste de regles


**Returns**

* **env**  : un environnement permettant de verifier tous les buts de goals


**Raises**

* **NotUnifiable**  : si aucun environnement ne permet de verifier tous les buts


----


### solve2
```python
.solve2(
   goals: DeclGoal, rules: list, env: Environment, numbut = 0, trace = False,
   listDejafait = []
)
```

---
Fonction permettant de prouver successivement chaque but de la liste goals à  l'aide d'une suite de regles rules et de faire du backtracking en cas d'erreur

**Args**

* **goals**  : une liste de buts a prouver
* **rules**  : une liste de regles
* **env**  : Environnement a utiliser
* **numbut**  : numero servant pour renomer les variables des regles, incremente enfonction des recursion
* **trace**  : si le mode trace doit etre active


**Returns**

* **env**  : un environnement permettant de verifier tous les buts de goals


**Raises**

* **NotUnifiable**  : si aucun environnement ne permet de verifier tous les buts


----


### filter
```python
.filter(
   env: Environment
)
```

---
Fonction permettant de filter les variables dans l'environnement pour ne garder que celles qui ne sont pas prefixées afin de garder ques les variables des buts

**Args**

* **env**  : un environnement


**Returns**

Environment
