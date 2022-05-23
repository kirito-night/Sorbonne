insecte(fourmi).
animal(X) :- mammifere(X).
mammifere :- felin(X).
felin(chat).

?- felin(X), insecte(X).