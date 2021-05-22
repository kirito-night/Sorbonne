#include "comparaison.h"
#include<time.h>
#include<stdlib.h>
#include<stdio.h>
#define NB 5

//PS : le code peut prendre bcp de temps pour fonctionner soiyer patient
int main(){

    FILE *f = fopen("00014_burma.txt", "w");  // Création du fichier 
    if(f == NULL){ // Verification de la création du fichier
        printf("erreur d'ouverture de fichier");
        exit(1);
    }

    FILE *chaine_aLire = fopen("00014_burma.cha" , "r");
    Chaines* c =  lectureChaines(chaine_aLire); // Création de la chaine à partir du fichier 
    clock_t temps_initial; 
    clock_t temps_final;
    double temps_cpu;


    // Temps de calcul pour la liste chainée
    temps_initial = clock();
    reconstitueReseauListe(c);
    temps_final = clock();
    temps_cpu = ((double)(temps_final -temps_initial));
    fprintf(f,"temps_cpu pour reconstitueReseauListe : %f\n", temps_cpu);


    // Temps de calcul pour l'arbre
    temps_initial = clock();
    reconstitueReseauArbre(c);
    temps_final = clock();
    temps_cpu = ((double)(temps_final -temps_initial));
    fprintf(f,"temps_cpu pour reconstitueReseauArbre : %f\n", temps_cpu);
    

    // Temps de calcul pour la table de hachage
    int taille_tab_hachage [NB] = {5,10,100,1000,10000};
    int n; 
    for(n = 0 ; n < NB; n++){
        temps_initial = clock();
        reconstitueReseauHachage(c,taille_tab_hachage[n]);
        temps_final = clock();
        temps_cpu = ((double)(temps_final -temps_initial));
        fprintf(f,"temps_cpu pour reconstitueReseauHachage avec table de hachage de taille %d : %f\n", taille_tab_hachage[n], temps_cpu);  
    }

    fclose(f);  
    fclose(chaine_aLire);




    //############################
    //############################
    //############################
    //pour  05000_USA-road-d-NY




    FILE *f1 = fopen("05000_USA-road-d-NY.txt", "w");  // Création du fichier 
    if(f1 == NULL){ // Verification de la création du fichier
        printf("erreur d'ouverture de fichier");
        exit(1);
    }

    FILE *chaine_aLire_1 = fopen("05000_USA-road-d-NY.cha" , "r");
    Chaines* c1 =  lectureChaines(chaine_aLire_1); // Création de la chaine à partir du fichier 
    
    /*clock_t temps_initial; 
    clock_t temps_final;
    double temps_cpu;*/


    // Temps de calcul pour la liste chainée
    temps_initial = clock();
    reconstitueReseauListe(c1);
    temps_final = clock();
    temps_cpu = ((double)(temps_final -temps_initial));
    fprintf(f1,"temps_cpu pour reconstitueReseauListe : %f\n", temps_cpu);


    // Temps de calcul pour l'arbre
    temps_initial = clock();
    reconstitueReseauArbre(c1);
    temps_final = clock();
    temps_cpu = ((double)(temps_final -temps_initial));
    fprintf(f1,"temps_cpu pour reconstitueReseauArbre : %f\n", temps_cpu);
    

    // Temps de calcul pour la table de hachage
    
    for(n = 0 ; n < NB; n++){
        temps_initial = clock();
        reconstitueReseauHachage(c1,taille_tab_hachage[n]);
        temps_final = clock();
        temps_cpu = ((double)(temps_final -temps_initial));
        fprintf(f1,"temps_cpu pour reconstitueReseauHachage avec table de hachage de taille %d : %f\n", taille_tab_hachage[n], temps_cpu);  
    }

    fclose(f1);  
    fclose(chaine_aLire_1);

    //############################
    //############################
    //############################

    // pour  07397_pla

    FILE *f2 = fopen("07397_pla.txt", "w");  // Création du fichier 
    if(f2 == NULL){ // Verification de la création du fichier
        printf("erreur d'ouverture de fichier");
        exit(1);
    }

    FILE *chaine_aLire_2 = fopen("07397_pla.cha" , "r");
    Chaines* c2 =  lectureChaines(chaine_aLire_2); // Création de la chaine à partir du fichier 
    
    /*clock_t temps_initial; 
    clock_t temps_final;
    double temps_cpu;*/


    // Temps de calcul pour la liste chainée
    temps_initial = clock();
    reconstitueReseauListe(c2);
    temps_final = clock();
    temps_cpu = ((double)(temps_final -temps_initial));
    fprintf(f2,"temps_cpu pour reconstitueReseauListe : %f\n", temps_cpu);


    // Temps de calcul pour l'arbre
    temps_initial = clock();
    reconstitueReseauArbre(c2);
    temps_final = clock();
    temps_cpu = ((double)(temps_final -temps_initial));
    fprintf(f2,"temps_cpu pour reconstitueReseauArbre : %f\n", temps_cpu);
    

    // Temps de calcul pour la table de hachage
    
    for(n = 0 ; n < NB; n++){
        temps_initial = clock();
        reconstitueReseauHachage(c1,taille_tab_hachage[n]);
        temps_final = clock();
        temps_cpu = ((double)(temps_final -temps_initial));
        fprintf(f2,"temps_cpu pour reconstitueReseauHachage avec table de hachage de taille %d : %f\n", taille_tab_hachage[n], temps_cpu);  
    }

    fclose(f2);  
    fclose(chaine_aLire_2);


    return 0;
}