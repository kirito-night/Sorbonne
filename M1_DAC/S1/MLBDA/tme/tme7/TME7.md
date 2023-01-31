1. Tous les titres de films.
`//FILM/TITRE`
2.  Les titres des films d'horreur.
`//FILM[GENRE='Horreur']/TITRE`
3.  Le résumé d'Alien.
`//FILM[TITRE = 'Alien']/RESUME `
4. Titre des films avec James Stewart.
`//FILM[ROLES/ROLE[NOM = 'Stewart' and PRENOM = 'James']]/TITRE`
5. Titre des films avec James Stewart et Kim Novak.

# XPath Notebook
(: 1.  Tous les titres de films. :)
`//FILM/TITRE! string()`

(: Les titres des films d'horreur. :)
`//FILM[GENRE='Horreur']/TITRE! string()`

 (: Le résumé d'Alien. :)
` //FILM[TITRE = 'Alien']/RESUME! string()`

(: Titre des films avec James Stewart. :)
`//FILM[ROLES/ROLE[NOM = 'Stewart' and PRENOM = 'James']]/TITRE ! string()`

(: Titre des films avec James Stewart et Kim Novak. :)
`//FILM[ROLES/ROLE[NOM = 'Stewart' and PRENOM = 'James'] and ROLES/ROLE[NOM = 'Novak' and PRENOM = 'Kim']]/TITRE ! string()`

(: Quels films ont un résumé ? :)
`//FILM[RESUME] ! string()`

(: Quels films n'ont pas de résumé ? :)
`//FILM[not(RESUME)] ! string()`

(: Quel est l'identifiant du metteur en scène du film Vertigo? :)
`//FILM[TITRE = 'Vertigo']/MES/@idref ! string()`

(: Quel rôle joue Harvey Keitel dans le film Reservoir dogs ? :)
`//FILM[TITRE = 'Reservoir dogs']/ROLES/ROLE[NOM = 'Keitel' and PRENOM = 'Harvey']/INTITULE ! string()`

(:Quel est le dernier film du document ?:)
`//FILM[last()] ! string()`

(: Quel est le titre du film qui précède immédiatement le film Shining (dans l'ordre du document). :)
`//FILM[TITRE = 'Shining']/preceding-sibling::FILM[1]/TITRE ! string()`

(: Donnez les titres des films qui contiennent un 'V' (utiliser la fonction contains) :)
`//FILM[contains(TITRE, 'V')]/TITRE/text() ! string()`

(: Donner les noeuds qui ont exactement trois descendants (utiliser la fonction count). :)
`//descendant-or-self::node()[count(descendant::*) = 3] ! string()`

(: Donner les noeuds dont le nom contient la chaîne 'TU' (fonction name) :)
`/descendant-or-self::node()[contains(name(), 'TU')]`
(: or :)
`//*[contains(name(), 'TU')]`