--TD 4 
--question 3  requete

--d 
select e.nomE , value(c).nomU
from lesEtu e, table (e.contrat) c, lesUe u , table(u.contenu) s , table(s.presents) p
where value(p) = ref(e) and u.nomU = 'MLBDA' and s.numero = 2
