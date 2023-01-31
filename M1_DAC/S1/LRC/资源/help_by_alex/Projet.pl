nnf(not(and(C1,C2)),or(NC1,NC2)):- nnf(not(C1),NC1),nnf(not(C2),NC2),!.
nnf(not(or(C1,C2)),and(NC1,NC2)):- nnf(not(C1),NC1),nnf(not(C2),NC2),!.
nnf(not(all(R,C)),some(R,NC)):- nnf(not(C),NC),!.
nnf(not(some(R,C)),all(R,NC)):- nnf(not(C),NC),!.
nnf(not(not(X)),X):-!.
nnf(not(X),not(X)):-!.
nnf(and(C1,C2),and(NC1,NC2)):- nnf(C1,NC1),nnf(C2,NC2),!.
nnf(or(C1,C2),or(NC1,NC2)):- nnf(C1,NC1), nnf(C2,NC2),!.
nnf(some(R,C),some(R,NC)):- nnf(C,NC),!.
nnf(all(R,C),all(R,NC)) :- nnf(C,NC),!.
nnf(X,X).

equiv(sculpteur,and(personne,some(aCree,sculpture))).
equiv(auteur,and(personne,some(aEcrit,livre))).
equiv(editeur,and(personne,and(not(some(aEcrit,livre)),some(aEdite,livre)))).
equiv(parent,and(personne,some(aEnfant,anything))).
/*equiv(parent,not(and(auteur,some(aEnfant,anything)))).*/
/*equiv(sculpture,sculpteur).*/


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
iname(steph).

rname(aCree).
rname(aEcrit).
rname(aEdite).
rname(aEnfant).

inst(michelAnge,personne).
inst(david,sculpture).
inst(sonnets,livre).
inst(vinci,personne).
inst(joconde,objet).
inst(steph,parent).

instR(michelAnge, david, aCree).
instR(michelAnge, sonnets, aEcrit).
instR(vinci, joconde, aCree).



/********************************** PARTIE 1 **************************************/

/* predicat role */

role(R) :- setof(X,rname(X),L),member(R,L).

/* predicat concept */

concept(A) :- setof(X,cnamea(X),L),member(A,L),!.
concept(anything).
concept(nothing).
concept(not(A)) :- concept(A),!.
concept(and(A,B)) :- concept(A),concept(B),!.
concept(or(A,B)) :- concept(A),concept(B),!.
concept(some(R,A)) :- concept(A),role(R),!.
concept(all(R,A)) :- concept(A),role(R),!.
concept(A) :- setof(X,cnamena(X),L),member(A,L),equiv(A,G),concept(G).
concept(A) :- instance(A).
concept(A,B) :- setof(X,inst(X,_),L),member(A,L),setof(Y,inst(_,Y),L2),member(B,L2).
concept(A,B,C) :- instR(A,B,C).

/* predicat instance */

instance(I) :- setof(X,iname(X),L),member(I,L).

/* predicat  pas_autoref (a retravailler a la fin s'il y a le temps) */
/*
pas_autoref(C) :- setof(X,cnamea(X),L),member(C,L),!.
pas_autoref(C) :- equiv(C,G),pas_autoref(C,G),!.
pas_autoref(_,G) :- setof(X,cnamea(X),L),member(G,L),!.
pas_autoref(C,G) :- setof(X,cnamena(X),L),member(G,L),C\==G,!.
pas_autoref(C,not(G)) :- pas_autoref(C,G),!.
pas_autoref(C,and(G1,G2)) :- pas_autoref(C,G1),pas_autoref(C,G2),!.
pas_autoref(C,or(G1,G2)) :- pas_autoref(C,G1),pas_autoref(C,G2),!.
pas_autoref(C,some(_,G)) :- pas_autoref(C,G),!.
pas_autoref(C,all(_,G)) :- pas_autoref(C,G),!.
*/

/* predicat autoref */

