vote(X,Y) :- majeur(X),nationalite(X,Y).
majeur(X) :- personne(X).
pays(france).
nationalite(X,Y) :- personne(X), pays(Y).
personne(camille).

?- vote(X,Y).