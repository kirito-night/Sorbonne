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

instR(michelAnge, david, aCree).
instR(michelAnge, sonnets, aEcrit).
instR(vinci, joconde, aCree).


/* Prédicat nnf */
nnf(not(and(C1,C2)),or(NC1,NC2)):- nnf(not(C1),NC1), nnf(not(C2),NC2),!.
nnf(not(or(C1,C2)),and(NC1,NC2)):- nnf(not(C1),NC1), nnf(not(C2),NC2),!.
nnf(not(all(R,C)),some(R,NC)):- nnf(not(C),NC),!.
nnf(not(some(R,C)),all(R,NC)):- nnf(not(C),NC),!.
nnf(not(not(X)),X):-!.
nnf(not(X),not(X)):-!.
nnf(and(C1,C2),and(NC1,NC2)):- nnf(C1,NC1),nnf(C2,NC2),!.
nnf(or(C1,C2),or(NC1,NC2)):- nnf(C1,NC1), nnf(C2,NC2),!.
nnf(some(R,C),some(R,NC)):- nnf(C,NC),!.
nnf(all(R,C),all(R,NC)) :- nnf(C,NC),!.
nnf(X,X).

/*  __________________ Partie 1 ______________________ 
	__________________________________________________ */

/* autoref */
/* error: signale une erreur de définition de concepts circulaires */
error(A,B):- nl,
	write('Erreur: TBox contient concepts auto-référentes'),nl,
	write('ConceptNonAtom: '),
	write(A),nl,
	write('ConceptGen: '),
	write(B),nl.


autoref([(C,E)|Tbox]):- autoref(C,E), error(C,E);autoref(Tbox).

autoref(X,X,_). 
autoref(_,Y,L):- member(Y,L). /* "On vérifie si Y est dans L, ca veut dire qu'on a bouclé sur Y"*/
autoref(X,Y,L):- equiv(Y,Z), autoref(X,Z,[Y|L]).
autoref(X,and(Y,Z),L):- autoref(X,Y,L); autoref(X,Z,L).
autoref(X,or(Y,Z),L):- autoref(X,Y,L); autoref(X,Z,L).
autoref(X,some(_,Z),L):- autoref(X,Z,L).
autoref(X,all(_,Z),L):- autoref(X,Z,L).
autoref(X,not(Y),L):- autoref(X,Y,L).
autoref(X,Y):- autoref(X,Y, []).

/* Prédicat: concept */
/* Correction syntaxique et sémantique de la Tbox et ABox2 */
concept_all([], [], []).
concept_all(TBox, ABox1, [(A,B,C)|ABox2]):- concept(A,B,C), concept_all(TBox, ABox1, ABox2),!.
concept_all(TBox,[(A,B)|ABox1], ABox2):- concept(A,B), concept_all(TBox, ABox1, ABox2),!.
concept_all([(A,B)|TBox],ABox1, ABox2):- concept(A,B), concept_all(TBox, ABox1, ABox2),!.

/* Correction sur une assertion */
concept(I1, I2, R):- iname(I1), iname(I2), rname(R). /* Assertation de rôle */
concept(C, E):- (cnamena(C); iname(C)), concept(E). /* TBox et Assertation de concept */
concept(E):- cnamea(E);cnamena(E). /* E un concept atomique ou non atomique */
concept(not(E)):- concept(E).
concept(and(E1,E2)):- concept(E1),concept(E2).
concept(or(E1,E2)):- concept(E1),concept(E2).
concept(some(R,E)):- rname(R),concept(E).
concept(all(R,E)):- rname(R),concept(E).

remplace_Complexe(X,X):- cnamea(X).
remplace_Complexe(not(X), not(E)):- remplace_Complexe(X, E).
remplace_Complexe(and(X1, X2), and(E1,E2)):- remplace_Complexe(X1, E1),remplace_Complexe(X2,E2).
remplace_Complexe(or(X1, X2), or(E1,E2)):- remplace_Complexe(X1, E1),remplace_Complexe(X2, E2).
remplace_Complexe(some(R,X), some(R,E)):- remplace_Complexe(X, E).
remplace_Complexe(all(R,X), all(R,E)):- remplace_Complexe(X, E).
remplace_Complexe(X,Y):- equiv(X,Z), remplace_Complexe(Z,Y).

/* Traitement_Tbox */
traitement_Tbox([],[]).
traitement_Tbox([(A,B)|Tbox], [(A,D)|L]):- remplace_Complexe(B,C),nnf(C,D),traitement_Tbox(Tbox, L).

