% Donne par l enonce
compteur(1).

deuxieme_etape(Abi,Abi1,Tbox) :-
saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox).

saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox) :- 
    nl,
    write('Entrez   le   numero   du   type   de   proposition   que   vous   voulez demontrer :'),
    nl,
    write('1 Une instance donnee appartient a un concept donne.'),
    nl,
    write('2 Deux concepts n"ont pas d"elements en commun(ils ont une intersection vide).'),
    nl,
    read(R),
    suite(R,Abi,Abi1,Tbox).
    
suite(1,Abi,Abi1,Tbox) :-    acquisition_prop_type1(Abi,Abi1,Tbox),!.
suite(2,Abi,Abi1,Tbox) :-    acquisition_prop_type2(Abi,Abi1,Tbox),!.
suite(_,Abi,Abi1,Tbox) :-    nl,write('Cette reponse est incorrecte.'),nl,
                             saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox).

%Pour Type1
% Trouver le formule a demontrer
acquisition_prop_type1(Abi,Abi11,Tbox) :-write('Entrer le concept et l"instance en [I,C].'),nl,read([I,C]),
                                        ajouter1(I,C,Abi,Abi1,Tbox),my_flatten(Abi1,Abi11),!.

%Ajouter le formule traite dans Abox etendue
ajouter1(I,C,Abi,Abi1,Tbox):- prolonge_A_Tbox(C,Tbox,Abi,CC),nnf(not(CC),NF),my_flatten([Abi,(I,NF)],Abi1).

%Pour Type2
% Trouver le formule a demontrer
acquisition_prop_type2(Abi,Abi11,Tbox) :-write('Entrer deux concepts en [C1,C2].'),nl,read([C1,C2]),
                                        ajouter2(C1,C2,Abi,Abi1,Tbox),my_flatten(Abi1,Abi11).

%Ajouter le formule traite dans Abox etendue

ajouter2(C1,C2,Abi,Abi1,Tbox):- genere(A),prolonge_A_Tbox(C1,Tbox,Abi,CC1),
                                prolonge_A_Tbox(C2,Tbox,Abi,CC2),
                                nnf((and(CC1,CC2)),NF),
                                my_flatten([Abi,(A,NF)],Abi1),
                                write('2e etape'),nl,
                                write('Abox etendue: '),nl,
                                write(Abi1).


% Pronlonger un concept base de Tbox et Tbox( presque la meme que dans LRC_part1.pl)
prolonge_A_Tbox(C,Tbox,Abox,C):- member((_,C),Abox),
                                not(member((C,_),Tbox)). % si c”est atomique, on ne fait rien
prolonge_A_Tbox(anything,_,_,anything):-!.
prolonge_A_Tbox(nothing,_,_,nothing):-!.
prolonge_A_Tbox(C,Tbox,Abox,L):- not(member((_,C),Abox)),member((C,CC),Tbox),
                                prolonge_A_Tbox(CC,Tbox,Abox,L). % si c”est non-atomique, on le transforme en atomique
prolonge_A_Tbox(and(C,D),Tbox,Abox,and(L1,L2)):-prolonge_A_Tbox(C,Tbox,Abox,L1),
                                                prolonge_A_Tbox(D,Tbox,Abox,L2),!.
prolonge_A_Tbox(or(C,D),Tbox,Abox,or(L1,L2)):-prolonge_A_Tbox(C,Tbox,Abox,L1),
                                            prolonge_A_Tbox(D,Tbox,Abox,L2),!.
prolonge_A_Tbox(some(R,C),Tbox,Abox,some(R,L)):-prolonge_A_Tbox(C,Tbox,Abox,L),!.
prolonge_A_Tbox(all(R,C),Tbox,Abox,all(R,L)):-prolonge_A_Tbox(C,Tbox,Abox,L),!.
prolonge_A_Tbox(not(C),Tbox,Abox,not(L)):-prolonge_A_Tbox(C,Tbox,Abox,L),!.
