% Donne par l"enonce"
troisieme_etape(Abi,Abr) :- nl,nl,
                                write('Abox ins = '),write(Abi),nl,nl,
                                write('Abox relation = '),write(Abr),nl,nl,
                             tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),
                             create_ABR(Abr,Abi,Abe),
                             write('Abe = '),write(Abe),nl,
                             resolution(Lie,Lpt,Li,Lu,Ls,Abe),
                             nl,write('Youpiiiiii, on a demontre la proposition initiale !!!').

% Classement des formules dans Abox,les renvoyer dans 5 listes
tri_Abox([],[],[],[],[],[]).
tri_Abox([(I,some(R,C))|Abi],[(I,some(R,C))|Lie],Lpt,Li,Lu,Ls):-tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,all(R,C))|Abi],Lie,[(I,all(R,C))|Lpt],Li,Lu,Ls):-tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,and(C1,C2))|Abi],Lie,Lpt,[(I,and(C1,C2))|Li],Lu,Ls):-tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,or(C1,C2))|Abi],Lie,Lpt,Li,[(I,or(C1,C2))|Lu],Ls):-tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,not(C))|Abi],Lie,Lpt,Li,Lu,[(I,not(C))|Ls]):-tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),!.
tri_Abox([(I,C)|Abi],Lie,Lpt,Li,Lu,[(I,C)|Ls]):-tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),!.


create_ABR(Abr,Abi,Abe):-concat(Abr,Abi,Abe).
seperate_ABR([],[],[]).
seperate_ABR([(I1,I2,R)|Abe],Abi,[(I1,I2,R)|Abr]):-seperate_ABR(Abe,Abi,Abr),!.
seperate_ABR([(I,C)|Abe],[(I,C)|Abi],Abr):-seperate_ABR(Abe,Abi,Abr),!.

complete_some([],_,_,_,_,Abe,Abe).
complete_some(Lie,Lpt,Li,Lu,Ls,Abe,Abe21):- enleve((I,some(R,C)),Lie,Lie1),
                                        enleve((I,some(R,C)),Abe,Abe1),
                                        complete_some(Lie1,Lpt,Li,Lu,Ls,Abe1,Abe2),
                                        genere(Nom),
                                        concat([(Nom,C),(I,Nom,R)],Abe2,Abe21),!.

tansformation_and(_,_,[],_,_,Abe,Abe).
tansformation_and(Lie,Lpt,Li,Lu,Ls,Abe,Abe21):- enleve((I,and(C1,C2)),Li,Li1),
                                        enleve((I,and(C1,C2)),Abe,Abe1),
                                        complete_some(Lie,Lpt,Li1,Lu,Ls,Abe1,Abe2),
                                        concat([(I,C1),(I,C2)],Abe2,Abe21),!.

deduction_all(_,[],_,_,_,Abe,Abe).
deduction_all(Lie,Lpt,Li,Lu,Ls,Abe,Abe21):- enleve((I,all(R,C)),Lpt,Lpt1),
                                        enleve((I,all(R,C)),Abe,Abe1),
                                        deduction_all(Lie,Lpt1,Li,Lu,Ls,Abe1,Abe2),
                                        member((I,II,R),Abe1),
                                        concat([(II,C)],Abe2,Abe21),!.

tranformation_or(_,_,_,[],_,Abe,Abe).
tranformation_or(Lie,Lpt,Li,Lu,Ls,Abe,[Abe21,Abe22]):- enleve((I,or(C1,C2)),Lu,Lu1),
                                        enleve((I,or(C1,C2)),Abe,Abe1),
                                        tranformation_or(Lie,Lpt,Li,Lu1,Ls,Abe1,Abe2),
                                        concat([(I,C1)],Abe2,Abe21),
                                        concat([(I,C2)],Abe2,Abe22),!.



