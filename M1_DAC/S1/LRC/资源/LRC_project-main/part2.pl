
/*  ---- PARTIE 2 ---- */

/* NEGATION DE CONCEPTS */

nnf(not(and(C1,C2)),or(NC1,NC2)):- nnf(not(C1),NC1), nnf(not(C2),NC2),!.
nnf(not(or(C1,C2)),and(NC1,NC2)):- nnf(not(C1),NC1), nnf(not(C2),NC2),!.
nnf(not(all(R,C)),some(R,NC)) :- nnf(not(C),NC),!.
nnf(not(some(R,C)),all(R,NC)):- nnf(not(C),NC),!.
nnf(not(not(X)),X):-!.
nnf(not(X),not(X)):-!.
nnf(and(C1,C2),and(NC1,NC2)):- nnf(C1,NC1),nnf(C2,NC2),!.
nnf(or(C1,C2),or(NC1,NC2)):- nnf(C1,NC1), nnf(C2,NC2),!.
nnf(some(R,C),some(R,NC)):- nnf(C,NC),!.
nnf(all(R,C),all(R,NC)) :- nnf(C,NC),!.
nnf(X,X).


/* Verifier si instance existe idf */
instance(Instance) :- atom(Instance), setof(X,iname(X),L), member(Instance,L),!.

/* Verifier si un role existe*/
role(Role) :-  setof(X,rname(X),L), member(Role,L),!.

/* Verifier si un concept est correct grammaticalement et syntaxiquement*/

is_concept_atom(Concept) :- atom(Concept), setof(X,cnamea(X),L), member(Concept,L),!.
is_concept_gen(Concept) :- atom(Concept), setof(X,cnamena(X),L), member(Concept,L),!.


concept(Concept) :- is_concept_atom(Concept).
concept(Concept) :- is_concept_gen(Concept).
concept(not(Concept)) :- concept(Concept),!.
concept(and(Concept1,Concept2)) :- concept(Concept1), concept(Concept2),!.
concept(or(Concept1,Concept2)) :- concept(Concept1), concept(Concept2),!.
concept(all(R,Concept)) :- role(R),concept(Concept),!.
concept(some(R,Concept)) :- role(R),concept(Concept),!.

/* verifier si les propositions sont correctes */
is_correct_pro1(Instance,Concept) :- instance(Instance), concept(Concept),!.
is_correct_pro1(Instance,_) :- not(instance(Instance)),nl, 
                        write('L\'instance donnee est incorrecte'),instance(Instance),!.
is_correct_pro1(_,Concept) :- not(concept(Concept)),nl, write("Le concept donne est incorrect")
                                ,nl,concept(Concept),!.

is_correct_pro2(Concept1,Concept2) :- concept(Concept1), concept(Concept2),!.
is_correct_pro2(Concept1,_) :- not(concept(Concept1)),nl,
                               write("Le concept1 donne est incorrect"),nl,concept(Concept1),!.
is_correct_pro2(_,Concept2) :- not(concept(Concept2)),nl,
                                write("Le concept2 donne est incorrect"),nl,concept(Concept2),!.

/*tranformer un concept complexe par sa definition */

transform_concept(_,Concept,Concept) :-  is_concept_atom(Concept).
transform_concept(Tbox,Concept,X) :- is_concept_gen(Concept), member((Concept,X),Tbox).
transform_concept(Tbox,not(Concept),not(X)) :- transform_concept(Tbox,Concept,X),!.

transform_concept(Tbox,and(Concept1,Concept2),and(X1,X2)) :- transform_concept(Tbox,Concept1,X1),
                                                         transform_concept(Tbox,Concept2,X2),!.

transform_concept(Tbox,or(Concept1,Concept2),or(X1,X2)) :- transform_concept(Tbox,Concept1,X1), 
                                                         transform_concept(Tbox,Concept2,X2),!.

transform_concept(Tbox,all(R,Concept),all(R,X)) :- role(R), transform_concept(Tbox,Concept,X),!.
transform_concept(Tbox,some(R,Concept),some(R,X)) :- role(R), transform_concept(Tbox,Concept,X),!.

/* tranformer tous les  concepts complexes de l ABI  par leurs definitions */

transform_all(_,[],[]).
transform_all(Tbox,[(I,C)|Q],[(I,Prop_atomique)|Abi1]) :- transform_concept(Tbox,C,Prop_atomique),
                                                          transform_all(Tbox,Q,Abi1).
                                                



/* On demande a l utilisateur la proposition de type : I:C */
demander_proposition1(Instance,Concept) :-
        write('Entrez l\'instance :'),nl, 
        read(Instance),nl,
        write('Entrez le concept :'),nl, 
        read(Concept).

acquisition_prop_type1(Abi,Abi1,Tbox) :- demander_proposition1(Instance,Concept),
                        is_correct_pro1(Instance,Concept),
                        transform_all(Tbox,Abi,AbiA),
                        transform_concept(Tbox,not(Concept),Prop_atomique),
                        nnf(Prop_atomique,Negation),
                        concat(AbiA,[(Instance,Negation)],Abi1).


/* On demande a l utilisateur la proposition de type : C1 et C2 subsumés par le vide */

demander_proposition2(Concept1,Concept2) :-
        write('Entrez le concept 1 :'),nl, 
        read(Concept1),nl,
        write('Entrez le concept 2 :'),nl, 
        read(Concept2).


acquisition_prop_type2(Abi,Abi1,Tbox) :- demander_proposition2(Concept1,Concept2),
                        is_correct_pro2(Concept1,Concept2),
                        transform_all(Tbox,Abi,AbiA), 
                        transform_concept(Tbox,Concept1,Prop_atomique1),
                        transform_concept(Tbox,Concept2,Prop_atomique2),
                        genere(Nom), /* on genere un nom car on veut une instantiation
                        dans la negation */
                        concat(AbiA,[(Nom,and(Prop_atomique1,Prop_atomique2))],Abi1).




/* Saisie et traitement des demonstrations */

suite(1,Abi,Abi1,Tbox) :- acquisition_prop_type1(Abi,Abi1,Tbox),!.

suite(2,Abi,Abi1,Tbox) :- acquisition_prop_type2(Abi,Abi1,Tbox),!.

suite(R,Abi,Abi1,Tbox) :- R \== 1, R \== 2 ,nl,write('Cette reponse est incorrecte.'),nl,
saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox).

/* nl = new line, Abi1 = instances maj */

saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox) :-
nl,write('Entrez le numero du type de proposition que vous voulez demontrer :'),nl,nl,
write('1 - Une instance donnee appartient a un concept donne.'),nl,nl,
write('2 - Deux concepts n\'ont pas d\'elements en commun.'),nl,nl,read(R),nl,suite(R,Abi,Abi1,Tbox).


deuxieme_etape(Abi,Abi1,Tbox) :- saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox).
