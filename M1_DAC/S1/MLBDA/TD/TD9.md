2 triplets peuvent partager : 
- un sujet 
- une propriété
permet de factoriser
propriétés  : 
- enemy of 
- a
- name 
on développe le format factoriser 
si  : 
$p_1 \; o_1$
$$p_2 \;o_2 $$
$$\iff$$
si $p_1 \; o_1$
si $p_2 \; o_2$

si $p_1 \; o_1 \rightarrow$ si $p_1 \; o1$ 
				si $p_1 \; o_1$ 
### Ex2 
1 .  a)

? p email ?e . ?p web ?w  
? p : 
- mail ?e
- web  ?w
  
| ?p   |      ?e   |  
|------|:----:|
|p2 |  rick@ |
| p4 |    liz@   |   

| ?p   | ?w|  
|------|:----:|
|p3 |  lars |
| p4 |    liz.pers|    

Resultat : 

| ?p   | ?e | ?w | 
|------|:----:| ---|
| p4 |    liz@  | liz.pers

b) requete avec union 

(?p , ?e) union (?p, ?w) ? 

(?p, ?e , null) union (?p , null. ?w)

Resultat: 
| ?p   | ?e | ?w | 
|------|:----:| ---|
| p2 |    rick@ |null | 
| p4 | liz@| null|
|p3 | null | lars.com| 
| p4 | null | liz.pers

c) **OPTIONNEL : la partie gauche est toujours incluse dans le resultat**
| ?p   | ?e | ?w | 
|------|:----:| ---|
| p2 |    rick@ |null | 
| p4 | liz@| liz.pers|

2 . OPTIONNEL imbriqués 
{?pnme ?n optionnal ? p.email ?e } optionnal ?p web ?w

| ?p   |?n| ?e | ?w | 
|------|:---:| :----:| ---|
| p1 |  john  |null |null | 
| p2 | rick|rick@| null|
|p3 | lars |null | lars.com| 
| p4 | liz |liz@ | liz.pers|

3 .  les requetes 2) et 3) sont differentes 

| ?p   |?n| ?e | ?w | 
|------|:---:| :----:| ---|
| p1 |  john  |null |null | 
| p2 | rick|rick@| null|
|p3 | lars |null | null| 
| p4 | liz |liz@ | liz.pers|

la ligne 3 differente 

4  a) filter(bound(...))
?p web ?w optionnal ?p phone ?l filter (!bound(?l)) 

les personnes qui ont une page web et pas de telephone 

resultat : (p3 , lars.com , null)
p4 n'est pas dans le resultat 

### Ex 3 
1. extraire les villes citees dans des triplets de cette base
ville ? 
une entite `vit` dans une ville 
une entite `locatedAt`  une ville
une ville `a City`
```sparql 
select distinct ?v 
where {
	{?x : livesIn ?v}
	UNION
	{?y : locatedAT ?v}
	UNION
	{?v  aCity : City }
}
```
2. Extraire les personnes qui ont etudie dans la meme universtie que l'un de leur parents
```sparql 
select distinct ?p  
where { les points pour faire les jointures afin de relier les variables IMPORTANT
	{
		{
			?p :hasFather ?f .
			?f :studiedAt ?u .
			?p :studiedAt ?u 
		}
		UNION 
		{
			?p :hasMother ?m .
			?m :studiedAt ?u.
			?p :studiedAt ?u
		}
	}.
	?p a :Person   //ou bien de mettre ca comme jointure dans les 2
	Rmq : toutes les "personnes " de la base ne sont pas types en tant que :Personne donc on evite de preciser cette condition
	}
```
utilisation d'un **.** pour la jointure
Autre solution  **mieux factoriser** 
```sparql 
select distinct ?p  
where { les points pour faire les jointures afin de relier les variables IMPORTANT
	{
		?p :studiedAt ?u.
		?parent :studiedAt ?u
	}.
	{
		{
			?p :hasFather ?parent 
		}
		UNION 
		{
		?p :hasMother ?parent.
		}
	}.
	?p a :Person  //ou bien de mettre ca comme jointure dans les 2
}
```

3. extraire les personnes qui ont etudee dans une universite ou leurs deux parents ont etudie 
```sparql
select distinct ?p  
where {
	?p :studiedAt ?u.
	?p  :hasMother ?m.
	?m  :studiedAt ?u.
	?p :hasFather ?f.
	?f :studiedAt ?u
}
```
4. extraire les personnes qui ont etudie dans une universite differente de celle de leur pere et leur mere 
```sparql 
select distinct ?p  
where {
	?p :studiedAt ?u1.
	?p  :hasMother ?m.
	?m  :studiedAt ?u2.
	?p :hasFather ?f.
	?f :studiedAt ?u3.
	filter(?u1 != u2 && ?u1 !=u3)
}
```
autre solution avec **MINUS** ( Minus c'est une operation ensembliste, importance de renommage pour faire la soustraction que sur la variable qui nous interesse)
Rmq:  les Etudiants sans parent sont inclus dans le resultat 
```sparql 
select distinct ?p  
where {
	{?p :studiedAt ?u.} 
	MINUS 
	{
		?p :hasMother ?m.
		?p :hasFather ?f.
		?m :studiedAt ?u.
		?f :studiedAt ?u.
	}
}
```
6. extraire les personnes qui etudie dans une ville differente de celle ou ils habitent
```sparql 
select distinct ?p
where{ 
	?p :livesIn ?v1.
	?p :studiedAt ?u.
	?u :locatedAt ?v2.
	filter(?v1!=?v2)
}
```
modelisation de filter
| ?p   |?v1| ?v2 | ?u | 
|------|:---:| :----:| ---|
| p1 |  v1  |v2 |u1 | 
| p1 | v3|v2| u1|

autre solution  avec **MINUS**
```sparql 
select distinct ?p
where{ 
	{
		?p :livesIn ?v1.
		?p :studiedAt ?u.
		?u :locatedAt ?v2.
	}
	MINUS
	{
		?p :studiedAt ?v3
		?p :studiedAt ?u2.
		?u2 :locatedAt ?v3
		
	} 
}
```
OU 
```sparql 
select distinct ?p
where{ 
	{
		?p :livesIn ?v.
		?p :studiedAt ?u.
	}
	MINUS
	{
		?u :locatedAt ?v
		
	} 
}
```
