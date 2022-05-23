% R�gles
%% Tous les insectes sont des animaux
% animal/1
% insecte/1
% mammifere/1
% chat/0

animal(X) :- insecte(X).
   
%% Tous les mammif�res sont des animaux
animal(X) :- mammifere(X).

%% Tous les f�lins sont des mammif�res
mammifere(X) :- felin(X).


% Faits
%% Fourmi est un insecte

insecte(fourmi).

%% Chat est un f�lin
felin(chat).




  
?- animal(X), ,mammifere(Y).
