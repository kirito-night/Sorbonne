![[Cours2021.pdf]]

![[TD_2021.pdf]]
- **titre** TV01 : Emprunt nominal  ref a un scenario d'un UC 
- **contexte** : contexte applicatif -> etat des classes metier 
- strat1 -> un scenario par meme systeme dans cet etat 
- strat2 -> etat du system 
- **contexte : u007 est un abo privilegie sans emprunt  20k _ex1 est un exemplaire "en rayon"
- **Entree = id_ab = u007 id_Ex = 20k_ex1 
- **scenario :  que les etapes du testeur 
	1. choisir "emprunter"
	2. saisir idAbo = u007 et valider 
	3. saisir idEx = 20k-ex1 et valide 
**R.A**= le systeme affiche la description de u007 on voit l'exemplaire dans ses emprunts 
 -> l'emprunt de 20l_ex1 par u007 est enregistree
**M.V**
	- affichages : controle visuel 
	- le test "emprunt exemplaire deja en pret" est possible 
	- le test TV03 restituer nominal est possible


**titre**:  TV02 , emprunt alternatif , exemplaire en pret 
**contexte** : immediatement apres TV01
**entrer** : id_abo : u008 , id_Ex : 20k_ex1 
**Scenario** : 
	1. choisir "emprunter"
	2. saisir idAbo = u007 et valider 
	3. saisir idEx = 20k-ex1 et valide
**Resultat attendu** : 
- le systeme affiche "exemplaire deja en pret"
- l'emprunt existant n'est pas modifie 
**moyen de verification :**
- visuel pour l'affichage 
-  demarer un emprunt avec u007 , controler que 20k_ex1 est  a lui 

**Titre :** TV03 , UCO2 nominale , Restituer 
**Contexte :** immediatement apres TV01 , ou TV02 id_EX  =20k_ex1
**Entree :**   etat : TBE , idEX = 20k_ex1
**Scenario :**
1. choisir "Retour"
2. saisir idEx = 20k_ex1
3. saisir l'etat = "TBE" est valide 
**Resultat  attendue :**
- affichage fiche utilisateur de u007  avec 0 emprunt 
- l'emprunt et detruit 
- l'exemplaire est en rayon
**Moyen de Verification :**
- visuel pour les affichages 
- on peut executer de nouveau TV01 

**Titre :** TV04 , capacite d'emprunt 
**Contexte :** 
- u009 abonne occasionnnel sans emprunt 
- 20k_ex2 et 20k_ex3 exemplaires en rayon 
**Entree :**   etat : TBE , idEX = 20k_ex1
**Scenario :**
1. choisir emprunt 
2. saisir l'id abo = u009
3. saisir l'idEx = 20k_ex2
4. selectionner ajouter emprunt 
5. saisir idEx = 20k_ex3
**Resultat  attendue :**
- emprunt impossible affiche " emprunt impossible capacite  est atteinte pour u009"
- l'emprunt n'est pas enregistree 
**Moyen de Verification :**
- visuel pour les affichages 
- emprunter 20k_ex3 avec un autre abonne cf TV04

**Titre :** TV05 utilisateur interdit nominale 
**Contexte :** u007 n'est pas interdit 
**Entree :** idAbo = u007 
**Scenario :**
1. choisir sanction 
2. saisit de idAbo =u007  dans la recherche 
3. selectionne "statut interdit " et valider

**Resultat Attendue :**
- l'abonne u007 est interdit , il n'a plus de droit d'emprunter 
**Moyen de verification :**
- executer TV07 = UC01 , A5  - Utilisateur interdit 
