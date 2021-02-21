#include <stdlib.h>
#include <stdio.h>
//exercice 1 
typedef struct Cellule *PCellule;
typedef struct Cellule {
    int data;
    struct Cellule* suivant;
    struct Cellule* precedent;
    
}Cellule;

typedef struct  {
    struct Cellule* tete;
    struct Cellule* fin;
    
}List;


Cellule * creerCellule(int data){
    PCellule cell = (PCellule) malloc (sizeof(Cellule));
    cell->data = data;
    cell->precedent = NULL;
    cell->suivant = NULL;

    return cell;
}


List* creerListe(){
    List* res = (List*)malloc(sizeof(List));
    res->tete = NULL;
    res->fin = NULL;
    return res;
}


int listeVide(List* liste){
    if (!liste->tete && !liste->fin){
        return 1;
    }
    else return 0;
}

void insereEnTete(List *liste, Cellule * cell){
    if(listeVide(liste)){
        liste->tete = cell;
        liste->fin = cell;
        return ;
    }
    cell->suivant = liste->tete;
    cell->precedent = NULL;
    liste->tete->precedent = cell;
    liste->tete = cell;
    
    
}

void insereEnFin(List *liste, Cellule * cell){
    if(listeVide(liste)){
        liste->tete = cell;
        liste->fin = cell;
        return ;
    }
    liste->fin->suivant = cell;
    cell->suivant = NULL;
    cell->precedent =liste->fin;
    liste->fin = cell;
    
}

void afficher(List *liste){
    Cellule *tmp = liste->fin;
    while(tmp!= NULL){
        printf("%d \n" ,tmp-> data);
        tmp = tmp->precedent;
    }
}


Cellule *rechercher(List * l, int n){
    if(listeVide(l)){
        return NULL;
    }
    Cellule *tmp = l->tete;
    while (tmp != NULL){
        if(tmp -> data == n){
            return tmp;
        }
        tmp = tmp -> suivant;

    }

    return NULL;
    
}

void supprimerElement(List *l, int n){
    if(l->tete->data == n){
        Cellule *t = l->tete->suivant;
        free(l->tete);
        l->tete = t;
        return;
        
    }
    Cellule * tmp = l->tete, *tmpp = NULL,*tmps = NULL;
    
    while (tmp ->suivant != NULL){
        if(tmp->suivant-> data == n ){
            tmpp = tmp;
            tmps = tmp->suivant->suivant;
            free(tmp->suivant);
            tmp->suivant = tmps;
            tmps->suivant->precedent =tmpp;
            break;
        }
    }
    return;
}



void supprimerElement_cellule(List * l, PCellule target){
    PCellule prec = target->precedent;
    PCellule next = target->suivant;
    
    if(prec){
        prec->suivant = next;
    }else{
        l->tete = next;
    }
    if(next){
        next->precedent = prec;
    }else{
        l->fin = prec;
    }
    free(target);
}


void supprimerTete(List *l){
    supprimerElement_cellule(l, l->tete);    
}
void supprimerFin(List *l){
    supprimerElement_cellule(l, l->fin);    
}

void desalloueListe(List *l){
    Cellule * tmp = l->tete;
    Cellule *tmp_next = NULL;
    while (tmp !=NULL){
        tmp_next = tmp->suivant;
        free(tmp);
        tmp = tmp_next;
    }

    free(l);
}


//exercice2

//2.1 un tableau de liste

typedef struct poste {
    int nb;
    List * guichets;
}Poste;

Poste * creerBureauPoste(int nb);

void afficherPoste(Poste * poste){
    for(int i = 0 ; i < poste->nb ; i++){
        afficher(&(poste->guichets)[i]);
    }
}

void ajouterAuGuichet(Poste *poste, int i, int ident ){
    insereEnTete(&(poste->guichets)[i], ident );
}

int appelerAuGuicher(Poste *poste, int numGuichet){
    List *tmp = &(poste->guichets)[numGuichet];
    if(listeVide(tmp)){
        return -1;
    }
    return supprimerFin(tmp);
}