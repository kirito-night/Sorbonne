% Prolog a un type de données listes, similaire au type liste de Caml
% [X, Y, Z] désigne la liste à trois éléments
% [X | Y] désigne une liste dont l'élément de tête est X et la liste queue est Y
% Ici on se propose de définir les opérations de bases sur les listes
% Une liste est soit vide, soit la concaténation d'une tête et d'une liste.

list(empty).
list(concat(X, Xs)) :- list(Xs).


% element/2 element(X,Y) verifie si X est un element de la liste Y
element(X, concat(X,Xs)).
element(X, concat(Y,Ys)) :- element(X, Ys).

% prefix/2 prefix(X, Y) vérifie si X est une liste prefixe de la liste Y
prefix(empty, Ys).
prefix(concat(X,Xs), concat(X,Ys)) :- prefix(Xs,Ys).

% substitute/4 substitute(X, Y, L1, L2) si L2 est le résultat de la substitution dans L1 de toutes les occurences de X par Y
substitute(X, Y, empty, empty).
substitute(X, Y, concat(X,Xs), concat(Y,Ys)) :- substitute(X, Y, Xs, Ys).
substitute(X, Y, concat(Z,Xs), concat(Z,Ys)) :- substitute(X, Y, Xs, Ys).

			  
