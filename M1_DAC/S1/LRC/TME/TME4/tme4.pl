concatene(L,[],L).
concatene([],L,L).
concatene([X|L1],L2,[X|L3]) :- concatene(L1,L2,L3).

inverse([],[]).
inverse([X|L],L1) :- inverse(L,L2), concatene(L2,[X],L1).

supprime([],_,[]).
supprime(L,Y,Z):-L=[X|L1],X=Y,supprime(L1,Y,Z2),concatene([],Z2,Z).
supprime(L,Y,Z):-L=[X|L1],X\=Y,supprime(L1,Y,Z2),concatene([X],Z2,Z).

filtre(L,[],L).
filtre(L,[X|L1],Z):-supprime(L,X,Z2),filtre(Z2,L1,Z).

palindrome(L):-inverse(L,L).
palindrome([]).
palindrome([_]).
palindrome(L):-concatene([X|L1],[X],L),palindrome(L1).