autoref(X) :- equiv(X,Y),autoref(X,Y).
autoref(X,Y) :- autoref(X,Y,[]).
/* Il faut s'occuper du cas ou il y a une autoref interne, on va donc passer par une table qui garde en "memoire" les concepts par lesquels on passe */
autoref(X,Y,L) :- X==Y; member(Y,L);equiv(Y,Z),autoref(X,Z,[Y|L]). /* X==Y ou Y est dans L => erreur directement */
autoref(X,not(Y),L) :- autoref(X,Y,L),!.
autoref(X,and(Y,Z),L) :- autoref(X,Y,L);autoref(X,Z,L),!.
autoref(X,or(Y,Z),L) :- autoref(X,Y,L);autoref(X,Z,L),!.
autoref(X,some(_,Z),L) :- autoref(X,Z,L).
autoref(X,all(_,Z),L) :- autoref(X,Z,L).


/* predicat  traitement_TBox */

/* on doit d'abord modifier le concept complexe pour qu'il ne contienne que des concepts atomiques dans sa definition */
/* on peut ensuite le mettre sous forme nnf */

remplace_complexe(Complex,R) :- equiv(Complex,C),remplace_complexe(C,R),!.
/* Sur le modele de NNF, on completera */
remplace_complexe(not(C),not(R)) :- remplace_complexe(C,R),!.
remplace_complexe(and(C1,C2),and(R1,R2)) :- remplace_complexe(C1,R1),remplace_complexe(C2,R2),!.
remplace_complexe(or(C1,C2),or(R1,R2)) :- remplace_complexe(C1,R1),remplace_complexe(C2,R2),!.
remplace_complexe(some(R,C),some(R,Res)) :- remplace_complexe(C,Res),!.
remplace_complexe(all(R,C),all(R,Res)) :- remplace_complexe(C,Res),!.
/* On a trouve l'expression atomique !!!! test jouet (il faut commenter/decommenter dans les equiv) : traitement_TBox(parent,X). */
remplace_complexe(C,C) :- cnamea(C).

traitement_TBox(Complex,R) :- remplace_complexe(Complex,Simple),nnf(Simple,R).


/* predicat  traitement_ABox */

traitement_ABox(inst(I,Complex),inst(I,R)) :- traitement_TBox(Complex,R).


/******************************************* PARTIE 2 *******************************************/

/* on verifie qu'il n'y a pas de cycle dans la TBox, sinon on devra traiter ce cas en ecrivant un message pour changer la TBox */
liste_autoref_CNA([],[]).
liste_autoref_CNA([C|L],[1|LR]) :- autoref(C),liste_autoref_CNA(L,LR).
liste_autoref_CNA([C|L],[0|LR]) :- not(autoref(C)),liste_autoref_CNA(L,LR).
is_autoref_TBox(LR) :- setof(X,cnamena(X),L),liste_autoref_CNA(L,LR).
is_autoref_TBox(LR,0) :- is_autoref_TBox(LR),not(member(1,LR)).
is_autoref_TBox(LR,1) :- is_autoref_TBox(LR),member(1,LR).


liste_nnf([],[]).
liste_nnf([(X,Complex)|Box_Complex],[(X,Cnnf)|Box_Simple]) :- traitement_TBox(Complex,Cnnf),liste_nnf(Box_Complex,Box_Simple).



/******************************* LE PROGRAMME EN DESSOUS ****************************/

programme :- premiere_etape(TBox,Abi,Abr),deuxieme_etape(Abi,Abi1,TBox),troisieme_etape(Abi1,Abr).

/******************************* LE PROGRAMME AU DESSUS ****************************/


/* on traite le cas ou il y a une definition cyclique dans la TBox */

premiere_etape(TBox,Abi,Abr) :- is_autoref_TBox(_,X),suite_etape1(X,TBox,Abi,Abr).


suite_etape1(0,TBox,Abi,Abr) :- setof((X,Y),equiv(X,Y),TBox_Complex),liste_nnf(TBox_Complex,TBox),
                                setof((X,Y),inst(X,Y),Abi_Complex),liste_nnf(Abi_Complex,Abi),
                                setof((X,Y,Z),instR(X,Y,Z),Abr).
suite_etape1(1,_,_,_) :- nl,write('La TBox n"est pas definitoire ! Veuillez corriger le probleme avant de relancer le programme.').


