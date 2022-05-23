#


## Equation
```python 
Equation(
   t1: Term, t2: Term
)
```


---
Classe représentant une équation


**Attributes**

* **term1**  : terme a gauche de l'equation
* **term2**  : terme a droite de l'equation


----


## Environment
```python 

```


---
Classe représentant un environnement
Cette classe stocke les données dans un dictionnaire mais permet des tests additionnels notamment pour l'ajout d'elements


**Attributes**

* **env**  : Dictionnaire représentant l'environnement et associant des TermVariable a des Terms



**Methods:**


### .add
```python
.add(
   var: TermVariable, value: Term
)
```

---
Methode d'ajout d'une variable a l'environnement


**Args**

* **var**  : la variable a ajouter
* **value**  : le terme représentant la valeur de cette variable


**Returns**

True si la variable a bien été ajoutée (le test d'occurence est vérifié)

### .get
```python
.get(
   var: TermVariable
)
```


### .delete
```python
.delete(
   var: TermVariable
)
```


### .keys
```python
.keys()
```


### .update
```python
.update(
   env
)
```

