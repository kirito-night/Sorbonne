-- compléter l'entête 
-- ==================

-- NOM    : WANG
-- Prénom : Zhe

-- NOM    : ZENG
-- Prénom : Fanxiang

-- Groupe : 3
-- binome : Wang & Zeng

-- ================================================

-- nettoyer le compte
-- ------------------
--drop type T_un_type force;


-- Définition des types de données
-- -------------------------------

create type T_un_type as object (
 un_attribut Number
);


create type Piece as object(
    nom varchar2(20)
);


create type Matiere as object (
    nom varchar2(20),
    prix_kilo number(5,2),
    masse_volumique number(5,2)
);



create type EnsMatiere as table of REF Matiere;
create type PieceB under Piece(
    mat REF Matiere
);


create type Cube under PieceB(
    cote number(5,2)
);


create type Sphere under PieceB(
    rayon number(5,2)
);


create type Cylindre under PieceB(
    rayon number(5,2),
    hauteur number(5,2)
);


create type Paral under PieceB(
    longeur number(5,2),
    largeur number(5,2),
    hauteur number(5,2)
);


create type EnsPieceB as table of REF PieceB;

create type EnsPiece as table of REF Piece;
create type CompositeP under Piece (
    pieces EnsPiece,
    cout number(5,2),
    quantite number(5,2)

);






-- liste de tous les types créés
@liste

