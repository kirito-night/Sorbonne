/* PROJET LRC - M1 INFO */
/* ESTHER CHOI - GROUPE 1 */
/* AMINE KAHIL - GROUPE 1 */

/* FONCTIONS UTILES*/

/*member(X,L) : prédicat prédéfini, teste si l’élément X appartient à la liste L.*/

/*nonmember(X, L): predicat qui teste si l'élément X n'appartient pas à la liste L.*/
nonmember(Arg,[Arg|_]) :- !,fail.
nonmember(Arg,[_|Tail]) :- !, nonmember(Arg,Tail).
nonmember(_,[]).

/*concat/3 : concat les deux listes L1 et L2 dans L3.*/
concat([],L1,L1).
concat([X|Y],L1,[X|L2]) :- concat(Y,L1,L2).

/*enleve/3 : supprime  X  de  la  liste  L1  et  renvoie  la  liste  résultante  dans  L2.*/
enleve(X,[X|L],L) :-!.
enleve(X,[Y|L],[Y|L2]) :- enleve(X,L,L2).

generer(B):- random(10,100000,B).

/*genere/1 : génère un nouvel identificateur qui est fourni en sortie dans Nom.*/
genere(Nom) :- compteur(V),nombre(V,L1),
               concat([105,110,115,116],L1,L2),
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
                concat(L,[R2],L1).
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

/* --------------------------------------------------------------------------------------------------------------------------- */

/* PARTIE 1 : PRELIMINAIRES */

equiv(sculpteur,and(personne,some(aCree,sculpture))).
equiv(auteur,and(personne,some(aEcrit,livre))).
equiv(editeur,and(personne,and(not(some(aEcrit,livre)),some(aEdite,livre)))).
equiv(parent,and(personne,some(aEnfant,anything))).

cnamea(personne).
cnamea(livre).
cnamea(objet).
cnamea(sculpture).
cnamea(anything).
cnamea(nothing).

cnamena(auteur).
cnamena(editeur).
cnamena(sculpteur).
cnamena(parent).

iname(michelAnge).
iname(david).
iname(sonnets).
iname(vinci).
iname(joconde).

rname(aCree).
rname(aEcrit).
rname(aEdite).
rname(aEnfant).

inst(michelAnge,personne).
inst(david,sculpture).
inst(sonnets,livre).
inst(vinci,personne).
inst(joconde,objet).

instR(michelAnge,david,aCree).
instR(michelAnge,sonnets,aEcrit).
instR(vinci,joconde,aCree).

/*TBox :
[(sculpteur,and(personne,some(aCree,sculpture))), (auteur,and(personne,some(aEcrit,livre))), (editeur,and(personne,and(not(some(aEcrit,livre)),some(aEdite,livre)))), (parent,and(personne,some(aEnfant,anything)))]
*/

/*ABox :
  - assertions de concepts :
[(michelAnge,personne), (david,sculpture), (sonnets,livre), (vinci,personne), (joconde,objet)]
  - assertions de rôles :
  [(michelAnge, david, aCree), (michelAnge, sonnet, aEcrit),(vinci, joconde, aCree)]
*/


compteur(1).

programme :- premiere_etape(Tbox,Abi,Abr),
             deuxieme_etape(Abi,Abi1,Tbox),
             troisieme_etape(Abi1,Abr).

/*Paramètres
- premiere_etape :
  Tbox = liste représentant la TBox
  Abi = liste représentant les assertions de concepts de la ABox
  Abr = liste représentant les assertions de rôles de la ABox
- deuxieme_etape :
  Abi = liste des assertions de concepts initiales
  Abi1 = liste des assertions de concepts complétée après la soumission d'une proposition a demontrer
  Tbox = liste représentant la TBox
- troisieme_etape :
  Abi1 = liste des assertions de concepts complétée
  Abr =  liste des assertions de rôles qui peut également évoluer lors de la démonstration
*/