deuxieme_etape(Abi,Abi1,TBox) :- saisie_et_traitement_prop_a_demontrer(Abi,Abi1,TBox).

saisie_et_traitement_prop_a_demontrer(Abi,Abi1,TBox) :-
    nl,write('Entrez le numero du type de proposition que vous voulez demontrer :'),nl,
    write('1 Une instance donnee appartient a un concept donne.'),nl,
    write('2 Deux concepts n"ont pas d"elements en commun (ils ont une instersection vide).'),nl, read(R), suite(R,Abi,Abi1,TBox).

suite(1,Abi,Abi1,TBox) :- acquisition_prop_type1(Abi,Abi1,TBox),!.
suite(2,Abi,Abi1,TBox) :- acquisition_prop_type2(Abi,Abi1,TBox),!.
suite(_,Abi,Abi1,TBox) :-
    nl,write('Cette reponse est incorrecte.'),nl,
    saisie_et_traitement_prop_a_demontrer(Abi,Abi1,TBox).


/* predicats acquisition_prop_type1() et acquisition_prop_type2() */

acquisition_prop_type1(Abi,Abi1,TBox) :-
    nl,write('ecrivez l"instance I sous la forme "nomInstance" (avec une minuscule au debut).'),nl,
    read(I),
    nl,write('ecrivez le concept auquel appartient I sous la forme "nomConcept" (avec une minuscule au debut) ou "expression selon grammaire des concepts".'),nl,
    read(C),nl,suite_saisie_type1(I,C,Abi,Abi1,TBox).

/*
on traite les cas suivants :
- le couple (i, not(c)) est deja dans la ABox (on n'a pas besoin de la
modifier) - i est une instance qu'on connait, c appartient a la TBox
mais le couple (i,not(c)) n'est pas dans la ABox : on le rajoute - on ne
connait pas i : echec du programme, on demande a l'utilisateur de
recommencer on ne connait pas c : echec du programme, on demande a
l'utilisateur de recommencer
*/

suite_saisie_type1(I,C,Abi,Abi1,_) :- instance(I),concept(C),traitement_TBox(not(C),Ca),concatene([(I,Ca)],Abi,Abi1),!.
suite_saisie_type1(I,_,Abi,Abi1,TBox) :- not(instance(I)),nl,write('l"instance donnee n"est pas connue, veuillez la modifier'),acquisition_prop_type1(Abi,Abi1,TBox).
suite_saisie_type1(_,C,Abi,Abi1,TBox) :- not(concept(C)),nl,write('le concept donne n"est pas connu, veuillez le modifier'),acquisition_prop_type1(Abi,Abi1,TBox).


acquisition_prop_type2(Abi,Abi1,TBox) :-
    nl,write('Donnez le premier concept en minuscule'),nl,read(C1),nl,write(C1),nl,
    nl,write('Donnez le deuxieme concept en minuscule'),nl,read(C2),nl,write(C2),nl,
    suite_saisie_type2(C1,C2,Abi,Abi1,TBox).

/* On verifie si ce sont des concepts existant, on transforme, on cree une instance, l'assertion n'est pas presente dans Abi puisqu'on vient de creer l'instance donc on l'y ajoute */

/* Si les concepts n'existe pas, il n'y a pas de clash on ne demontre rien, si l'instance existe alors on ne demontre rien aussi*/

suite_saisie_type2(C1,C2,Abi,[(Inst,Ca)|Abi],_) :- concept(C1),concept(C2),traitement_TBox(and(C1,C2),Ca),
    genere(Inst).