% identifier les clash
test_collision(Abe):- member((I,C),Abe),member((I, not(C)),Abe),
                     write('collision between : '),!,
                     print_concepte((I,C)),!,
                     write(' and '),!,
                     print_concepte((I,not(C))),!,nl,nl.


%evolution
evolue(Abe,Lie1, Lpt1, Li1, Lu1, Ls1):- seperate_ABR(Abe,Abi,_),
                                    tri_Abox(Abi,Lie1,Lpt1,Li1,Lu1,Ls1).

%%%%%%%%%%NON

resolution(_,_,_,_,_,Abe):- test_collision(Abe),
                                    write('Collision'),nl,
                                    print_Abox(Abe),nl,!.

%FAIL
resolution(_,_,_,_,_,Abe):- not(test_collision(Abe)),
                                    evolue(Abe,Lie1, Lpt1, Li1, Lu1, _),
                                    Lie1=[],Lpt1=[],Li1=[],Lu1=[],
                                    write('Fail to match any rule, ce proposition n est pas valide'),nl,
                                    print_Abox(Abe),nl.

%%%%%%%%%% some
resolution(_,_,_,_,_,Abe):- not(test_collision(Abe)),
                                    evolue(Abe,Lie1, Lpt1, Li1, Lu1, Ls1),
                                    Lie1\=[],
                                    complete_some(Lie1,Lpt1,Li1,Lu1,Ls1,Abe,Abe2),
                                    nl,write('Some'),nl,nl,
                                    affiche_evolution_Abox([],[],[],[],[],Abe,Lie1,Lpt1,Li1,Lu1,Ls1,Abe2),nl,nl,
                                    not(test_collision(Abe2)),
                                    resolution(_,_,_,_,_,Abe2).

resolution(_,_,_,_,_,Abe):- not(test_collision(Abe)),
                                    evolue(Abe,Lie1, Lpt1, Li1, Lu1, Ls1),
                                    Lie1\=[],
                                    complete_some(Lie1,Lpt1,Li1,Lu1,Ls1,Abe,Abe2),
                                    test_collision(Abe2),nl,
                                    write('Collision exist here!!'),nl.



%ALL
resolution(_,_,_,_,_,Abe):- not(test_collision(Abe)),
                                    evolue(Abe,Lie1, Lpt1, Li1, Lu1, Ls1),
                                    Lpt1\=[],
                                    deduction_all(Lie1,Lpt1,Li1,Lu1,Ls1,Abe,Abe2),
                                    nl,nl,write('All'),nl,nl,
                                    affiche_evolution_Abox([],[],[],[],[],Abe,Lie1,Lpt1,Li1,Lu1,Ls1,Abe2),nl,nl,
                                    not(test_collision(Abe2)),
                                    resolution(_,_,_,_,_,Abe2).

resolution(_,_,_,_,_,Abe):- not(test_collision(Abe)),
                                    evolue(Abe,Lie1, Lpt1, Li1, Lu1, Ls1),
                                    Lpt1\=[],
                                    deduction_all(Lie1,Lpt1,Li1,Lu1,Ls1,Abe,Abe2),
                                    test_collision(Abe2),
                                    write('Collision exist here!!'),nl.

                                    

%AND
resolution(_,_,_,_,_,Abe):- not(test_collision(Abe)),
                                    evolue(Abe,Lie1, Lpt1, Li1, Lu1, Ls1),
                                    Li1\=[],
                                    tansformation_and(Lie1,Lpt1,Li1,Lu1,Ls1,Abe,Abe2),
                                    nl,nl,write('AND'),nl,nl,
                                    affiche_evolution_Abox([],[],[],[],[],Abe,Lie1,Lpt1,Li1,Lu1,Ls1,Abe2),nl,nl,
                                    not(test_collision(Abe2)),
                                    resolution(_,_,_,_,_,Abe2).

