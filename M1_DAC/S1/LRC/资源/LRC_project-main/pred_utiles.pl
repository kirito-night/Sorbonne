/* concatenation */
concat([],L1,L1).
concat([X|Y],L1,[X|L2]) :- concat(Y,L1,L2).


/* generer un nom */
compteur(1).

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


genere(Nom) :- compteur(V),nombre(V,L1),
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




/* enleve un element X de L et retourne L2 */
enleve(X,[X|L],L) :-!.
enleve(X,[Y|L],[Y|L2]) :- enleve(X,L,L2).
