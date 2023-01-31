# TD 5 DTD

### ex1
```xml
doc A  
<?xml version="1.0" standalone="yes"?>  
<!DOCTYPE document [  
<!ELEMENT document  
(#PCDATA | title | publisher)*>  
<!ELEMENT title (#PCDATA)>  
<!ELEMENT publisher (name)>  
<!ELEMENT name (#PCDATA)>  
]>  
<document>  
<title> BD et sites Web </title>  
<publisher>  
<name>Alfred Pub</name>  
</publisher>  
Publishers are noted in report by name  
</document>
```
 - donnees
 - document
	 - title
	 - publisher
		 - name 
	- publisher  ...
est ce que la sequence `title,publisher`, "publisher are ..." satisfait le modele `(#PCDATA|title|publisher)*`
=> oui
Meme raisonnement pour chaque noeud de l'arbre 
(pendant un parcoursen profondeur d'abord)

```xml
doc B
<?xml version="1.0" standalone="yes"?>  
<!DOCTYPE document [  
<!ELEMENT document  
((title | publisher)*, name?) >  
<!ELEMENT title (#PCDATA)>  
<!ELEMENT publisher (name)>  
<!ELEMENT name (#PCDATA)>  
]>  
<document>  
<title> BD </title>  
<publisher> Alfred Pub </publisher>  
</document>
```
- doc 
	- title
	- 
est ce `title , publisher` est conforme au modele `((title|publisher)*,name?)`
=> oui
```
* => 0 ou N
| => l'un ou l'autre
, => suivie de 
? => 0 ou 1
+ => 1 ou N 
```
est ce que "Alfred..." est conforme au modele (name?)
=> non on pourrait modifier la DTD
`<!Element publisher(#PCDATA)>`

```xml
doc C  
<?xml version="1.0" standalone="yes"?>  
<!DOCTYPE document [  
<!ELEMENT document (title?)>  
<!ELEMENT title (#PCDATA)>  
<!ATTLIST title num ID #REQUIRED  
info CDATA #IMPLIED>  
]>  
<document>  
<title> Bases de données et sites Web </title>  
</document>
```
- doc
	- title (n'a pas d'attribut) `bases de ....`
or l'element `title` a un modele avec un attribut `num` obligatoire
=> <span style ="color : red">donc title n'est pas conforme </span>
on pourrait modifier le DTD : 
```<!ATTLIST tile num CDATA #implied
				info CDATA #implied>
```

```xml
doc D  
<?xml version="1.0" encoding="ISO-8859-1"  
standalone="yes" ?>  
<!DOCTYPE document [  
<!ELEMENT document  
(title, publisher, #PCDATA)>  
<!ELEMENT title (#PCDATA)>  
<!ELEMENT publisher (#PCDATA)>  
]>  
<document>  
<title> hello  
<title>The Publishers</title>  
<publisher>Alfred Pub</publisher>  
</title>  
Publishers are noted in report by name.  
</document>
```
- doc
	- title 
		- title
		- publisher
la sequence `title` n'est pas conforme au modele 
`(title , publisher,#PCDATA)`
on pourrait modifier le DTD : 
`!ELEMENT document(title,publisher?,#PCDATA>`
Rmq : le seul contenu mixte (melange texte et elements) autorise est `(#PCDATA |title|publisher)*`

### Ex2 DTD d'une famille 
1) 
#PCDATA=> (chaine element texte)
on pourra definir un simpleType dee type `xs :integer` dans le prochain TD pour `age`
SimpleType `xs:decimal` restriction "2 chiffre apres la virgule"
```xml
<!Element famille (nom, personne+)>
<!Element personne (prenom, age ,poids-kg|poids-lb , taille?)>
<!ATTLIST personne pnumber ID #REQUIRED
					mere ID #IMPLIED
					pere ID #IMPLIED
<!ELEMENT nom (#PCDATA)>
<!ELEMENT prenom (#PCDATA)>
<!ELEMENT age (#PCDATA)> 
<!ELEMENT poids-kg (#PCDATA)> 
<!ELEMENT poinds-lb (#PCDATA)> 
<!ELEMENT taille (#PCDATA)> 
```
2)
`IDREF`: fait reference a texte valeur de type ID
"il existe un attribut id dont la valeur est celle de l'attribut IDREF"
3.
`<!ELEMENT animal(#PCDATA)>`
`<!ATTLIST animal numero ID #REQUIRED`
pb : un seul ensemble de valeurs ID pour les ID de personne + les IDde animal
```
<!ELEMENT famille(nom , personne+ , animal?)>
autre possibilite:
<!ELEMENT famille((nom,((personne+,animal+)|(animal?, personne+)))>
```
4. 
>Un document avec une personne ayant le même numéro qu'un animal, est-il valide ?

non car l'unicite porte sur l'ensemble `{panumber} U {numero}`
5. oui 

### Exercice 4 : DTD générique du modèle relationnel
1.
base Cinema(sql) -> cinema.xml  qui est conforme a la DTD `schema-rel.dtd` valable pour tout schema SQL
`schema-relationnel.dtd`
```dtd 
<!ELEMENT Schema (nom, relation+)>
<!ELEMENT nom (#PCDATA)>
<!ELEMENT relation(attributs+ ,contraintes*)
<!ATTLIST relation nom ID #REQUIRED>
<!ELEMENT attribut ((entier| nombre|chaine|date),notnull? ,default?) > 
<!ATTLIST attribut nom CDATA #REQUIRED>
<!ELEMENT entier EMPTY>
<!ATTLIST entier taille CDATA #IMPIED>
<!ELEMENT contraintes(#PCDATA)>



```
`schama-cinema.xml`
```xml
<!DOCTYPE Schema System "shema-rel.dtd">
<schema>
	<nom>cinema</nom>
	<relation nom ="Realisateur">
		<attribut nom = "nom">
			<chaine taille = "30"/>
		


```

