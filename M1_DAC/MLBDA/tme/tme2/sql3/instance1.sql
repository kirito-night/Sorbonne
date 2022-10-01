-- compléter l'entête 
-- ==================

-- NOM    :
-- Prénom :

-- NOM    :
-- Prénom :

-- Groupe :
-- binome :

-- ================================================

-- stockage des données : définition des relations
-- ====================
create table LesMatieres of Matiere;
create  table LesPieces of Piece;
create table LESPIECESB of PieceB ;
create table LesPiecesC of CompositeP nested table pieces store as t2;


-- instanciation des objets
-- ========================
