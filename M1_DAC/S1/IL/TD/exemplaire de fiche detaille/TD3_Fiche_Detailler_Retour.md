- [Titre UC02 Retour](#titre-uc02-retour)
  - [Acteur](#acteur)
  - [Description (Optionel)](#description-optionel)
  - [Preconditon](#preconditon)
  - [Postcondition](#postcondition)
  - [Scenario Nominal](#scenario-nominal)
  - [Alternatives](#alternatives)
    - [A1  (Numero N'Existe Pas)](#a1--numero-nexiste-pas)
    - [A2 (Non Emprunté)](#a2-non-emprunté)
    - [A2 (Etat Deplorable)](#a2-etat-deplorable)
  - [Execptions](#execptions)
    - [EX1](#ex1)
# Titre UC02 Retour

## Acteur

- **Bibliothécaire**

## Description (Optionel)
Permet d'enregister le retour d'un exemplaire par le **Bibliothécaire**.

## Preconditon

$VOID$ || L'utilisateur est log en tant que **Bibliothécaire**

## Postcondition

Le retour du document est enregistrer

## Scenario Nominal

1. Le **Bibliothécaire** *choisit* "Retour Emprunt"
2. Le **Systeme** *affiche* un formulaire permettant d'entrer le numéro de l'exemplaire et son état
3. Le **Bibliothécaire** *choisit* "Ok"
4. Le **Systeme** *verifie* que le numéro de l'exemplaire existe
5. Le **Systeme** *verifie* l'exemplaire est bien en prêt
6. Le **Systeme** *verifie* que le l'état de l'exemplaire n'a pas perdu plus de 3 niveaux
7. Le **Systeme** *verifie* que l'exemplaire n'est pas en retard
8. Le **Systeme** met a jour l'état de l'exemplaire et met a jour la liste des emprunt
9. Le **Systeme** *affiche* un formulaire permettant d'entrer le numéro de l'exemplaire et son état
10. Le **Bibliothécaire** *choisit* "Quitter"
11. Le **Systeme** *affiche* le menu

## Alternatives

### A1  (Numero N'Existe Pas)
1. Le **Systeme** decouvre que numéro d'exemplaire n'existe pas.
2. Le **Systeme** affiche un message "L'exemplaire n'existe pas"
3. Retour en SN2

### A2 (Non Emprunté)
1. Le **Systeme** decouvre que l'exemplaire n'a pas été emprunté.
2. Le **Systeme** affiche un message "L'exemplaire n'a jamais été emprunté"
3. Retour en SN2

### A2 (Etat Deplorable)
1. Le **Systeme** decouvre que l'etat de l'exemplaire a perdu 3 niveau.
2. Le **Systeme** affiche un message "L'exemplaire a été endommager"
3. Le **Systeme** calcule le montant de l'amande basé sur une grille ????
4. Le **Systeme** affiche un message decrivant le montant de l'amande
5. Retour en SN2


## Execptions

### EX1
1. SN3 Le **Bibliothécaire** *choisit* "Quitter"
2. Le **Systeme** *affiche* le menu