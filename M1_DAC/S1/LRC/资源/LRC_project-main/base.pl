/*Exo 3 TD 4 */

/* A-BOX */ 

/* idf des instances */

iname(michelAnge).
iname(david).
iname(sonnets).
iname(vinci).
iname(joconde).
/*iname(monalisa).*/

/* idf des roles */

rname(aCree).
rname(aEcrit).
rname(aEdite).
rname(aEnfant).

/* concepts atomiques */ 

cnamea(personne).
cnamea(livre).
cnamea(objet).
cnamea(sculpture).
cnamea(anything).
cnamea(nothing).


/* concepts non atomiques */

cnamena(auteur).
cnamena(editeur).
cnamena(sculpteur).
cnamena(parent).

/* instanciation des concepts */

inst(michelAnge,personne).
inst(david,sculpture).
inst(sonnets,livre).
inst(vinci,personne).
inst(joconde,objet).

/* instantiation des roles */

instR(michelAnge, david, aCree).
instR(michelAnge, sonnets, aEcrit).
instR(vinci, joconde, aCree).
/*instR(vinci, monalisa, aCree).*/

/* TBOX */

/* DEF SCULPTEUR */
equiv(sculpteur,and(personne,some(aCree,sculpture))).

/* DEF AUTEUR */
equiv(auteur,and(personne,some(aEcrit,livre))).

/* DEF EDITEUR */
equiv(editeur,and(personne,and(not(some(aEcrit,livre)),some(aEdite
,livre)))).


/* DEF ENFANT */
equiv(parent,and(personne,some(aEnfant,anything))).




