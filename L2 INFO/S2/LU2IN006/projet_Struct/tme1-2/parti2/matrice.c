#include <stdio.h>
#include<stdlib.h>
#include<time.h>
#include<string.h>
#include "matrice.h"
int **alloue_matrice(int ** T, int n){ //1er version peu efficace car la matrice passer en argument fait une copie qui occupe une place memoire
    /* on utilisera donc pas cette fonction pour alloer les matrice*/
    T=(int **) malloc(sizeof(int*)*n);
    
    int i;
    for(i = 0 ; i< n; i++ ){
         T[i]= (int *)malloc(sizeof(int)*n);
    }
    return T;

}
void alloue_matrice_2(int *** T, int n){ //2eme version plus efficace en donnant comme argument de la foncition une pointeur de matrice
    *T=(int **) malloc(sizeof(int*)*n);
    if(*T == NULL){
        printf("erreur d'allocation dynamique");
        exit(1);
    }
    int i;
    for(i = 0 ; i< n; i++ ){
        (*T)[i]= (int *)malloc(sizeof(int)*n);
        if(*T == NULL){
        printf("erreur d'allocation dynamique");
        free(*T);
        exit(1);
        }
    }
    
    

}


void desalloue_matrice(int **T, int n){
    int **tab = T;
    int i;
    for(i = 0 ; i< n ; i++){ //comme c'est une matrice nous vons besoin d'une boucle
        free(tab[i]);
    }
    free(tab);
}

void remplir_matrice(int **T, int n, int V){
    int i , j;
    for(i =0 ; i< n; i++){
        for(j= 0 ; j < n ; j++){
            T[i][j] = rand()%V;
        }
    }
}
void remplir_matrice_trisup(int **T, int n, int V){ // fonction additionnel pour remplir une matrice triangulaire superieur
    int i , j;
    for(i =0 ; i< n; i++){
        for(j= 0 ; j < n ; j++){
            if(i > j){
                T[i][j] = 0;
            }else{
                T[i][j] = rand()%V;
            }
            
        }
    }
}
void remplir_matrice_triinf(int **T, int n, int V){// fonction additionnel pour remplir une matrice triangulaire inferieur
    int i , j;
    for(i =0 ; i< n; i++){
        for(j= 0 ; j < n ; j++){
            if(i < j){
                
                T[i][j] = 0;
            }else{
                T[i][j] = rand()%V;
            }
            
        }
    }
}

void afficher_matrice(int **T, int n){
    int i , j;
    for(i =0 ; i< n; i++){
        for(j= 0 ; j < n ; j++){
            printf("%d \t",T[i][j]);
        }
        putchar('\n');
    }
}

int all_diff_1(int **Mat, int n){
    int i, j, k, l; 
    /*
        avec 4 boucle, pour chaque element de la matrice nous parcourons la matrice en entier 
        mauvaise complexite
    */
     for(i =0 ; i< n; i++){
        for(j= 0 ; j < n ; j++){
            for(k = 0; k < n ; k++){
                for(l = 0 ; l < n ; l ++){
                    if (Mat[i][j] == Mat[k][l]){
                        return 0;
                    }
                }
            }
        }
    }
    return 1;
}

int all_diff_2(int **Mat, int n , int V){
    /*
       les valeur de la matrice sont entre 0 et V-1,  avec un tableau entier de V case, si dans la matrice une valeur n apparait 
       on dit que la n-ieme  case de notre taleau = 1 , et si on voit une case de notre tableau est de 1 c'est a dire la matrcie s'est repete, 
       on return 0, et si on sort de la boucle et on est tjr dans la fonction, dans ce cas tousles valeurs sont differents on return 1 

       meilleur complexite
    */
    int *tab = (int *)malloc(sizeof(int)*V);
    memset(tab,0,V*sizeof(int));
    int i , j; 
    for(i=0 ; i< n; i++){
        for(j=0 ; j < n ; j++){
            if(tab[Mat[i][j]]== 1){
                free(tab);
                return 0;
            }
            else{
                tab[Mat[i][j]] = 1;
            }
        }
     }
     free(tab);
     return 1;

}

int **produit_matrice(int **mat1, int ** mat2 , int n){
    /* on fait la produit des matrices en respectant la formule mathematique */
    int **res = NULL;
    alloue_matrice_2(&res,n);
    int i,j,k;
    for(i=0 ; i < n ; i++){
        for(j=0;j<n; j++){
            res[i][j] = 0;
            for(k=0;k<n; k++){
                res[i][j] += mat1[i][k]  * mat2[k][j];
                
            }
        }
    }
    return res;
}

int **produit_matrice_2(int **mat1, int **mat2,int n){
    /*P.S les matrices passez en argument sont suppose triangulaire de base, avec mat1 triangulaire superieur et mat2 triangulaire inferieur 
    et la fonction ne peut que faire le produit d'une matrice triangulaire superieur multiplier par une matrice triangulaire  inferieur */
    /*avec les matrices triangulaire certain parcours de la  boucle sont inutile donc avec une condition on peut eviter ces parcours */
    int **res = NULL;
    alloue_matrice_2(&res,n);
    int i,j;
    int k, tmp;
    for(i=0 ; i < n ; i++){
        for(j=0;j<n; j++){
            res[i][j] =0;
            if (i>j){
                tmp = j;
            }else {
                tmp = i;
            }
            for (k = 0; k < tmp ; k++){
                res[i][j] += mat1[i][k] * mat2[k][i];
            }
        }
    }

    return res;
    
}

