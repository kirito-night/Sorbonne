#include "Reseau.h"
#include "Chaine.h"
#include<stdio.h>
#include<stdlib.h>
#include"SVGwriter.h"
#include "Hachage.h"
#include"ArbreQuat.h"
#include "Graphe.h"
#include "Struct_File.h"
#include "Struct_Liste.h"
#define TAILLE 50




int main(int argc , char *argv[]){
    //executez ReorganiseReseau avec un fichier .cha dans le dossier 
    if(argc != 2 ){
        printf("veuillez entrer le nom du fichier .cha ");
    }

    char *nomfic = argv[1]; //le nom du fichier passer en argument lors de l'execution 
    
    FILE *f = fopen(nomfic,"r");
    if(f==NULL) //verification de l'ouverture du fichier 
    {
        printf("Le fichier n'as pas pu être ouvert\n");
        exit(1);
    }

    Chaines *c = lectureChaines(f); 
    Reseau *res = reconstitueReseauArbre(c); //ici nous utilisons reconstitueReseauArbre car nous avons vue avec l'exo6 que c'est la maniere de reconstituer le reseau la plus rapide
    libererChaine(c);
    int test = reorganiseReseau(res); //reorganisation du reseau qui va renvoye un vrai (1) ou faux(0)

    if(test ==0){
        printf(" le reseau n 'est pas optimise \n");
    }else{
        printf(" le reseau  est  optimise \n");
    }

}


//7.5

/* pour ameliorer la performance de la fonction : 
dans un premier temps on aura pu remplacer la matrice_2D par une autre structure de donnee comme un tableau de liste chainne qui contient pour chaque somme ses aretes 
comme ca on evite les cas ou entre 2 sommet il n'existe pas d'arete

La chaine la plus courte de chaque commodite ne sera pas renvoye par cette fonction elle est afficher mais pas 
on peut  ecrire une fonction pour le faire ou bien de moidifier la signature en passant en argument  un  tableau de liste pour stocker less chaines plus courte 

*/
