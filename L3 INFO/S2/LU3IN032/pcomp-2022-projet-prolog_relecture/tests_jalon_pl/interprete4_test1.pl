p(X) :- q(X).
p(X) :- r(X).
q(a).
r(b).

?- p(b),p(a).