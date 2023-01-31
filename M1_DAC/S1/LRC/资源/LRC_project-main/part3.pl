/* je met chaque element de ABI dans la bonne liste */

tri_Abox([],[],[],[],[],[]).

tri_Abox([(I,some(R,C))|Q],[(I,some(R,C))|Lie],Lpt,Li,Lu,Ls) :- tri_Abox(Q,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,all(R,C))|Q],Lie,[(I,all(R,C))|Lpt],Li,Lu,Ls) :- tri_Abox(Q,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,and(C1,C2))|Q],Lie,Lpt,[(I,and(C1,C2))|Li],Lu,Ls) :- tri_Abox(Q,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,or(C1,C2))|Q],Lie,Lpt,Li,[(I,or(C1,C2))|Lu],Ls) :- tri_Abox(Q,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,C)|Q],Lie,Lpt,Li,Lu,[(I,C)|Ls]) :- tri_Abox(Q,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,not(C))|Q],Lie,Lpt,Li,Lu,[(I,not(C))|Ls]) :- tri_Abox(Q,Lie,Lpt,Li,Lu,Ls),!.


/* affiche un element en infixé -> ici il faut ajouter le !*/

affiche(not(Concept),Res) :- affiche(Concept,Res1),
                             atom_concat('\u00AC',Res1,Res),!.

affiche(and(Concept1,Concept2),Res) :- affiche(Concept1,Res1), 
                                    affiche(Concept2,Res2),
                                    atom_concat(Res1,' \u2293 ',Res3),
                                    atom_concat('(',Res3,Res4),
                                    atom_concat(Res2,')',Res5),
                                    atom_concat(Res4,Res5,Res),!.

affiche(or(Concept1,Concept2),Res) :- affiche(Concept1,Res1), 
                                      affiche(Concept2,Res2),
                                      atom_concat(Res1,' \u2294 ',Res3),
                                      atom_concat('(',Res3,Res4),
                                      atom_concat(Res2,')',Res5),
                                      atom_concat(Res4,Res5,Res),!.
                                      
affiche(all(R,Concept),Res) :- affiche(R,Res1), 
                               affiche(Concept,Res2),
                               atom_concat('\u2200',Res1,Res3),
                               atom_concat('.',Res2,Res4),
                               atom_concat(Res3,Res4,Res),!.

affiche(some(R,Concept),Res) :- affiche(R,Res1), 
                                affiche(Concept,Res2),
                                atom_concat('\u2203',Res1,Res3),
                                atom_concat('.',Res2,Res4),
                                atom_concat(Res3,Res4,Res),!.

/* affiche les concepts atomic en chaines de cars */
affiche(anything,'\u22A4').
affiche(nothing,'\u22A5').
affiche(Concept,Res) :- atom_string(Concept,Res).


/* affiche une des listes Abi en infixé */
afficheAbi([]).

afficheAbi([(A,B)|Q]) :- 

    affiche(B,Inf),

    atom_concat(A, ' : ',Res1),
    atom_concat(Res1, Inf,Res),
    write(Res),write(','),tab(3),
    afficheAbi(Q)

.


/* affiche Abr en infixé */

afficheAbr([]).
afficheAbr([(A,B,R)|Q]) :- 

    atom_concat('<',A,Res1),
    atom_concat(Res1, ',',Res2),
    atom_concat(B, '>',Res3),
    atom_concat(Res2,Res3,Res4),
    atom_concat(Res4, ':',Res5),
    atom_concat(Res5,R,Res),
    write(Res),write(','),tab(3),
    afficheAbr(Q)
.

/* affiche la TBox et la Abox */


afficheList([]):- write('vide').

afficheList(L) :- L = [(_,_,_)|_], afficheAbr(L).

afficheList(L) :- L = [(_,_)|_], afficheAbi(L).

/* affiche l'evolution d'un etat à un autre*/

affiche_evolution_Abox(Ls1, Lie1, Lpt1, Li1, Lu1, Abr1, Ls2, Lie2,Lpt2, Li2, Lu2, Abr2) :-
    write('######################################################'),
    nl,write('Etat de depart  = '),nl,nl,

    write('Liste Lie = '),
    afficheList(Lie1),nl,nl,

    write('Liste Lpt = '),
    afficheList(Lpt1),nl,nl,

    write('Liste Li = '),
    afficheList(Li1),nl,nl,

    write('Liste Lu = '),
    afficheList(Lu1),nl,nl,

    write('Liste Ls = '),
    afficheList(Ls1),nl,nl,

    write('Liste Abr = '),
    afficheList(Abr1),nl,nl,

    nl,nl,write('Etat d\'arrive  = '),nl,nl,
    
    write('Liste Lie = '),
    afficheList(Lie2),nl,nl,

    write('Liste Lpt = '),
    afficheList(Lpt2),nl,nl,

    write('Liste Li = '),
    afficheList(Li2),nl,nl,

    write('Liste Lu = '),
   afficheList(Lu2),nl,nl,

    write('Liste Ls = '),
    afficheList(Ls2),nl,nl,


    write('Liste Abr = '),
    afficheList(Abr2),nl,nl,
    write('######################################################'),!