suite_saisie_type2(C1,C2,Abi,Abi1,TBox) :- not(concept(C1)),not(concept(C2)),nl,write('Aucun des concepts donne sont connus, veuillez les modifier'),acquisition_prop_type2(Abi,Abi1,TBox).
suite_saisie_type2(C1,_,Abi,Abi1,TBox) :- not(concept(C1)),nl,write('le premier concept donne n"est pas connu, veuillez le modifier'),acquisition_prop_type2(Abi,Abi1,TBox).
suite_saisie_type2(_,C2,Abi,Abi1,TBox) :- not(concept(C2)),nl,write('le
deuxieme concept donne n"est pas connu, veuillez le
modifier'),acquisition_prop_type2(Abi,Abi1,TBox).




/********************************** PARTIE 3 *********************************/


troisieme_etape(Abi,Abr) :- tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),nl,write("Listes : "),nl,write(Lie),nl,write(Lpt),nl,
    write(Li),nl,write(Lu),nl,write(Ls),
    resolution(Lie,Lpt,Li,Lu,Ls,Abr),
    nl,write('Youpiiiiii, on a demontre la
    proposition initiale !!!').
/*troisieme_etape(Abi,Abr) :- tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),nl,write("Listes : "),nl,write(Lie),nl,write(Lpt),nl,
    write(Li),nl,write(Lu),nl,write(Ls),
    not(resolution(Lie,Lpt,Li,Lu,Ls,Abr)),
    nl,write('Cette proposition ne peut etre demontre').*/

/* predicat tri_ABox(Abi,Lie,Lpt,Li,Lu,Ls) */
/* Abi est entierement triee */
tri_Abox([],[],[],[],[],[]).
/* On cherche tous les elements du type I : some(R,C) */
tri_Abox([(I,some(R,C))|Abi],[(I,some(R,C))|Lie],Lpt,Li,Lu,Ls) :- tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls).
/* On cherche tous les elements du type I : all(R,C) */
tri_Abox([(I,all(R,C))|Abi],Lie,[(I,all(R,C))|Lpt],Li,Lu,Ls) :- tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls).
/* On cherche tous les elements du type I : and(C1,C2) */
tri_Abox([(I,and(C1,C2))|Abi],Lie,Lpt,[(I,and(C1,C2))|Li],Lu,Ls) :- tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls).
/* On cherche tous les elements du type I : or(C1,C2) */
tri_Abox([(I,or(C1,C2))|Abi],Lie,Lpt,Li,[(I,or(C1,C2))|Lu],Ls) :- tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls).
/* On cherche tous les elements du type (I : C) ou (I : not(C)) */
tri_Abox([(I,C)|Abi],Lie,Lpt,Li,Lu,[(I,C)|Ls]) :- cnamea(C),tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls).
tri_Abox([(I,not(C))|Abi],Lie,Lpt,Li,Lu,[(I,not(C))|Ls]) :- cnamea(C),tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls).

/* Coeur du demonstrateur */

/* Predicat evolue(A, Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1) */

evolue((I,some(R,C)), Lie, Lpt, Li, Lu, Ls, [(I,some(R,C))|Lie], Lpt, Li, Lu, Ls).
evolue((I,all(R,C)), Lie, Lpt, Li, Lu, Ls, Lie, [(I,all(R,C))|Lpt], Li, Lu, Ls).
evolue((I,and(R,C)), Lie, Lpt, Li, Lu, Ls, Lie, Lpt, [(I,and(R,C))|Li], Lu, Ls).
evolue((I,or(R,C)), Lie, Lpt, Li, Lu, Ls, Lie, Lpt, Li, [(I,or(R,C))|Lu], Ls).
evolue((I,C), Lie, Lpt, Li, Lu, Ls, Lie, Lpt, Li, Lu, [(I,C)|Ls]).
evolue((I,not(C)), Lie, Lpt, Li, Lu, Ls, Lie, Lpt, Li, Lu, [(I,not(C))|Ls]).
/* On peut eventuellement verifier que C est atomique */

/* Predicat pour verifier un clash */

/* Si on retrouve non(C) dans Ls, on a un clash */
isclash([(I,C)|Ls]) :- nnf(not(C),NC),write('\nOn teste : '),write((I,NC)),nl,member((I,NC),Ls),nl,write(' ON A UN CLASH '),!.
/* On doit faire la verification pour chaque element */
isclash([_|Ls]) :- isclash(Ls),!.


/* Les predicats des regles a implementer */

/*complete_some*/

