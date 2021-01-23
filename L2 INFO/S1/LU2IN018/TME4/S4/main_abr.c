#include <stdio.h>
#include <stdlib.h>
#include "liste.h"
#include "abr.h"

int main(int argc, char **argv)
{

  /* a completer. Exercice 3, question 1 */
  /*Lm_mot *copie = NULL;
  l->suiv = copie; 
  for( int i = 0 ; i < 19 ; i ++){
      copie = lire_dico_Lmot("french_za");
      copie = copie->suiv;
  }
  Nd_mot* dico = Lm2abr(l);
  char * mot_trouver = lire_dico_Lmot("french_za")->mot; 

 for (int i = 0; i < atoi(argv[1]); i++){
    if(mot_trouver == argv[1]){
        printf("Mot trouvé : %s\n", mot_trouver);
    }
 }
*/
  Lm_mot *l = lire_dico_Lmot("french_za");
  Nd_mot *abr;
  int i ;
  Nd_mot *pm1;
  int nbr;
  if(argc != 3 ){
      fprintf(stderr,"usage : main_abr not nb_recherche \n" );
      exit(1);
  }
  char *mot = argv[1];
  nbr = atoi(argv[2]);
  printf("----------------liste construite----------\n");
  abr = Lm2abr(l);
  printf("----------------ABR constuit ----------\n");
  for(i = 0; i< nbr; i++){
      pm1 = chercher_Nd_mot(abr, mot);
  }
  if(pm1 == NULL ){
    printf("le mot :  %s  n'a pas ete trouver dans le dictionnaire\n", mot);
  }
  else{
      printf("le mot : %s  a pas ete trouver dans le dictionnaire\n", mot);
  }

  detruire_abr_mot(abr);

  return 0;
}