/* Traitement_Abox */
traitement_Abox([],[]).
traitement_Abox([(A,B)|Abox], [(A,D)|L]):- remplace_Complexe(B,C),nnf(C,D),traitement_Tbox(Abox, L).


/*  __________________ Partie 2 ______________________ 
	__________________________________________________ */

programme :- premiere_etape(Tbox, Abi, Abr),
			deuxieme_etape(Abi, Abi1, Tbox),
			troisieme_etape(Abi1, Abr).

premiere_etape(Tbox, Abi, Abr):-  
	/* On récupere la TBox et la ABox */
	setof((A,B),equiv(A,B), Tbox_0), 
	setof((C,D), inst(C,D), Abi_0),
	setof((E,F,G), instR(E,F,G), Abr),
	/* On fait les vérifications, correction et traitement */
	not(autoref(Tbox_0)),
	concept_all(Tbox_0, Abi_0, Abr),
	traitement_Tbox(Tbox_0, Tbox),
	traitement_Abox(Abi_0, Abi).


deuxieme_etape(Abi, Abi1, Tbox):- 
	saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox).


saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox) :- nl,
	write('Entrez le numero du type de proposition que vous voulez demontrer :'),nl,
	write('1 Une instance donnee appartient a un concept donne.'),nl, 
	write('2 Deux concepts n ont pas d elements en commun.'), nl,
	read(R),
	suite(R,Abi,Abi1,Tbox).


suite(1,Abi,Abi1,Tbox) :-
    acquisition_prop_type1(Abi,Abi1,Tbox),!.
suite(2,Abi,Abi1,Tbox) :-
    acquisition_prop_type2(Abi,Abi1,Tbox),!.
suite(_,Abi,Abi1,Tbox) :- nl,
	write('Cette reponse est incorrecte.'),nl,
    saisie_et_traitement_prop_a_demontrer(Abi,Abi1,Tbox).

/* Saisie de concept */
saisieConcept(Concept):- nl,
	write('Saisissez le concept'),nl, 
	read(C),
	(((not(autoref(C)),concept(C),Concept=C));  /* On vérifie si c est un concept correct, sinon on rappelle le prédicat */
	(nl, write('Concept saisie incorrecte.'),saisieConcept(Concept))).

/* Saisie de l'instance */
saisieInstance(Instance):- nl,
	write('Saisissez l"instance'),nl, 
	read(I),
	(((iname(I),I=Instance)); /* On vérifie si c est une instance, sinon on rappelle le prédicat */
	(nl, write('Instance saisie incorrecte.'),saisieInstance(Instance))).

/* acquisition_prop_type1 */
acquisition_prop_type1(Abi,[(I,E)|Abi],_):- nl,
	write('Proposition de type I:'),nl, 
	saisieInstance(I),
	saisieConcept(C),
	remplace_Complexe(C,S),
	nnf(not(S), E).

/* acquisition_prop_type2 */
acquisition_prop_type2(Abi,[(I,E)|Abi],_):- nl,
	write('Proposition de type II:'),nl, 
	saisieConcept(C1),
	saisieConcept(C2),
	remplace_Complexe(C1,S1),
	remplace_Complexe(C2,S2),
	genere(I),
	nnf((and(S1,S2)), E).	



/*  __________________ Partie 3 ______________________ 
	__________________________________________________ */

