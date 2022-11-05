# Titre UC01 Enregistrer Emprunt.

- [Titre UC01 Enregistrer Emprunt.](#titre-uc01-enregistrer-emprunt)
  - [Auteur/Date : Bibi, 05/10 (Optionel)](#auteurdate--bibi-0510-optionel)
  - [Description (Optionel)](#description-optionel)
  - [Acteurs](#acteurs)
    - [PreCondition](#precondition)
    - [Scenario Nominal](#scenario-nominal)
    - [PostCondition](#postcondition)
  - [Alternatives](#alternatives)
    - [A1 (Capacité dépassé)](#a1-capacité-dépassé)
    - [A2 (Interdit)](#a2-interdit)
    - [Ax (Plusieurs exemplaire)](#ax-plusieurs-exemplaire)
    - [Exercice Ajouter les autres alternatives](#exercice-ajouter-les-autres-alternatives)
  - [Exceptions](#exceptions)
    - [EX1 (Annulation)](#ex1-annulation)
    - [Exercice Ajouter les autres exceptions](#exercice-ajouter-les-autres-exceptions)

## Auteur/Date : Bibi, 05/10 (Optionel)

## Description (Optionel)
Permet d'enregister l'emprunt d'un exemplaire par le **Bibliothécaire**.

## Acteurs  
- **Bibliothécaire**

### PreCondition
$VOID$ | Etre log en tant que **Bibliothécaire**

### Scenario Nominal

1. Le **Bibliothécaire** choisit "Emprunt"
2. Le **Systeme** *affiche* un formulaire permettant de saisir l'identifiant d'abonné
3. Le **Bibliothécaire** saisit le numéro d'abonné et ensuite valide
4. Le **Systeme** *verifie* que l'identifiant abonné est valide
5. Le **Systeme** *verifie* que l'abonné n'a pas atteint sa capacité d'emprunt
    - 1 si Abo dit Occasionnel
    - 4 si Abo dit Ordinaire
    - 8 si Abo dit privilégié
6. Le **Systeme** *verifie* que l'abonné n'est pas interdit
7. Le **Systeme** *verifie* que l'abonné n'a pas d'emprunt en retard
8. Le **Systeme** *affiche* les infos sur l'abonné :
   - idAbo,nom, catégorie la liste de ses emprunts avec pour chacun date de debut/fin
9. Le **Bibliothécaire** choisit "Ajouté Emprunt"
10. Le **Systeme** *affiche* un formulaire de saisi de l'identifiant de l'exemplaire
11. Le **Bibliothécaire** saisit un identifiant et valide
12. Le **Systeme** *verifie* que l'identifiant est valide
13. Le **Systeme** *verifie* que l'exemplaire est bien "en rayon"\
     *(Verifier sur le diagramme classe métier que cela existe)*
    
14. Le **Systeme** enregistre l'emprunt de cet exemplaire par cet abonné, date de fin depend de la catégorie de l'abonné
    - 15 Jours si Abo dit Occasionnel
    - 1 Mois si Abo dit Ordinaire
    - 1 Mois si Abo dit privilégié\
    Et passe l'exemplaire au statut en prêt.

15. Le **Systeme** *affiche* les infos sur l'abonné mise a jour (**cf SN8**) pour la description
16. Le **Bibliothécaire** choisit de "Terminer"
17. Le **Systeme** *affiche* l'ecran d'acceuil

### PostCondition
L'emprunt du ou des exemplaires par l'abonné est enregistré, le statut des exemplaire est mis "En Pret"

## Alternatives
### A1 (Capacité dépassé)
1. En SN5 si la capacité est atteinte
2. Le **Systeme** *affiche* "La Capacité de l'abonné est dépassée"
3. Reviens sur la description de l'utilisateur avec le bouton ajouté désactivé retour en **cf. SN8**

### A2 (Interdit)
1. En SN6 l'abonné est interdit
2. Le **Systeme** *affiche* "L'abonné est interdit."
3. Reviens sur la description de l'utilisateur avec le bouton ajouté désactivé retour en **cf. SN8**

### Ax (Plusieurs exemplaire)
1. SN16 le **Bibliothécaire** choisit d'ajouté emprunt
2. Le **Systeme** reviens en **SN8**

### Exercice Ajouter les autres alternatives

## Exceptions

### EX1 (Annulation)
1. En SN3 le **Bibliothécaire** peut annuler
2. Le **Systeme** retourne a l'acceuil

### Exercice Ajouter les autres exceptions