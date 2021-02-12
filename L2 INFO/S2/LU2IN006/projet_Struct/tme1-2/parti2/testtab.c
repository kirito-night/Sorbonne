#include "tab1.h"
#include <stdio.h>
#include<stdlib.h>
#include<time.h>
#define NB 500

int main(){
    srand(time(NULL));
    int n ;
    int *tab =NULL;
    clock_t temps_initial; 
    clock_t temps_final;
    double temps_cpu;
    FILE *f = fopen("sortie_vitesse.txt", "w");
    for(n = 1; n < NB; n++){
        alloue_tableau(&tab, n);
        remplir_tableau(&tab,n,10);
        temps_initial = clock();
        sum_carre_1(tab,n);
        //sum_carre_2(tab,n);
        temps_final = clock();
        temps_cpu = ((double)(temps_final -temps_initial));
        fprintf(f,"%d %f \n", n, temps_cpu);
        desalloue_tableau(tab);
        



    }
    fclose(f);
    return 0;

}