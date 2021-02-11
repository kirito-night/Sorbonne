#include <stdio.h>
#include<stdlib.h>
#include<time.h>
#include "tab1.h"

//2.1
void alloue_tableau(int ** T, int n){
    *T = (int*)malloc(n *sizeof(int));
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
        tab[i] = rand()%V;
    }

}

//2.1.4
void afficher_tableau(int *T, int n){
    int i;
    for(i = 0 ; i < n; i++){
        printf(" %d \n", T[i]);
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
    int i; 
    int sum = 0 ;
    for (i = 0 ; i < n; i++){
        sum+= tab[i];
    }
    return sum* sum;
}


/*int main(){
    srand(time(NULL));
    int n = 10;
    int *tab;
    alloue_tableau(&tab, 10);
    remplir_tableau(&tab,n,10);
    afficher_tableau(tab,n);
    clock_t temps_initial; 
    clock_t temps_final;
    double temps_cpu;

    temps_initial = clock();
    int i =sum_carre_1(tab,n);
    //int j = sum_carre_2(tab,n);
    temps_final = clock();

    temps_cpu = ((double)(temps_final -temps_initial));
    printf("%d  \n", i);
    //  printf("%d  \n", j);
    printf("%d %f \n", n, temps_cpu);
    //FILE *f = fopen("sortie_","w");


    desalloue_tableau(tab);
    return 0;
}*/