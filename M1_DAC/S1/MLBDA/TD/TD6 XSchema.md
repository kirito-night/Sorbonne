### Ex 1 
```xml
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">  
<xs:element name="A">  
<xs:complexType mixed="true">  
<xs:choice minOccurs="2" maxOccurs="3">  
<xs:sequence>  
<xs:element name="B" minOccurs="0"type="xs:integer"/>  
</xs:sequence>  
<xs:sequence>  
<xs:element name="C" maxOccurs="3" type="myint"/>  
<xs:element name="D" minOccurs="0" type="xs:integer"/>  
</xs:sequence>  
</xs:choice>  
</xs:complexType>  
</xs:element>  
<xs:simpleType name="myint">  
<xs:restriction base="xsd:integer">  
<xs:minExclusive value="1"/>  
<xs:maxInclusive value="6"/>  
</xs:restriction>  
</xs:simpleType>  
</xs:schema
```
1. `<A><B/><B/></A><A><B/><B/></A>`
- A 
	- B
	- B
	non car B est un entier qui ne peut pas etre vide
2. `<A><C>1</C><D>2</D></A>`
- A
	- C
	- D
	non car la valeur de C doit etre dans ]1,6]
3. `<A>abc<C>6</C>def<C>2</C><B>0</B></A>`
- A
	- 'abc'
	- C 6
	- "..."
	- C 2
	- B 0
oui car A a un contenue mixte `mixted = true`
on peut avoir des avoirs des fils directes de A qiu sont des noeuds de texte

4. `<A><C>6</C><C>7</C><C>5</C><C>4</C>abc<D>3</D></A>`
- A 
	- C
	- C 7 `not in ]1 , 6]`
	- C
	- ".."
	- D
faux
5. `<A><B>7</B></A`
oui
6. `<A><B>1</B><D>1</D>hello<C>2</C>bonjour</A>`
- A 
	- B
	- D `non valid car D doit preceder un C`
	- "hello"
	- C 2
	- "bonjour"
7. `<A><B>abc</B><C>2</C><D>2</D></A>`
	- A 
		- B "abc" `non entier`
	non , B doit etre entier 
8. `<A><B>2</B><C>2</C></A>`
- A
	- B 
	- C
oui 

### Ex 2
on va modifier le code pour verifier les contraintes
```xml
<?xml version="1.0" encoding="UTF-8"?>  
2 <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">  
3 <xs:element name='magasin'>  
4 <xs:complexType>  
5 <xs:sequence>  
6 <xs:element name='clients'>  
7 <xs:complexType>  
8 <xs:sequence>  
9 <xs:element name='client' type='ClType'  
minOccurs='0' maxOccurs='unbounded' />  
10 </xs:sequence>  
11 </xs:complexType>  
12 </xs:element>  
13 <xs:element name='commandes'>  
14 <xs:complexType>  
15 <xs:sequence>  
16 <xs:element name='commande' type='CdeType'  
minOccurs='0' maxOccurs='unbounded' />  
17 </xs:sequence>  
18 </xs:complexType>  
19 </xs:element>  
20 </xs:sequence>  
21 </xs:complexType>  
22 </xs:element>  
23 <xs:complexType name='ClType'>  
24 <xs:sequence>  
25 <xs:element name='nom' type='xs:string'/>  
26 <xs:element name='prenom' type='xs:string'/>  
27 <xs:element name='dateNaissance' type='xs:string'/>  
32 </xs:sequence>  
33 <xs:attribute name='clientID' type='xs:integer'/>  
34 </xs:complexType>  
35 <xs:complexType name='CdeType'>  
36 <xs:sequence>  
37 <xs:element name='clientID' type='xs:integer'/>  
38 <xs:element name='dateCommande' type='xs:date'/>  
39 <xs:element name='dateLivraison' type='xs:date'/>  
40 <xs:element name='article' type='xs:string'/>  
41 <xs:element name='cout'> <!--ligne 41 modifier -->
	<xs : simpleType>
		<xs : restriction base  ="xsd : integer">
			<minInclusive value ="10">
			<maxExclusive value = "unbound">  <!--ici non necessaire-->
		</xs : restriction >
	</xs :simpleType>
42 </xs:sequence>  
43 </xs:complexType>  
44</xs:schema>
```
1. Le coût d’une commande doit être supérieur ou égal à 10.
 - cf . ligne 41 
2.  Un document ne peut pas contenir deux fois le même client
	- definir une contranite d'unicite sur l'attribut clientID
	- inserer un element  `<xs : unique>` entre les lignes  en tant que fils direct d'un element `<xs : element>` celui qui definit clients (au pluriel)
	- et apres le fils `<xs : complexType>` entre les lignes 11 et 12 
	- Remarque : on prefere definir une contrainte `<xs : key>` plutor qu'une `<xs  : unique>` car on aura besoin de la cle de client dans la question d)
```
<xs: key name="cleClient">
	<xs : selector  xpath = "client"/> <!-- permet d'atteindre les elements client a partir de clients-->
	<xs : field xpath: "@clientID"/> <!-- atteindre la valeur de la cle a partir de client(au singulier)-->
</xs :key>
```
3. n'est pas faisable 
4. Pour qu’une commande soit valide, elle doit concerner un client existant.
- Commande
	- clientId 
	- dateCommande
- Definir  un element `<xs : keyref>` dans la definition de l'element permettant d'atteindre les  elements commande
- Inserer `<xs : keyref>` entre les lignes 18 et 19 
```xml
<xs: keyref name= 'refClient' refer= "Cleclient">
	<xs : selector xpath = "commande"/>
	<xs : field xpath = "clientID"/>
</xs keyref>
	
```
### Ex3 
- exemple de donne 
```xml
1<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">  
2 <xs:element name="personnes">  
3 <xs:complexType>  
4 <xs:sequence maxOccurs="unbounded" >  
5 <xs:element ref="personne"/>  
6 </xs:sequence>  
7 </xs:complexType>  
8 </xs:element>  
9  
10 <xs:complexType name="T-Personne">  
11 <xs:sequence>  
12 <xs:element name="nom" type="xs:string"/>  
13 <xs:element name="prénom" type="xs:string"/>  
14 <xs:element name="dateN" type="xs:date"/>  
15 <xs:element name="genre" type="T-genre" />  
16 <xs:element name="enfants" type="T-enfants"/>  
17 </xs:sequence>  
18 <xs:attribute name="idP" type="xs:string" use="required" />  
19 </xs:complexType>  
20  
21 <xs:element name="personne" type="T-Personne"/>  
22</xs:schema>
```
```
<personne>
	<nom> dupont </nom>
	<prenom> bobs <date/N>
	<dateN> 01/01/2002 <dateN>
	<genre> M</genre> type simple
	ou 
	<genre g='M'/> type  complex
	ou 
	<genre> <M/></genre> type complex
```
- question 1
Définissez le type T-genre, sachant que l’élément genre peut prendre les valeurs  
‘masculin’ ou ‘féminin’.
```xml
<xs : simpleType name = T-genre>
	<xs : restriction base= "xs : string"> type simple restrictrion par defaut
		<xs : enumeration value = 'M'/>
		<xs : enumeration value = 'F'/>
	</xs:restriction>
</xs : simpleType>
```
ou 
```xml
<xs : complexType name = T-genre>
	<xs : attribute name= 'g' type  = 'T-genre'/>
</xs : complexType>
 
```

### Ex5 
de Xpath vers DTD on ne peut pas toujours car Xpath est plus general 
de DTD vers XPath est possible 
