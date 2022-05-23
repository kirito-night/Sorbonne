% Règles
%% Tous les insectes sont des animaux
% animal/1
% insecte/1
% mammifere/1
% chat/0

animal(X) :- insecte(X).
   
%% Tous les mammifères sont des animaux
animal(X) :- mammifere(X).

%% Tous les félins sont des mammifères
mammifere(X) :- felin(X).


% Faits
%% Fourmi est un insecte

insecte(fourmi).

%% Chat est un félin
felin(chat).

felin(chien).




% Requetes (ajouter a la main)
%?- animal(chat).

