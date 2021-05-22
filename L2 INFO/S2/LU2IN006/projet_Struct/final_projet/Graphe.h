#ifndef __GRAPHE_H__
#define __GRAPHE_H__
#include<stdlib.h>
#include<stdio.h>
#include "Reseau.h"
#include "Struct_Liste.h"
#include"Struct_File.h"
typedef struct{
  int u,v;        /* Numeros des sommets extremité */
} Arete;


typedef struct cellule_arete{
  Arete *a;     /* pointeur sur l'arete */
  struct cellule_arete *suiv;
} Cellule_arete;


typedef struct {
  int num;                 /* Numero du sommet (le meme que dans T_som) */
  double x, y;
  Cellule_arete* L_voisin;  /* Liste chainee des voisins */
} Sommet;


typedef struct{
  int e1,e2;    /* Les deux extremites de la commodite */
} Commod;


typedef struct{
  int nbsom;        /* Nombre de sommets */
  Sommet* *T_som;    /* Tableau de pointeurs sur sommets */
  int gamma;     
  int nbcommod;     /* Nombre de commodites */
  Commod *T_commod; /* Tableau des commodites */
} Graphe;

Sommet* InitieSommet(int num , double x, double y);
Graphe* creerGraphe(Reseau* r);
int plus_petit_nbChaine (Graphe* g, int u , int v);
ListeEntier chaine_arborescence(Graphe * g, int u , int  v);
int reorganiseReseau(Reseau* r);


void afficher_graph(Graphe* g);
void afficher_matrice_2D(int **tab, int taille);

void liberer_matrice_2D(int **tab, int taille);
void liberer_file(S_file* file);
#endif
