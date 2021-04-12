#ifndef __RESEAU_H__
#define __RESEAU_H__
#include "Chaine.h"


typedef struct noeud Noeud;

/* Liste chainee de noeuds (pour la liste des noeuds du reseau ET les listes des voisins de chaque noeud) */
typedef struct cellnoeud {
    Noeud *nd;               /* Pointeur vers le noeud stock\'e */
    struct cellnoeud *suiv;         /* Cellule suivante dans la liste */
} CellNoeud;

/* Noeud du reseau */
struct noeud{
   int num;                        /* Numero du noeud */
   double x, y;                    /* Coordonnees du noeud*/
   CellNoeud *voisins;             /* Liste des voisins du noeud */
};

/* Liste chainee de commodites */
typedef struct cellCommodite {
    Noeud *extrA, *extrB;       /* Noeuds aux extremites de la commodite */
    struct cellCommodite *suiv;     /* Cellule suivante dans la liste */
} CellCommodite;

/* Un reseau */
typedef struct {
    int nbNoeuds;                   /* Nombre de noeuds du reseau */
    int gamma;                      /* Nombre maximal de fibres par cable */
    CellNoeud *noeuds;              /* Liste des noeuds du reseau */
    CellCommodite *commodites;      /* Liste des commodites a relier */
} Reseau;

Noeud* rechercheCreeNoeudListe(Reseau *R, double x, double y);
Reseau* reconstitueReseauListe(Chaines *C);
void ecrireReseau(Reseau *R, FILE *f);
int nbLiaisons(Reseau *R);
int nbCommodites(Reseau *R);
void afficheReseauSVG(Reseau *R, char* nomInstance);



//fonction elementaire ajouter par moi
CellNoeud * insererNoeud(CellNoeud * liste_nd, Noeud *insere);
/*
typedef struct table {
    int nE;
    int taille; 
    CellNoeud **tab ; // le table hachage
}TableHachage;*/

//fonction pour les tables d'hachage
#include "Hachage.h"
int fonctionCle(double x, double y);
int fonctionHachage(int cle, int m);
Noeud* rechercheCreeNoeudHachage(Reseau* R, TableHachage *H, double x ,double y);
CellNoeud * insererNoeud(CellNoeud * liste_nd, Noeud *insere);
Reseau * reconstitueReseauHachage(Chaines *C, int M);




//fonction pour les arbres quat
#include "ArbreQuat.h"

void chaineCoordMinMax(Chaines* C, double* xmin, double* ymin, double* xmax, double* ymax);
ArbreQuat* creerArbreQuat(double xc, double yc, double coteX,double coteY);
void insererNoeudArbre(Noeud *n, ArbreQuat** a, ArbreQuat* parent);
Noeud* rechercheCreeNoeudArbre(Reseau* R, ArbreQuat** a, ArbreQuat* parent, double x, double y);
Reseau * reconstitueReseauArbre(Chaines *C);


Noeud* creerNoeud(int num, double x, double y);
#endif

