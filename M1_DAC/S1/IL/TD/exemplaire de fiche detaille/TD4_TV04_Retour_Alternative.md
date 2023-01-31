# Titre TV04 Retour Emprunt (Capacité Dépassé)

## Contexte
- u42 abonné occasionel sans emprunt
- Narnia_vol1_EX1 un exemplaire dit "en rayon"
- Narnia_vol2_EX2 un exemplaire dit "en rayon"

## Entrée

## Scenario
1. Choisir "Emprunter"
2. Saisir idAbo : u42
3. Saisir l'idEx : Narnia_vol1_EX1
4. Choisir "Ajouter emprunt"
5. Saisir l'idEx : Narnia_vol2_EX2

## Resultat Attendu
- Le Systeme **affiche** que u42 a déja dépassé sa capacité d'emprunt
- L'emprunt de l'exemplaire Narnia_vol1_EX1 est enregistré
- L'emprunt de l'exemplaire Narnia_vol2_EX1 n'est pas enregistré
- Le systeme **affiche** la fiche abonnée de u42 avec seulement l'emprunt de Narnia_vol1_EX1

## Moyen de Verifivation
- Verification visuel du message de capacité dépassé
- Verification visuel que la page abonnée n'affiche que l'emprunt de Narnia_vol1_EX1
- Emprunter Narnia_vol2_EX1 avec un autre abonnée $\rArr$ TV02
- Retour Narnia_vol1_EX1 $\rArr$ TV03