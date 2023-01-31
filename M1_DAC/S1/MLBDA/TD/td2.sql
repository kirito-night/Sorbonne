create type Ville as object(
    nom varchar2 (30),
    nbhab number (6),
)

create type Etablissement as object ( 
    nonm varchar2(30),  
    horaire varchar2(30),
    dans REF Ville
    propose EnsBierre
) not final ;
-- creer un type representant un ensemble de references a des bieres 

create type EnsBiere as table of REF Biere;

create type Magasin under Etablissement (
    surface number(4)
)
create type Bar undre Etablissement(
    capacite number(4)
)

create type Biere as object(
    marque varchar2(30),
    typee varchar2(30),
    prix number (4)
    fabriquant varchar2(30)
    pays varchar2(30)
)

create type Consommateur as object(
    id number(10),
    nom varchar2(30),
    age number(3) ,
    reside REF Ville,
    consommation EnsConsomme 

)
create type Consomme as object (
    lieu REf Etablissement,
    produit REF Biere,

)

create type EnsConsomme as table of Consomme;

--3 
create table LesBars of Bar nested table propose store as t1;
create table LesBierres of Biere;
create table LesConsommateur of Consommateur nested table consommation store as t2;
--Ex 2 
create type Etudiant as object  (
    num number(10),
    nom varchar2(30),
    age number(3),
    contrat Unites 
)
create type Unite  as object ( 
    code varchar2(10),
    nom varchar2(30),
    credit number(2),
    contenus Seances, 
    notsetInstcrit EnsC2
)

create type Unites as table of REF Unite;

create type Enseignant as object( 
    nom varchar2(30),
)
create type Seances as table of Seance; -- pas de REF car une seance est contenue dans une ue 

create type Seance as object ( 
    sujet varchar2(30),
    numSeance number(2),
    present ensEtu

)
create type EnsEtu as table of REF Etudiant;

create type C2 as object ( 
    note number(2,2)
    etu REF etudiant
)