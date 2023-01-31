/*
Projet LRC de conception d'un démonstrateur de proposition basé sur la méthode des tableaux de la logique ALC.
Réalisé par Jérémy DUFOURMANTELLE et Antoine TOULALLAN
2021/2022
*/

/*
Tous les prédicats ont été expliqué en détails dans le rapport joint à ce fichier
*/


/*  PRELIMINAIRES */

/*-----------------------------------------------------------------*/
/*                    Partie  1 : Preliminaires                    */
/*-----------------------------------------------------------------*/

/*T-Box*/

/* definitions des concepts complexes */

equiv(sculpteur,and(personne,some(aCree,sculpture))).
equiv(auteur,and(personne,some(aEcrit,livre))).
equiv(editeur,and(personne,and(not(some(aEcrit,livre)),some(aEdite,livre)))).
equiv(parent,and(personne,some(aEnfant,anything))).

/* declaration des concepts atomiques */
cnamea(personne).
cnamea(sculpture).
cnamea(livre).
cnamea(objet).
cnamea(anything).
cnamea(nothing).

/* déclararion des concepts complexes */

cnamena(auteur).
cnamena(editeur).
cnamena(sculpteur).
cnamena(parent).

/* déclaration des instances */

iname(michelAnge).
iname(david).
iname(sonnets).
iname(vinci).
iname(joconde).

/* déclarations des roles */

rname(aCree).
rname(aEcrit).
rname(aEdite).
rname(aEnfant).

/*A-BOX*/

/* instanciation de concept*/

inst(michelAnge,personne).
inst(david,sculpture).
inst(sonnets,livre).
inst(vinci,personne).
inst(joconde,objet).

/* instanciations de roles*/

instR(michelAnge, david, aCree).
instR(michelAnge, sonnets, aEcrit).
instR(vinci, joconde, aCree).

/* Predicat optionnel qui detecte les cycles récursif au sein des concepts complexes */
autoref(C,C).
autoref(C1,equiv(_,X)) :- autoref(C1,X),!.
autoref(C,and(C1,_)) :- autoref(C,C1),!.
autoref(C,and(_,C2)) :- autoref(C,C2),!.
autoref(C,or(C1,_)) :- autoref(C,C1),!.
autoref(C,or(_,C2)) :- autoref(C,C2),!.
autoref(C,some(_,C1)) :- autoref(C,C1),!.
autoref(C,all(_,C1)) :- autoref(C,C1),!.
autoref(C,not(C1)) :- autoref(C,C1),!.

/* 
    point d'entrée de la premiere étape :
    setof va récuperer la liste des equiv , inst , instR
    permet de remplir la ABox : liste des assertion de concept, (doublet)
                      la TBox : liste des définitions de concept,
                      et la ABr : liste des assertions de role (triplet)
*/
premiere_etape(Tbox,Abi,Abr) :-
    setof((X,Y),equiv(X,Y),Tbox),
    setof((X,Y),inst(X,Y),Abi),
    setof((X,Y,Z),instR(X,Y,Z),Abr).


/*-----------------------------------------------------------------*/
/*              Partie  2 : Saisie de la proposition               */
/*-----------------------------------------------------------------*/

deuxieme_etape(Abi,Abi1,Tbox) :-
 saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox).

/*
    On a rajouté dans cette partie une troisieme réponse pour sortir du programme.
*/

saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox) :-
    nl,write("Entrez le numero du type de proposition que vous voulez demontrer :"),
    nl,write("1 : Une instance donnee appartient a un concept donne. (I : C)"),
    nl,write("2 : Deux concepts ne possede aucun elements en commun(ils ont une intersection vide => C1 ⊓ C2 ⊑ ⊥ ) : "),
    nl,write("3 : Pour sortir du programme"),
    nl,read(R),
    suite(R,Abi,Abi1,Tbox).

/*
    Traitement de la réponse de l'utilisateur et redirection 
    vers le prédicat de saisie de proposition
*/

