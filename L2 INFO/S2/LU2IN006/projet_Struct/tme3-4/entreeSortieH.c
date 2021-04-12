#include "entreeSortieH.h"
#include<stdio.h>
#include<string.h>
BiblioH* charger_n_entrees_h(char* nomfic, int n){
    BiblioH *b = creer_biblio(n);
    FILE *f = fopen(nomfic,"r");
    if(f ==NULL){
        fprintf(stderr," erreur d'ouverture de ficher\n ");
        return NULL;
    }

    char buffer[256];
    int num;
    char titre [256];
    char auteur [256];

    int i; 
    for( i = 0 ; i < n ; i++){
        fgets(buffer, 256,f);
        sscanf(buffer,"%d %s %s ", &num, titre, auteur);
        inserer(b,num, titre, auteur);//pas besoins de strdup car la fonction inserer fait appel a la fonction creer livre qui alloue les memeoire necessaire 

    }
    

    fclose(f);
    return b;
}

void enregistrer_biblio_h(BiblioH *b, char* nomfic){
    FILE *f = fopen(nomfic,"w");
    if(f==NULL){
        printf("enregistrement echouer: unable to open file");
        return;
    }
    int i;
    LivreH **tmp = b->T;
    for(i =0  ; i< b->m ; i++){
        LivreH *l = tmp[i];
        while(l){
            fprintf(f,"%d %s %s\n", l->num, l->titre, l->auteur);
            l =l->suivant; 
        }
        
    }

    fclose(f);
    printf("enregistrement reussit");

}