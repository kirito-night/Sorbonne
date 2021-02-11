#include <stdio.h>
#include<stdlib.h>
#include<time.h>
#include "matrice.h"
int **alloue_matrice(int ** T, int n){
    T=(int **) malloc(sizeof(int*)*n);
    int i;
    for(i = 0 ; i< n; i++ ){
         T[i]= (int *)malloc(sizeof(int)*n);
    }
    return T;

}
void alloue_matrice_2(int *** T, int n){
    *T=(int **) malloc(sizeof(int*)*n);
    int i;
    for(i = 0 ; i< n; i++ ){
         (*T)[i]= (int *)malloc(sizeof(int)*n);
    }
    

}

void desalloue_matrice(int **T, int n){
    int **tab = T;
    int i;
    for(i = 0 ; i< n ; i++){
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
    int *tab = (int *)malloc(sizeof(int)*V);
    int i , j; 
    for(i=0 ; i< n; i++){
        for(j=0 ; j < n ; j++){
            if(tab[Mat[i][j]]== 1){
                return 0;
            }
            else{
                tab[Mat[i][j]] = 1;
            }
        }
     }
     return 1;

}

int **produit_matrice(int **mat1, int ** mat2 , int n){
    int **res = NULL;
    alloue_matrice_2(&res,n);
    int i,j,k;
    for(i=0 ; i < n ; i++){
        for(j=0;j<n; j++){
            res[i][j] = 0;
            for(k=0;k<n; k++){
                res[i][j] += mat1[i][k]  + mat2[k][j];
            }
        }
    }
    return res;
}

int **produit_matrice_2(int **mat1, int **mat2,int n){
    
}

/*int main(){
    srand(time(NULL));
    int n = 500, V = 15;
    int **mat = NULL; 
    //mat = alloue_matrice(mat, n);
    alloue_matrice_2(&mat, n);
    remplir_matrice(mat,n ,V);
    afficher_matrice(mat,n);
    clock_t temps_initial; 
    clock_t temps_final;
    double temps_cpu;
    temps_initial = clock();
    all_diff_1(mat,n);
    //all_diff_2(mat,n,V);
    temps_final = clock();

    temps_cpu = ((double)(temps_final -temps_initial));
    printf("%d %.2f \n", n, temps_cpu);

    desalloue_matrice(mat,n);
  
    return 0;
}*/