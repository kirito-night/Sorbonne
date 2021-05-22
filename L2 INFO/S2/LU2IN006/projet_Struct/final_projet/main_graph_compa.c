#include "comparaison.h"
#include<time.h>
#include<stdlib.h>
#include<stdio.h>
#define NB 5

// pour les faire les graphes des differents fonction commentez et decommentez pour genererz les graphes avec GNUplot 
int main(void){

    FILE *f = fopen("sortie_vitesse_hachage.txt", "w");  // Création du fichier 
    if(f == NULL){ // Verification de la création du fichier
        printf("erreur d'ouverture de fichier");
        exit(1);
    }

    int i = 500; // Nombre de chaines
    while(i<=5000) //nous avons choisit 2500 pour les listes  car 5000 c'etait trop long pour l'execution ^^'
    
    {
        Chaines* c =  generationAleatoire(i,100,5000,5000); // Création de la chaine de façon aléatoire
        clock_t temps_initial; 
        clock_t temps_final;
        double temps_cpu;

        // Mettre en commentaire l'une des deux méthodes pour obtenir les graphs

        // Temps de calcul pour la liste chainée
     /*  
        temps_initial = clock();
        reconstitueReseauListe(c);
        temps_final = clock();
        temps_cpu = ((double)(temps_final -temps_initial));
        fprintf(f,"%d  %f\n", i*100, temps_cpu);

      */  

        // Temps de calcul pour la table de hachage

        // Faire varier la taille n de la table de hachage à la main (avec n ) car c'est pas un parametre pour le graph
        

        int taille_tab_hachage [NB] = {5,10,100,1000,10000};
        int n =2;  
        
        temps_initial = clock();
        reconstitueReseauHachage(c,taille_tab_hachage[n]);
        temps_final = clock();
        temps_cpu = ((double)(temps_final -temps_initial));
        fprintf(f,"%d  %f\n", i*100, temps_cpu);
        



        // Pour l'arbre 
/*
        temps_initial  = clock();
        reconstitueReseauArbre(c);
        temps_final = clock();
        temps_cpu = ((double)(temps_final -temps_initial));
        fprintf(f,"%d  %f\n", i*100, temps_cpu);

*/
        i+=500;
    }
    

    fclose(f);  

    return 0;
}