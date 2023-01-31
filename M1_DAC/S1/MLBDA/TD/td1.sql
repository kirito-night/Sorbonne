-- question 1
select c.name 
from country c, organisation o, isMember isMember
where c.code = o.country and o.abbreviation = m.organisation and o.name ="United Nation"
ORDER BY c.name;
-- question 2
select c.name , c.population
from country c, organisation o, isMember isMember
where c.code = o.country and o.abbreviation = m.organisation and o.name ="United Nation"
ORDER BY c.population DESC;
-- question 3 a 
select c.name 
from country c 
where c.code not in (select 
                    from organisation , isMember isMember
                    where m.organisation = o. abbreviation
                    and o.name = 'United Nations')
-- avec une soustraction A-B les elements de A et B sont des identifiants de pays "code", on peut garder le nom de pays dans A et B : element = couple(code , name)
--3.b
select c.code , c.name
from country c 
MINUS
select m.country , c.name
from country c , organisation o , isMember m
where c.code = m.country and o.abbreviation = m.organisation and o.name ="United Nation"

-- question 4
select b.country2
from Country f, Borders b
where  f.name = 'France' and f.code = b.country1 
UNION
select b.country1
from Country f, Borders b
where  f.name = 'France' and f.code = b.country2

-- question 5
select v.code, v.name
from Country f, Border B, Country v
where f.name = 'France' and ((f.code = b.country1 and v.code = b.country2) or (f.code = b.country2 v.code = b.country1)) 

-- question 6
select SUM(b.length)
from country f, border b
where f.name = 'France' and (f.code = b.country1 or f.code = b.country2)

-- question 7
select p.code , Count(p.*)
from Border b, Country p
where (p.code = b.country1 or p.code = b.country2)
Group by p.code

-- question 8
select p.name , SUM(v.population)
from border b, country p , country v
where (p.code = b.country1 and v.code  = b.country2) OR (p.code = b.country2 and v.code = b.country1)
GROUP BY p.code

-- question 9
select p.name , SUM(v.population) as pTotal
from border b, country p , country v, Encompasse e
where p.code= e.country and e.continent= 'Europe' (p.code = b.country1 and v.code  = b.country2) OR (p.code = b.country2 and v.code = b.country1)
GROUP BY p.code

select p.name , SUM(v.population) as pTotal
from border b, country p , country v, Encompasse e
where (p.code = b.country1 and v.code  = b.country2) OR (p.code = b.country2 and v.code = b.country1)
GROUP BY p.code
having p.code in (select e.country
                  from Encompasse e
                  where e.continent = 'Europe')

-- question 10
select count(*) , sum(p.population)
from organisation o , Country p  , isMember m
where m.country = p.code and m.organisation = o.abbreviation
group by o.abbreviation;

-- on peut remplacer count(*) par count(1) count ( distinct m.country) count (m.country , m.organisation) count (organisation)
-- question 11
select count(*) , sum(p.population)
from organisation o , Country p  , isMember m
where m.country = p.code and m.organisation = o.abbreviation
group by o.abbreviation
having count(*)>100

-- question 12 les pays avec leur montagne tq la hateur  = [ hauteur max des montagne de ce pays ]
-- ou  il n'existe pas une autre montagne de ce pays avec une hauteur plus grande