suite(1,Abi,Abi1,Tbox) :- acquisition_prop_type1(Abi,Abi1,Tbox),!.
suite(2,Abi,Abi1,Tbox) :- acquisition_prop_type2(Abi,Abi1,Tbox),!.
suite(3,_,_,_) :- nl,write("Vous êtes sorti de l'invité de commande"),!.
suite(_,Abi,Abi1,Tbox) :- 
    nl, write("Cette reponse est incorrecte."),
    nl, saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox).


/*
    1 : entrée I : C
    2 : verification semantique sur les données pour vérifier quelle existent bien
    avec verifSemantiqueConcept
    3 : verification syntaxique avec concept pour vérifier que C respecte bien la grammaire des concepts.
    4 : on remplace C par une définition avec des concepts atomiques
    5 : on applique la négation et on simplifie la formule avec nnf
    6 : on concatene le resultat à la Abox qui est maintenant une Abox étendu. 
*/
acquisition_prop_type1(Abi , Abi1 ,_) :- 
    nl,write("Entrer linstance I :"),
    nl,read(I),
    nl,write("Entrer le concept C :"),
    nl,read(C),
    verifSemantiqueConcept(C),
    verifSemantiqueConcept(I),
    concept(C),
    replace(C,RC),
    nnf(not(RC),NRC),
    concatList(Abi,[(I,NRC)],Abi1).

/*
    1 : entrée C1 et C2
    2 : verification semantique sur les données pour vérifier quelle existent bien
    avec verifSemantiqueConcept
    3 : verification syntaxique avec concept pour vérifier que C1 et C2 respectent bien la grammaire des concepts.
    4 : on remplace C1 et C2 par une définition avec des concepts atomiques
    5 : on applique la négation et on simplifie la formule avec nnf
    6 : on genere une instance car la négation d'une intersection vide est : il existe au moins un élémént dans l'intersection.
    7 : on concatene le resultat à la Abox qui est maintenant une Abox étendu. 
*/
acquisition_prop_type2(Abi,Abi1,_) :- 
    nl,write("Entrer le premier concept de lintersection : "),
    nl,read(C1),
    verifSemantiqueConcept(C1),
    concept(C1),
    nl,write("Entrer le deuxieme concept de lintersection : "),
    nl,read(C2),
    verifSemantiqueConcept(C2),
    concept(C2),
    replace(and(C1,C2),RC),
    nnf(not(RC),NRC),
    genere(Nom),
    concatList(Abi,[(Nom,NRC)],Abi1).

/* Remplacement de concepts non atomique */

replace(and(C1,C2),and(RC1,RC2)) :- replace(C1,RC1), replace(C2,RC2), !.
replace(or(C1,C2),or(RC1,RC2)) :- replace(C1,RC1), replace(C2,RC2), !.
replace(all(R,C),all(R,RC)) :- replace(C,RC) ,!.
replace(some(R,C),some(R,RC)) :- replace(C,RC) ,!.
replace(not(C),not(RC)) :- replace(C,RC) ,!.
replace(C,RC) :- setof(X,cnamena(X),L) , member(C,L) , equiv(C,RC),!.
replace(C,C) :- setof(X,cnamea(X),L) , member(C,L),!.

/*negation des propostions*/

nnf(not(and(C1,C2)),or(NC1,NC2)):- nnf(not(C1),NC1),
nnf(not(C2),NC2),!.
nnf(not(or(C1,C2)),and(NC1,NC2)):- nnf(not(C1),NC1), 
nnf(not(C2),NC2),!.
nnf(not(all(R,C)),some(R,NC)) :- nnf(not(C),NC),!.
nnf(not(some(R,C)),all(R,NC)):- nnf(not(C),NC),!.
nnf(not(not(X)),X):-!.
nnf(not(X),not(X)):-!.
nnf(and(C1,C2),and(NC1,NC2)):- nnf(C1,NC1),nnf(C2,NC2),!.
nnf(or(C1,C2),or(NC1,NC2)):- nnf(C1,NC1), nnf(C2,NC2),!.
nnf(some(R,C),some(R,NC)):- nnf(C,NC),!.
nnf(all(R,C),all(R,NC)) :- nnf(C,NC),!.
nnf(X,X).

