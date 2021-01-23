#include <assert.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <strings.h>
#include "ecosys.h"



#define NB_PROIES 20
#define NB_PREDATEURS 20
#define T_WAIT 60000
#define ENERGIE 20

/* Parametres globaux de l'ecosysteme (externes dans le ecosys.h)*/



int main(void) {
  srand(time(NULL));
  /* A completer. Part 2:
   * exercice 6, question 1
   * exercice 7, question 2
   * exercice 8, question 3
   * exercice 9, question 1
   */

  int monde[SIZE_X][SIZE_Y];
  int i, j; 
  for(i =0 ; i< SIZE_X ; i++ ){
      for(j = 0 ; j < SIZE_Y ; j++){
          monde[i][j] = 0; 
      }
  }
  
  Animal *liste_proies = NULL;

  for(i=0;i< NB_PROIES;i++){
  	ajouter_animal(rand()%SIZE_X,rand()%SIZE_Y,ENERGIE,&liste_proies);
  }

  Animal *liste_predateurs = NULL;

 for(i=0;i< NB_PREDATEURS;i++){
  	ajouter_animal(rand()%SIZE_X,rand()%SIZE_Y,ENERGIE,&liste_predateurs);
  }
  FILE *file = fopen("Evol_Pop.txt", "w");

  i = 0;
  int nb_proi, nb_pred;
  afficher_ecosys(liste_proies, liste_predateurs);

  while(i<200 && liste_proies && liste_predateurs) {
    	rafraichir_proies(&liste_proies, monde);
      rafraichir_predateurs(&liste_predateurs,&liste_proies);
      reproduce(&liste_proies, p_reproduce_proie);
		  reproduce(&liste_predateurs, p_reproduce_predateur);
      rafraichir_monde(monde);
      usleep(T_WAIT);
      
      nb_proi = compte_animal_it(liste_proies);
      nb_pred = compte_animal_it(liste_predateurs);
      afficher_ecosys(liste_proies,liste_predateurs);
      fprintf(file,"nb_iteration : %d  ;  nb_predateur : %d  ;  nb_proie : %d \n", i, nb_pred, nb_proi);
      printf("predateur : %d , proie : %d", nb_pred, nb_proi);
      printf("\n");
      i++;
  }
  liberer_liste_animaux(liste_proies);
  liberer_liste_animaux(liste_predateurs);
  fclose(file);

  return 0;
}

