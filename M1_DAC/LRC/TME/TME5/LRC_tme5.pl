
# T-BOX 
subs(chat,felin).                                    /* ligne 3 */ 
subs(felin,mammifere).
subs(mammifere,animal).
subs(canide,mammifere).
subs(chien,canide).
subs(canide,chien).
subs(canari,animal).
subs(animal,etreVivant).
subs(lion,felin).
subs(lion,carnivoreExc).
subs(carnivoreExc,predateur).
subs(chihuahua,and(chien,pet)).                      /* ligne 14 */
subs(souris,mammifere).
subs(and(animal,some(aMaitre)),pet).                 /* ligne 16 */
subs(pet,some(aMaitre)).
subs(animal,some(mange)).
subs(some(aMaitre),all(aMaitre,personne)).           /* ligne 19 */
subs(and(animal,plante),nothing).
subs(and(all(mange,nothing),some(mange)),nothing).   /* ligne 21 */
equiv(carnivoreExc,all(mange,animal)).               /* ligne 22 */
equiv(herbivoreExc,all(mange,plante)).

# Exo 1
# Signe de Intersection : \sqcap
# Signe de Quantification existentielle: \exists
# Signe de Quantification universelle : \forall
# Signe de subsomption : \sqsubseteq
# Signe d'Equivalence : \equiv
# ligne 3
    # chat \sqsubseteq felin
# ligne 14
    # chihuahua \sqsubseteq  (chien \sqcap pet)
# ligne 16
    # TD4 EX2 g
# ligne 19
    # \exists aMaitre \sqsubseteq (\forall aMaitre.personne)
# ligne 21
    # ((\forall mange.nothing) \sqcap (\exists mange)) \sqsubseteq nothing
# ligne 22
    # carnivoreExc \equiv (\forall mange.animal)

# Exo 2

subsS1(C,C).
subsS1(C,D):-subs(C,D),C\==D.
subsS1(C,D):-subs(C,E),subsS1(E,D).
# 2.2.
# subsS1(canari,animal) donne true
# subsS1(chat,etreVivant) donne true
# Nous rentrons dans une boucle insfinie pour la requête subsS1(chient,souris)
# la cause étant qu'on reutilise un concept déjà utilisé, on n'a pas de cas de base qui permet d'arreter le programme quand on a false

subsS(C,D) :- subsS(C,D,[C]).
subsS(C,C,_).
subsS(C,D,_):-subs(C,D),C\==D.
subsS(C,D,L):-subs(C,E),not(member(E,L)),subsS(E,D,[E|L]),E\==D.

# 2.3. 
# ?- subsS(chat,etreVivant) donne true 
# ?- subsS(chien,canide) donne true
# ?- subsS(chien,chien) donne true
# ?- subsS(chien,souris) donne false et echoue

#2.4
#?- subsS(souris,some(mange)) donne true car souris est un mammifere, mammifere est un animale et annimale subsume some(mange) ducoup fonctionne 

#2.5
# chat subs X donne : 
# X = chat
# X = felin
# X = mammifere
# X = animal
# X = etreVivant
# X = some(mange)

# subsS(X,mammifere)v donne : 
# X = mammifere
# X = felin
# X = canide
# X = souris
# X = chat
# X = chien

subs(C,D) :- equiv(C,D).
subs(D,C) :- equiv(C,D).

#ex3
 
 