/* verification syntaxique de C */

/*
    Verifie si le concept donné en parametre est correctement defini en fonction des 
    regles de grammaire fourni dans l'énoncé du sujet.
*/

concept(nothing).
concept(anything).
concept(not(C)) :- concept(C),!.
concept(and(C1,C2)):- concept(C1), concept(C2),!.
concept(or(C1,C2)) :- concept(C1), concept(C2),!.
/*
    on verifie separement que R soit bien un role et C un concept.
*/
concept(some(R,C)) :- role(R), concept(C),!.
concept(all(R,C)) :- role(R), concept(C),!.

/*
    Verification semantique du concept/role/instance.
*/
concept(C1) :- setof(X,iname(X),L), member(C1,L),!.
concept(C2) :- setof(X,cnamea(X),L), member(C2,L),!.
concept(C3) :- setof(X,cnamena(X),L), member(C3,L),!.
instance(I) :- setof(I, iname(I),L), member(I,L) ,!.
role(R) :- setof(X,rname(X),L), member(R,L),!.

/* verification sémantique de C*/

/*
  permet de verifier que les Concept,instances fassent bien parti de la liste des concepts fourni.
*/

verifSemantiqueConcept(C) :- setof(C, iname(C),L), member(C,L) ,!.
verifSemantiqueConcept(C) :- setof(C, rname(C),L), member(C,L) ,!.
verifSemantiqueConcept(C) :- setof(C, cnamena(C),L), member(C,L) ,!.
verifSemantiqueConcept(C) :- setof(C, cnamea(C),L), member(C,L) ,!.

/*
    Recursion  sur les formules afin d'obtenir des formules sous la forme atomique
*/

verifSemantiqueConcept(and(C1,C2)) :- verifSemantiqueConcept(C1),verifSemantiqueConcept(C2),!.
verifSemantiqueConcept(or(C1,C2)) :- verifSemantiqueConcept(C1),verifSemantiqueConcept(C2),!.
verifSemantiqueConcept(all(R,C)) :- verifSemantiqueConcept(R),verifSemantiqueConcept(C),!.
verifSemantiqueConcept(some(R,C)) :- verifSemantiqueConcept(R),verifSemantiqueConcept(C),!.
verifSemantiqueConcept(not(C)) :- verifSemantiqueConcept(C),!.

/*-----------------------------------------------------------------*/
/*                  Partie  3 : Demonstrateur                      */
/*-----------------------------------------------------------------*/

/* Point d'entrée du programme donné dans l'énoncé */

programme :- 
    premiere_etape(Tbox,Abi,Abr), 
    deuxieme_etape(Abi,Abi1,Tbox),
    troisieme_etape(Abi1,Abr).

troisieme_etape(Abi,Abr) :-
    tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),
    not(resolution(Lie,Lpt,Li,Lu,Ls,Abr)),
    nl,write('Youpiiiiii, on a demontre la proposition initiale !!!').

/* Tri de la Abox */

tri_Abox([],[],[],[],[],[]).
tri_Abox([(I,C)|Abi],Lie,Lpt,Li,Lu,[(I,C)|Ls]) :- 
    setof(X,cnamea(X),L), member(C,L) , 
    tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,not(C))|Abi],Lie,Lpt,Li,Lu,[(I,not(C))|Ls]) :- 
    setof(X,cnamea(X),L), 
    member(C,L) , 
    tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,some(R,C))|Abi],[(I,some(R,C))|Lie],Lpt,Li,Lu,Ls)  :- 
    tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,all(R,C))|Abi],Lie,[(I,all(R,C))|Lpt],Li,Lu,Ls) :- 
    tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,and(C1,C2))|Abi],Lie,Lpt,[(I,and(C1,C2))|Li],Lu,Ls)  :- 
    tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,or(C1,C2))|Abi],Lie,Lpt,Li,[(I,or(C1,C2))|Lu],Ls) :- 
    tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),!.