troisieme_etape(Abi,Abr) :-
                            tri_Abox(Abi,Lie,Lpt,Li,Lu,Ls),
							nl, write("Etat initial: "), nl,
							write("\t---------------------------------"),
							affiche([Abr,Lie,Lpt,Li,Lu,Ls]),
							write("\t---------------------------------"), nl, nl,!,
                            resolution(Lie,Lpt,Li,Lu,Ls,Abr),
                            nl,write('Youpiiiiii, on a demontre la
                            proposition initiale !!!').

/* tri_Abox */
tri_Abox([],[],[],[],[],[]).
tri_Abox([(I,some(R,C))|Abi], [(I,some(R,C))|L1],L2,L3,L4,L5) :- tri_Abox(Abi,L1,L2,L3,L4,L5).
tri_Abox([(I,all(R,C))|Abi],  L1,[(I,all(R,C))|L2],L3,L4,L5) :- tri_Abox(Abi,L1,L2,L3,L4,L5).
tri_Abox([(I,and(C1,C2))|Abi],L1,L2,[(I,and(C1,C2))|L3],L4,L5) :- tri_Abox(Abi,L1,L2,L3,L4,L5).
tri_Abox([(I,or(C1,C2))|Abi], L1,L2,L3,[(I,or(C1,C2))|L4],L5) :- tri_Abox(Abi,L1,L2,L3,L4,L5).
tri_Abox([(I,C)|Abi],         L1,L2,L3,L4,[(I,C)|L5]) :- tri_Abox(Abi,L1,L2,L3,L4,L5).
tri_Abox([(I,not(C))|Abi],    L1,L2,L3,L4,[(I,not(C))|L5]) :- tri_Abox(Abi,L1,L2,L3,L4,L5).



/* resolution */


resolution(Lie, Lpt, Li, Lu, Ls, Abr):- 
	(test_clash_all(Lie, Lpt, Li, Lu, Ls);
	complete_some(Lie, Lpt, Li, Lu, Ls, Abr);
	transformation_and(Lie,Lpt,Li,Lu,Ls,Abr);
	deduction_all(Lie,Lpt,Li,Lu,Ls,Abr);
	transformation_or(Lie,Lpt,Li,Lu,Ls,Abr)),!.

resolution([],[],[],[],_,_):-
	write("Aucune règle applicable"),nl,!,false.

/* evolue */	
evolue((I, some(R,C)),L1,L2,L3,L4,L5, [(I, some(R,C))|L1], L2,L3,L4,L5).
evolue((I, all(R,C)), L1,L2,L3,L4,L5, L1,[(I,all(R,C))|L2],L3,L4,L5).
evolue((I,and(C1,C2)),L1,L2,L3,L4,L5, L1,L2,[(I,and(C1,C2))|L3],L4, L5).
evolue((I,or(C1,C2)), L1,L2,L3,L4,L5, L1,L2,L3,[(I,or(C1,C2))|L4],L5).
evolue((I,not(C)),    L1,L2,L3,L4,L5, L1,L2,L3,L4,[(I,not(C))|L5]).
evolue((I,C),         L1,L2,L3,L4,L5, L1,L2,L3,L4,[(I,C)|L5]).

/* evolue amélioré pour plusieurs assertions  */
evolue_all([(I, some(R,C))|L],L1,L2,L3,L4,L5, Res, L2,L3,L4,L5):- concat([(I, some(R,C))|L], L1, Res).
evolue_all([(I, all(R,C))|L], L1,L2,L3,L4,L5, L1,Res,L3,L4,L5):- concat([(I, all(R,C))|L], L2, Res).
evolue_all([(I,and(C1,C2))|L],L1,L2,L3,L4,L5, L1,L2,Res,L4, L5):-concat([(I, and(C1,C2))|L], L3, Res).
evolue_all([(I,or(C1,C2))|L], L1,L2,L3,L4,L5, L1,L2,L3,Res,L5):- concat([(I, or(C1,C2))|L], L4, Res).
evolue_all([(I,not(C))|L],    L1,L2,L3,L4,L5, L1,L2,L3,L4,Res):- concat([(I,not(C))|L], L5, Res).
evolue_all([(I,C)|L],         L1,L2,L3,L4,L5, L1,L2,L3,L4,Res):- concat([(I,C)|L], L5, Res).

/* test_clash */
test_clash([(A,B)|L]):- nnf(not(B),E), member((A,E),L), write("\t\tClash\n\n").
test_clash([_|L]):- test_clash(L). 

test_clash_all(L1,L2,L3,L4,L5):-
	(test_clash(L1);
	test_clash(L2);
	test_clash(L3);
	test_clash(L4);	
	test_clash(L5)),!.

/* 1. complete_some */
complete_some([(I,some(R,C))|Lie], Lpt, Li, Lu, Ls, Abr):-
	write("On applique la règle ∃: "), nl,
	genere(Objet),
	evolue((Objet,C), Lie, Lpt, Li, Lu, Ls, L1, L2, L3, L4, L5),
	affiche_evolution_Abox(Ls, [(I,some(R,C))|Lie], Lpt, Li, Lu, Abr, L1, L2, L3, L4, L5, [(I,Objet,R)|Abr]),!,
	resolution(L1, L2, L3, L4, L5, [(I,Objet,R)|Abr]).


/* 2. deduction_all */
deduction_all(Lie,[(I,all(R,C))|Lpt],Li,Lu,Ls,Abr):-
	setof((B,C), member((I,B,R),Abr), LB),
	write(LB),nl,
	write("On applique la règle ∀: "), nl,
	evolue_all(LB, Lie, Lpt, Li, Lu, Ls, L1, L2, L3, L4, L5),
	affiche_evolution_Abox(Ls, [(I,all(R,C))|Lpt], Lpt, Li, Lu, Abr, L1, L2, L3, L4, L5, Abr),!,
	resolution(L1, L2, L3, L4, L5, Abr).


/* 3. transformation_and */
transformation_and(Lie,Lpt,[(I,and(C1,C2))|Li],Lu,Ls,Abr):-
	write("On applique la règle ⊓: "), nl,
	evolue((I,C1), Lie, Lpt, Li, Lu, Ls, L1, L2, L3, L4, L5),
	evolue((I,C2), L1, L2, L3, L4, L5, L6, L7, L8, L9, L10),
	affiche_evolution_Abox(Ls, Lie, Lpt, [(I,and(C1,C2))|Li], Lu, Abr, L1, L2, L3, L4, L5, Abr),!,
	resolution(L6, L7, L8, L9, L10, Abr),!.


/* 4. transformation_or */
transformation_or(Lie,Lpt, Li, [(I,or(C1,C2))|Lu],Ls,Abr):-
	write("On applique la règle ⊔: "), nl,
	evolue((I,C1), Lie, Lpt, Li, Lu, Ls, L1, L2, L3, L4, L5),
	evolue((I,C2), Lie, Lpt, Li, Lu, Ls, L6, L7, L8, L9, L10),
	affiche_evolution_Abox(Ls, Lie, Lpt, Li, [(I,or(C1,C2))|Lu], Abr, L1, L2, L3, L4, L5, Abr),!,
	resolution(L1, L2, L3, L4, L5, Abr),
	affiche_evolution_Abox(Ls, Lie, Lpt, Li, [(I,or(C1,C2))|Lu], Abr, L6, L7, L8, L9, L10, Abr),
	resolution(L6, L7, L8, L9, L10, Abr).

/* Affiche evolution Abox */
affiche_evolution_Abox(Ls1, Lie1, Lpt1, Li1, Lu1, Abr1, Ls2, Lie2, Lpt2, Li2, Lu2, Abr2):-
	write("\t---------------------------------"),
	affiche([Ls1, Lie1, Lpt1, Li1, Lu1, Abr1]),
	write("\t---------------------------------"),nl,
	write("\t\t|\n"),
	write("\t\t|\n"),
	write("\t---------------------------------"),
	affiche([ Ls2, Lie2,Lpt2, Li2, Lu2, Abr2]),
	write("\t---------------------------------"),
	nl,nl,!.


/* Affiche l'état de la Abox étendue*/
affiche([]):- nl.
affiche([L1 | L]):-
	expression_all(L1), affiche(L).

/* Affiche une liste d'assertion*/
expression_all([]).
expression_all([(A,C)|L]):- nl,
	(cnamena(A) ,writef('\t| %w ≡ ', [A]));
	(writef('\t| %w : ', [A])),
	expression(C),
	expression_all(L).

expression_all([(A,B,R)|L]):- nl,
	writef('\t| <%w , %w> : %w', [A,B,R]),
	expression_all(L).

/* Affiche une assertion */
expression(and(C1,C2)):-
	expression(C1),
	write(" ⊓ "),
	expression(C2).

expression(or(C1,C2)):-
	expression(C1),
	write(" ⊔ "),
	expression(C2).

expression(all(R,C)):-
	writef('∀%w.',[R]),expression(C).

expression(some(R,C)):-
	writef('∃%w.',[R]), expression(C).

expression(not(C)):-
	write('¬ '), expression(C).

expression(C):- write(C).
	
/*  __________________ ANNEXE ________________________ 
	__________________________________________________ */

/* Concaténation de 2 listes */
concat([],L1,L1).
concat([X|Y],L1,[X|L2]) :- concat(Y,L1,L2).

/* enleve(X, L1, L2) */
enleve(X,[X|L],L) :-!.
enleve(X,[Y|L],[Y|L2]) :- enleve(X,L,L2).

/* genere(Nom) : génère un nouvel identificateur qui est fourni en sortie dans Nom. */
compteur(1).
genere(Nom) :-  compteur(V),nombre(V,L1),
				concat([105,110,115,116],L1,L2),
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