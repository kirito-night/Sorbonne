#


### interprete4
```python
.interprete4(
   ast: Program, trace = False, listDejaFait = []
)
```

---
quatrième version de l'interpreteur
Permet d'interpreter un programme avec plusieurs buts


**Args**

* **ast**  : un AST contenant un ou plusieurs but et une seule règle par symbole de prédica
* **trace**  : si le mode trace doit etre active pour la fonction solve2


**Returns**

l'environnement qui contient la solution de ce prolog