/* Résolution */

resolution(Lie,Lpt,Li,Lu,Ls,Abr) :-
    verif_contradiction(Ls),
    complete_some(Lie,Lpt,Li,Lu,Ls,Abr),
    transformation_and(Lie,Lpt,Li,Lu,Ls,Abr),
    deduction_all(Lie,Lpt,Li,Lu,Ls,Abr),
    transformation_or(Lie,Lpt,Li,Lu,Ls,Abr).

/* Complete_some */
/* Cas ou la liste est vide */

complete_some([],_,_,_,_,_).
complete_some([(A,some(R,C))|Lie],Lpt,Li,Lu,Ls,Abr):-
    genere(B),
    evolue((B,C), Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1),
    affiche_evolution_Abox(Ls, Lie, Lpt, Li, Lu, Abr, Ls1, Lie1, Lpt1, Li1, Lu1, Abr),
    concatList([(A,B,R)],Abr,Abr1),
    verif_contradiction(Ls1),
    resolution(Lie1, Lpt1,Li1,Lu1,Ls1,Abr1).



/* transformation_and */
/* Cas ou la liste est vide */
transformation_and(_,_,[],_,_,_).
transformation_and(Lie, Lpt,[(I,(and(C1,C2)))|Li],Lu, Ls,Abr) :-
    evolue((I,C1), Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1),
    affiche_evolution_Abox(Ls, Lie, Lpt, Li, Lu, Abr, Ls1, Lie1, Lpt1, Li1, Lu1, Abr),
    verif_contradiction(Ls1),
    evolue((I,C2), Lie1, Lpt1, Li1, Lu1, Ls1, Lie2, Lpt2, Li2, Lu2, Ls2),
    affiche_evolution_Abox(Ls1, Lie1, Lpt1, Li1, Lu1, Abr, Ls2, Lie2, Lpt2, Li2, Lu2, Abr),
    verif_contradiction(Ls2),
    resolution(Lie2, Lpt2, Li2, Lu2, Ls2,Abr).

/* deduction_all */
/* Cas ou la liste est vide */
deduction_all(_,[],_,_,_,_).
deduction_all(Lie,[(A,all(R,C))|Lpt],Li,Lu,Ls, Abr) :-
    member((A,B,R),Abr),
    evolue((B,C), Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1),
    affiche_evolution_Abox(Ls, Lie, Lpt, Li, Lu, Abr, Ls1, Lie1, Lpt1, Li1, Lu1, Abr),
    verif_contradiction(Ls1),
    resolution(Lie1, Lpt1, Li1, Lu1, Ls1,Abr).

/* 
    Dans le cas ou la pré-condition qui est que <a,b> : R n'est pas dans Abr
    alors on ne prends pas en considération cette regle.
*/
deduction_all(Lie,[(A,all(R,_))|Lpt],Li,Lu,Ls, Abr) :-
    not(member((A,_,R),Abr)),
    affiche_evolution_Abox(Ls, Lie, Lpt, Li, Lu, Abr, Ls, Lie, Lpt, Li, Lu, Abr).

/* transformation_or */

/*
    pour une assertion de la forme a : C ou D on ajoute les noeuds a : C , a : D.
    on commence par a : C, si ce noeud échoue, on va voir le noeud a : D
*/

/* Cas ou la liste est vide */
transformation_or(_,_,_,[],_,_).
/* Premier noeud du OU */
transformation_or(Lie,Lpt,Li,[(I,or(C1,_))|Lu],Ls,Abr) :-
    evolue((I,C1), Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1),
    affiche_evolution_Abox(Ls, Lie, Lpt, Li, Lu, Abr, Ls1, Lie1, Lpt1, Li1, Lu1, _),
    verif_contradiction(Ls1),
    resolution(Lie1, Lpt1, Li1, Lu1, Ls1,Abr).
