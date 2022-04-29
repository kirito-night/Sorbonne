LU3IN026 - mars 2022

Informations sur les fichiers MULTICLASSES

Fichiers sont présents dans ce répertoire :
	- names.csv : nom des attributs colonne par colonne des données
	- fichiers d'exemples:
		- train_multi.csv : fichier pour l'apprentissage du modèle
		- test_multi.csv  : fichier permettant de tester le modèle appris
		- eval_multi.csv  : fichier d'évaluation 
		
Les fichiers d'exemples comportent tous un identifiant (unique) 1ere colonne: 
   - train_multi.csv: identifiant de 10001 a 12000
   - test_multi.csv : identifiant de 20001 à 22000
   - eval_multi.csv : identifiant de 90001 à 92000 

Pour tous ces fichiers, le label (classe) se trouve en dernière colonne.
Le séparateur de colonne est le point-virgule (;).

Pour train_multi.csv et test_multi.csv : le label est un entier compris entre 0 et 9 (inclus).

ATTENTION: pour eval_multi.csv, le label indiqué pour tous les exemples est -1.
Ce n'est pas une bonne valeur de classe...
Grâce à un classifier appris sur train_multi.csv (et uniquement sur train_multi.csv), il vous faut fournir pour chaque exemple de eval_multi.csv la classe (0, 1, ..., ou 9) prédite par votre modèle.

A titre indicatif, pour un classifieur basique: apprentissage du modèle avec train_multi.csv :
	- évaluation de test_multi.csv: taux de bonne classification de 0.732
	- évaluation de eval_multi.csv: taux de bonne classification de 0.741
	
	
