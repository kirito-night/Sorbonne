#include "Chaine.h" 
#include<stdio.h>
#include<stdlib.h>
#include"SVGwriter.h"

/*
int  main(int argc, char *argv[]){
    char* nom_fic = argv[1];
    if(argc != 2 ){
        printf("erreur d'execution mettez le nom de fichier a lire ");
        exit(1);
    }

    FILE *f = fopen("00014_burma.cha","r");
    Chaines *c1 = lectureChaines(f);
    

    fclose(f);
    FILE *save = fopen("enregistrement.txt", "w");
    ecrireChaine(c1,save);
    fclose(save);

    return 0;



}*/

int main(int argc, char* argv[]) {
    char *nomfic = argv[1];
    FILE *f = fopen(nomfic,"r");

    if(f==NULL)
    {
        printf("Le fichier n'as pas pu être ouvert\n");
        exit(1);
    }

    FILE *chaines = fopen("chainesEcrites.txt","w");
    if(chaines == NULL)
    {
        printf("Le fichier n'as pas pu être ouvert\n");
        exit(2);
    }

    Chaines* c = lectureChaines(f);
    ecrireChaines(c,chaines);
    afficheChainesSVG(c,"affichage");

    fclose(f);
    fclose(chaines);
    
    
    return 0;
}