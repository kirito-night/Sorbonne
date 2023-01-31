> bear://x-callback-url/open-note?id=2E0D9AF7-E2BD-4C29-83B8-43EFA7266302-11007-000000DC32B5F2A2

# MLBDA cour2
* Schema relationnel : 

![](&&&SFLOCALFILEPATH&&&E9B2DB8E-222F-429D-996B-5B7F00229669.png)

* Modele objet : 
	* Une entité est représentée par un objet identifié
	* reliés entree eux par des liens de composition et des liens d’heritage
	* Les opérations sont associées à l’objet
![](&&&SFLOCALFILEPATH&&&BE08755E-4983-4773-8191-68FAB4B83D00.png)

## objet 
Objet  : couple (identificateur , valeur)
* identificateur  : indépendante de la valeur et géré par le système
* objet atomique :objet avec une valeur atomique
* objet complexe : objet avec une valeur complexe.
Deux objets  : 
	* sont  identique si ils ont le meme identificateurs 
	* égaux s’ils contiennent les même valeurs et objets
	* égaux en profondeur (deep equal) s’ils contiennent les mêmes valeurs et des objets qui sont égaux en profondeur
Exemples : 
![](&&&SFLOCALFILEPATH&&&9466BE0D-A219-41F2-99DD-222D05E6AFFD.png)
L’identité de l’objet permet le partage : 
	* le **partage** des objets
	* les **cycles** entre les objets 
	* le maintien automatique des **contraintes référentielles**
## Classes 
* Definition : les objets partageant des caractéristiques communes 
* Permet l’abstraction des informations et leur representations (utile pour les schema de base ) 
* exemple  : class Personne [nom : string, age : integer, adresse : string, conjoint : Personne, enfants : {Personne} ] …
* 




