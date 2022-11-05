# TME3 ex1
r(a,b).
r(f(X),Y) :- p(X,Y).
p(f(X),Y) :- r(X,Y).

?-r(f(f(a)),b) #true
?-p(f(a),b).#true

# TME3 ex2
r(a,b).
q(X,X).
q(X,Z) :- r(X,Y),q(Y,Z).

?-q(X,b) #true X=b , X=a 
q(b,X). # true X = b 

# TME3 ex3
reviser(X) :- serieux(X).
devoirs(X) :- consciencieux(X).
reussir(X) :- reviser(X).
serieux(X) :- devoirs(X).
consciencieux(pascal).
consciencieux(zoe).
# La requete qui permet de répondre à la question "Qui va réussir?" est ?- reussir(X)
# On obtient X = pascal et X = zoe

# TME3 ex4
pere(berthe,charlemagne).
mere(berthe,charlemagne).
#parent/2 , pere/2 , mere/2.
mere(berthe,charlemagne).

parent(X,Y) :- pere(X,Y).
parent(X,Y) :- mere(X,Y).
# Test : 
# parent(X, charlemagne) donne X = pepin et X = berthe
# parent(charlemagne, X) donne false
# parent(pepin, Y) donne Y = charlamagne
# parent(A,B) donne A = pepin, B = charlemagne et A = berthe, B = charlemagne

#parents/3.
parents(X,Y,Z) :- pere(X,Z), mere(Y,Z).
# Test : 
# parents(pepin,Y,Z) donne Y = berthe et Z = charlemagne 

pere(charlemagne, coco)
#grandpere/2.
grandpere(X,Y) :- pere(X,Z), parent(Z,Y).
# Test : 
# granpere(pepin,X) donne X = coco
# granpere(X, coco) donne X = pepin
# granpere(X, charlemagne) donne false 

pere(pepin, bro).
#frereOUsoeur/2
frereOUsoeur(X,Y) :- parent(Z,X), parent(Z,Y), X\=Y.
# Test :
# frereOUsoeur(X,Y) donne X = charlemagne, Y = bro et X = bro, Y = Charlemagne
# frereOUsoeur(X,charlemagne) donne X = bro
# frereOUsoeur(berthe,pepin) donne false

#ancetre/2
ancetre(X,Y) :- parent(X,Y).
ancetre(X,Y) :- parent(Z,Y) ,ancetre(X ,Z).
# Test : 
# ancetre(X,coco) donne X = charlemagne, X = pepin, X = berthe

# TME3 ex5
#et/3, ou/3, non/2.

and(1,1,1).
and(0,1,0).
and(1,0,0).
and(0,0,0).
or(1,0,1).
or(0,1,1).
or(1,1,1).
or(0,0,0).
non(0,1).
non(1,0).
xor(0,0,0).
xor(0,1,1).
xor(1,0,1).
xor(1,1,0).
# Test : 
# et(X,Y,1) donne X = Y, Y = 1
# et(0,0,R) donne R = 0
# et(X,Y,R) donne X = Y, Y = 1, R = X
#                 X = 1, Y = 0, R = Y
#                 X = 0, Y = 1, R = X
#                 X = Y, Y = 0, R = X

#circuit/3
circuit(X,Y,Z) :- and(X,Y,A), non(A,B), non(X,C), xor(B,C,D) non(D,Z).

# La requête permettant de construire la table de vérité du circuit est : circuit(X,Y,Z)
# cela donne : 
# X = 0, Y = Z, Z = 1
# X = 1, Y = Z, Z = 0
# X = Y, Y = 0, Z = 1 
# X = Y, Y = Z, Z = 1

# TME4 ex1
?- [a,[b,c],d] = [X].
# donne false
?- [a,[b,c],d] = [X,Y,Z].
# donne X = a, Y = [b,c], Z = d
?- [a,[b,c],d] = [a|L].
# donne L = [[b,c],d]
?- [a,[b,c],d] = [X,Y].
# donne false
?- [a,[b,c],d] = [X|Y].
# donne X = a, Y = [[b,c],d]
?- [a,[b,c],d] = [a,b|L].
# donne false
?- [a,b,[c,d]] = [a,b|L].
# donne L = [c,d]
?- [a,b,c,d|L1] = [a,b|L2].
# donne L2 = [c,d,L1]

# TME4 ex2
# append/3, delete/3, reverse/3
# concatene/3
concatene([],Y,Y).
concatene([T|LQ],LY,LZ) :- concatene(LQ,LY,LZ2),LZ=[T|LZ2].
#ou correction : 
concatene([],L2,L2)
concatene([T|LQ],L2,[T|L4]) :- concatene(LQ,L2,L4)
# Test : 
# concatene([a],[b],[a,b]) donne true
# concatene([a,b,c],[d],L2) donne L2 = [a,b,c,d]

# inverse/2
inverse([],[]).
inverse([T|LQ],LZ) :- inverse(LQ,LZ2),concatene(LZ2,[T],LZ).
# Test : 
# inverse([a,b],[b,a]) donne true
# inverse([a,b,c,d],L2) donne L2 = [d,c,b,a]

#supprime/3
supprime([],_,[]).
supprime([T|LQ],X,LZ) :- supprime(LQ,X,LZ2), (T=X -> LZ=LZ2 ; LZ=[T|LZ2]).

# Test : 
# supprime([a,b,c],b,[a,c]) donne true
# supprime([a,b,a,c],a,L) donne L = [b,c]

# filtre/3
filtre(L,[],L).
filtre(L,[X|Y], Z) :- supprime(L,X,L2), filtre(L2,Y,Z).
# Test : 
# filtre([2,4,2,4,1,3],[2,4],[1,3]) donne true
# filtre([1,2,3,4,2,3,4,2,4,1],[2,4],L) donne L = [1,3,3,1]
# filtre([2,4,2,4],[2,4],[]) donne true

# TME4 ex3
# palindrome/1
palindrome(L) :- inverse(L,L).
# Test :
# palindrome([a,b,c,b,a]) donne true
# palindrome([b,a,v,a,l]) donne false

# palindrome2/1
