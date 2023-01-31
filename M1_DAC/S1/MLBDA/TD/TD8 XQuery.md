### ex1
1. le nom de tous les menus des restaurants
```xml
<results>{
for $m in document("guide.xml")//menu
return <menu nom = "{$m/@nom}"}/>
}
</results>
```
2. Donner le nom et le prix de tous les menus dont le prix est inférieur à 100
```xml 
<results>{
	for $m in document("guide.xml")//menu 
	return $m
}
<results>
```
avec `document("guide.xml")` optionnel
3. Donner le nom des restaurants 2 étoiles avec leur nom de menu
```xml 
<results>{
for $r in //restaurant
where $r/@etoile = 2
return <restaurant nom = "{$r/@nom}">{
	for $m in $r/menu
	return <menu nom = "{$m/@nom}"/>
	}</restaurant>
}
</results>
```
4. Donner le nom de chaque restaurant avec son numéro de département
```xml
<results> {
for $r in //restaurant 
	for $v //ville
		where $v/@nom = $r/@ville
	return <restaurant nom = "{$r/@nom}" departement = "{$v/@departement}"
}</results>
```
autre solution 
```xml 
<results> {
for $r in //restaurant 
	return <restaurant nom = "{$r/@nom}" departement = "//ville[@nom = $r/@ville]/@departement"/>
}</results>
```
5. Quels sont les restaurants ayant (au moins) un menu dont le prix est égal au tarif de visite du plus  
beau monument de la ville ? Donner le nom du restaurant et le tarif.
```xml 
<results>{
	for $r in //restaurant
	for $m in $r/menu
		for $v in //ville
		where $v/@nom = $r/@ville and  $m/@prix  = $v/plusBeauMonument/@tarif 
		return 
		<result>
			<restaurant nom  = "{$r/@nom}"/>
			<tarif_monument prix = " {$m/@prix}"/>
		</result>
	} 
}</results>
```

solution ci-dessus presque ok mais contient des elements `<result>`  en double si un restaurant a plus d'un menu qui satisfait la condition 
```xml 
<results>{
	for $r in //restaurant,
		$v in //ville,
		$p in distinct-values($r/menu/@prix)
	where $v/@nom = $r/@ville and  $p= $v/plusBeauMonument/@tarif 
	return 
	<result>
		<restaurant nom  = "{$r/@nom}"/>
		<tarif_monument prix = " {$p}"/>
	</result>
	} 
}</results>
```
Remarque : 
- ne pas ecrire `distinct-values(//menu)` car c'est un element complexe
- a utiliser quand on a les ensemble de valeur 

autre solution avec '' il existe au moins 1 menu qui satisfait la condition sur le tarif" : 
**sous format** :  `some variable in satisfies`
```xml 
<results>{
	for $r in //restaurant,
		$v in //ville,
	where $v/@nom = $r/@ville and  some $m in $r/menu satisfies $m/@prix = $v/plusBeauMonument/@tarif 
	return 
	<result>
		<restaurant nom  = "{$r/@nom}"/>
		<tarif_monument prix = " {$m/@prix }"/>
	</result>
	} 
}</results>
```


**Pour les XQuery, on peut retourner un arbre mais pas un foret**
- **si on  veut retourner 2 elements nous avons besoin de mettre un element au dessus**

### ex2 

R4 : Auto jointure d’un document  
Pour chaque auteur, donner la liste des titres de ses livres. Un élément du résultat contient un auteur avec tous les  
titres qu’il a écrit.
`//author` contient des elements en double`
```xml 
<results>{
	for $l  in distinct-values(//last), 
		$f in distinct-values(//author[last = $l]/first)
	return 
		<result> 
			<author> 
				<last>{$l}</last>
				<first>{$f}</first> 
			</author>
			<title> {//book[author[last = $l and first $f]]/titre}</title> 
		</result>
</results>
}
```
- autre solution 
```xml 
<results>{
	for $l  in distinct-values(//last), 
		$f in distinct-values(//author[last = $l]/first)
	return 
		<result> 
			<author> 
				<last>{$l}</last>
				<first>{$f}</first> 
			</author>
			{
				for $b in //book 
				where some $a in $b/author satisfies $a/last = $l and $a/first =$f
				return $b/title
			}
		</result>
</results>
}
```

Remarque: 
- `//book[author/last = $l and author/first = $f]` n'est pas bon car `author/last` est un ensemble qu'on arrivera pas a distinguer