premiere_etape(
  [(sculpteur,and(personne,some(aCree,sculpture))), (auteur,and(personne,some(aEcrit,livre))), (editeur,and(personne,and(not(some(aEcrit,livre)),some(aEdite,livre)))), (parent,and(personne,some(aEnfant,anything)))],
  [(michelAnge,personne), (david,sculpture), (sonnets,livre), (vinci,personne), (joconde,objet)],
  [(michelAnge, david, aCree), (michelAnge, sonnets, aEcrit),(vinci, joconde, aCree)]
).


/* PARTIE 2 : SAISIE DE LA PROPOSITION A DEMONTRER */

deuxieme_etape(Abi,Abi1,Tbox) :-
  saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox).

saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox) :-
  nl, write('Entrez le numero du type de proposition que vous voulez demontrer :'), nl,
  write('1 = Une instance donnee appartient a un concept donne.'), nl,
  write('2 = Deux concepts n`ont pas d"elements en commun (ils ont une intersection vide).'), nl,
  read(R), suite(R,Abi,Abi1,Tbox).

suite(1,Abi,Abi1,Tbox) :- acquisition_prop_type1(Abi,Abi1,Tbox),!.
suite(2,Abi,Abi1,Tbox) :- acquisition_prop_type2(Abi,Abi1,Tbox),!.
suite(_,Abi,Abi1,Tbox) :- nl, write('Cette reponse est incorrecte'),nl,
  saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox).


acquisition_prop_type1(Abi,Abi1,Tbox) :-
  nl, write("Entrez la proposition a montrer (d'abord l'instance, puis le concept, puis tapez 'fin.') :"), nl,
  lecture(P), /*P = [I,C] où I est une instance I et C un concept existants*/
  correction(P),  /*verification syntaxique et semantique*/
  traitement(P,Ptraitennf), /*P=[I,C], Ctraitennf = nnf de not(C)*/
  ajout(Ptraitennf,Abi,Abi1). /*ajout dans Abi1*/

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
concept([not(C)|_]) :- concept([C]),!.
concept([and(C1,C2)|_]) :- concept([C1]),concept([C2]),!.
concept([or(C1,C2)|_]) :- concept([C1]),concept([C2]),!.
concept([some(R,C)|_]) :- rname(R),concept([C]),!.
concept([all(R,C)|_]) :- rname(R),concept([C]),!.
concept([C|_]) :- setof(X,cnamea(X),L),member(C,L),!.
concept([C|_]) :- setof(X,cnamena(X),L),member(C,L),!.

/* Remplacement des concepts complexes par leur definition equivalente en nnf */

remplace_concepts_complexes([not(C)|_],not(Ctraite)) :- remplace_concepts_complexes([C],Ctraite).
remplace_concepts_complexes([and(C1,C2)|_],and(C1traite,C2traite)) :- remplace_concepts_complexes([C1],C1traite),
                                                                  remplace_concepts_complexes([C2],C2traite), !.
remplace_concepts_complexes([or(C1,C2)|_],or(C1traite,C2traite)) :- remplace_concepts_complexes([C1],C1traite),
                                                                remplace_concepts_complexes([C2],C2traite), !.
remplace_concepts_complexes([some(R,C)|_],some(R,Ctraite)) :- remplace_concepts_complexes([C],Ctraite), !.
remplace_concepts_complexes([all(R,C)|_],all(R,Ctraite)) :- remplace_concepts_complexes([C],Ctraite), !.
remplace_concepts_complexes([C|_],C) :- cnamea(C), !.
remplace_concepts_complexes([C|_],Ctraite) :- cnamena(C),equiv(C,Ctraite), !.

correction([I|C]) :- iname(I),concept(C).

traitement([I|C],(I,Ctraitennf)) :- remplace_concepts_complexes(C,Ctraite),
                                    nnf(not(Ctraite),Ctraitennf).