/* Si le premier noeud du OU echoue on essai de statisfaire cette regle */
transformation_or(Lie,Lpt,Li,[(I,or(_,C2))|Lu],Ls,Abr) :-
    evolue((I,C2), Lie, Lpt, Li, Lu, Ls, Lie2, Lpt2, Li2, Lu2, Ls2),
    affiche_evolution_Abox(Ls, Lie, Lpt, Li, Lu, Abr, Ls2, Lie2, Lpt2, Li2, Lu2, _),
    verif_contradiction(Ls2),
    resolution(Lie2, Lpt2, Li2, Lu2, Ls2,Abr).    


/* Evolue */

/* les 4 premieres regles de evolue permettent de detecter le cas ou C est juste un concept (atomique ou complexe)
    et de prendre en charge aussi la forme not(...)
*/

evolue((B,C), Lie, Lpt, Li, Lu, Ls, Lie, Lpt, Li, Lu,[(B,C)| Ls]) :-   
    setof(C, cnamea(C),L), member(C,L),!.
evolue((B,C), Lie, Lpt, Li, Lu, Ls, Lie, Lpt, Li, Lu,[(B,C)| Ls]) :-   
    setof(C, cnamena(C),L), member(C,L) ,!.
evolue((B,not(C)), Lie, Lpt, Li, Lu, Ls, Lie, Lpt, Li, Lu,[(B,not(C))| Ls]) :-  
    setof(C, cnamena(C),L), member(C,L),!.
evolue((B,not(C)), Lie, Lpt, Li, Lu, Ls, Lie, Lpt, Li, Lu,[(B,not(C))| Ls]) :-  
    setof(C, cnamea(C),L), member(C,L) ,!. 
evolue((I,and(C1,C2)), Lie, Lpt,Li, Lu, Ls, Lie, Lpt,  [(I,and(C1,C2))|Li], Lu, Ls):-!.
evolue((I,or(C1,C2)), Lie, Lpt, Li,Lu, Ls, Lie, Lpt, Li, [(I , or(C1,C2))| Lu], Ls):-!.
evolue((I,some(R,C)), Lie, Lpt, Li, Lu, Ls, [(I,some(R,C))|Lie], Lpt, Li, Lu, Ls):-!.
evolue((I,all(R,C)), Lie, Lpt, Li, Lu, Ls, Lie, [(I,all(R,C))|Lpt], Li, Lu, Ls):-!.

/* Detection de contradiction dans la liste L composé de doublet (I,C)*/

/*
    - si on a dans une liste de doublet une contradiction de la forme [(david,personne),(david,not(personne))]
        retourne False
    - si aucune contradiction
        retourne True
    - prend en charge aussi les instances générer dynamiquement grâce a genere() tel que 
    verif_contradiction( [(david,personne),(inst1,not(personne))]) retournera False.
*/

/* permet de faire la recursion sur tous les elements de la liste */
verif_contradiction([]).
verif_contradiction([(I,E)|L]) :- verif_contradiction_elem(L,(I,E)), verif_contradiction(L).

/* liste vide aucune contradiction */
verif_contradiction_elem([],(_,_)).
/* si I et U sont deux instances appartenant a la liste des instances de base on verifie si elle sont differents car il n'y aura
pas de contradiction*/
verif_contradiction_elem([(U,_)|L],(I,E)) :-
    setof(X,iname(X),L3) , member(I,L3), member(U,L3),
    (U \== I ), verif_contradiction_elem(L,(I,E)),!.

/*

si U et I sont deux instances appartenant a la liste des instances de base et sont egales alors on vérifie quelles ne soient 
pas complementaires l'une de lautre
*/
verif_contradiction_elem([(U,A)|L],(I,E)) :-
    setof(X,iname(X),L3) , member(I,L3), member(U,L3),
    U == I,
    A \== not(E), 
    E \== not(A),
    setof(X,iname(X),L3) , member(I,L3),
    verif_contradiction_elem(L,(I,E)),!.

