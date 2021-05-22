#include<stdlib.h>
#include"Struct_Liste.h"

void Init_Liste(ListeEntier *L){
  *L=NULL;
}


int estLileVide(ListeEntier *L){
  return (*L==NULL);
}

void ajoute_en_tete(ListeEntier* L, int u){
  Cell_entier *nouvsom=(Cell_entier*) malloc(sizeof(Cell_entier));
  nouvsom->u=u;
  nouvsom->suiv=*L;
  *L=nouvsom;
}


void desalloue(ListeEntier *L){
  Cell_entier *cour,*prec;
  cour=*L;
  while(cour!=NULL){
    prec=cour;
    cour=cour->suiv;
    free(prec);
  }
  *L=NULL;
}
