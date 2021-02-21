#include "entreeSortieLC.h"
#include <string.h>
#include <stdio.h>
#include<stdlib.h>
Biblio* charger_n_entrees(char* nomfic, int n){
    Biblio *b = creer_biblio();
    FILE *f = fopen(nomfic,"r");
    if(f ==NULL){
        fprintf(stderr," erreur d'ouverture de ficher");
    }

    char buffer[256];
    int num;
    char titre [256];
    char auteur [256];

    int i; 
    for( i = 0 ; i < n ; i++){
        fgets(buffer, 256,f);
        sscanf(buffer,"%d %s %s ", &num, titre, auteur);
        inserer_en_tete(b,num, strdup(titre), strdup(auteur));

    }
    

    fclose(f);
    return b;
}


void enregistrer_biblio(Biblio *b, char* nomfic){
    FILE *f = fopen(nomfic,"w");
    if(f==NULL){
        printf("enregistrement echouer: unable to open file");
    }
    Livre *tmp = b->L;
    while(tmp){
        fprintf(f,"%d %s %s\n", tmp->num, tmp->titre, tmp->auteur);
        tmp = tmp->suiv;
    }

    fclose(f);
    printf("enregistrement reussit");

}