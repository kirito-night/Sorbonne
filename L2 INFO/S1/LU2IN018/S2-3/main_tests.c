#include <assert.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "ecosys.h"


#define NB_PROIES 20
#define NB_PREDATEURS 20
#define ENERGIE 10




int main(void) {
srand(time(NULL));

  /* A Completer. Exercice 5, question 3 */
  int i; 
  Animal *liste_predateur = NULL;
  Animal *liste_proie = NULL;
  for(i=0;i< NB_PROIES;i++){
  	ajouter_animal(rand()%SIZE_X,rand()%SIZE_Y,ENERGIE,&liste_proie);
  }
  for(i=0;i< NB_PREDATEURS;i++){
  	ajouter_animal(rand()%SIZE_X,rand()%SIZE_Y,ENERGIE,&liste_predateur);
  }
  printf("%d\n",compte_animal_it(liste_predateur));
  printf("%d\n",compte_animal_it(liste_proie));
  assert(compte_animal_it(liste_predateur) == NB_PREDATEURS);
  
  assert(compte_animal_rec(liste_proie) == NB_PROIES);
  
  afficher_ecosys(liste_predateur,liste_proie);
  
  Animal *a1 = creer_animal(1,2,3);
  liste_predateur = ajouter_en_tete_animal(liste_predateur, a1);
  
  afficher_ecosys(liste_predateur,liste_proie);
  //observez dans le coin en haut a gauche
  enlever_animal(&liste_predateur, a1);
  
  afficher_ecosys(liste_predateur,liste_proie);


  liberer_liste_animaux(liste_predateur);
  liberer_liste_animaux(liste_proie);
  
  

  return 0;
}
