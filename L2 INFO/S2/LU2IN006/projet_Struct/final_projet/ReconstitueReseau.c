#include "Reseau.h"
#include "Chaine.h"
#include<stdio.h>
#include<stdlib.h>
#include"SVGwriter.h"
#include "Hachage.h"
#include"ArbreQuat.h"

#define TAILLE 10000 //pour la taille de la table d'Hachage on a pris un taille considerable pour que la fonction est vrai 

void menu(){
    printf("entrez 1 pour la reconstitution de reseau par liste\n");
    printf("entrez 2 pour la reconstitution de reseau par table d'hachage\n");
    printf("entrez 3 pour la reconstitution de reseau par arbre quat\n");

}

/*
int main(int argc , char *argv[]){ //main pour debug 
    
    FILE *f = fopen("05000_USA-road-d-NY.cha","r");
    
    //FILE *chaines = fopen("chainesEcrites.txt","w");
    if(f == NULL)
    {
        printf("Le fichier n'as pas pu être ouvert\n");
        exit(2);
    }

    Chaines* c = lectureChaines(f); 


    Reseau *resH = reconstitueReseauHachage(c, TAILLE);
    afficheReseauSVG(resH, "affichageReseauHachage");
    printf("reseau afficher, veuiller regarder le fichier html affchagereseauHachage\n");

    FILE *fw_hachage = fopen("ReseauEcriteHachage", "w");
    ecrireReseau(resH,fw_hachage);
    fclose(fw_hachage);
    




 
    fclose(f);

    
    

   
    return 0;
}

*/


int main(int argc , char *argv[]){
    menu();
    if(argc != 3 ){ //veifier si on a passer le nom du ficher.cha et le reconstitue le reseau de quel maniere avec un entier 
        printf("veuillez entrer le nom du fichier .cha et le un entier");
    }

    char *nomfic = argv[1]; //le nom du fichier passer en executable 
    int rep = atoi(argv[2]); //comment executer 
    FILE *f = fopen(nomfic,"r");//ouverture du fichier 
    if(f==NULL)
    {
        printf("Le fichier n'as pas pu être ouvert\n");
        exit(1);
    }

    Chaines *c = lectureChaines(f); //on lit la chaine 

    switch (rep)
    {
    case 1: ;
        Reseau *res = reconstitueReseauListe(c);

        
        afficheReseauSVG(res,"affichagereseauListe");
        FILE *fw = fopen("ReseauEcriteliste", "w");
        ecrireReseau(res,fw);
        fclose(fw);

        printf("reseau afficher, veuiller regarder le fichier html affchagereseauListe\n");
        break;
    case 2 : ;
        Reseau *resH = reconstitueReseauHachage(c, comptePointsTotal(c));
        afficheReseauSVG(resH, "affichageReseauHachage");
        printf("reseau afficher, veuiller regarder le fichier html affchagereseauHachage\n");

        FILE *fw_hachage = fopen("ReseauEcriteHachage", "w");
        ecrireReseau(resH,fw_hachage);
        fclose(fw_hachage);
        
        break ;
    case 3 : ;
        Reseau* resABR  =reconstitueReseauArbre(c);
        afficheReseauSVG (resABR , "affchageReseauArbre");
        
        FILE *fw_arbre = fopen("ReseauEcriteArbre", "w");
        ecrireReseau(resABR,fw_arbre);
        fclose(fw_arbre);


        printf("reseau afficher, veuiller regarder le fichier html affchagereseauArbre\n");
        break; 
    
    default:
        printf("veuillez suivre l'indication du menu");
        break;

    }
    FILE * fw_chaine = fopen("ecritureChaine.txt","w");
    
    ecrireChaines(c,fw_chaine);

    fclose(fw_chaine);
    libererChaine(c);


    return 0;



}
