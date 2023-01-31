>1. L'ensemble des couleurs des produits (ensemble signifie pas de doublons).
```sql
select distinct color 
From product 
```
> 2- La liste des prix des 10 premiers produits triés par prix décroissant.
```sql 
select name, unitPrice 
From product 
order by unitPrice desc
limit 10
```
> 3  Les identifiants des produits (productId) ayant au moins une note (rating) de 5. Les identifiants de produits sont tous distincts dans le résultat.
```sql 
select productId
From reviews 
where rating = 5
```
> 4  Les identifiants des clients (customerId) ainsi que leurs emails (emailAddress) qui se terminant par '@herman.com'.
- LIKE "%" qui permet de chercher les contenus d'un string 
```sql 
select customerId
From customer
where emailAddress LIKE '%@herman.com'
```
>  5 Les identifiants des clients (customerId) qui ont acheté exactement 5 produits différents. Les produits achetés sont renseignés par purchases.lineItems. Ne retourner que les customerId uniques.
```sql 
select  p.customerId
From purchases p unnest lineItems as item
group by p.customerId
having count(distinct item.product) =5
```

```sql 
select distinct customerId
From purchases
where array_length(lineItems) =5
```
> 6  Les identifiants des clients (customerId) ayant acheté au moins un produit avec une quantité > 4. Ne retourner que les customerId uniques.
```sql 
select  p.customerId, count(*)
From purchases p unnest lineItems as item
where item.count > 4
```
> 7 - Les identifiants des clients (customerId) dont tout les produits achetés le sont avec une quantité > 4.
```sql 
select customerId
from purchases 
where Every item in lineItems satisfies item.count > 4 end

```
>8.a) Les paires identifiants de produits (productId) et catégorie. Retourner une paire pour chaque combinaison de `productId` et de chaine de caractères dans le tableau correspondant à `categories`.
```sql 
select p.productId, c
From product p unnest categories as c 
```
> 8.b) Les identifiants des clients (customerId) et les produits achetés. Chaque client sera retourné autant de fois qu'il a acheté de produits.
```sql 
select p.customerId, item
From purchases p unnest lineItems as item 
```
>  9 -  Les identifiants des clients (customerId) et la liste des produits achetés ; cette liste est obtenue en projetant l'attribut product de lineItems.
``` sql 
select p.customerId, array for item in lineItems end as pd
From purchases p 
group by customerId
```
### jointure 
> 10- L'identifiant des clients (customerId), leur état de résidence (state) et la liste des produits achetés (lineItems). Compléter la requête ci-dessous.
```sql 
select 
From 
```