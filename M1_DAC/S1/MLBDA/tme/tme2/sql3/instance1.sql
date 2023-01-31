-- compléter l'entête 
-- ==================

-- NOM    :zeng
-- Prénom :fanxiang

-- NOM    :wang
-- Prénom :zhe

-- Groupe :3
-- binome : wang & zeng

-- ================================================

-- stockage des données : définition des relations
-- ====================
drop table LESMATIERES
/

drop table LESPIECESB
/

drop table LESPIECESC
/



create table LesMatieres of T_Matiere;
create table LESPIECESB of T_PIECE_BASE nested table entre_dans store as t1;
create table LesPiecesC of T_PIECE_COMPOSITE nested table contient_pieces store as t2 nested table  entre_dans store as t3;


-- instanciation des objets
-- ========================
insert into LesMatieres values (T_Matiere('bois' , 10, 2));
insert into LesMatieres values (T_Matiere('fer' , 5, 3));
insert into LesMatieres values (T_Matiere('ferrite' , 6, 10));


create or replace procedure p1 as
    BOIS REF T_MATIERE;
    FER REF T_MATIERE;
    FERRITE REF T_MATIERE;
    plateau REF T_PIECE;
    pied REF T_PIECE;
    clou REF T_PIECE;
    tab REf T_PIECE;
    boule REF T_PIECE;
    canne ref T_PIECE;
Begin
select ref(m) into BOIS from LesMatieres m where m.nom = 'bois';
select ref(m) into FER from  LesMatieres m where m.nom = 'fer';
select ref(m) into FERRITE from LesMatieres m where m.nom ='ferrite';



insert into LesPiecesB values (T_CYLINDRE('canne', null, BOIS, 2, 30));
insert into lesPiecesB values(T_PARAL('plateau' , null ,BOIS, 1,100,80));
insert into lesPiecesB values(T_SPHERE('boule',null,FER, 30));
insert into LESPIECESB values(T_SPHERE('pied' , null , BOIS, 30));
insert into LEspiecesB values(T_CYLINDRE('clou' , null , FER, 1, 20));
insert into LesPiecesB values(T_CYLINDRE('aimant' , null , FERRITE, 2, 5));


select ref (p) into plateau from LesPiecesB p where p.nom = 'plateau';
select ref (p) into pied from LesPiecesB p where p.nom = 'pied';
select ref (p) into clou from LesPiecesB p where p.nom = 'clou';
select ref (p) into boule from LesPiecesB p where p.nom = 'boule';
select ref (p) into canne from LesPiecesB p where p.nom = 'canne';


insert into  LesPiecesC values('table', null , 100, T_CONTIENT_PLUSIEURS(T_CONTIENT(plateau,1), T_CONTIENT(pied,4), T_CONTIENT(clou,12)));
select ref (p) into tab from LesPiecesC p where p.nom = 'table';

insert into  LesPiecesC values('billard', null , 10, T_CONTIENT_PLUSIEURS(T_CONTIENT(tab,1), T_CONTIENT(boule,3), T_CONTIENT(canne,2) ) );
end;
/
@compile
begin
p1;
end;
/
show errors
select p.nom , p.est_en.nom
from LESPIECESB p
