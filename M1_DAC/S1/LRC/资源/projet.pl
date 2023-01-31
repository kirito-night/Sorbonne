:-[init].
:-[utils].
/* ---------------------- Partie 1 ---------------------- */

%autoreference check pour Tbox non circulaire 
error_autoref(A,B) :- writeln("Error : autoreference check failed for "),writeln((A,B)). 
autoref([(C,E)|Tbox]):- autoref(C,E), error_autoref(C,E);autoref(Tbox).
autoref(X,X,_). 
autoref(_,Y,L):- member(Y,L). 
autoref(X,Y,L):- equiv(Y,Z), autoref(X,Z,[Y|L]).
autoref(X,and(Y,Z),L):- autoref(X,Y,L); autoref(X,Z,L).
autoref(X,or(Y,Z),L):- autoref(X,Y,L); autoref(X,Z,L).
autoref(X,some(_,Z),L):- autoref(X,Z,L).
autoref(X,all(_,Z),L):- autoref(X,Z,L).
autoref(X,not(Y),L):- autoref(X,Y,L).
autoref(X,Y):- autoref(X,Y, []).


/* Verification syntaxique et semantique */
concept(I1, I2, R):- iname(I1), iname(I2), rname(R).
concept(C, E):- (cnamena(C); iname(C)), concept(E).
concept(not(C)) :- concept(C),!.
concept(and(C1,C2)) :- concept(C1),concept(C2),!.
concept(or(C1,C2)) :- concept(C1),concept(C2),!.
concept(some(R,C)) :- rname(R),concept(C),!.
concept(all(R,C)) :- rname(R),concept(C),!.
concept(C) :- setof(X,cnamea(X),L),member(C,L),!.
concept(C) :- equiv(C,C2), concept(C2),cnamena(C).


concept_all([], [], []).
concept_all(TBox, ABox1, [(A,B,C)|ABox2]):- concept(A,B,C), concept_all(TBox, ABox1, ABox2),!.
concept_all(TBox,[(A,B)|ABox1], ABox2):- concept(A,B), concept_all(TBox, ABox1, ABox2),!.
concept_all([(A,B)|TBox],ABox1, ABox2):- concept(A,B), concept_all(TBox, ABox1, ABox2),!.


/*TBox :
[(sculpteur,and(personne,some(aCree,sculpture))), (auteur,and(personne,some(aEcrit,livre))), (editeur,and(personne,and(not(some(aEcrit,livre)),some(aEdite,livre)))), (parent,and(personne,some(aEnfant,anything)))]
*/
/*ABox :
  - assertions de concepts :
[(michelAnge,personne), (david,sculpture), (sonnets,livre), (vinci,personne), (joconde,objet)]
  - assertions de rôles :
  [(michelAnge, david, aCree), (michelAnge, sonnet, aEcrit),(vinci, joconde, aCree)]
*/

/* avant de faire les traitement on doit remplacer les  concepts complexe, pour qu'il contient que des concepts atomique */
remplace_complexe(X,X) :-cnamea(X).
remplace_complexe(not(X),not(Y)) :- remplace_complexe(X,Y).
remplace_complexe(and(X,Y),and(Z,W)) :- remplace_complexe(X,Z),remplace_complexe(Y,W).
remplace_complexe(or(X,Y),or(Z,W)) :- remplace_complexe(X,Z),remplace_complexe(Y,W).
remplace_complexe(some(R,X),some(R,Y)) :- remplace_complexe(X,Y).
remplace_complexe(all(R,X),all(R,Y)) :- remplace_complexe(X,Y).
remplace_complexe(X,Y) :- equiv(X,Z), remplace_complexe(Z,Y).

%traitement de TBox
traitement_Tbox([],[]).
traitement_Tbox([(C1,C2)|Tbox],[(C1,C4)|Tbox2]) :- remplace_complexe(C2,C3),nnf(C3,C4) ,traitement_Tbox(Tbox,Tbox2). %remplacement des concepts complexes par des concepts atomiques et mettre sous forme nnf



%traitement de ABox
traitement_Abox([],[]).
traitement_Abox([(A,B)|Abox], [(A,D)|L]):- remplace_complexe(B,C),nnf(C,D),traitement_Tbox(Abox, L).






/* ---------------------- Partie 2 ---------------------- */

tbox(L) :- setof((X,Y),equiv(X,Y),L).
/*  ABI = liste de ( instance, concept) */
abi(L) :- setof((X,Y),inst(X,Y),L).

