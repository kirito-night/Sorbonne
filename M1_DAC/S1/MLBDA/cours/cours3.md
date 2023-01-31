# MLBDA cours 3
>obsidian://open?vault=M1_DAC&file=MLBDA%2F%20cours%2Fcours3
## creation de Table
![[Pasted image 20220928161737.png]]

## expression de chemin 
Un chemin permet de naviguer à travers les objets
 **Syntaxe d'une expression de chemin : `v.a1.a2. ... .ak.f`**
 * un chemin commence par un variable `v`
 *  les termes intermediaires `a_i` sont des nom d'attributs de type atomique ,  objet , `REF` ou `collection` a un objet 
 *  Le mot final `f` est un nom d'attribut de type atomique, objet, REF ou  collection.

**=>Un chemin traverse des objets intermédiaires en suivant des  
associations 1-1 ou N-1 (objet, ref), mais pas 1-N ni N-M (collections).**
* exemple: 
![[Pasted image 20220928162809.png]]
![[Pasted image 20220928162845.png]]
![[Pasted image 20220928162911.png]]
![[Pasted image 20220928163020.png]]
- fonctiond `deref` : La fonction deref prend une expression de chemin dont le type est une  
référence à un objet et retourne un type objet.
![[Pasted image 20220928163239.png]]
interrogation 
![[Pasted image 20220928163324.png]]
### Interroger les varray
![[Pasted image 20220928163752.png]]

## Navigation dans les  collections (1-n) : table
Pour parcourir les collections (associations 1-N), il faut les désimbriquer (ou aplatir).  
L’expression table permet d’interroger une collection dans la clause from comme  
une table.
![[Pasted image 20220928164003.png]]
renvoie la collection des membres , sous formes desimbriques 
![[Pasted image 20220928164050.png]]
## Interrogation de collections de collections
![[Pasted image 20220928164146.png]]
## Expression `Table` 
**L’expression table peut contenir une sous-requête d’une collection.**
![[Pasted image 20220928164447.png]]
Remarques :  
◦ la sous-requête doit renvoyer une seule valeur de type collection  
◦ la clause select de la sous-requête doit contenir un seul attribu

## La fonction `value` et `is of type`
- L’expression * renvoie la valeur (nuplet) des objets de type Personne:
![[Pasted image 20220928164910.png]]
- La fonction value(p) retourne l’objet de type Personne:
![[Pasted image 20220928164949.png]]
- tous les Etudiants parmsi les personnes 
![[Pasted image 20220928165021.png]]

![[Pasted image 20220928165910.png]]
- exemple : 
![[Pasted image 20220928170227.png]]
- comparer par les valeur 
![[Pasted image 20220928170256.png]]
- comparer par reference ![[Pasted image 20220928170329.png]]

## PL/SQL et SQL3 : `bulk  collect into`
L’instruction bulk collect into permet d’affecter à une variable l’ensemble des objets retournés par une requête SQL.
L’instruction se place dans la clause select, juste avant la clause from de  
la requête.
![[Pasted image 20220928172622.png]]

## <u>Requete de mises a jour</u>
**Pour mettre à jour des éléments d’une collection imbriquée,  il faut utiliser l’expression table dans l’instruction du DML  **
- insert: insertion de nouveaux éléments dans une collection
- update : Suppression d’un élément
- delete : Mise à jour d’un élément
**<u>VARRAY</u> :**  
◦ Oracle ne permet pas d’insertion, de suppression et de mise-à-  jour d’éléments sur les colonnes de type VARRAY  
◦ seules les modifications atomiques (« remplaçant tout le varray ») sont autorisées.

### creation d'instance 
Les instances sont créées avec des instructions SQL (insert ou update)
`insert into <table> values (<constructeur>( <valeur>,<valeur> ...)`
![[Pasted image 20220928173234.png]]
- **Création d’instances  avec références**
- ![[Pasted image 20220928173509.png]]
- creation d'instances dans les collections 
![[Pasted image 20220928173548.png]]
- *exemple : *
![[Pasted image 20220928173611.png]]
![[Pasted image 20220928173652.png]]

- **Collections de collections** 
	- Types collection dont les éléments sont eux-mêmes des  
	collections :  
	◦ Nested table of nested table  
	◦ Nested table of varray  
	◦ Varray of nested table  
	◦ Varray of varray  
	◦ Nested table (ou varray) d’un type défini (par l’utilisateur), qui  
	possède un attribut collection (varray ou nested table)
- **Mises a jour des collections de collections 
	- Les modifications dans les collections de collections peuvent être faites de façon  atomique, sur la collection en entier, ou sur des éléments sélectionnés.
	- ![[Pasted image 20220928173923.png]]
	- **Insertion dans une collection de collection 
	-![[Pasted image 20220928174130.png]]
