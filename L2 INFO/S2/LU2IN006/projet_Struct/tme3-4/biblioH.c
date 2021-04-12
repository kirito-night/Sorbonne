#include "biblioH.h"
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include<stdio.h>
int fonctionClef(char* auteur){
    /*  la clef est la somme des valeur ASCII   du nom de l'auteur */
    int sum = 0;
    int i = 0;
    while(auteur[i]!= '\0'){
        sum += auteur[i];
        i++;
    }

    return sum;
}

LivreH * creer_livre( int num , char * titre, char * auteur){
    LivreH *res = (LivreH *)malloc(sizeof(LivreH));
    res->clef = fonctionClef(auteur);
    res->num = num;
    res->titre = strdup(titre);
    res->auteur = strdup(auteur);
    res->suivant = NULL;
    return res;
}

void liberer_livre(LivreH *l){
    free(l->auteur);
    free(l->titre); // liberer les chaines de caractere allouer avec strdup
    free(l);
}

BiblioH * creer_biblio(int m){
    BiblioH* res = (BiblioH *)malloc(sizeof(BiblioH));
    res->m = m;
    res->nE = 0;
    res->T = (LivreH**)malloc(sizeof(LivreH*)*m); //allocation d'un tableau de pointeur 
    memset(res->T, 0 , sizeof(LivreH*)*m); // les case de tableau initier a NULL , et va etre allouer lors de l'insersion d'un livre avec  fonction creer livre 
    return res;

}

void liberer_biblio(BiblioH * b){
    int i ; 
    for(i = 0 ; i < b->m; i++){
        LivreH * tmp = b->T[i];
        while (tmp){
            LivreH *tmps = tmp->suivant;
            liberer_livre(tmp);
            
            tmp = tmps;
        }

    }
    free(b->T);
    free(b);
}

int fonctionHachage(int cle, int m){
    /*une fonction de hachage qui rend une valeur entre 0 et m-1 pour une clef donne */ 
    float A =(sqrt(5)-1)/2;
    return (int)(m*(cle* A - (int)(cle*A)));
}

void inserer(BiblioH * b, int num, char* titre , char* auteur){
    //trouvre la case a inserer le livre avec auteur et les fonctions d'hacahge et de faire une insertioin en tete de liste */
    int i = fonctionHachage(fonctionClef(auteur), b->m); 
    //printf("achage : %d \n", i);
    LivreH *ins = creer_livre(num,titre,auteur);
    ins->suivant = (b->T)[i];
    (b->T)[i] = ins;
    b->nE +=1;
}

LivreH * recherche_ouvrage_num(BiblioH *b, int num){
    /*parcours le tableau et la listes dans les  case du tableaux pour trouver le num correspondant et renvoie le livre*/
    int i; 
    for(i =0 ; i< b->m ; i++){
        LivreH *tmp = b->T[i];
        while (tmp){
            if(tmp->num == num){
                printf("livre  trouver\n");
                return tmp;
            }
            tmp = tmp->suivant;
        }
    }
    printf("livre non trouver\n");
    return NULL;
}


LivreH * recherche_ouvrage_titre(BiblioH *b, char * titre){
    /*parcours le tableau et la listes dans les  case du tableaux pour trouver le titre  correspondant et renvoie le livre*/
    int i; 
    for(i =0 ; i< b->m ; i++){
        LivreH *tmp = b->T[i];
        while (tmp){
            if(strcmp(tmp->titre, titre)== 0){
                printf("livre  trouver\n");
                return tmp;
            }
            tmp= tmp->suivant;
        }
    }
    printf("livre non trouver\n");
    return NULL;
}

BiblioH * recherche_ouvrage_auteur(BiblioH *b, char * auteur){
    /*trouver la case ou est l'auteur avec auteur passer en argument*/
    /*revoioe un biblio qui contient la liste des livre de l'auteur rechercher dans la case qu'il faut*/
    int i = fonctionHachage(fonctionClef(auteur), b->m);
    BiblioH *res = creer_biblio(b->m);
    LivreH * liste  = b->T[i];
    int j = 0; 
    while(liste){
        if(strcmp(liste->auteur , auteur) == 0){
            inserer(res, liste->num, liste->titre, liste->auteur);
            j++;

        }
       
        liste = liste->suivant;
    }
    printf(" %d  ouvrage de l'auteur : %s sont trouve : \n ", j , auteur);
    return res ;
}


void afficher_livre(LivreH *l){
    if(l==NULL){
        return ; 
    }
     printf("Titre : %s, Auteur : %s, Numero : %d , Clef : %d\n", l->titre, l->auteur, l->num, l->clef);
}

void afficher_biblio(BiblioH *b){
    /*affichage biblio par parcours */
    if(b==NULL){
        printf("biblio vide");
        return;
    }
    int i ; 
    for(i = 0 ; i < b->m; i++){
        LivreH *tmp = b->T[i];
        while (tmp){
            afficher_livre(tmp);
            tmp = tmp->suivant;
        }
    }
}



void suppression_ouvrage(BiblioH * b , int num,  char * titre,  char* auteur){
    int i = fonctionHachage(fonctionClef(auteur), b->m);
    LivreH *liste = b->T[i];
    if(liste->num == num && strcmp(liste->titre, titre)==0 && strcmp(liste->auteur , auteur) == 0){
        LivreH *suiv = liste->suivant;
        free(liste);
        b->T[i] = suiv;
        printf("l'ouvrage est supprimer");
        return;

    }
    while(liste-> suivant){
        if(liste->suivant->num == num && strcmp(liste->suivant->titre, titre)==0 && strcmp(liste->suivant->auteur , auteur) == 0){
            LivreH *tmp = liste->suivant->suivant;
            liberer_livre(liste->suivant);
            liste->suivant=tmp;
            printf("l'ouvrage est supprimer");
            return;
        }
        liste= liste->suivant;
    }
    printf("element a supprimer n'est pas dans la bibliotheque");
    return ;

}


BiblioH* recherche_meme_ouvrage(BiblioH* b){ // a voir 
    BiblioH *res = creer_biblio(b->m);
    int i ; 
    for(i=  0 ; i < b->m; i++){

        LivreH *l = b->T[i];
        while(l){
            char * titre = l->titre;
            LivreH *tmp = l->suivant;
            while(tmp){
                if(strcmp(titre,tmp->titre)==0){
                    inserer(res,b->T[i]->num,b->T[i]->titre,b->T[i]->auteur);
                    inserer(res,tmp->num,tmp->titre,tmp->auteur);
                }
                tmp=tmp->suivant;
            }
            l=l->suivant;
        }
    }
    return res;

}

BiblioH* fusion_biblio(BiblioH  *b1 , BiblioH *b2){

    if(b2 == NULL){
        return b1;
    }
     if(b1 == NULL){
        return b2;
    }
    int i ;
    for(i=0 ; i < b2->m; i++){
        LivreH *liste =b2->T[i];
        while(liste){
          
            inserer(b1,liste->num,liste->titre, liste->auteur);
            liste = liste->suivant;
        }
    }
    liberer_biblio(b2);
    printf("fusion fait !");
    return b1;
}