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