.

/* je parcours toutes les listes si y a aucun clash -> return true else false*/
test_clash([]).
test_clash(L):- is_clash(L,L).

is_clash([],_).
is_clash([(I,C)|Q],L):- not(member((I,not(C)),L)), is_clash(Q,L).



/* rajoute la nouvelle assertion a la liste adequate seulement si elle n'y 
existe pas deja */
add_new_element(X,L1,L2) :- not(member(X,L1)), concat([X],L1,L2),!.
add_new_element(X,L1,L2) :- member(X,L1), concat([],L1,L2),!.


get_all_new_elts(_,[],[]).
get_all_new_elts((I,all(R,C)),[(I1,I2,R1)|Q],[(I2,C)|New]) :- I == I1, R == R1, get_all_new_elts((I,all(R,C)),Q, New),!.
get_all_new_elts((I,all(R,C)),[_|Q],New) :- get_all_new_elts((I,all(R,C)),Q, New),!.


/* Ajoute la nouvelle assertion de concepts a la bonne liste */

evolue((I,some(R,C)), Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1) :-

    add_new_element((I,some(R,C)),Lie,Lie1),
    concat([],Lpt,Lpt1),
    concat([],Li,Li1),
    concat([],Lu,Lu1),
    concat([],Ls,Ls1),!
.

evolue((I,all(R,C)), Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1) :-

    concat([],Lie,Lie1),
    add_new_element((I,all(R,C)),Lpt,Lpt1),
    concat([],Li,Li1),
    concat([],Lu,Lu1),
    concat([],Ls,Ls1),!
.

evolue((I,and(C1,C2)), Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1) :-

    concat([],Lie,Lie1),
    concat([],Lpt,Lpt1),
    add_new_element((I,and(C1,C2)),Li,Li1),
    concat([],Lu,Lu1),
    concat([],Ls,Ls1),!
.

evolue((I,or(C1,C2)), Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1) :-

    concat([],Lie,Lie1),
    concat([],Lpt,Lpt1),
    concat([],Li,Li1),
    add_new_element((I,or(C1,C2)),Lu,Lu1),
    concat([],Ls,Ls1),!
.

evolue((I,not(C)), Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1) :-

    concat([],Lie,Lie1),
    concat([],Lpt,Lpt1),
    concat([],Li,Li1),
    concat([],Lu,Lu1),
    add_new_element((I,not(C)),Ls,Ls1),!
.

evolue((I,C), Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1) :-

    concat([],Lie,Lie1),
    concat([],Lpt,Lpt1),
    concat([],Li,Li1),
    concat([],Lu,Lu1),
    add_new_element((I,C),Ls,Ls1),!
.



/* Ajoute une liste de parcours la liste des nouvelles assertions
de concepts et les ajoute a la bonne liste */


evolue_all([],Lie, Lpt, Li, Lu, Ls,Lie, Lpt, Li, Lu, Ls).

evolue_all([T|Q], Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1) :-

evolue(T, Lie, Lpt, Li, Lu, Ls, Lie2, Lpt2, Li2, Lu2, Ls2),

evolue_all(Q, Lie2, Lpt2, Li2, Lu2, Ls2, Lie1, Lpt1, Li1, Lu1, Ls1),!.


/* Application de la regle d'il existe */
complete_some(Lie,Lpt,Li,Lu,Ls,Abr) :-
            
    /*enleve la tete de liste de Lie*/
    enleve((I,some(R,C)),Lie,Q),

    /* ajout de des nouvelles assertions de concepts*/
    genere(B), 

    evolue((B,C), Q, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1),

    /* ajout de des nouvelles assertions de roles */
    concat([(I,B,R)],Abr,Abr1), 

    affiche_evolution_Abox(Ls, Lie, Lpt, Li, Lu, Abr, Ls1, Lie1,
    Lpt1, Li1, Lu1, Abr1),

    /* je met toutes les listes ensemble */
    flatten([Lie1, Lpt1, Li1, Lu1, Ls1],Y),

    /* test de clash */
    test_clash(Y),
    
    resolution(Lie1,Lpt1,Li1,Lu1,Ls1,Abr1)
