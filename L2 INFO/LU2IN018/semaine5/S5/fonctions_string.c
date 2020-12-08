#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "devel.h"

void *dupliquer_str(const void *src) {
  /* a completer. Exercice 5, question 1 */
  char *s  = (char *)src;
  int i = strlen(s);
  char *dest = malloc(i*sizeof(char));
  if (dest==NULL) {
    affiche_message("Erreur d'allocation");
    return NULL;
  }
  strcpy(dest,s);


  return NULL; // pour que cela compile
}

void copier_str(const void *src, void *dst) {
  /* a completer. Exercice 5, question 1 */
  char * s = (char *) dst; 
  strcpy(s,(char *) src);
}

void detruire_str(void *data) {
  /* a completer. Exercice 5, question 1 */
  free(data);
}

void afficher_str(const void *data) {
  /* a completer. Exercice 5, question 1 */
  char * s = (char *)data;
  printf("%s \n", s);
}

int comparer_str(const void *a, const void *b) {
  /* a completer. Exercice 5, question 1 */
  char *ia=(char *)a;
  char *ib=(char*)b;
  strcmp(ia,ib);
  return 0; // pour que cela compile
}

int ecrire_str(const void *data, FILE *f) {
  /* a completer. Exercice 5, question 1 */
  char * s = (char *)data; 
  fprintf(f, " %s \n", s);
  return 0; // pour que cela compile
}

void * lire_str(FILE *f) {
  /* a completer. Exercice 5, question 1 */
  char *s; 
  int r = fscanf(f, "%s\n", s);
  if(r<1){
    return NULL;
  }
  afficher_str((void *) s);

  return NULL; // pour que cela compile
}