/* Lie est vide */
complete_some([],Lpt,Li,Lu,Ls,Abr) :- transformation_and([],Lpt,Li,Lu,Ls,Abr),!.
/* On genere B et on ajoute B : C et on cree un nouveau noeud*/
complete_some([(A,some(R,C)) | Lie],Lpt,Li,Lu,Ls,Abr) :- genere(B),evolue((B,C),Lie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1),
    write('\nevolution :\n'),
    affiche_evolution_Abox(Lie,Lpt,Li,Lu,Ls,Abr,Lie1,Lpt1,Li1,Lu1,Ls1,[(A,B,R)|Abr]),nl,
    !,resolution(Lie1,Lpt1,Li1,Lu1,Ls1,[(A,B,R)|Abr]),!.


/*transformation_and*/

transformation_and(Lie,Lpt,[],Lu,Ls,Abr) :- deduction_all(Lie,Lpt,[],Lu,Ls,Abr),!. /* Li est vide */


transformation_and(Lie,Lpt,[(A,and(C,D))|Li],Lu,Ls,Abr) :- evolue((A,C),Lie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1),evolue((A,D),Lie1,Lpt1,Li1,Lu1,Ls1,Lie2,Lpt2,Li2,Lu2,Ls2),write('\nevolution :\n'),
     affiche_evolution_Abox(Lie,Lpt,Li,Lu,Ls,Abr,Lie2,Lpt2,Li2,Lu2,Ls2,Abr),nl,!,resolution(Lie2,Lpt2,Li2,Lu2,Ls2,Abr),!.

/*On ajoute A : C et A : D et on cree un nouveau noeud*/

/*deduction_all*/

evolue_all([(I,some(R,C))|Tmp],Lie,Lpt,Li,Lu,Ls,Lie1,Lpt,Li,Lu,Ls) :- concatene([(I,some(R,C))|Tmp],Lie,Lie1).
evolue_all([(I,all(R,C))|Tmp],Lie,Lpt,Li,Lu,Ls,Lie,Lpt1,Li,Lu,Ls) :- concatene([(I,all(R,C))|Tmp],Lpt,Lpt1).
evolue_all([(I,and(R,C))|Tmp],Lie,Lpt,Li,Lu,Ls,Lie,Lpt,Li1,Lu,Ls) :- concatene([(I,and(R,C))|Tmp],Li,Li1).
evolue_all([(I,or(R,C))|Tmp],Lie,Lpt,Li,Lu1,Ls,Lie,Lpt,Li,Lu,Ls) :- concatene([(I,or(R,C))|Tmp],Lu,Lu1).
evolue_all([(I,C)|Tmp],Lie,Lpt,Li,Lu,Ls,Lie,Lpt,Li,Lu,Ls1) :- concatene([(I,C)|Tmp],Ls,Ls1).




/*Lpt est vide*/
deduction_all(Lie,[],Li,Lu,Ls,Abr) :- transformation_or(Lie,[],Li,Lu,Ls,Abr),!.

/* On cherche tous les couples <A,Y> : R, si trouve, on ajoute Y : C pour chaque element et on cree un nouveau noeud*/

deduction_all(Lie,[(A,all(R,C))|Lpt],Li,Lu,Ls,Abr) :- setof((Y,C),member((A,Y,R),Abr),Tmp),evolue_all(Tmp,Lie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1),write('\nevolution :\n'),
     affiche_evolution_Abox(Lie,Lpt,Li,Lu,Ls,Abr,Lie1,Lpt1,Li1,Lu1,Ls1,Abr),nl,!,resolution(Lie1,Lpt1,Li1,Lu1,Ls1,Abr),!.


/*transformation_or*/

/* Si c'est vide on ne peut que verifier Ls */
/* On ajoute A : C et A : D et on cree deux nouveaux noeuds */


transformation_or(Lie,Lpt,Li,[(A,or(C,D))|Lu],Ls,Abr) :- evolue((A,C),Lie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1),evolue((A,D),Lie,Lpt,Li,Lu,Ls,Lie2,Lpt2,Li2,Lu2,Ls2),
    write('\nevolution or branche 1:\n'),affiche_evolution_Abox(Lie,Lpt,Li,Lu,Ls,Abr,Lie1,Lpt1,Li1,Lu1,Ls1,Abr),nl,
    write('\nevolution or branche 2:\n'),affiche_evolution_Abox(Lie,Lpt,Li,Lu,Ls,Abr,Lie2,Lpt2,Li2,Lu2,Ls2,Abr),nl,!,
    resolution(Lie1,Lpt1,Li1,Lu1,Ls1,Abr),!,resolution(Lie2,Lpt2,Li2,Lu2,Ls2,Abr),!.


