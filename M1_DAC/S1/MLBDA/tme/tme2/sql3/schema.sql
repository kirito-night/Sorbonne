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
--
-- create type T_un_type as object (
--  un_attribut Number
-- );
--
--
-- create type Piece as object (
--     nom varchar2(20)
-- )not final;
--
--
-- create type Matiere as object (
--     nom varchar2(20),
--     prix_kilo number(5,2),
--     masse_volumique number(5,2)
-- );
--
--
--
-- create type EnsMatiere as table of REF Matiere;
-- create type PieceB under Piece(
--     mat REF Matiere
-- )not final;
--
--
-- create type Cube under PieceB(
--     cote number(5,2)
-- );
--
--
-- create type Sphere under PieceB(
--     rayon number(5,2)
-- );
--
--
-- create type Cylindre under PieceB(
--     rayon number(5,2),
--     hauteur number(5,2)
-- );
--
--
-- create type Paral under PieceB(
--     longeur number(5,2),
--     largeur number(5,2),
--     hauteur number(5,2)
-- );
--
--
-- create type EnsPieceB as table of REF PieceB;
--
-- create type EnsPiece as table of REF Piece;
-- create type CompositeP under Piece (
--     pieces EnsPiece,
--     cout number(5,2),
--     quantite number(5,2)
--
-- );
--
-- select OBJECT_NAME, OBJECT_TYPE, STATUS
-- from USER_OBJECTS
-- where STATUS = 'INVALID';





set sqlbl on


-- EXERCICE CAO


-- SCHEMA avec heritage

-- vider le compte oracle pour tjrs repartir du meme etat initial
@vider
drop type T_ENTRE_DANS force
/
drop type T_PIECE_COMPOSITE force
/
drop type T_CONTIENT force
/
drop type T_CUBE force
/
drop type T_SPHERE force
/
drop type T_CYLINDRE force
/
drop type T_PARAL force
/
drop type T_PIECE_BASE force
/
drop type T_MATIERE force
/
drop type T_PIECE force
/
drop type T_ENTRE_DANS_PLUSIEURS force
/

drop type T_CONTIENT_PLUSIEURS force;
/



-- D�finition des types de donn�es

-- Matiere

create type T_Matiere as object (
	nom varchar2(30),
	prix_au_kilo number,
	masse_volumique number
);
-- compiler chaque d�finition de type
@compile


-- un type utilis� avant d'�tre d�fini doit �tre pr�-d�clar�
create type T_Piece;
@compile

create type T_Piece_Composite;
@compile



-- L'association ENTRE_DANS : une piece (de base ou composite) ENTRE_DANS la fabrication d'une ou plusieurs pieces composites,
-- en pr�cisant la quantit� de composants n�cessaire pour fabriquer la pi�ce composite.

create type T_entre_dans as object (
	quoi      ref T_Piece_Composite,
	quantite  number
);
@compile

-- cardinalit� de l'association : 1-N
create type T_entre_dans_plusieurs as table of T_entre_dans;
@compile


-- L'association contient : une piece composite CONTIENT des pieces (de base ou composite),
-- en pr�cisant la quantit� contenue.

create type T_contient as object (
	quoi      ref T_Piece,
	quantite  number
);
@compile

-- cardinalit� de l'association : 1-N
create type T_contient_plusieurs as table of T_Contient;
@compile


-- PIECE
-- est une sur-classe de Piece_base et Piece_Composite

prompt T_Piece

create type T_Piece as object (
	nom         varchar2(30),
        entre_dans  T_entre_dans_plusieurs
) not final;
@compile


-- PIECE de BASE
prompt T_Piece_base

create type T_Piece_base under T_Piece (
	est_en ref   T_Matiere,
	member function volume return number
) not final not instantiable ;
@compile

-- Les pi�ces de base r�elles : cube, sphere et cylindre

prompt T_Cube

create type T_Cube under T_Piece_base (
	cote        number,
    overriding member function volume return number
);
@compile

prompt T_Sphere

create type T_Sphere under T_Piece_base (
	rayon        number,
	overriding member function volume return number
);
@compile

prompt T_Cylindre

create type T_Cylindre under T_Piece_base (
	diametre        number,
    hauteur         number,
    overriding member function volume return number
);
@compile

-- un parall�l�pip�de rectangle
prompt T_Paral
create type T_Paral under T_Piece_base (
	hauteur        number,
        largeur        number,
        profondeur     number,
        overriding member function volume return number
);
@compile


-- PIECE COMPOSITE
prompt T_Piece_Composite

create type T_Piece_composite under T_Piece(
 	cout_assemblage  number,
 	contient_pieces  T_contient_plusieurs
);
@compile







-- liste de tous les types créés
--@liste

