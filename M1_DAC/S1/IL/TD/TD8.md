## Annales 2020 
### question de cours 
1. deux defauts du cycle en V 
	- absence d'iteration 
	- interaction client limite(negociation contrat) -> effet tunnel
	- rigidite du cycle etapes trop contraint 
	- lourdeur de mise en application + artefacts non operationels
	
2. expliquer le rapport entre le cahier des charges(cdc) et les tests de validation ,d'integration et unitaire
	- cdc -> TV : oui , directement lie 
		- Test de validation : capture des requirements 
	- cdc -> TI : derive des sequences d'analyse valide des interactions "business"
	- cdc -> TU : peu ou pas de rapport avec Cdc 
	
3. pourquoi demandes-t-on dans l'UE de e pas placer les interfaces dans les packages qui realisent les composants 
	- eviter les dependances entre differents implementation 
4. pourquoi les diagrammes de structure interne sont-ils necessaires en plus des diagrammes de composants 
	- permet de representer une instanciation , une configuration , les topologies  de connexion  des instances des composants
	
### Probleme Conception DMC 
#### 1 un graph construit a la volee 
![[Pasted image 20221130111739.png]]
![[Pasted image 20221130111814.png]]
1. CExplorer 
![[Pasted image 20221130112724.png]]
2. diagramme de sequence 
![[Pasted image 20221130114053.png]]
3. construction a la volee 
#### 2. Distributed Hash Table DHT 
![[Pasted image 20221130114303.png]]
1. (1 point) Sur un diagramme de composant, définir un composant basique `CHashNodeSet` qui permet  le stockage des nœuds dans une table de hash.
	- ![[Pasted image 20221130120409.png]]
2. Sur un diagramme de séquence, modéliser l’ajout d’un nœud n0 dans un CHashNodeSet initialement vide
	- ![[Pasted image 20221130120439.png]]
3. On considère un composant CSetMulti qui réalise INodeSet et prépare la réalisation de la   DHT. Ce composant s’appuie sur N occurrences de INodeSet et joue un rôle d’aiguillage : quand on   ajoute un nœud au CSetMulti, il calcule d’abord sa valeur de hash modulo N, puis il ajoute le nœud à   l’instance de INodeSet désignée par cet indice. Représenter ce composant sur un diagramme de   composant.
	- ![[Pasted image 20221130120550.png]]
4. Modéliser sur un diagramme de séquence l’ajout d’un nœud n0 à une occurrence de   CSetMulti configurée pour s’appuyer sur deux occurrences (s0 et s1) de INodeSet.
	- ![[Pasted image 20221130120956.png]]
![[Pasted image 20221130121042.png]]
5. Modéliser ces composants `CSetProxy` et `CSetStub` sur un diagramme de composants
	- ![[Pasted image 20221130122322.png]]
6. Sur un diagramme de structure interne, modéliser cette configuration en considérant que  l’on a une table de hash distribuée sur deux machines d’indices 0 et 1. On précisera par des  commentaires sur quelle machine chaque composant est instancié.
	- ![[Pasted image 20221130124028.png]]
#### 3. Une File de noueds a explorer 
![[Pasted image 20221130124119.png]]

