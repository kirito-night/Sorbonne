#include "matrice.h"
#define NB 500
int main(){
    int n, V = 10;
    int **mat = NULL;
    clock_t temps_initial; 
    clock_t temps_final;
    double temps_cpu;
    FILE *f = fopen("sortie_vitesse.txt", "w");
    for(n = 1 ; n < NB ; n++){
        alloue_matrice_2(&mat, n);
        remplir_matrice(mat,n ,V);
        
        temps_initial = clock();
        all_diff_1(mat,n);
        //all_diff_2(mat,n,V);
        temps_final = clock();

        temps_cpu = ((double)(temps_final -temps_initial));
        //printf("%d %.2f \n", n, temps_cpu);
        fprintf(f,"%d  %f\n", n, temps_cpu);
        desalloue_matrice(mat,n);

    }
}