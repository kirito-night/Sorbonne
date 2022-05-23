p(Z, h(Z, W), f(W)) :- r(X, XX), q(XX).
q(a).
r(b,a).
?- p(f(X), h(Y, a), Y), q(a).