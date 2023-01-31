> bear://x-callback-url/open-note?id=F0528FE8-45DB-41E0-91CB-0411F3177AA2-16663-0000034BC5CC03C6

# MLBDA cours 1
### type de traitement 
![](&&&SFLOCALFILEPATH&&&EA07C7ED-DBDB-4E0D-B045-9DD64526ED05.png)

* transaction ( banque , assurances , gestion de stock) , interaction ( web, jeux , réseaux socio)  , analyses ( marketing statistiques, machine learning)
### application sciences de données 
![](&&&SFLOCALFILEPATH&&&AC641C50-7B05-43EA-8E6B-8E162BBA5052.png)
1. Collection des données et les stockés
2. Analyse supervisé (extraction de modele) et non supervisé ( extraction de structure )
3. Application. : decision, prediction , recommandation et visualisation après  apprentissage

## Evolution des modèles et systèmes
* Architecture classique 
![](&&&SFLOCALFILEPATH&&&A0581E90-19FA-4D8B-9E99-87CA13616E19.png)
	* Couche physique : 
![](&&&SFLOCALFILEPATH&&&Screenshot%202022-09-14%20at%2016.32.02.png)

	* Couche logique :
![](&&&SFLOCALFILEPATH&&&86A5D880-436A-44AB-BB6A-4B5BAB4AA4D4.png)
* evolutions recentes : 
	1. virtualisation des services : cloud computing
	
![](&&&SFLOCALFILEPATH&&&5EAA3E0F-9F4B-4C15-9C93-70AA4E208213.png)
**=> Séparation entre les programmes informatiques et leur environnement d’exécution**
	2.  Parallelisation des traitements : 
	
![](&&&SFLOCALFILEPATH&&&CCB21AB7-E567-49B7-B987-EFD0B838A3F8.png)
**=> Augmentation des performances**
	3. System Big Data 
![](&&&SFLOCALFILEPATH&&&5DDF364B-6F08-49B6-BD74-F526ACB37C1F.png)
		* SGBD classique : architecture verticale , serveurs centraux avec grande capacité de stockage et de calcul; separation entre la couche de calcul et la couche de stockage ; 
			* donnes structurées (tables) => Difficile a adapter a l’evolution des 	donnés et des besoins 
		* Big Data : Architecture parallèle Réseau extensible de nœuds avec « faibles » capacités de calcul et stockage Virtualisation des ressources et services Données hétérogènes (tables, documents, graphes, …)
			* => Adaptation à l’évolution des données et besoins Coût 		proportionnel au bénéfice

## Modéliser et interroger des données
* modele relationnel : SQL
![](&&&SFLOCALFILEPATH&&&FAF937B8-B061-44FB-99B3-BAB8568EFFFF.png)
	* apport modele  relationnel : logique première ordre  , language déclaratif , système efficaces ( SGBD) 
		* ⇒ Un standard et une technologie efficace, sûre, éprouvée pour gérer des données structurées
	* Limite du modele relationnel : 
		1. Modélisation d’information complexes difficile : nombreuse jointure, éclatement  des entités information dispersées, information non factorisees , redondant
		2. 1ere forme normale : attribut de type simple  ( entier, chaine, reels , date)  mais extension difficile 
	- Limite de SQL :
		- théorique : expressivité limitée : pas de récusions / itération
		- pratique : interactivité limitée : utilisation difficile pour les non experts
		- solution : sql + language de programmation ( python , java ) 

## Approche noSQL (not only sql) 
* NoSQL : Not only SQL
Représentation « proche » des information et des besoins applicatifs :
	* Données hétérogènes : tables, graphes, documents, images
	* Nouveaux traitements : échange, transformation, intégration
* Nouveaux modèles et langages
◦ Modèles de données « plus riches » : documents, arbres, graphes
◦ Schéma de données : validation, intégration, transformation,
inférence, …
◦ Langages de requêtes : données et structure (schéma), motifs
de graphes, navigation, exploration, ..

![](&&&SFLOCALFILEPATH&&&991E29A3-33F4-40B7-990C-E02F82F02A15.png)
* systeme NoSQL 
![](&&&SFLOCALFILEPATH&&&5D2F972D-4F36-4F68-B354-D249BA8A80E2.png)
	*  modele relationnel
	
![](&&&SFLOCALFILEPATH&&&D4364004-B264-4D66-8526-BB6F5BEEE091.png)
	* XML 
![](&&&SFLOCALFILEPATH&&&61A85974-C0AD-43AB-AFB4-03D613370D76.png)
	- JSON 
	
![](&&&SFLOCALFILEPATH&&&A693F103-AC30-459A-8AAF-B90C5315C87A.png)
	- RDF 
![](&&&SFLOCALFILEPATH&&&3A1A0FDB-50E6-46A7-B881-F4F6BE7601F4.png)
		
![](&&&SFLOCALFILEPATH&&&A7256A01-2B3C-405E-8AC9-DB9190B90291.png)




	