/*  ABR = liste de ( instance,instance, role) */
abr(L) :- setof((X,Y,Z),instR(X,Y,Z),L).

% premiere_etape(Tbox,Abi,Abr) :- tbox(Tbox), abi(Abi), abr(Abr).
premiere_etape(Tbox, Abi, Abr):-  
	/* On récupere la TBox et la ABox */
    tbox(Tbox_0),
    abi(Abi_0),
    abr(Abr),
	/* On fait les vérifications, correction et traitement */
	not(autoref(Tbox_0)),
	concept_all(Tbox_0, Abi_0, Abr),
	traitement_Tbox(Tbox_0, Tbox),
	traitement_Abox(Abi_0, Abi).

programme :- 
    premiere_etape(Tbox,Abi,Abr),
    deuxieme_etape(Abi,Abi1,Tbox),
    troisieme_etape(Abi1,Abr).

deuxieme_etape(Abi,Abi1,Tbox) :-
    saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox).
  
saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox) :-
    nl, write("Entrez le numero du type de proposition que vous voulez demontrer :"), nl,
    write("1 = Une instance donnee appartient a un concept donne."), nl,
    write("2 = Deux concepts n`ont pas d'elements en commun (ils ont une intersection vide)."), nl,
    read(R), suite(R,Abi,Abi1,Tbox).
  
suite(1,Abi,Abi1,Tbox) :- acquisition_prop_type1(Abi,Abi1,Tbox),!.
suite(2,Abi,Abi1,Tbox) :- acquisition_prop_type2(Abi,Abi1,Tbox),!.
suite(_,Abi,Abi1,Tbox) :- nl, write("Cette reponse est incorrecte"),nl,saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox).

/*lire les Instances  depuis le Terminal*/
lecture_Instance(Instance) :- nl,
    write("Entrez le nom de l`instance :"), nl,
    read(I),
    (iname(I) , I = Instance;
    write("Cette instance n`existe pas"), nl,
    lecture_Instance(Instance)).

/*lire les Concepts depuis le Terminal*/
lecture_Concept(Concept) :- nl,
    write("Entrez le nom du concept :"), nl,
    read(C),
    ((not(autoref(C)),concept(C), Concept=C);
    write("Ce concept n`existe pas"), nl,
    lecture_Concept(Concept)).

acquisition_prop_type1(Abi,[(I,C1)| Abi],_) :- nl, 
    lecture_Instance(I),
    lecture_Concept(C),
    remplace_complexe(C,NC),
    nnf(not(NC),C1),
    write('La proposition a ete ajoutee a la Abox'), nl.

acquisition_prop_type2(Abi,[(I,C)| Abi],_) :- nl,
    lecture_Concept(C1),
    lecture_Concept(C2),
    genere(I),
    remplace_complexe(C1,NC1),
    remplace_complexe(C2,NC2),
    nnf(and(NC1,NC2),C),
    write('La proposition a ete ajoutee a la Abox'), nl.

/* ---------------------- Partie 3 ---------------------- */

troisieme_etape(Abi,Abr) :-
    tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),
    resolution(Lie,Lpt,Li,Lu,Ls,Abr),
    nl,write('Youpiiiiii, on a demontre la
    proposition initiale !!!').


% - la liste Lie des assertions du type (I,some(R,C))
% - la liste Lpt des assertions du type (I,all(R,C))
% - la liste Li des assertions du type (I,and(C1,C2))
% - la liste Lu des assertions du type (I,or(C1,C2))
% - la liste Ls des assertions restantes, à savoir les assertions du type (I,C) ou (I,not(C)), C étant un concept atomique.

/*ri est réalisé avant le processus de résolution pour accélérer la recherche d’une assertion
 susceptible de provoquer l’application de l‘une des 4 règles permettant de développer l’arbre de démonstration.*/
tri_Abox([],[],[],[],[],[]).

/*  parcours de tous les elements du type I : some(R,C) */
tri_Abox([(I,some(R,C))|Abi],[(I,some(R,C))|Lie],Lpt,Li,Lu,Ls) :- tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls).

/*  parcours de tous les elements du type I : all(R,C) */
tri_Abox([(I,all(R,C))|Abi],Lie,[(I,all(R,C))|Lpt],Li,Lu,Ls) :- tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls).

/*  parcours de  tous les elements du type I : and(C1,C2) */
tri_Abox([(I,and(C1,C2))|Abi],Lie,Lpt,[(I,and(C1,C2))|Li],Lu,Ls) :- tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls).

