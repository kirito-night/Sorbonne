--NOM : zeng
--Prenom : fanxiang


-- les Pays
;
select *
from Country
order by name
;


-- les organisations
;
select name from Organization
order by name
;

-- question 12

;
select c.name, mt.name, mt.height
from Country c , Mountain mt,  (select  c.code , max(HEIGHT) as max_height
from Mountain mt, GEO_MOUNTAIN m, Province p, Country c, ENCOMPASSES e
where m.country= p.COUNTRY
    and m.PROVINCE = p.NAME
    and p.COUNTRY= c.code
    and c.code = e.COUNTRY
    and e.CONTINENT = 'America'
    and mt.name = m.MOUNTAIN
group by c.CODE) as T
where T.CODE = c.CODE and mt.height = T.max_height
order by c.name

-- question 13

;
select r.Name
from River r
where r.River = 'Nile'
;

-- question 14

;
select r.Name
from river r
where r.river in
(
    select r.name from River r
    where r.River = 'Nile'
)
union select r.name from River r
where r.River = 'Nile'
;

-- question 15

;
select sum(R.LENGTH) as total_length
from
(
    select r.name, r.length
    from river r
    where r.river in
    (
        select r.name from River r
        where r.River = 'Nile'
    )
    union select r.name, r.length from River r
    where r.River = 'Nile' or r.Name = 'Nile'
) as R
;

-- question 16A

;
select top 1 T.ABBREVIATION, T.nb
from
(
    select o.ABBREVIATION, count(c.code) as nb
    from ORGANIZATION o, country c, ISMEMBER m
    where o.ABBREVIATION = m.organization
    and m.country = c.code
    group by o.ABBREVIATION
) as T
order by T.nb desc
;

select o.ABBREVIATION
from ORGANIZATION o, (select max(T.nb) as max_nb
from
(
    select o.ABBREVIATION, count(c.code) as nb
    from ORGANIZATION o, country c, ISMEMBER m
    where o.ABBREVIATION = m.organization
    and m.country = c.code
    group by o.ABBREVIATION
) as T) as R
where R.max_nb = (select count(o.COUNTRY) from ORGANIZATION o)


-- question 16B

;
select top 3 T.ABBREVIATION, T.nb
from
(
    select o.ABBREVIATION, count(c.code) as nb
    from ORGANIZATION o, country c, ISMEMBER m
    where o.ABBREVIATION = m.organization
    and m.country = c.code
    group by o.ABBREVIATION
) as T
order by T.nb desc
;

-- question 17

;
select (sum(T.population)/sum(T.area)) as density
from
(
    select distinct v.NAME, v.POPULATION, v.AREA
    from Country alg, country lyb , BORDERS b, COUNTRY v
    where alg.name = 'Algeria' and lyb.name = 'Libya'
    and ((alg.code = b.COUNTRY1 and v.code = b.COUNTRY2) or ((alg.code = b.COUNTRY2 and v.code = b.COUNTRY1))
    or (lyb.code = b.COUNTRY1 and v.code = b.COUNTRY2) or ((lyb.code = b.COUNTRY2 and v.code = b.COUNTRY1)))
    order by v.NAME
) as T
;

-- question 18











