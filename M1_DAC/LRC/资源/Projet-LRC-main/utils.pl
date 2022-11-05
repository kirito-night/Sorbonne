/* PROJET LRC - M1 DAC */
/* ESTHER CHOI - GROUPE 1 */

/*member(X,L) : prédicat prédéfini, teste si l’élément X appartient à la liste L.*/

/*concat/3 : concatene les deux listes L1 et L2 dans L3.*/
concatene([],L1,L1).
concatene([X|Y],L1,[X|L2]) :- concatene(Y,L1,L2).

/*enleve/3 : supprime  X  de  la  liste  L1  et  renvoie  la  liste  résultante  dans  L2.*/
enleve(X,[X|L],L) :-!.
enleve(X,[Y|L],[Y|L2]) :- enleve(X,L,L2).

/*genere/1 : génère un nouvel identificateur qui est fourni en sortie dans Nom.*/
genere(Nom) :- compteur(V),nombre(V,L1),
               concatene([105,110,115,116],L1,L2),
               V1 is V+1,
               dynamic(compteur/1),
               retract(compteur(V)),
               dynamic(compteur/1),
               assert(compteur(V1)),nl,nl,nl,
               name(Nom,L2).

nombre(0,[]).
nombre(X,L1) :- R is (X mod 10),
                Q is ((X-R)//10),
                chiffre_car(R,R1),
                char_code(R1,R2),
                nombre(Q,L),
                concatene(L,[R2],L1).
chiffre_car(0,'0').
chiffre_car(1,'1').
chiffre_car(2,'2').
chiffre_car(3,'3').
chiffre_car(4,'4').
chiffre_car(5,'5').
chiffre_car(6,'6').
chiffre_car(7,'7').
chiffre_car(8,'8').
chiffre_car(9,'9').

/*lecture/1 : permet  d’acquérir  au  clavier  la  liste  L  des  termes  Prolog  entrés  par
l’utilisateur, de la façon suivante :
l’utilisateur  entre  un  terme  Prolog  directement  suivi  d’un  point  et  d’un  retour  chariot  à  la
vue  du  prompteur  et  ainsi  de  suite.  Lorsque  l’utilisateur  souhaite  interrompre  la  saisie  de
termes, il doit taper fin, suivi d’un point et d’un retour chariot.*/

lecture([X|L]):- read(X),
                 X \= fin, !,
                 lecture(L).
lecture([]).

/*flatten(Liste1,Liste2) : Aplanit Liste1 et met le résultat dans Liste2. Liste1 peut
contenir  de  nombreuses  listes  imbriquées  récursivement.  flatten/2   extrait  tous  les
éléments  contenus  dans  Liste1  et  stocke  le  résultat  dans  une  liste  "plane"  (à  une  seule
dimension).*/

/* Ajout dans Abil
Si on a (I,and(C1,C2)), on ajoute (I,C1) et (I,C2) dans Abil,
sinon on ajoute (I,C) directement ??????????*/
/*ajout(Ctraite,Abi,Abil).*/
/* Transformation en forme normale negative */
nnf(not(and(C1,C2)),or(NC1,NC2)) :- nnf(not(C1),NC1), nnf(not(C2),NC2),!.
nnf(not(or(C1,C2)),and(NC1,NC2)) :- nnf(not(C1),NC1), nnf(not(C2),NC2),!.
nnf(not(all(R,C)),some(R,NC)) :- nnf(not(C),NC),!.
nnf(not(some(R,C)),all(R,NC)) :- nnf(not(C),NC),!.
nnf(not(not(X)),X) :- !.
nnf(not(X),not(X)) :- !.
nnf(and(C1,C2),and(NC1,NC2)) :- nnf(C1,NC1),nnf(C2,NC2),!.
nnf(or(C1,C2),or(NC1,NC2)) :- nnf(C1,NC1), nnf(C2,NC2),!.
nnf(some(R,C),some(R,NC)) :- nnf(C,NC),!.
nnf(all(R,C),all(R,NC)) :- nnf(C,NC),!.
nnf(X,X).

/* Verification syntaxique et semantique */
concept(C) :- cnamea(C),!.
concept(C) :- cnamena(C),!.
concept(not(C)) :- concept(C),!.
concept(and(C1,C2)) :- concept(C1),concept(C2),!.
concept(or(C1,C2)) :- concept(C1),concept(C2),!.
concept(some(R,C)) :- rname(R),concept(C),!.
concept(all(R,C)) :- rname(R),concept(C),!.

/* Remplacement des concepts complexes par leur definition equivalente en nnf */
remplace_concepts_complexes(C,C) :- cnamea(C), !.
remplace_concepts_complexes(C,Ctraite) :- cnamena(C),equiv(C,Ctraite), !.
remplace_concepts_complexes(not(C),not(Ctraite)) :- remplace_concepts_complexes(C,Ctraite).
remplace_concepts_complexes(and(C1,C2),and(C1traite,C2traite)) :- remplace_concepts_complexes(C1,C1traite),
                                                                  remplace_concepts_complexes(C2,C2traite), !.
remplace_concepts_complexes(or(C1,C2),or(C1traite,C2traite)) :- remplace_concepts_complexes(C1,C1traite),
                                                                remplace_concepts_complexes(C2,C2traite), !.
remplace_concepts_complexes(some(R,C),some(R,Ctraite)) :- remplace_concepts_complexes(C,Ctraite), !.
remplace_concepts_complexes(all(R,C),all(R,Ctraite)) :- remplace_concepts_complexes(C,Ctraite), !.

/* ajout3((I,C),Lie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1) : selon la nature de C,
ajoute (I,C) dans une des listes Lie,Lpt,Li,Lu,Ls et met le resultat dans Lie1,Lpt1,Li1,Lu1 ou Ls1. */
ajout3((I,C),Lie,Lpt,Li,Lu,Ls,Lie,Lpt,Li,Lu,Ls1) :- concatene([(I,C)],Ls,Ls1).
ajout3((I,and(C1,C2)),Lie,Lpt,Li,Lu,Ls,Lie,Lpt,Li1,Lu,Ls) :- concatene([(I,and(C1,C2))],Li,Li1).
/*etc*/
