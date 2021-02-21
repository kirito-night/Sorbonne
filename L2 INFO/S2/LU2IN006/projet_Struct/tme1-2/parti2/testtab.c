#include "tab1.h"
#include <stdio.h>
#include<stdlib.h>
#include<time.h>
#define NB 500

int main(){
    srand(time(NULL));
    int n ;
    int *tab =NULL;
    
    FILE *f = fopen("sortie_vitesse.txt", "w");
    if(f == NULL){
        printf("erreur d'ouverture de fichier");
        exit(1);
    }
    printf("fichier est ouvert\n");
    for(n = 1; n < NB; n++){
        clock_t temps_initial; 
        clock_t temps_final;
        double temps_cpu;
        alloue_tableau(&tab, n);
        remplir_tableau(&tab,n,10);
        temps_initial = clock();
        //sum_carre_1(tab,n);
        sum_carre_2(tab,n);//decommentez et commentez cette fonction pour mesurez l'autre fonction 
        temps_final = clock();
        temps_cpu = ((double)(temps_final -temps_initial));
        fprintf(f,"%d %f \n", n, temps_cpu);
        desalloue_tableau(tab);
        



    }
    fclose(f);
    printf("fichier est ferme\n");

    return 0;

}