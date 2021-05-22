#include <stdlib.h>
#include <stdio.h>
#include "Struct_File.h"

void Init_file(S_file *f){
  f->tete=NULL;
  f->dernier=NULL;
}

int estFileVide(S_file *f){
  return f->tete == NULL;
}

void enfile(S_file * f, int donnee){
 Cellule_file *nouv=(Cellule_file *) malloc(sizeof(Cellule_file));
  nouv->val=donnee;
  nouv->suiv=NULL;
  if (f->tete==NULL)
    f->tete=nouv;
  else
    f->dernier->suiv=nouv;
  f->dernier=nouv;
}


int defile(S_file *f){
  int v=f->tete->val;
  Cellule_file *temp=f->tete;
  if (f->tete==f->dernier)
    f->dernier=NULL;
  f->tete=f->tete->suiv;
  free(temp);
  return v;

}