/*
    Cas générique pour les instances generer dynamiquement avec genere() on verifie alors quelle ne soient pas complementaires
    l'une de l'autre;
*/
verif_contradiction_elem([(_,A)|L],(I,E)) :- 
    setof(X,iname(X),L3) , 
    not(member(I,L3)), 
    A \== not(E), 
    E \== not(A), 
    verif_contradiction_elem(L,(I,E)),!.


/* Affichage de l'évolution des noeuds de l'arbre*/

/*
    Affichage recursif sur les listes d'instanciations de concepts sur un avant et un apres.
    Permet de comprendre et debugger un arbre de regle sur une proposition.
    principe : recursion sur les elements dune liste puis sur les composants d'une formule
*/

affiche_evolution_Abox(Ls1, Lie1, Lpt1, Li1, Lu1, Abr1, Ls2, Lie2, Lpt2, Li2, Lu2, Abr2) :-
    write("----------------------"),nl,
    write("Avant modification : "),nl,
    write("----------------------"),nl,
     write("Assertions de roles (<a,b> : R) : "),nl,
    affiche_listeAbr(Abr1),nl,
    write("Assertions de concept (I : C) : "),nl,
    affiche_liste(Ls1),
    affiche_liste(Lie1),
    affiche_liste(Lpt1),
    affiche_liste(Li1),
    affiche_liste(Lu1),
    write("----------------------"),nl,
    write("Apres modification : "),nl,
    write("----------------------"),nl,
    write("Assertions de roles (<a,b> : R) : "),nl,
    affiche_listeAbr(Abr2),nl,
    write("Assertions de concept (I : C) : "),nl,
    affiche_liste(Ls2),
    affiche_liste(Lie2),
    affiche_liste(Lpt2),
    affiche_liste(Li2),
    affiche_liste(Lu2),!.

affiche_liste([]).
affiche_liste([(I , F)| L2]) :- 
    write(I),write(" : "),affiche_formule(F),nl,
    affiche_liste(L2),!.

affiche_listeAbr([]).
affiche_listeAbr([(A , B , R)| L2]) :- 
    write("<"),write(A),write(","),write(B),write("> : "),write(R),nl,
    affiche_listeAbr(L2),!.

affiche_formule(F) :-setof(C, cnamena(C),L), member(F,L),write(F).
affiche_formule(F) :-setof(C, cnamea(C),L), member(F,L),write(F).
affiche_formule(or(F1,F2)) :- write("(") , affiche_formule(F1) , write(") ⊔ (") , affiche_formule(F2), write(")").
affiche_formule(and(F1,F2)) :- write("(") , affiche_formule(F1) , write(") ⊓ (") , affiche_formule(F2), write(")").
affiche_formule(all(R,F)) :- write("∀."),write(R) , write("(") , affiche_formule(F),write(")").
affiche_formule(some(R,F)) :- write("∃."),write(R) , write("(") , affiche_formule(F),write(")").
affiche_formule(not(F)) :- write(" ¬"),affiche_formule(F).


/*-----------------------------------------------------------------*/
/*                        PREDICATS UTILES                         */
/*-----------------------------------------------------------------*/

genere(Nom) :- 
    compteur(V),
    nombre(V,L1), 
    concatList([105,110,115,116],L1,L2), 
    V1 is V+1, 
    dynamic(compteur/1), 
    retract(compteur(V)), 
    dynamic(compteur/1),
    assert(compteur(V1)),nl,nl,nl,
    name(Nom,L2).

nombre(0,[]).
nombre(X,L1) :- 
    R is (X mod 10), 
    Q is ((X-R)//10),
    chiffre_car(R,R1),
    char_code(R1,R2),
    nombre(Q,L),
    concatList(L,[R2],L1).

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


lecture([X|L]):-
read(X), 
X \= fin, !, 
lecture(L).
lecture([]).

compteur(1).

concatList([],L1,L1).
concatList([X|Y],L1,[X|L2]) :- concatList(Y,L1,L2).

enleve(X,[X|L],L) :-!.
enleve(X,[Y|L],[Y|L2]) :- enleve(X,L,L2).