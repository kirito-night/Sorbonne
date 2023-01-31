-- compléter l'entête 
-- ==================

-- NOM    :
-- Prénom :

-- NOM    :
-- Prénom :

-- Groupe :
-- binome :

-- ================================================
create or replace type BODY T_cube as
    overriding member function volume return number is
    res number;
    begin
        res := self.cote * self.cote * self.cote;
        return res;
    end;
end;

create or replace type BODY T_sphere as
    overriding member function volume return number is
    res number;
    begin
        res := 4/3 * 3.14 * self.rayon * self.rayon * self.rayon;
        return res;
    end;
end;

create or replace type BODY T_CYLINDRE as
    overriding member function volume return number is
    res number;
    begin
        res := 3.14 * self.diametre/2 * self.diametre/2 * self.hauteur;
        return res;
    end;
end;

create or replace type BODY T_Paral as
    overriding  member function volume return number is
    res number;
    begin
        res := self.longueur * self.largeur * self.profondeur;
        return res;
    end;
end;

create or replace type BODY T_piece_base as
    overriding member function masse return number is
    res number;
    masse_volumique number;
    begin
        select deref(self.est_en).masse_volumique into masse-volumique from dual;
        res := self.volume() * masse-volumique;
        return res;

    end;

end;

select p.volume() from LESPIECESB p where p.nom = 'clou';