/*Resolution */

/* Si clash on doit s'arreter */
resolution([],[],[],[],Ls,_) :- isclash(Ls),!.
/*; write('Tous les tableaux sont fermees la proposition ne peut pas etre demontree !'),!.*/
/* resolution cherche avec complete_some qui appellera transformation_and si Lie vide puis deduction_all si Li est vide etc. */

resolution(Lie,Lpt,Li,Lu,Ls,Abr) :- isclash(Ls);complete_some(Lie,Lpt,Li,Lu,Ls,Abr),!.



/* Affichage de l'evolution affiche_evolution_Abox(Ls1, Lie1, Lpt1, Li1, Lu1, Abr1, Ls2, Lie2,
Lpt2, Li2, Lu2, Abr2) */

/* Pour l'evolution il va falloir parcourir chaque liste une a une et afficher avec les signes qu'on souhaite */

affiche_evolution_Abox(Ls1, Lie1, Lpt1, Li1, Lu1, Abr1, Ls2, Lie2,Lpt2, Li2, Lu2, Abr2) :-
    nl,write('**** Abox initiale ****'),nl,
    afficheAbox(Ls1),nl,afficheAbox(Lie1),nl,afficheAbox(Lpt1),nl,
    afficheAbox(Li1),nl,afficheAbox(Lu1),nl,afficheAbox(Abr1),
    write('\n\n**** Abox suivant ****'),nl,
    afficheAbox(Ls2),nl,afficheAbox(Lie2),nl,afficheAbox(Lpt2),
    afficheAbox(Li2),nl,afficheAbox(Lu2),nl,afficheAbox(Abr2),
    write('\n**** Fin affichage ****\n').

/* Le predicat afficheAbox pour traiter chaque cas */

/* FIN */
afficheAbox([]).

/* On parcourt la liste */
afficheAbox([A|L]) :- afficheAbox(A),afficheAbox(L),!.

/* Les signes mathematiques affichent un symbole inconnu*/
/*I : some(R,C)*/
afficheAbox((I,some(R,C))) :-
    nl,afficheAbox(I),write(' : ∃ '),afficheAbox(R),!,write('.'),afficheAbox(C),!.

/*I : and(C1,C2)*/
afficheAbox((I,and(C1,C2))) :- nl,afficheAbox(I),write(' : '),
    afficheAbox(C1),write(' ⊓ '),afficheAbox(C2),!.

/*I : all(R,C)*/
afficheAbox((I,all(R,C))) :- nl,afficheAbox(I),write(' : ∀ '),
    afficheAbox(R),write('.'),afficheAbox(C),!.

/*I : or(C1,C2)*/
afficheAbox((I,or(C1,C2))) :- nl,afficheAbox(I),write(' : '),
    afficheAbox(C1),write(' ⊔ '),afficheAbox(C2),!.

/* <a,b> : R */
afficheAbox((A,B,R)) :- nl,write(' <'),afficheAbox(A),write(', '),
    afficheAbox(B),write('> : '),afficheAbox(R),!.

/* I : C */
afficheAbox((I,C)) :- nl,afficheAbox(I),write(' : '),afficheAbox(C),!.


afficheAbox(not(C)) :- write('¬ '),afficheAbox(C),!.

/* On affiche */
afficheAbox(C) :- write(C).


/*********************************** PREDICATS ANNEXES **************************************/


/* Quelques predicats fournis dans le projet */
/* concat(L1,L2,L3) : concatene les deux listes L1 et L2 et renvoie la liste L3 */

concatene([],L1,L1).
concatene([X|Y],L1,[X|L2]) :- concatene(Y,L1,L2).

/*genere(Nom) : genere un nouvel identificateur qui est fourni en sortie dans Nom.*/

compteur(1).
genere(Nom) :- compteur(V),nombre(V,L1),concatene([105,110,115,116],L1,L2),
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