/*  parcours  de tous les elements du type I : or(C1,C2) */
tri_Abox([(I,or(C1,C2))|Abi],Lie,Lpt,Li,[(I,or(C1,C2))|Lu],Ls) :- tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls).

/*  parcours  de tous les elements du type (I : C) ou (I : not(C)) */
tri_Abox([(I,C)|Abi],Lie,Lpt,Li,Lu,[(I,C)|Ls]) :- cnamea(C),tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls).
tri_Abox([(I,not(C))|Abi],Lie,Lpt,Li,Lu,[(I,not(C))|Ls]) :- cnamea(C),tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls).

% une nouvelle assertion de concepts à intégrer dans l’une des listes Lie, Lpt, Li, Lu ou Ls qui décrivent
% les assertions de concepts de la Abox étendue et Lie1, Lpt1, Li1,Lu1 et Ls1 représentent les nouvelles listes mises à jour.
evolue((I,some(R,C)), Lie, Lpt, Li, Lu, Ls, [(I,some(R,C))|Lie], Lpt, Li, Lu, Ls).
evolue((I,all(R,C)), Lie, Lpt, Li, Lu, Ls, Lie, [(I,all(R,C))|Lpt], Li, Lu, Ls).
evolue((I,and(R,C)), Lie, Lpt, Li, Lu, Ls, Lie, Lpt, [(I,and(R,C))|Li], Lu, Ls).
evolue((I,or(R,C)), Lie, Lpt, Li, Lu, Ls, Lie, Lpt, Li, [(I,or(R,C))|Lu], Ls).
evolue((I,C), Lie, Lpt, Li, Lu, Ls, Lie, Lpt, Li, Lu, [(I,C)|Ls]).
evolue((I,not(C)), Lie, Lpt, Li, Lu, Ls, Lie, Lpt, Li, Lu, [(I,not(C))|Ls]).

% evolue_all([], Lie, Lpt, Li, Lu, Ls, Lie, Lpt, Li, Lu, Ls).
% evolue_all([A|L], Lie, Lpt, Li, Lu, Ls, Lie1, Lpt1, Li1, Lu1, Ls1) :-
%     evolue(A, Lie, Lpt, Li, Lu, Ls, Lie2, Lpt2, Li2, Lu2, Ls2),
%     evolue_all(L, Lie2, Lpt2, Li2, Lu2, Ls2, Lie1, Lpt1, Li1, Lu1, Ls1).
 
evolue_all([(I,some(R,C))|Tmp],Lie,Lpt,Li,Lu,Ls,Lie1,Lpt,Li,Lu,Ls) :- concatene([(I,some(R,C))|Tmp],Lie,Lie1).
evolue_all([(I,all(R,C))|Tmp],Lie,Lpt,Li,Lu,Ls,Lie,Lpt1,Li,Lu,Ls) :- concatene([(I,all(R,C))|Tmp],Lpt,Lpt1).
evolue_all([(I,and(R,C))|Tmp],Lie,Lpt,Li,Lu,Ls,Lie,Lpt,Li1,Lu,Ls) :- concatene([(I,and(R,C))|Tmp],Li,Li1).
evolue_all([(I,or(R,C))|Tmp],Lie,Lpt,Li,Lu1,Ls,Lie,Lpt,Li,Lu,Ls) :- concatene([(I,or(R,C))|Tmp],Lu,Lu1).
evolue_all([(I,C)|Tmp],Lie,Lpt,Li,Lu,Ls,Lie,Lpt,Li,Lu,Ls1) :- concatene([(I,C)|Tmp],Ls,Ls1).


/* test si y'a un clash */
clash_test([(A,B)|L]):- nnf(not(B),E), member((A,E),L), write("\t\tClash\n\n").
clash_test([_|L]):- clash_test(L). 

resolution([],[],[],[],Ls,_) :- clash_test(Ls),!.
resolution(Lie,Lpt,Li,Lu,Ls,Abr) :- 
    (clash_test(Ls);
    complete_some(Lie,Lpt,Li,Lu,Ls,Abr);
    transformation_and(Lie, Lpt,Li,Lu,Ls,Abr);
    deduction_all(Lie,Lpt,Li,Lu,Ls,Abr);
    transformation_or(Lie,Lpt,Li,Lu,Ls,Abr)),!.





