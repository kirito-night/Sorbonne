![[TD_2021.pdf]]
### introduction
-  class metier 
	- resp d'annee (cf. la diagrame class metier ) 
- class systeme 
	- ens de fonctionnalite

- **Composant et decoupage** 
	- essayer de rompre les liens
		- ex : lien maitre chien -> remplacer les liens composant par utilisation des identifiants, chien a l'id maitre et vise vers ca, qui permet de reconstruire le lien. 
	![[Pasted image 20221019111034.png]]
On choisit le decoupage le plus fin  num 3
	du coup nous allons mettre les identifiants pour reconstruire les liens afin de remplacer les liens 
![[Pasted image 20221019111827.png]]
- SIGB
	- demandeEmprunt(idAbos)
	- ajoutEx(idEx)
	- finEmprunt()
	- restituer(idEx, etatEx)
nous allons mettre les differents interface qui va composer le systeme 
-  iter IDocs
	- recherche(critere)
- IEmps
	- demanderEmprunt()
	- ajoutEx()
	- restituer
	- prolongerEmprunt()
- IAbos
	- listeRetardataire()
	- envoyerMail
- IExs 
diagramme de sequence : 

	bob : Bi             SIGB
			demanderEmp(001)->
										checkAbo()
			<- true
			ajoutEx(10k_)-> 
										checkEx()
										createEx()
			<-true	
			finEmp()->				

![[Pasted image 20221019120730.png]]
![[Pasted image 20221019123139.png]]
