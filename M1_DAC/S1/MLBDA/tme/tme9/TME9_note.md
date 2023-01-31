1. 1-a les affluents (:River) directs et indirects de la mer du nord
```sparql
Select distinct ?r
where {
	?r a :River.
	?r :flowsInto <seas/North+Sea/>
}
```
3. 1-b- le nombre d'affluents (:River) directs et indirects de la mer du nord
```sparql
Select distinct ?r
where {
	{
		?r a :River.
		?r :flowsInto+ <seas/North+Sea/>.  // le + tres important en sparql
	}
}
```
5. 2-a Le affluents (:River) directs ou indirects de la mer du nord qui passent par le Rhin
```sparql
Select distinct ?r

where {
	{
	?r a :River.
	?r :flowsInto+ <rivers/Rhein/>.
	?r :flowsInto+ <seas/North+Sea/>.
	}
}
```
7. 2-b Le affluents (:River) directs et indirects de la mer du nord qui ne passent pas par le Rhin
```sparql
Select distinct ?r
where {
{
?r a :River.
?r :flowsInto+ <seas/North+Sea/>.
}
MINUS
{
?r a :River.
?r :flowsInto+ <rivers/Rhein/>.
?r :flowsInto+ <seas/North+Sea/>.
}
}
```
9. 3- Les rivières avec au moins vingt affluents.
```sparql
SELECT ?r
WHERE {
?r a :River.
?r1 :flowsInto ?r.
}
GROUP BY ?r
HAVING ( count(?r1) >= 20)
```
11. 4- Les lacs traversés par le Rhone
```sparql
SELECT distinct ?l
WHERE {
?l a :Lake.
?r a :River.
?r :name "Rhone".
?r :flowsThrough ?l.
}
```
13. 5- Les pays avec plus que 10000000 habitants traversés par le Danube
```sparql
SELECT distinct ?p ?pop
WHERE {
	?r a :River.
	?r :name "Donau".
	?p a :Country.
	?r :locatedIn ?p.
	?p :population ?pop.
	FILTER (?pop > 10000000)
}
```
15. 6 .Les Pays qui appartiennent à toutes les organisations auxquelles appartient aussi le Liechtenstein
```sparql
SELECT ?name
WHERE {
?c a :Country.
?c :name "Liechtenstein".
?c :isMember ?o.
?res :isMember ?o.
?res :name ?name.
}
```