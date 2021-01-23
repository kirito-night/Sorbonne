#include <stdlib.h>
#include<stdio.h>
#include <string.h>
#include <assert.h>
typedef struct Nd_mot_ {
    char *mot;
    struct Nd_mot_ *g;
    struct Nd_mot_ *d;
}Nd_mot;

typedef struct Lm_mot_ Lm_mot;
struct Lm_mot_{
     char *mot;
     Lm_mot *suiv;
};
Lm_mot *creer_liste(int length){
    int i;
    Lm_mot *new2 = NULL;
    for(i = 0 ; i< length ; i++){
        char *tmp = malloc(sizeof(char)* 155);
        Lm_mot *new = (Lm_mot *) malloc(sizeof(Lm_mot));
        
        sprintf(tmp,"%d ", i);
        new->mot = strdup(tmp); 
        printf("%s \n" , new->mot);
        free(tmp);
        new->suiv = new2; 
        new2 =new; 
        
    }
    return new2;

}


void detruire_liste(Lm_mot *liste){
    Lm_mot *tmp = liste;
    Lm_mot *tmp2 = NULL;
    while(tmp != NULL){
        tmp2 = tmp->suiv;
        free(tmp->mot);
        free(tmp);
        tmp = tmp2; 
    }
    return ;
}
int taille_Lmot(Lm_mot * mot){
    Lm_mot * tmp = mot;
    int cpt = 0 ; 
    while(tmp != NULL){
        cpt++;
        tmp = tmp->suiv;
    }
    return cpt;
}
Lm_mot  *part_Lmot(Lm_mot **pl){
    int nbm = taille_Lmot(*pl);
    Lm_mot * p = *pl;
    Lm_mot *pivot;

    if(nbm  < 3){
        *pl = NULL;
        return p;
    }

    nbm = nbm / 2; 
    printf("%d \n" , nbm);
    for(p = *pl ; nbm-- > 1;p = p->suiv);
     

    pivot = p->suiv;
    p->suiv = NULL;
    return pivot; 


}
Lm_mot *Lm2abr(Lm_mot *l){
    Lm_mot *pivot;
    Nd_mot *nd;
    if(l == NULL){
        return NULL;
    }
    nd = (Nd_mot *)malloc (sizeof(Nd_mot));
    pivot = part_Lmot(&l);

    nd->mot = pivot ->mot;
    nd->d = Lm2abr(pivot->suiv);
    nd->g = Lm2abr(l);

    free(pivot);
    return nd;
}


Nd_mot  *chercher_Nd_mot(Nd_mot * abr, const char * mot){
    if( abr == NULL){
        return NULL;
    }
    Nd_mot *tmp = abr; 
    if (strcmp(mot, tmp->mot) == 0){
        return abr;
    }
    if (strcmp(mot, abr -> mot)< 0 ) return chercher_Nd_mot(abr->g,mot);
    return chercher_Nd_mot(abr->d,mot);

}

void detruire_abr(Nd_mot * abr){
    if(abr == NULL)
    {
        return ;

    }
    detruire_abr(abr->g);
    detruire_abr(abr->d);
    free(abr->mot);
    free(abr);
    return;
}
    

    
    


int main(){
    Lm_mot *l = creer_liste(12);
    Lm_mot *pivot   = part_Lmot(&l);
    detruire_liste(l);
    return 0;

}