ajout(Ptraitennf,Abi,Abi1) :- concat([Ptraitennf],Abi,Abi1).


acquisition_prop_type2(Abi,Abi1,Tbox) :-
  nl, write("Entrez la proposition a montrer (d'abord le premier concept, puis le second, puis tapez 'fin.') :"), nl,
  lecture(P), /*P=[C1,C2] où C1 et C2 sont des concepts. */
  correction2(P),  /*verification syntaxique et semantique*/
  traitement2(P,Ptraite), /*P=[C1,C2], Ptraite = P où on a remplacé les concepts complexes par leur définition*/
  ajout2(Ptraite,Abi,Abi1). /*ajout dans Abi1*/

correction2([]).
correction2([C1|C2]) :- concept([C1]),correction2(C2).

traitement2([],(_,_)).
traitement2([C2],(_,C2traite)) :- remplace_concepts_complexes([C2],C2traite), !.
traitement2([C1|C2],(C1traite,C2traite)) :- remplace_concepts_complexes([C1],C1traite),
                                            traitement2(C2,(C1traite,C2traite)).

ajout2((C1traite,C2traite),Abi,Abi1) :- genere(Nom),
                                        concat([(Nom,and(C1traite,C2traite))],Abi,Abi1).


/* PARTIE 3 : DEMONSTRATION DE LA PROPOSITION */

troisieme_etape(Abi,Abr) :- tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),
                            write("Début de la résolution\n"),
                            resolution(Lie,Lpt,Li,Lu,Ls,Abr),
                            nl, write('Youpiiiiii, on a demontre la proposition initiale !!!').

tri_Abox([],[],[],[],[],[]). /*cas d'arrêt*/
tri_Abox([(I,some(R,C))|T],LieNew,Lpt,Li,Lu,Ls) :- concat([(I,some(R,C))],Lie,LieNew), tri_Abox(T,Lie,Lpt,Li,Lu,Ls),!. /*some -> Lie*/
tri_Abox([(I,all(R,C))|T],Lie,LptNew,Li,Lu,Ls) :- concat([(I,all(R,C))],Lpt,LptNew), tri_Abox(T,Lie,Lpt,Li,Lu,Ls),!. /*all -> Lpt*/
tri_Abox([(I,and(C1,C2))|T],Lie,Lpt,LiNew,Lu,Ls) :- concat([(I,and(C1,C2))],Li,LiNew), tri_Abox(T,Lie,Lpt,Li,Lu,Ls),!. /*and -> Li*/
tri_Abox([(I,or(C1,C2))|T],Lie,Lpt,Li,LuNew,Ls) :- concat([(I,or(C1,C2))],Lu,LuNew), tri_Abox(T,Lie,Lpt,Li,Lu,Ls),!. /*or -> Lu*/
tri_Abox([(I,not(C))|T],Lie,Lpt,Li,Lu,LsNew) :- concat([(I,not(C))],Ls,LsNew), tri_Abox(T,Lie,Lpt,Li,Lu,Ls),!. /*not(concept) -> Ls*/
tri_Abox([(I,C)|T],Lie,Lpt,Li,Lu,LsNew) :- concat([(I,C)],Ls,LsNew), tri_Abox(T,Lie,Lpt,Li,Lu,Ls),!. /*concept -> Ls*/

/*affiche/1: predicat qui affiche une liste d'assertions*/
affiche([]).
affiche([A|L]):- affiche(A),affiche(L).

affiche((A,B,R)) :- nl,write("<"),write(A),write(","),write(B),write("> : "),write(R).


affiche((I,or(C1,C2))) :- nl,write(I),write(" : "), affiche(C1),write(" ⊔ "),affiche(C2).
affiche((I,and(C1,C2))) :- nl,write(I),write(" : "), affiche(C1),write(" ⊓ "),affiche(C2).
affiche((I,C)) :- nl,write(I), write(" : "), affiche(C).