.

/* Application de la regle de qlq soit */
deduction_all(Lie,Lpt,Li,Lu,Ls,Abr) :- 
            
    /* enleve la tete de liste de Lpt */
    enleve((I,all(R,C)),Lpt,Q),

    /* reecupere toutes les assertions I2 : C possibles */
    get_all_new_elts((I,all(R,C)),Abr,New_assertions),

    evolue_all(New_assertions, Lie, Q, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1),

   
    affiche_evolution_Abox(Ls, Lie, Lpt, Li, Lu, Abr, Ls1, Lie1,
    Lpt1, Li1, Lu1, Abr),

     /* je met toutes les listes ensemble */
    flatten([Lie1, Lpt1, Li1, Lu1, Ls1],Y),


    /* test de clash */
    test_clash(Y),
    
    resolution(Lie1,Lpt1,Li1,Lu1,Ls1,Abr)
.


/* transformation ET */
transformation_and(Lie,Lpt,Li,Lu,Ls,Abr) :- 

    /* enleve la tete de liste de Li et retourne le reste de la liste dans Q*/
    enleve((I,and(C1,C2)),Li,Q),

    /* ajout de des nouvelles assertions SI elles existent */
    evolue((I,C1), Lie, Lpt, Q, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1),
    evolue((I,C2), Lie1, Lpt1, Li1, Lu1, Ls1, Lie2, Lpt2, Li2, Lu2, Ls2),


    affiche_evolution_Abox(Ls, Lie, Lpt, Li, Lu, Abr, Ls2, Lie2,
    Lpt2, Li2, Lu2, Abr),
    
    /* test de clash */
    flatten([Lie2, Lpt2, Li2, Lu2, Ls2],Y),

    test_clash(Y),

    resolution(Lie2,Lpt2,Li2,Lu2,Ls2,Abr)
.

/* tranformation or */
transformation_or(Lie,Lpt,Li,Lu,Ls,Abr) :- 

    /* enleve la tete de liste de Lu et retourne le reste de la liste dans Q*/
    enleve((I,or(C1,_)),Lu,Q),
    /* creation de la 1ere branche */
    evolue((I,C1),Lie, Lpt, Li,Q, Ls, Lie1, Lpt1, Li1, Lu1, Ls1), 
    affiche_evolution_Abox(Ls, Lie, Lpt, Li, Lu, Abr, Ls1, Lie1,
    Lpt1, Li1, Lu1, Abr),
    /* test de clash */
    flatten([Lie1, Lpt1, Li1, Lu1, Ls1],Y),
    test_clash(Y),
    resolution(Lie1,Lpt1,Li1,Lu1,Ls1,Abr).
    
transformation_or(Lie,Lpt,Li,Lu,Ls,Abr) :- 
    enleve((I,or(_,C2)),Lu,Q),
    /* Creation de la seconde branche */
    evolue((I,C2),Lie, Lpt, Li,Q,  Ls, Lie2, Lpt2, Li2, Lu2, Ls2), 
    affiche_evolution_Abox(Ls, Lie, Lpt, Li, Lu, Abr, Ls2, Lie2,
    Lpt2, Li2, Lu2, Abr),
    /* test de clash */
    flatten([Lie2, Lpt2, Li2, Lu2, Ls2],Y1),
    test_clash(Y1),
    resolution(Lie2,Lpt2,Li2,Lu2,Ls2,Abr)
.


/* Partie resolution */
resolution([],[],[],[],Ls,_):- member(_,Ls),test_clash(Ls),nl,
                            write('La proposition donnee en entree n\'est pas valide').

resolution(Lie,Lpt,Li,Lu,Ls,Abr) :- member(_,Lie), complete_some(Lie,Lpt,Li,Lu,Ls,Abr).
resolution(Lie,Lpt,Li,Lu,Ls,Abr) :- member(_,Li),transformation_and(Lie,Lpt,Li,Lu,Ls,Abr).
resolution(Lie,Lpt,Li,Lu,Ls,Abr) :- member(_,Lu),transformation_or(Lie,Lpt,Li,Lu,Ls,Abr).
resolution(Lie,Lpt,Li,Lu,Ls,Abr) :- member(_,Lpt),deduction_all(Lie,Lpt,Li,Lu,Ls,Abr).


/* TROISIEME ETAPE CALL */

troisieme_etape(Abi,Abr) :-

    tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),

    not(resolution(Lie,Lpt,Li,Lu,Ls,Abr)),

    nl,write('La proposition donnee en entree est valide').

