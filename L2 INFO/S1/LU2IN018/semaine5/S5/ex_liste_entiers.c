
#include <stdlib.h>
#include "liste.h"
#include "devel.h"
#include "fonctions_entiers.h"
#include <assert.h>

int main(void) {

  /* a completer. Exercice 4, question 1 */
 
  PListe pliste = malloc(sizeof(Liste));
  PListe p2 = malloc(sizeof(Liste));
  pliste->dupliquer = dupliquer_int;
  pliste->copier = copier_int;
  pliste->afficher = afficher_int;
  pliste->comparer = comparer_int;
  pliste->ecrire = ecrire_int;
  pliste->lire = lire_int;
  pliste->detruire = detruire_int;

  p2->dupliquer = dupliquer_int;
  p2->copier = copier_int;
  p2->afficher = afficher_int;
  p2->comparer = comparer_int;
  p2->ecrire = ecrire_int;
  p2->lire = lire_int;
  p2->detruire = detruire_int;

  int * i;
  int j; 
  for(j=0 ; j < 5 ; j++){
    i = malloc(sizeof(int));
    *i = j; 
    inserer_debut(pliste, i);

  }

  for(j=10 ; j <15 ; j++){
    i = malloc(sizeof(int));
    *i = j; 
    inserer_fin(pliste, i);
    
  }


  for(j=0 ; j <5; j++){
    i = malloc(sizeof(int));
    *i = j; 
    inserer_place(pliste, i);
    
  }
   for(j=5 ; j <10; j++){
    i = malloc(sizeof(int));
    *i = j; 
    inserer_place(pliste, i);
    
  }



  afficher_liste(pliste);
  
  /*
  *i = 2;
  PElement e1 =  (chercher_liste(pliste, i));
  printf("%d \n ",(int) e1->data);

*/

  ecrire_liste(pliste,"donnee.txt");
  
  printf("###############\n");


  lire_liste(p2,"donnee.txt");

  
  printf("###############\n");
  afficher_liste(p2);
  *i = 2; 

  *i = 100;
  PElement pEl = pliste->elements;
  pliste->copier(i, pEl->data);
  *i = *(int*) pliste->dupliquer(pEl->data);
  assert(*i == 100);

  int *test = malloc(sizeof(int)) ; 
  *test = 200;
  assert(pliste->comparer(i, test) < 0);

  FILE *file = fopen("entier_sortie.txt", "w");
  pliste->ecrire(test, file);
  fclose(file);

  file = fopen("entier_sortie.txt", "r");
  test =(int *) pliste->lire(file);
  fclose(file);
  assert(*test == 200);

  ajouter_liste(pliste, 2, test, i);
    printf("###############\n");

  afficher_liste(pliste);

PElement tete = extraire_en_tete(pliste);
int * data_tete = (int *) tete->data;
printf("%d \n ", *data_tete);

  chercher_liste(pliste,i );
  detruire_liste(pliste);
  detruire_liste(p2);


  return 0;
}