affiche(or(C1,C2)) :- write("("),affiche(C1),write(" ⊔ "),affiche(C2),write(")").
affiche(and(C1,C2)) :- write("("),affiche(C1),write(" ⊓ "),affiche(C2),write(")").
affiche(all(R,C)) :- write("∀"),write(R),write("."),affiche(C).
affiche(some(R,C)) :- write("∃"), write(R), write("."), affiche(C).
affiche(not(C)) :- write("¬"),affiche(C).
affiche(C) :- write(C).
/*affiche_evolution_Abox/12 : Affiche l'évolution de la Abox étendue*/
affiche_evolution_Abox(Ls, Lie, Lpt, Li, Lu, Abr, Ls1, Lie1, Lpt1, Li1, Lu1, Abr1) :- write("---État de départ de la Abox---"),
                                                                                      affiche(Ls),
                                                                                      affiche(Lie),
                                                                                      affiche(Lpt),
                                                                                      affiche(Li),
                                                                                      affiche(Lu),
                                                                                      affiche(Abr),
                                                                                      nl,nl,write("---Etat d'arrivée---"),
                                                                                      affiche(Ls1),
                                                                                      affiche(Lie1),
                                                                                      affiche(Lpt1),
                                                                                      affiche(Li1),
                                                                                      affiche(Lu1),
                                                                                      affiche(Abr1),
                                                                                      nl,write("=======FIN========"),nl,!.

/* test_clash/1 : predicat qui vaut vrai s'il n'y a pas de clash,
et faux s'il y en a un dans la liste passée en argument */
test_clash([]).
test_clash([(I,C)|T]) :- nnf(not(C),Cnnf), not(member((I,Cnnf),T)), test_clash(T).

resolution(Lie,Lpt,Li,Lu,Ls,Abr) :- not(length(Lie,0)), test_clash(Ls), write("\nAppel de complete_some\n"),  complete_some(Lie,Lpt,Li,Lu,Ls,Abr). /*règle il existe*/
resolution(Lie,Lpt,Li,Lu,Ls,Abr) :- not(length(Li,0)), test_clash(Ls), write("\nAppel de transformation_and\n"), transformation_and(Lie,Lpt,Li,Lu,Ls,Abr). /*règle et*/
resolution(Lie,Lpt,Li,Lu,Ls,Abr) :- not(length(Lpt,0)), test_clash(Ls), write("\nAppel de deduction_all\n"), deduction_all(Lie,Lpt,Li,Lu,Ls,Abr). /*règle pour tout*/
resolution(Lie,Lpt,Li,Lu,Ls,Abr) :- not(length(Lu,0)), test_clash(Ls), write("\nAppel de transformation_or \n"), transformation_or(Lie,Lpt,Li,Lu,Ls,Abr). /*règle ou*/
resolution([],[],[],[],Ls,Abr) :- not(test_clash(Ls)), write("\nBranche fermée !!\n").

/*evolue/11 : màj des listes de Abe*/
evolue((I,some(R,C)),Lie,Lpt,Li,Lu,Ls,Lie1,Lpt,Li,Lu,Ls) :- concat([(I,some(R,C))],Lie,Lie1),!.
evolue((I,and(C1,C2)),Lie,Lpt,Li,Lu,Ls,Lie,Lpt,Li1,Lu,Ls) :- concat([(I,and(C1,C2))],Li,Li1),!.
evolue((I,or(C1,C2)),Lie,Lpt,Li,Lu,Ls,Lie,Lpt,Li,Lu1,Ls) :- concat([(I,or(C1,C2))],Lu,Lu1),!.
evolue((I,all(R,C)),Lie,Lpt,Li,Lu,Ls,Lie,Lpt1,Li,Lu,Ls) :- concat([(I,all(R,C))],Lpt,Lpt1),!.
evolue((I,not(C)),Lie,Lpt,Li,Lu,Ls,Lie,Lpt,Li,Lu,Ls1) :- cnamea(C), concat([(I,not(C))],Ls,Ls1),!. /*si C est un concept atomique, on ajoute dans Ls*/
evolue((I,not(C)),Lie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1) :- not(cnamea(C)), nnf(not(C),NotCnnf), evolue((I,NotCnnf),Lie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1),!. /*sinon, on prend le nnf pour le mettre dans la bonne liste*/
evolue((I,C),Lie,Lpt,Li,Lu,Ls,Lie,Lpt,Li,Lu,Ls1):- concat([(I,C)],Ls,Ls1),!.



