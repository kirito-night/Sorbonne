# TD 3 IL 
## fiche détaillée des cas d’utilisation
- titre 
- description 
- acteurs
- <u>precautions</u> 
- sequencement nominal
- post precaution 
- alternatives
- Exception 
*cf fiche de cours*

fiche detaille de l'emprunt 

- nom du cas d'utilisation : Emprunt
- date : 04/10/2022
- auteurs : creators 
- description : decris l'emprunt d'un documents / livre du bibliotheque,
- Pre-condition : 
	- le livre 

- Scenario nominal : 
	1.  le bibliothecaire choisit d'emprunter 
	2. systeme propose une interface graphique pour  entrer les informations 
	3. bibliothecaire  donne   l'id emprunteur au  dans le systeme 
	4. le systeme verifie combien de livre a  emprunte l'emprunteur et en fonction de son statut verifie l'emprunteur peut toujours emprunte des livres 
	5. le systeme affiche une interface indiquant cb de livre il a emprunte , des retards , cb de livre il peut emprunte 
	6. bibliothecaire scanne le code barre du  document / livre empruntee 
	7. le systeme affiche une interface graphique en fonction de l'etat  de livre verifie si le livre est empruntable, si non empruntable le systeme renvoie l'etat du livre et indique que ce livre n'est pas empruntable pour le moment
	8. Si empruntable, en fonction du statut de l'emprunteur, renvoie sur une nouvelle fenetre la date de retour du livre 
	9. le systeme met le livre / document sous l'id scanner sous statut emprunter 
- Post-Condition : 
	-  le nombre de livre empruntee par l'emprunteur incremente de 1, le nb de livre qu'il peut empruntee decremente de 1 
	- le  livre / document sous l'id scanner est mises sous statut emprunter 
	- une nouvelle emprunt avec date de retour est mises dans le systeme 
- Alternative/ Exeption : 
	- l'emprunteur a atteint le quotas maximum de son statut 
	- l'emprunteur a des livres en retard 
	- le livre dont l'emprunteur veut emprunter n'est pas en rayon 

