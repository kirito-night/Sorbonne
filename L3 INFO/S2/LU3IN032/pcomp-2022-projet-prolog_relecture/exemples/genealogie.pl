% Généalogie des doctorant.e.s de Church

% Church
%   |
%   +-------------+----------+---------.....
%   |             |          |
% Kleene       Turing      Scott
%                 |
%   +-------------+----------+
%   |             |          |
% Gandy       Routledge   Worsley
%

% supervisor/2
% descendant/2

supervisor(church, kleene).
supervisor(church, turing).
supervisor(church, scott).
supervisor(turing, gandy).
supervisor(turing, routledge).
supervisor(turing, worsley).

descendant(X, Y) :- supervisor(Y, X).
descendant(X, Y) :- supervisor(Y, Z), descendant(X, Z). 



?- descendant(X,church),supervisor(church,Y).