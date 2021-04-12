#include "biblioH.h"
#include "entreeSortieH.h"
#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define NB 1000

// Pour obtenir le temps de calcul avec la table de hachage.

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

    BiblioH* b_hachage;
    
    int n; 
    for(n = 0 ; n < NB; n++){
        clock_t temps_initial; 
        clock_t temps_final;
        double temps_cpu;

        b_hachage = charger_n_entrees_h("GdeBiblio.txt", n);

        temps_initial = clock();
        // Recherche avec le numero du livre.

        /*
        recherche_ouvrage_num(b_hachage, -1);
        //recherche_ouvrage_num(b_hachage, n);
        */


        // Recherche à partir du nom d'un auteur
        
        
        char* auteur = (char*) malloc(sizeof(char)*50);
        strcpy(auteur,"tuomak");
        recherche_ouvrage_auteur(b_hachage, auteur);
        
        // Recherche à partir du nom du livre
        
        
        /*char* titre = (char*) malloc(sizeof(char)*50);
        strcpy(titre,"PDHPXJPZTUGNNRLX");
        recherche_ouvrage_titre(b_hachage, titre);
        */
        temps_final = clock();
        
        temps_cpu = ((double)(temps_final -temps_initial));

        fprintf(f,"%d  %f\n", n, temps_cpu);
        
        liberer_biblio(b_hachage);
    }


    fclose(f);  // Fermeture du fichier pour gnuplot 

    return 0;
}
