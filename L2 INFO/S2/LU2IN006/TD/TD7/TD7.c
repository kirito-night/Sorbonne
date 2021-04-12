#include<stdlib.h>
#include<stdio.h>
typedef struct noeud {
    int valeur ;
    struct noeud * fg ;
    struct noeud * fd ;

    int hauteur; //1.4 besoin de hauteur de l'arbre pour AVL
} Noeud ;
typedef Noeud * ABR ;
//1.1
Noeud * rechercherValeur(ABR ab, int val){
    if(ab ==NULL){
        return NULL;
    }
    if(ab->valeur == val){
        return ab;
    }
    if(val<ab->valeur){
        return rechercherValeur(ab->fg, val);
    }
    return rechercherValeur(ab->fd, val);
}

//1.2
void insereElem_ss_eq(ABR *ab , int val){
    if(ab == NULL){
        *ab = (ABR)malloc(sizeof(Noeud));
        (*ab)->valeur = val;
        (*ab)->fg = NULL;
        (*ab)->fd = NULL;
    }

    if(val< (*ab)->fd){
        insereElem_ss_eq(&(*ab)->fg, val);
    }
    else{
        insereElem_ss_eq(&(*ab)->fd,val);
    }
    
}
/* correction  
void insertion_ss_eq(ABR* tree, int val)
{
    if(*tree == NULL) {
        Noeud* n = (Noeud*)malloc(sizeof(Noeud));
        n->valeur = val;
        n->fg = NULL;
        n->fd = NULL;
        *tree = n;
    } else {
        if(*tree->valeur > val) insertion_ss_eq(&((*tree)->fg), val);
        else                    insertion_ss_eq(&((*tree)->fg), val);
    }
}*/

//1.4
inline int max (int a , int b){
    return (a>b)? a :b;
}

//(condition) ? (si c'est vrai) : si c'est faux
int AB_hauteur(ABR ab){
    return (!ab) ? -1 : ab->hauteur;
}

int AB_calcul_hauteur(ABR ab){
    if(ab == NULL){
        return 0;
    }
    return  1 + max(AB_hauteur(ab->fd) , AB_hauteur(ab->fd));
}

void majHauteur(ABR ab){
    
    ab->hauteur = AB_calcul_hauteur(ab);
}

void rotationdDroite(ABR * ab){
    ABR g = (* ab)->fg;
    (*ab)->fg = g->fd;
    g->fd = (*ab);
    *ab = g;
    majHauteur(*ab);
    majHauteur((*ab)->fd);
}

void rotationGauche(ABR * ab){
    ABR d  = (*ab) -> fd;
    (*ab) -> fd - d->fg;
    d->fg = (*ab);
    *ab = d;
    majHauteur(*ab);
    majHauteur((*ab)->fg);
}
/*
void insererElem_avec_eq(ABR *ab, int v){
    insereElem_ss_eq(ab,v);
    /*if(*ab ==NULL){
        *ab = (ABR)malloc(sizeof(Noeud));
        (*ab)->valeur = v;
        (*ab)->fg = NULL;
        (*ab)->fd = NULL;
    }*//*
    int hauteur_diff = AB_hauteur((*ab)->fg) - AB_hauteur((*ab)->fd);
    if(hauteur_diff < 2 ){
        
        
        insererElem_avec_eq(ab,v);

    }
    else{
        if(hauteur_diff == 2 ){
            Noeud *g  = (*ab)->fg->fg;
            Noeud *d  = (*ab) -> fd -> fd;
            if(AB_hauteur(g) < AB_hauteur(d)){
                rotationGauche((*ab)->fg);
            }
            rotationdDroite(*ab);

        }

    }
}*/
void insertion_eq(ABR* ab, int val)
{
    if(*ab == NULL) {
        Noeud* n = (Noeud*)malloc(sizeof(Noeud));
        n->valeur  = val;
        n->fg      = NULL;
        n->fd      = NULL;
        n->hauteur = 0;
        *ab = n;
    } else {
        if( (*ab)->valeur > val) insertion_eq(&((*ab)->fg), val);
        else                       insertion_eq(&((*ab)->fd), val);
        majHauteur(*ab);

        int dh = hauteur( (*ab)->fg ) - hauteur( (*ab)->fd );
        if(dh == 2) { // rot gauche droite
            if(hauteur( (*ab)->fg->fg ) < hauteur( (*ab)->fg->fd )) {
                rotationGauche((*ab)->fg);
            }
            rotationDroite(ab);
        }else if(dh == -2) { //rot droite gauche
            if(hauteur( (*ab)->fd->fd ) < hauteur( (*ab)->fd->fg )) {
                rotationDroite((*ab)->fd);
            }
            rotationGauche(ab);
        }
    }
}


void doubleRotationDroite(ABR* ab){ //pour une double rotation droite 
    rotationGauche(&(*ab)->fg); // une rotation gauche sur le fils gauche
    
    rotationDroite(ab); // une rotation droit sur la racine 
}

void doubleRotationGauche(ABR* ab){ // pour une double rotation gauche 
    rotationDroite(&(*ab)->fd); // une rotation droit sur le fils droit 
    rotationGauche(ab); // une rotation gauche sur la racine 
}

//exercice 2 
