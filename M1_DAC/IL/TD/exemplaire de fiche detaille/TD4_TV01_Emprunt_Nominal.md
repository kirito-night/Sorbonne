- [Titre TV01 Emprunt Nominal par abo privilégié](#titre-tv01-emprunt-nominal-par-abo-privilégié)
  - [Contexte :](#contexte-)
    - [How ?](#how-)
    - [Exemple](#exemple)
  - [Scenario](#scenario)
    - [How ?](#how--1)
    - [Exemple](#exemple-1)
  - [Resultat Attendu](#resultat-attendu)
    - [How ?](#how--2)
    - [Exemple](#exemple-2)
  - [Moyen de Validation](#moyen-de-validation)
    - [How ?](#how--3)
    - [Exemple](#exemple-3)

# Titre TV01 Emprunt Nominal par abo privilégié

## Contexte :
- Decrire l'etat de l'application au moment ou on démarre le teste
    - Etat des classes métier.

### How ?
1. Donner un scenario qui mène le **Systeme** vers cet etat
2. Precisé l'etat du sytème formellement ?

### Exemple

- u0007 est un abonné privilégié sans emprunts.
- Narnia_vol1_EX1 est un exemplaire "en rayon"

## Scenario

### How ?
- Un scenario nominal mais uniquement avec les actions du testeur
  - On ne s'interesse pas au **Systeme** ici


### Exemple
1. Choisir "emprunter"
2. Saisir idAbo: u007 et valider.
3. Saisir idEx: Narnia_vol1_EX1 et valide.

## Resultat Attendu
Objectif decrire la sortie du **Systeme**

### How ?
Expliqué les resultat attendus

### Exemple
- Le **Systeme** *affiche* la description de u007 on voit l'exemplaire dans ses emprunts.
- L'emprunt de Narnia par u007 est enregistré

## Moyen de Validation
Decrire comment l'on verifie le resultat attendu

### How ?
Expliqué comment l'on verifie les resultats

### Exemple

- Affichage controle visuel de la sortie
- Verifier l'echec du réemprunt du même exemplaire. $\rArr$ cité le test $TV02$ Nominal
- Verifier la réussite du retour du même exemplaire $\rArr$ cité le test $TV03$ Nominal