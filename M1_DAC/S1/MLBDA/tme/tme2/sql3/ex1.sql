-- compléter l'entête 
-- ==================

-- NOM    : Zeng
-- Prénom : Fanxiang

-- NOM    : Wang
-- Prénom : Zhe

-- Groupe : 3
-- binome : Wang & Zeng

-- ================================================

-- suppression des types créés

-- ==========================

drop type Etudiant;
drop type Adresse;
drop type Cercle;
drop type Polygone;
drop type Point;


-- définition des types 
-- ====================

-- un étudiant décrit par les attributs nom, prénom, diplôme,
create type Etudiant as object (
  nom varchar2(20),
  prenom varchar2(20),
  diplome varchar2(20)
);

-- un module d'enseignement décrit par les attributs nom, diplôme,
create type Module as object (
  nom varchar2(20),
  diplome varchar2(20)
);

-- un point du plan euclidien,
create type Point as object (
  x number,
  y number
);

-- un cercle,
create type Cercle as object (
  centre Point,
  rayon number
);