complete_some([(I,some(R,C))|Tie],Lpt,Li,Lu,Ls,Abr) :- generer(B), /*on cree un nouvel objet B*/
                                                       concat([(I,B,R)],Abr,AbrNew), /*on ajoute (I,B,R) dans Abr*/
                                                       evolue((B,C),Tie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1), /*l'ajout de (B,C) depend de la nature de C*/
                                                       affiche_evolution_Abox(Ls,[(I,some(R,C))|Tie], Lpt, Li, Lu ,Abr, Ls1, Lie1, Lpt1, Li1, Lu1, AbrNew),
                                                       resolution(Lie1,Lpt1,Li1,Lu1,Ls1,AbrNew). /*on boucle*/


transformation_and(Lie,Lpt,[(I,and(C1,C2))|Ti],Lu,Ls,Abr) :- evolue((I,C1),Lie,Lpt,Ti,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1), /*ajout de (I,C1)*/
                                                             evolue((I,C2),Lie1,Lpt1,Li1,Lu1,Ls1,Lie2,Lpt2,Li2,Lu2,Ls2), /*ajout de (I,C2)*/
                                                             affiche_evolution_Abox(Ls,Lie, Lpt, [(I,and(C1,C2))|Ti], Lu ,Abr, Ls2, Lie2, Lpt2, Li2, Lu2, Abr),
                                                             resolution(Lie2,Lpt2,Li2,Lu2,Ls2,Abr).


presence_all((I,B,R),Abr):- member((I,B,R), Abr),!.


deduction_all(Lie,[(I,all(R,C))|Lpt],Li,Lu,Ls,Abr) :- member((I,B,R),Abr), /*on a du I : all(R,C) donc pour utiliser la règle ∀, on cherche une relation <I,B> : R*/
                                                      evolue((B,C),Lie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1), /* ajout de (B,C)*/
                                                      affiche_evolution_Abox(Ls,Lie, [(I,all(R,C))|Lpt], Li, Lu , Abr, Ls1, Lie1, Lpt1, Li1, Lu1, Abr),
                                                      resolution(Lie1,Lpt1,Li1,Lu1,Ls1,Abr).


transformation_or(Lie,Lpt,Li,[(I,or(C1,C2))|Tu],Ls,Abr) :- evolue((I,C1),Lie,Lpt,Li,Tu,Ls,Lie1g,Lpt1g,Li1g,Lu1g,Ls1g), /*ajout fils gauche*/
                                                           affiche_evolution_Abox(Ls,Lie,Lpt, Li, [(I,or(C1,C2))|Tu] , Abr, Ls1g, Lie1g,Lpt1g,Li1g,Lu1g, Abr),
                                                           evolue((I,C2),Lie,Lpt,Li,Tu,Ls,Lie1d,Lpt1d,Li1d,Lu1d,Ls1d), /*ajout fils droit*/
                                                           affiche_evolution_Abox(Ls,Lie,Lpt, Li, [(I,or(C1,C2))|Tu] , Abr, Ls1d, Lie1d,Lpt1d,Li1d,Lu1d, Abr),
                                                           resolution(Lie1g,Lpt1g,Li1g,Lu1g,Ls1g,Abr), /*fils gauche*/
                                                           resolution(Lie1d,Lpt1d,Li1d,Lu1d,Ls1d,Abr). /*fils droit*/
