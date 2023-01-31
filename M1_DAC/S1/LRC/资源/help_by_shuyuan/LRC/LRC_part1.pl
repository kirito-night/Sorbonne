% Vérification sémantique : 
% On vérifie la grammaire de la logique ALC ,concept: ConceptGen -> boolean

concept(not(C)) :- concept(C), !.
concept(and(C1, C2)) :- concept(C1), concept(C2), !.
concept(or(C1, C2)) :- concept(C1), concept(C2), !.
concept(some(R, C)) :- role(R), concept(C), !.
concept(all(R, C)) :- role(R), concept(C), !.

concept(C) :- cnamea(C), !. % Vérification des concepts atomique
concept(CG) :- cnamena(CG), !. % Vérification des concepts non atomique
instance(I) :- iname(I), !. % Vérification des identificateurs d“instance
role(R) :- rname(R), !. % Vérification des identificateurs de rôle.

test_concept :-
    concept(sculpteur),
    concept(auteur),
    concept(editeur),
    concept(parent),
    not(concept(and(objet, all(creePar, and(personne, some(aCree, sculpture)))))).

% Vérification syntaxique
% Pour autoref
% autoref: ConceptNonAtom -> boolean

autoref(CNA) :- equiv(CNA, CG), !, not(pas_autoref(CNA, CG)).
pas_autoref(CNA, not(CG)) :- !, pas_autoref(CNA, CG).
pas_autoref(CNA, or(CG1, CG2)) :- !, pas_autoref(CNA, CG1), pas_autoref(CNA, CG2).
pas_autoref(CNA, and(CG1, CG2)) :- !, pas_autoref(CNA, CG1), pas_autoref(CNA, CG2).
pas_autoref(CNA, some(_, CG)) :- !, pas_autoref(CNA, CG).
pas_autoref(CNA, all(_, CG)) :- !, pas_autoref(CNA, CG).
pas_autoref(CNA, CG) :- !, CNA \= CG.

test_autoref :-
    not(autoref(sculpteur)),
    not(autoref(auteur)),
    not(autoref(editeur)),
    not(autoref(parent)),
    not(pas_autoref(sculpture, and(objet, all(creePar, and(personne, some(aCree, sculpture)))))).

% Pour developper les ConceptGen
% prolonge: ConceptGen -> composition de concepts atomiques
prolonge(C,C):-cnamea(C),!.
prolonge(C1,L):-cnamena(C1),equiv(C1,C2),concept(C2),prolonge(C2,L). % si c”est non-atomique, on le transforme en atomique
prolonge(and(C1,C2),and(L1,L2)):-prolonge(C1,L1),prolonge(C2,L2),!.
prolonge(or(C1,C2),or(L1,L2)):-prolonge(C1,L1),prolonge(C2,L2),!.
prolonge(some(R,C),some(R,L)):-role(R),prolonge(C,L),!.
prolonge(all(R,C),all(R,L)):-role(R),prolonge(C,L),!.
prolonge(not(C),not(L)):-prolonge(C,L),!.

%% Création d“un Tbox
% creation_Tbox: -> liste[(Concept,composition de concepts atomiques)]

creation_Tbox(L):- setof((CA,CG), equiv(CA,CG), L).
traitement_Tbox([],[]).
traitement_Tbox([(CA,CG)|L],[(CA,NF)|L1]):-     concept(CA),
                                                concept(CG),
                                                not(autoref(CA)),
                                                prolonge(CG,CG1),
                                                nnf(CG1,NF),
                                                traitement_Tbox(L,L1).

% Création de Abox
% creation_Abox: -> liste[liste1[(instance,role)],liste2[((instance1,instance2,role))]]

creation_Abox(L1,L2):- setof((CA,NF), inst(CA,NF),L1), setof((CA,CB,CD), instR(CA,CB,CD),L2).

traitement_Abox1([],[]).
traitement_Abox1([(CA,CG)|L],[(CA,NF)|L1]):-
                                            instance(CA),   
                                            concept(CG),
                                            prolonge(CG,CG1),
                                            nnf(CG1,NF),
                                            traitement_Abox1(L,L1).

traitement_Abox2([],[]).
traitement_Abox2([(CA,CB,R)|L],[(CA,CB,R)|L1]):-instance(CA),
                                                instance(CB),
                                                role(R),
                                                instR(CA, CB, R),
                                                traitement_Abox2(L,L1).

traitement_Abox(L1,L2,LL1,LL2):-traitement_Abox1(L1,LL1),traitement_Abox2(L2,LL2).


%% Tous ce qu''on a fait jusqu''à maintenant

premiere_etape(TboxR,AboxIR, AboxRR) :-
    write('Création de la TBox ...'), nl,
    creation_Tbox(Tbox),
    write('Création de la TBox réussi'), nl,

    write('Création des ABox ...'), nl,
    creation_Abox(AboxI,AboxR),
    write('Création des ABox réussi'), nl,
    
    write('Traitement de la TBox ...'), nl,
    traitement_Tbox(Tbox, TboxR),
    write('Traitement de la TBox réussi'), nl,

    write('Traitement des ABox ...'), nl,
    traitement_Abox(AboxI, AboxR, AboxIR, AboxRR),
    write('Traitement des ABox réussi'), nl.
    
    write('Tbox : '),nl,
    write(TboxR),nl,
    write('AboxIR : '),nl,
    write(AboxIR),nl,
    write('AboxIRR'),nl,
    write(AboxRR),nl,nl.








