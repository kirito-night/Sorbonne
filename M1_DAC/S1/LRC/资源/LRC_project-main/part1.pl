
/*  ----PARTIE 1---- */

/*  TBOX = liste de ( concept Atomique, concept generique) */
tbox(L) :- setof((X,Y),equiv(X,Y),L).

/*  ABI = liste de ( instance, concept) */
abi(L) :- setof((X,Y),inst(X,Y),L).

/*  ABR = liste de ( instance,instance, role) */
abr(L) :- setof((X,Y,Z),instR(X,Y,Z),L).

premiere_etape(Tbox,Abi,Abr) :- tbox(Tbox), abi(Abi), abr(Abr).


