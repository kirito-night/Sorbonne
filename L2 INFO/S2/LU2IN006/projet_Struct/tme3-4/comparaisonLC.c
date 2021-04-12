#include "biblioLC.h"
#include "entreeSortieLC.h"
#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define NB 1000

// Pour obtenir le temps de calcul avec une liste chainée.

int main(int argc, char* argv[]){

    /*if(argc != 2){
        printf("veuilez entrer le nom du fichier à lire.\n");
        return 0;
    }
    char *nomfic = argv[1];*/

    FILE *f = fopen("sortie_vitesse.txt", "w");  // Ouverture du fichier pour gnuplot 
    if(f == NULL){
        printf("erreur d'ouverture de fichier");
        exit(1);
    }

    Biblio* b_LC;
    
    int n; 
    for(n = 0 ; n < NB; n++){
        clock_t temps_initial; 
        clock_t temps_final;
        double temps_cpu;

        b_LC = charger_n_entrees("GdeBiblio.txt", n);

        temps_initial = clock();
        // Recherche avec le numero du livre.



        //recherche_ouvrage_num(b_LC, -1);
        //recherche_ouvrage_num(b_LC, -1);// le cas ou l'element chercher non dedans  



        // Recherche à partir du nom d'un auteur


        
        char* auteur = (char*) malloc(sizeof(char)*50);
        strcpy(auteur,"tuoma");
        recherche_ouvrage_auteur(b_LC, auteur);
        



        // Recherche à partir du nom du livre

        /*
        char* titre = (char*) malloc(sizeof(char)*50);
        strcpy(titre,"PDHPXJPZTUGNNRLX");
        recherche_ouvrage_titre(b_LC, titre);
        */

        temps_final = clock();

        temps_cpu = ((double)(temps_final -temps_initial));

        fprintf(f,"%d  %f\n", n, temps_cpu);
        
        liberer_biblio(b_LC);
    }


    fclose(f);  // Fermeture du fichier pour gnuplot 

    return 0;
}
