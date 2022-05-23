#


### interprete0
```python
.interprete0(
   ast: Program
)
```

---
Premiere version de l'interpreteur
Permet d'unifier un seul fait avec un seul but
Si plusieurs sont présents, le premier sera sélectionné et les autres seront ignorés


**Args**

* **ast**  : un AST contenant au moins une assertion et un but


**Returns**

l'environnement unifiant la permiere assertion avec le premier but

----


### interprete1
```python
.interprete1(
   ast: Program
)
```

---
Seconde version de l'interpreteur
Permet d'unifier un seul fait avec un seul but
Si plusieurs faits sont présents, le premier fait ayant un symbole similaire au but sera sélectionné


**Args**

* **ast**  : un AST contenant au moins une assertion et un but


**Returns**

l'environnement unifiant la permiere assertion ayant un symbole similaire au but, avec le premier but


**Raises**

* **NotUnifiable**  : si aucun fait n'a de symbole correspondant au but


----


### interprete2
```python
.interprete2(
   ast: Program
)
```

---
Troisieme version de l'interpreteur
Permet d'unifier plusieurs faits avec plusieurs buts


**Args**

* **ast**  : un AST contenant au moins une assertion et un but


**Returns**

l'environnement unifiant la permiere assertion ayant un symbole similaire au but, avec le premier but
