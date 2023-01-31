# Titre TV02 Emprunt Alternative (En Prêt)

## Contexte
Le Test TV01 Emprunt Nominal par u007 vient d'être executé
- u042 est un abonné occasionel sans emprunts
- Narina vol1_EX1 est a la suite du test TV01 un exemplaire déja emprunter 

## Entrée 

- iAbo : u042
- idExemplaire : Narina vol1_EX1

## Scenario

1. Choisir "Emprunter"
2. Saisir "Numero Abonné" u42
3. Saisir "Exemplaire" Narnia_vol1_EX1


## Resultat Attendu
- Le **Systeme** affiche un message indiquant que l'exemplaire est déja en prêt.
- L'emprunt existant ne subit aucun changement et u42 voit son nombre d'emprunt inchangé


## Moyen de Validation
- Controle visuel de l'affiche du bon message
- Lancer un emprunt avec u007 afin de verifier que l'emprunt est toujours valable