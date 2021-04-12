#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "biblioLC.h"

Livre* creer_livre(int num, char* titre, char* auteur){
    Livre* l = (Livre*) malloc(sizeof(Livre));  
    l->titre = (char*) malloc(sizeof(char)*200);
    l->auteur = (char*) malloc(sizeof(char)*200);

    l->num = num;
    strcpy(l->titre,titre);
    strcpy(l->auteur,auteur);

    return l;
}

void liberer_livre(Livre* l){
    free(l->titre);
    free(l->auteur);
    free(l);
}

Biblio* creer_biblio(){
    Biblio* b = (Biblio*) malloc(sizeof(Biblio));
    b->L =NULL;
    return b;
}

void liberer_biblio(Biblio* b){
    Livre * l = b->L;
    while(l){
        Livre * tmp = l->suiv;
        liberer_livre(l);
        l = tmp;
    }
    free(b);
}

void inserer_en_tete(Biblio* b, int num, char* titre, char* auteur){
    Livre* l = creer_livre(num, titre, auteur);
    l->suiv = b->L;
    b->L = l;
}

Livre * recherche_ouvrage_num (Biblio *b, int num){
    Livre * l =b->L;
    while(l){
        if(l->num == num){
            printf("livre  trouver\n");
            return l;
        }
        l= l->suiv;
    }
    printf("livre non trouver\n");
    return NULL;
}

Livre * recherche_ouvrage_titre (Biblio *b, char* titre){//revoie le premier ouvrage de bon titre
    Livre * l =b->L;
    while(l){
        if(strcmp(l->titre,titre)==0){
            printf("livre  trouver\n");
            return l;
        }
        l= l->suiv;
    }
    printf("livre non trouver\n");
    return NULL;
}

Biblio * recherche_ouvrage_auteur(Biblio *b, char* auteur){
    Biblio *res = creer_biblio();
    Livre *l = b->L;
    while(l){
        if(strcmp(l->auteur, auteur)== 0){
            inserer_en_tete(res,l->num, l->titre, l->auteur);
        }
        l =  l->suiv;
    }
    if(res->L == NULL){
        printf("livre non trouver\n");
    }else{
        printf("livre  trouver\n");
    }
    return res;
}


void afficherLivre(Livre* l){
    printf("Titre : %s, Auteur : %s, Numero : %d\n", l->titre, l->auteur, l->num);
}

void afficherBiblio(Biblio* b){
    Livre* l = b->L;
    while(l){
        afficherLivre(l);
        l = l->suiv;
    }
}

void suppression_ouvrage(Biblio * b , int num,  char * titre,  char* auteur){
    Livre *liste = b->L;
    //l'element a supprimer se trouve en tete de liste on a besoin de pousser la tete
    if(strcmp(liste->auteur, auteur)==0 && liste->num == num && strcmp(liste->titre, titre)==0){
        b->L = liste->suiv;
        liberer_livre(liste);
        printf("l'ouvrage est supprimer");
        return;
    }
    liste= liste->suiv;
    while (liste->suiv){
        if(strcmp(liste->suiv->auteur, auteur)==0 && liste->suiv->num == num && strcmp(liste->suiv->titre, titre)==0){
            Livre *tmp = liste->suiv->suiv;
            liberer_livre(liste->suiv);
            liste->suiv=tmp;
            printf("l'ouvrage est supprimer");
            return;
        }
        liste= liste->suiv;
    } 
    printf("element a supprimer n'est pas dans la bibliotheque");
    return ;
}


/*Biblio *fusion_biblio_1(Biblio *b1, Biblio *b2){
    Livre *liste = b1->L;
    while(liste->suiv){
        liste = liste->suiv;
    }
    liste->suiv = b2->L;
    free(b2);
    return b1;
}*/

Biblio *fusion_biblio_2(Biblio *b1, Biblio *b2){
     if(b2 == NULL){
        return b1;
    }
     if(b1 == NULL){
        return b2;
    }
    Livre * liste = b1->L;
    while(liste){
        inserer_en_tete(b2,liste->num,liste->titre,liste->auteur);
        liste = liste->suiv;

    }
    liberer_biblio(b1);
    printf("fusion fait !");
    return b2;

}

Biblio* recherche_meme_ouvrage(Biblio* b){
    Biblio* res = (Biblio*) malloc(sizeof(Biblio));
    Livre* temp = b->L;
    Livre* temp2 = NULL;

    while(temp){
        temp2 = temp->suiv;
        while(temp2){
            if((strcmp(temp->auteur,temp2->auteur)==0)&&(strcmp(temp->titre,temp2->titre)==0)){
                if(recherche_ouvrage_num(res,temp->num) == NULL){
                    inserer_en_tete(res,temp->num,temp->titre,temp->auteur);
                }
                if(recherche_ouvrage_num(res,temp2->num)==NULL){
                    inserer_en_tete(res,temp2->num,temp2->titre,temp2->auteur);
                }
            }
            temp2 = temp2->suiv;
        }
        temp = temp->suiv;
    }

    return res;
}

/*

int main(void){
    return 0;
}*/