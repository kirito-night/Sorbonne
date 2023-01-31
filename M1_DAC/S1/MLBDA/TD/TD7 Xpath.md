### Exercice 1
**Xpath qui s'applique sur un element de l'ensemble**
question 1
1. tous les menus à moins de 50 EUR
 `/base/restaurant/menu[@prix  <50]`
 `//menu[@prix<50]`
 `restaurant/menu[@prix < 50]`
 2. les menus des restaurants 2 ou 3 étoiles
 `//restaurant[@etoile = 2 or @etoile = 3]`
 3. le nom des villes dans le département 69
 `//ville[@departement  = 69]/@nom`
 4. le nom des restaurants à Lyon
 `//restaurant[@ville = 'Lyon']/@nom`
 5. le nom des restaurants dans le département 75
 `//restaurant[@ville = //ville[@departement = 75 ]/@nom]`
 A = B signifie  $$\exists x \in A , \exists y \in B, x=y $$
 c'est a dire il existe une ville du departement 75 tq son nom = la valeur de l'attribut ville du restaurant
 autre solution:
 `//restaurant[@ville = ../ville[@departement = 75]/@nom]/@nom`
.. permet de remonter vers l'element base contenant les villes et les restaurants
6. Le plus beau monument des villes ayant au moins 1 restaurant 3 étoiles
`//ville[@nom = //restaurant[@etoile =3]/@ville]/plusBeauMonument`
autre solution 
`id(//restaurants[@etoile = 3]/@ville)/plusBeauMonument`
7. les villes avec au moins un restaurant qui a au moins 4 menus
`//ville[@nom = //retaurant[count(menu)>=4]/@ville]`
autre solution sans count () 
	un restaurant pour lequel il existe un 4eme menu ( position() = 4)
`//ville[@nom  = //restaurant[menu[position() = 4]]/@ville`
on peut ecrire `menu[4]` a  la place de `menu[position() = 4]`
8. les restaurants 3 étoiles fermés le dimanche
`//retaurant[@etoile = 3 and contains(./fermeture,'dimanche']`
**contains(A, B ) vrai si A contien B**
9. les restaurants ayant au moins un menu contenant le nom de la ville
`//restaurant[menu[contains(@nom , ../@ville)]`
10.  a. le 2ème menu de chaque restaurant ( notion d'axe)
`//menu[2]`
`//restaurant/menu[2]`
`descendant-or-self::node()/child::menu[2]`
**`descendant-or-self::node()` equivalent a  //** 
b. 
`//descedant::menu[5]` attention l'expression `//menu[5]` n'est pas une expression equivalente .
11. le nombre d'étoiles des restaurants qui se trouvent dans la troisième ville du documents
`//restaurant[@ville = /descendant :: ville[3]/@nom]/@nom`
rmq : tous les elements ville sont des fils directs de base, donc il est possible ici de remplacer `/descendant :: ville[3]` par `//ville[3]`
12. (a) le 2ème menu à moins de 150 EUR de chaque restaurant.
`//restaurant/menu[@prix <150][2]`
position 2 dans le resultat de `//restaurant/menu[@prix <150]`
12. (b)Le 2ème menu de chaque restaurant s'il vaut moins de 150 EUR.
`//restaurant/menu[position() = 2 and @prix <=150]`
ou 
`//restaurant/menu[position() = 2][@prix <=150]`
14. les villes sans restaurant 3 étoiles ( negation avec not())
`//ville[@nom = //restaurant[not(@etoile =3)]/@ville]` => faux donne les villes avec aumoins  1 restaurant qui n'a pas  etoile
bonne solution : `ville[not(@nom = //restaurant[@etoile= 3]/@ville)]`
il n'existe pas de ville d'un restau 3 etoile egale au nom de la ville recherchee
15. (a) les villes sans plus beau monument
`//ville[not(plusBeauMonument)]` avec `plusBeauMonument` dans le [] veut dire il existe au moins 1 plus beau Monument
15. (b) les restaurants dans une ville sans plus beau monument
`//restaurant[@ville = //ville[not(plusBeauMonument)]/@nom]`
reponse incorrecte : `//restaurant [@ville != //ville[plusBeauMonument]/@nom]` car si un element de l'ensemble verifie la condition la condition est vrai
16. les noms des restaurants dont tous les menus coûtent moins cher que les menus du restaurant "Les quatre saisons".