complete_some([(A,some(R,C)) | Lie],Lpt,Li,Lu,Ls,Abr) :- 
    genere(B),
    evolue((B,C),Lie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1),
    write("\n evolution :\n"),
    affiche_evolution_Abox(Lie,Lpt,Li,Lu,Ls,Abr,Lie1,Lpt1,Li1,Lu1,Ls1,[(A,B,R)|Abr]),nl,
    !,resolution(Lie1,Lpt1,Li1,Lu1,Ls1,[(A,B,R)|Abr]),!.

transformation_and(Lie,Lpt,[(A,and(C,D))|Li],Lu,Ls,Abr) :- 
    evolue((A,C),Lie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1),
    evolue((A,D),Lie1,Lpt1,Li1,Lu1,Ls1,Lie2,Lpt2,Li2,Lu2,Ls2),
    write("\n evolution :\n"),
    affiche_evolution_Abox(Lie,Lpt,Li,Lu,Ls,Abr,Lie2,Lpt2,Li2,Lu2,Ls2,Abr),nl,!,resolution(Lie2,Lpt2,Li2,Lu2,Ls2,Abr),!.

deduction_all(Lie,[(A,all(R,C))|Lpt],Li,Lu,Ls,Abr) :- 
    setof((Y,C),
    member((A,Y,R),Abr),Tmp),
    evolue_all(Tmp,Lie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1),
    write("\nevolution :\n"),
    affiche_evolution_Abox(Lie,Lpt,Li,Lu,Ls,Abr,Lie1,Lpt1,Li1,Lu1,Ls1,Abr),nl,!,resolution(Lie1,Lpt1,Li1,Lu1,Ls1,Abr),!.


transformation_or(Lie,Lpt,Li,[(A,or(C,D))|Lu],Ls,Abr) :- 
    evolue((A,C),Lie,Lpt,Li,Lu,Ls,Lie1,Lpt1,Li1,Lu1,Ls1),
    evolue((A,D),Lie,Lpt,Li,Lu,Ls,Lie2,Lpt2,Li2,Lu2,Ls2),
    write("\nevolution or branche 1:\n"),
    affiche_evolution_Abox(Lie,Lpt,Li,Lu,Ls,Abr,Lie1,Lpt1,Li1,Lu1,Ls1,Abr),nl,
    write("\nevolution or branche 2:\n"),
    affiche_evolution_Abox(Lie,Lpt,Li,Lu,Ls,Abr,Lie2,Lpt2,Li2,Lu2,Ls2,Abr),nl,!,
    resolution(Lie1,Lpt1,Li1,Lu1,Ls1,Abr),!,resolution(Lie2,Lpt2,Li2,Lu2,Ls2,Abr),!.


/* Affiche evolution Abox */
affiche_evolution_Abox(Ls1, Lie1, Lpt1, Li1, Lu1, Abr1, Ls2, Lie2, Lpt2, Li2, Lu2, Abr2):-
	write("\t---------------------------------"),
	affiche([Ls1, Lie1, Lpt1, Li1, Lu1, Abr1]),
	write("\t---------------------------------"),nl,
	write("\t|\n"),
	write("\t|\n"),
	write("\t---------------------------------"),
	affiche([ Ls2, Lie2,Lpt2, Li2, Lu2, Abr2]),
	write("\t---------------------------------"),
	nl,nl,!.


/* Affiche l'état de la Abox étendue*/
affiche([]):- nl.
affiche([L1 | L]):-
	afficheAbox_all(L1), affiche(L).

/* Affiche une liste d'assertion*/
afficheAbox_all([]).
afficheAbox_all([(A,C)|L]):- nl,
	(cnamena(A) ,writef('\t| %w ≡ ', [A]));
	(writef('\t| %w : ', [A])),
	afficheAbox(C),
	afficheAbox_all(L).

afficheAbox_all([(A,B,R)|L]):- nl,
	writef('\t| <%w , %w> : %w', [A,B,R]),
	afficheAbox_all(L).

/* Affiche une assertion */
afficheAbox(and(C1,C2)):-
	afficheAbox(C1),
	write(" ⊓ "),
	afficheAbox(C2).

afficheAbox(or(C1,C2)):-
	afficheAbox(C1),
	write(" ⊔ "),
	afficheAbox(C2).

afficheAbox(all(R,C)):-
	writef('∀%w.',[R]),afficheAbox(C).

afficheAbox(some(R,C)):-
	writef('∃%w.',[R]), afficheAbox(C).

afficheAbox(not(C)):-
	write('¬ '), afficheAbox(C).

afficheAbox(C):- write(C).
	