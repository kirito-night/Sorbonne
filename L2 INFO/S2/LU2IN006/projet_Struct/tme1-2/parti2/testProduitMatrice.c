#include "matrice.h"
#define NB 50
int main(){
    int n, V = 10;
    int **mat1 = NULL;
    int **mat2 = NULL;

    
    FILE *f = fopen("sortie_vitesse.txt", "w");
    if(f == NULL){
        printf("erreur d'ouverture de fichier");
        exit(1);
    }
    printf("fichier est ouvert\n");
    for(n = 1 ; n < NB ; n++){
        clock_t temps_initial; 
        clock_t temps_final;
        double temps_cpu;
        alloue_matrice_2(&mat1, n);
        alloue_matrice_2(&mat2, n);
        remplir_matrice_trisup(mat1,n ,V);
        remplir_matrice_triinf(mat2,n ,V);
        temps_initial = clock();//decommentez et commentez cette fonction pour mesurez l'autre fonction 
        //produit_matrice(mat1,mat2,n);
        produit_matrice_2(mat1,mat2, n);
        temps_final = clock();

        temps_cpu = ((double)(temps_final -temps_initial));
        //printf("%d %.2f \n", n, temps_cpu);
        fprintf(f,"%d  %f\n", n, temps_cpu);
        desalloue_matrice(mat1,n);
        desalloue_matrice(mat2,n);
    }
    fclose(f);
    printf("fichier est ferme\n"); 
    return 0;
}