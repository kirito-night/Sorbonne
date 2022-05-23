# Poster Science de donnée 
### Fanxiang Zeng 28600693
### Thomas Hebert 

### Introduction 


### Problème d’apprentissage supervisé
Pour apprentissage supervisé nous avons utilisé le dataset `synthèse`.
**Problématique : quel est le groupe d'aliment qui impacte le plus l'environnement**
* Dans un premier temps pour avoir des données fiable , on a filtré les données en ne gardant seulement les données ayant un `DQR` inférieur à 2.5. 
* Ensuite nous avons gardé seulement les colonnes les plus significatifs et les plus fiables : Changement climatique (kg CO2 eq/kg de produit), Particules (E-06 disease inc./kg de produit) et Appauvrissement de la couche d'ozone (E-06 kg CVC11 eq/kg de produit).
* Puis nous créeons un `ClassifierArbreNumerique` et avec un les colonnes qu'on a choisit et on l'entraine sur nos données réduite, avec comme label la colonne de `Groupe d'aliment` et nous obtenons un arbre numérique. Nous avons choisit celui-ci de classifier car c'est celui qui nous l'air le plus logique qui peut naturellement prendre les labels sous forme de String.
* Pour vérifier si l'arbre que nous avons obtenus est correcten, on effectue la validation croisée et fait la moyenne des accuracies sur 10 test après les entrainement, et nous obtenons une accuracy moyenne de 0.789. Un accuracy certe pas très performant mais acceptable. Nous avons remarqué également que si on voulez faire des classifications plus précis sur les `sous-groupes d'aliment` l'accuracy baisse fortement avec en moyenne 0.65 d'accuracy sur les validatons croisés.
* Après avoir montrer que l'arbre que nous avons obtenue est correcte, nous obtenons  le groupe d'aliment qui impacte le plus l'environnment en terme de synthèse est viandes, œufs, poisson en se trouvant de nombreux reprise tout à droite de l'arbre.