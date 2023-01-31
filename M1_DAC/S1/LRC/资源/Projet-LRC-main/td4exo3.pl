/* PROJET LRC - M1 DAC */
/* ESTHER CHOI - GROUPE 1 */

/* PARTIE 1 : PRELIMINAIRES */

equiv(sculpteur,and(personne,some(aCree,sculpture))).
equiv(auteur,and(personne,some(aEcrit,livre))).
equiv(editeur,and(personne,and(not(some(aEcrit,livre)),some(aEdite,livre)))).
equiv(parent,and(personne,some(aEnfant,anything))).

cnamea(personne).
cnamea(livre).
cnamea(objet).
cnamea(sculpture).
cnamea(anything).
cnamea(nothing).

cnamena(auteur).
cnamena(editeur).
cnamena(sculpteur).
cnamena(parent).

iname(michelAnge).
iname(david).
iname(sonnets).
iname(vinci).
iname(joconde).

rname(aCree).
rname(aEcrit).
rname(aEdite).
rname(aEnfant).

inst(michelAnge,personne).
inst(david,sculpteur).
inst(sonnets,livre).
inst(vinci,personne).
inst(joconde,objet).

instR(michelAnge,david,aCree).
instR(michelAnge,sonnets,aEcrit).
instR(vinci,joconde,aCree).

/*TBox :
[(sculpteur,and(personne,some(aCree,sculpture))), (auteur,and(personne,some(aEcrit,livre))), (editeur,and(personne,and(not(some(aEcrit,livre)),some(aEdite,livre)))), (parent,and(personne,some(aEnfant,anything)))]
*/

/*ABox :
  - assertions de concepts :
[(michelAnge,personne), (david,sculpture), (sonnets,livre), (vinci,personne), (joconde,objet)]
  - assertions de rôles :
  [(michelAnge, david, aCree), (michelAnge, sonnet, aEcrit),(vinci, joconde, aCree)]
*/
