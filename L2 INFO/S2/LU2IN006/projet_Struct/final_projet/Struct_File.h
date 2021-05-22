#ifndef _STRUCT_FILE_H
#define _STRUCT_FILE_H

/* Structure d une file contenant un entier */
typedef struct cellule_file {
   int val;
   struct cellule_file *suiv;
} Cellule_file;

typedef struct{
   Cellule_file* tete;  /* pointeur sur la tete de la liste */
   Cellule_file* dernier;  /* pointeur sur le dernier element de la liste */
} S_file;


/* Initialisation d une file */
void Init_file(S_file *f);


/* Teste si la file est vide */
int estFileVide(S_file *f);

/* Ajoute un element don`e en fin de file */
void enfile(S_file * f, int donnee);

/* Supprime le premier element de la file et retourne sa valeur */
/* PREREQUIS: la file ne doit pas etre vide */
int defile(S_file *f);


#endif 
