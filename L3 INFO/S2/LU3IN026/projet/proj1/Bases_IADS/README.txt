LU3IN026 - Mars 2022

Informations sur les fichiers

Fichiers sont présents dans ce répertoire :
	- names.csv : nom des attributs colonne par colonne des données
	- fichiers d'exemples:
		- train.csv : fichier pour l'apprentissage du modèle
		- test.csv  : fichier permettant de tester le modèle appris
		- eval.csv  : fichier d'évaluation 
		
Les fichiers d'exemples comportent tous un identifiant (unique) 1ere colonne: 
   - train.csv: identifiant de 10001 a 11000
   - test.csv : identifiant de 20001 à 21000
   - eval.csv : identifiant de 90001 à 91000 

Pour tous ces fichiers, le label (classe) se trouve en dernière colonne.

Pour train.csv et test.csv : le label est la classe à trouver soit +1 soit -1 (entier)

ATTENTION: pour eval.csv, le label indiqué pour tous les exemples est 0.
Ce n'est pas une bonne valeur de classe...
Grâce à un classifier appris sur train.csv (et uniquement sur train.csv), il vous faut fournir pour chaque exemple de eval.csv la classe (+1 ou -1) prédite par votre modèle.


