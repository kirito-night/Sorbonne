#include <stdio.h>
#include<stdlib.h>
#include<time.h>
#include "tab1.h"

//2.1
void alloue_tableau(int ** T, int n){
    *T = (int*)malloc(n *sizeof(int));
    if(*T == NULL){
        printf("erreur d'allocation dynamique");
        exit(1);
    }
}
/* nous avons choisi cette version car l'autre version va creer un copie 
du tableau passer en argument et de renvoyer le resultat, qui est bcp plus couteux 
*/
//2.1.2
void desalloue_tableau(int *T){
    free(T);
}

//2.1.3
void remplir_tableau(int **T, int n, int V){
    int *tab = *T;
    int i;
    for (i = 0; i< n ; i++){
        tab[i] = rand()%V; //remplir le tableau  avec une valeur aleatoire entre 0 a V-1
    }

}

//2.1.4
void afficher_tableau(int *T, int n){
    int i;
    for(i = 0 ; i < n; i++){
        printf(" %d \n", T[i]); //afficher les valeur du tableau passer en argument
    }
}

//2.2
int sum_carre_1(int *tab, int n){
    int i , j; 
    int sum = 0;
    for (i = 0 ; i < n; i++){
        for(j = 0 ; j< n ; j++){
            sum += (tab[i]-tab[j]) * (tab[i]-tab[j]);
        }
    }
    return sum;

}


int sum_carre_2(int *tab, int n){
    int res = 0 , res_carre = 0;
    int i;
    for (i = 0 ; i < n; i++){
        res+= tab[i];
        res_carre+= tab[i] * tab[i];
    }
    return 2* (n * res_carre - res*res);
}


