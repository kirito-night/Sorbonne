### ex1 
1. Le nom de la personne dont l'identifiant est “person0”.
```xml
<results>{
    for $p in //person
    where $p/@id ='person0'
    return $p/name
}</results>
```

2. La valeur initiale (élément initial) des trois premières enchères en cours.
```xml
<results>{
    for $a in//open_auctions/auction[position() <= 3]
    return 
	<result id = "{$a/@id}"> 
	    {$a/initial}
	</result>
  }
</results>
```
3. La valeur de la première et de la dernière augmentation (élément increase de bidder) effectuée sur les trois premières des enchères en cours, selon l'ordre des enchères défini dans les données xml (ne pas trier les enchères chronologiquement).
```xml
<results>{
    for $a in//open_auctions/auction[position() <= 3]
    return 
    <result id= "{$a/@id}">
      <first>{$a/bidder[1]/increase/text()}</first>
      <last>{$a/bidder[last()]/increase/text()}</last>
    </result>
  }
</results>
```
4. Le prix des objets vendus à plus de 480.
```xml
for $p in //price[text() > 480]
return $p
equivalent 
for $p in //price[. > 480]
return $p
```
autre solution 
```xml 
for $p in //auction[price > 480]
return $p/price
```
5. Le nom des objets du continent africain
```xml 
for $c in //africa
return $c/item/name
```
6. Le nom des objets du continent africain avec leur prix de vente
```xml
for $a in //closed_auctions/auction
for $o in //africa/item[@id = $a/itemref/@item]
return 
<res>
{$o/name}
{$a/price}
</res>
```
7. Le nombre de personnes qui n'ont pas de page web
```xml
count(//person[not(homepage)])
```

### ex2 
resultat faux pour l'instant 
```xml
for $t in distinct-values(//lieutournoi)
for $g in distinct-values(//gain[lieutournoi]/annee)
return <tournoi lieu = "{$t}" annee = "{$g}"/>
```