resolution(_,_,_,_,_,Abe):- not(test_collision(Abe)),
                                    evolue(Abe,Lie1, Lpt1, Li1, Lu1, Ls1),
                                    Li1\=[],
                                    tansformation_and(Lie1,Lpt1,Li1,Lu1,Ls1,Abe,Abe2),
                                    test_collision(Abe2),
                                    write('Collision exist here!!'),nl.


%OR
resolution(_,_,_,_,_,Abe):- not(test_collision(Abe)),
                                    evolue(Abe,Lie1, Lpt1, Li1, Lu1, Ls1),
                                    Lu1\=[],
                                    tranformation_or(Lie1,Lpt1,Li1,Lu1,Ls1,Abe,[Abe21,Abe22]),
                                    not(test_collision(Abe21)),
                                    write('previous Abe'),nl,nl,
                                    print_Abox(Abe),nl,nl,
                                    nl,nl,write('Or'),nl,nl,
                                    write('branch 1'),nl,
                                    print_Abox(Abe21),nl,nl,
                                    resolution(_,_,_,_,_,Abe21),
                                    not(test_collision(Abe22)),
                                    nl,nl,nl,write('Go back to branch 2'),nl,
                                    print_Abox(Abe22),nl,nl,
                                    resolution(_,_,_,_,_,Abe22).
  

resolution(_,_,_,_,_,Abe):- not(test_collision(Abe)),
                                    evolue(Abe,Lie1, Lpt1, Li1, Lu1, Ls1),
                                    Lu1\=[],
                                    tranformation_or(Lie1,Lpt1,Li1,Lu1,Ls1,Abe,[Abe21,Abe22]),
                                    test_collision(Abe21),
                                    nl,write('branch 1 collided'),nl,nl,nl,
                                    print_Abox(Abe21),nl,nl,
                                    not(test_collision(Abe22)),
                                    write('branch 2'),nl,
                                    print_Abox(Abe22),nl,nl,
                                    resolution(_,_,_,_,_,Abe22). 
                                   

 
resolution(_,_,_,_,_,Abe):- not(test_collision(Abe)),
                                    evolue(Abe,Lie1, Lpt1, Li1, Lu1, Ls1),
                                    Lu1\=[],
                                    tranformation_or(Lie1,Lpt1,Li1,Lu1,Ls1,Abe,[Abe21,Abe22]),
                                    test_collision(Abe21),
                                    test_collision(Abe22),
                                    write('branch 1 and 2 collided'),nl,
                                    write(Abe22),nl,nl,nl.  



% print the evolution
print_concepte(and(C1,C2)):- 
                           write('('),
                           print_concepte(C1),
                           write(')'),
                           write('  ⊓  '),
                           write('('),
                           print_concepte(C2),
                           write(')').

print_concepte(or(C1,C2)):- write('('),
                           print_concepte(C1),
                           write(')'),
                           write('  ⊔  '),
                           write('('),
                           print_concepte(C2),
                           write(')').

print_concepte(not(C)):- write('¬ '),print_concepte(C).

print_concepte(C):- cnamea(C),write(C).
print_concepte(R):- role(R),write(R).
print_concepte((I1,I2,R)):-write('〈'),write(I1),write(','),write(I2),write('〉'),write(' : '),write(R),nl,!.
print_concepte((I,C)):- write(I),write(' : '),print_concepte(C),nl,!.
print_concepte(some(R,C)):- write('∃ '),write(R),write(': '),print_concepte(C).
print_concepte(all(R,C)):- write('∀ '),write(R),write(': '),print_concepte(C).


print_Abox([]).
print_Abox([A|Abe]):-
                    print_concepte(A),
                    print_Abox(Abe).


affiche_evolution_Abox(_,_,_,_,_,Abe1,_,_,_,_,_,Abe2) :- 
                  write('Evolution from Abox1'),nl,
                    print_Abox(Abe1),
                    nl,nl,write('To Abox2'),nl,
                    print_Abox(Abe2),nl,nl,nl.
