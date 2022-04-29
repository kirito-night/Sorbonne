LU3IN026 - mars 2022

Informations sur les fichiers MULTICLASSES

Fichiers sont pr�sents dans ce r�pertoire :
	- names.csv : nom des attributs colonne par colonne des donn�es
	- fichiers d'exemples:
		- train_multi.csv : fichier pour l'apprentissage du mod�le
		- test_multi.csv  : fichier permettant de tester le mod�le appris
		- eval_multi.csv  : fichier d'�valuation 
		
Les fichiers d'exemples comportent tous un identifiant (unique) 1ere colonne: 
   - train_multi.csv: identifiant de 10001 a 12000
   - test_multi.csv : identifiant de 20001 � 22000
   - eval_multi.csv : identifiant de 90001 � 92000 

Pour tous ces fichiers, le label (classe) se trouve en derni�re colonne.
Le s�parateur de colonne est le point-virgule (;).

Pour train_multi.csv et test_multi.csv : le label est un entier compris entre 0 et 9 (inclus).

ATTENTION: pour eval_multi.csv, le label indiqu� pour tous les exemples est -1.
Ce n'est pas une bonne valeur de classe...
Gr�ce � un classifier appris sur train_multi.csv (et uniquement sur train_multi.csv), il vous faut fournir pour chaque exemple de eval_multi.csv la classe (0, 1, ..., ou 9) pr�dite par votre mod�le.

A titre indicatif, pour un classifieur basique: apprentissage du mod�le avec train_multi.csv :
	- �valuation de test_multi.csv: taux de bonne classification de 0.732
	- �valuation de eval_multi.csv: taux de bonne classification de 0.741
	
	
