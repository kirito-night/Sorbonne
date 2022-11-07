--TD3 
--Schema (les type ) : ex2 p4 cf. annexes 
-- Requetes et methodes Ex3 p8 
-- Q1 stockage 
create table lesUeE as table of Unite nested table contenu store as t1
(nested table presents store as t11) nested table noteInscrits store as t2
-- question 2 instanciation 
-- a 
insert into lesUE values(
    Unite(MLBDA , 4I801,6 , seances(
        seance(
            1, 'SQL', Etudiant()
        ) ,
        seance( 
            2, 'SQL3', Etudiant()
        ) ,
        seance( 
            3, 'XML', Etudiant()
        ) ,
        ),
        EnsC2()
    )
);  -- insertion des objets a multi contenance avec leur type de maniere imbriquee 
--b) 
insert into lesUE values(
    Unite(PLDAC, 4I101,6,seances(
        seance(
            4,'Intro',Etudiant()
        ),
        seance(
            5,'Methodologie',Etudiant()
        )
    ),EnsC2()
    )
)
--c)
insert into LesEtudiants values( -- si c'est une table de reference on passe par un requete SQL en donnant la reference des elements trouver
    Etudiant(123, 2022,'Alice',28 , Unites( -- u est une unite au singulier 
        (select ref(u) from lesUE u where u.code ="4I801"),
        (select ref(u) from lesUE u where u.code ="4I101")
    ))
)
--d) ajouter la ref d'alice dans les "presents" de la 3eme seance MLBDA
insert into table (
    select s.prensents  -- ici c'est un singleton , son type est element de son relation ,ici sont type est Etudiants (pluriels)
    from lesUE u, table(u.contenu)s
    where u.nomU = 'MLBDA' and s.numero = 3
    ) values (select ref(a) from LesEtudiants a where a.nom = 'Alice')
 -- autre solutio avec update 
 update table (select u.contenu
 From lesUE u,
 where u.nomU = 'MLBDA'
 ) s set presents= Etudiants(select ref(a) from LesEtudiants a where a.nom = 'Alice') where numero = 3 -- alice est la seule presente avec cette requete, on aura besoin de fair un union des restes pour avoir tous les etudiants presents
 -- le syntaxe s.presents = s.presents union Etudiants n'est pas supporter

--question 3 Requete 
--a) -- faire tableau expression type quand bloquer , quand c'est des ensembles souligner en rouge , et si on veut les manipuler on a besoin d'un variable ,instancier dans le from 
select 
from LesEtudiants e , table(e.contrat) c  -- quand est ce que un variable est de type ref , on a besoin de utiliser value() pour extraire les attributs
where e.age > 25 and value(u).code = '4IN801'-- le point fait un dereferencement de maniere implicite 
-- deref prend en argument une expression et renvoie un type unite 
-- Rmq : value (variable ) est une fonction identite le type retourne est egale au type de l'argument 
--autre solution : Remplacer value(u).code par deref(value(u)).code

--b)
select value(e).nomE
from lesUE u , table(u.contenu) s, table(s.presents)e 
where u.nomU = 'MLBDA' and s.numero = 3 

--c) 
select 
from lesUE u  , table (u.contenue) , table(s.prensent) e 
where where u.code = '4I801' 
group by 

select e.nomE , (select from table ( value(u).contenu) s , table(s.present) p where  value(p)= ref(e))
from lesEtu e , table(e.contrat) u 
where value(u).code = '4I801'

--d pour les etudiants presents a la 2 eme seances de MLBDA , afficher nom et nom de l'unite
select e.nomE , value(c).nomU
from lesEtu e, table (e.contrat) c, lesUe u , table(u.contenu) s , table(s.presents) p
where value(p) = ref(e) and u.nomU = 'MLBDA' and s.numero = 2

--e pour chaque seances de chaque unite d'enseignement , le nb de presents s
select count(*) as nbpresents  ,value(u).code, value(u).nomU , s.numero
from les UE u , table(u.contenu) s ,table (s.presents) p
group by u.code , s.numero

--autre solution 
select count(*) as nbpresents  ,value(u).code, value(u).nomU , s.numero
from lesUE u , table(u.contenu) s ,table (s.presents) p
group by ref(u),ref(s) -- ou group by value(u),value(s)

-- avec select imbrique
select u.code , s.num, (select count(value(p)) from table(s.presents) p) as nbpresents
from lesUE u , table(u.contenu) s 


--f pour chaque etudiant et chaque unite a laquelle il est inscrit , quel est le nb de seances
select count(*) as nbseances
from lesEtudiant e , table(e.contrat) u , table(u.contenu) s
group by e.nomE , u.nomU

--g afficher le nom des etudiants presents a toutes les seances de toutes les unites de leur contrat 
select 
from LesEtudiants e , table(e.contrat) u , table(u.contenu) s , table(s.presents) p
where not exists(
    select 
    from 
    where not in 
)

--question 4 methodes 
--a)
select e.nomE, e.creditTotal()
from lesEtudiants e 
where e.creditTotal() > 600;

--b) 
select value(e1), value(e2)
from lesEtudiants e1 , lesEtudiants e2
where e1.nbUcommunes(e2) = 10 and ref(e1) <>ref(e2)


--c)
select e.note()
from lesEtu e 
where e.numero = '1234'
-- resultat EnsC1(C1('4I801', 18),C2('4I200', 16))
--le requete retourne un seul element de type EnsC1

select c.code
from lesEtudiant e , table(e.note()) c --c de type C1
where e.numero = '3456' and c.note <10


--d)
create or replace type BODY Etudiant as 
    member function notes return EnsC1 is
    res EnsC1 --variable affecte le resultat de la fonction
    begin 
        select C1(u.code,c.note)
        bulk collect into res -- on utilise bulk collect pour stocker le resultat dans une variable
        from table(self.contrat) u , table(value(u).noteInscrits) c
        where c.etu = ref(self)
        
        return res ;
    end;
    --tout autre methodes de etudiant
end;

-- question 5 recursion 
--a)
create type EnsUe as table of ref Unite;
--b)
create or replace TYPE BODY Unite as 
    member function prerequisTN return EnsUe is
    res Unites;
    begin 
        select value(p)
        bulk collect into res
        from table(self.prerequis) p
        union
        select value(p2)
        from table(self.prerequis)p1 , table(value(p).prerequisTN()) p2 -- methde recursive 

       
        return res;
    end;
end;
