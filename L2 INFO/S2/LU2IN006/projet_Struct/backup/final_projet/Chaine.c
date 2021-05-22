#include "Chaine.h"
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
#include<math.h>
#include"SVGwriter.h"

void libererChaine(Chaines *c){ //fonction de liberation de la chaine 
    CellChaine *tmp_chain = c->chaines;
    while(tmp_chain){
        CellPoint *tmp_pnt = tmp_chain->points;
        while(tmp_pnt){
            CellPoint *p_suiv = tmp_pnt->suiv;
            free(tmp_pnt);
            tmp_pnt = p_suiv;
        }
        CellChaine *c_tmp = tmp_chain->suiv;
        free(tmp_chain);
        tmp_chain = c_tmp;
    }
    free(c);
}

Chaines* lectureChaines(FILE *f){
    //fonction de lecture de la chaine 

    Chaines *res = (Chaines *)malloc(sizeof(Chaines));
    char buffer[256];
    char tmp[50]; // juste un tableau pour le stockage 
    int Nbchain;
    int Gamma;
    fgets(buffer, 256,f);
    sscanf(buffer,"%s %d" , tmp,&Nbchain);

    fgets(buffer, 256,f);
    sscanf(buffer,"%s %d" , tmp,&Gamma);
    res->nbChaines =Nbchain;
    res->gamma=Gamma;
    CellChaine *liste_cha = NULL;
    for(int j =  0 ;  j < res->nbChaines ; j++){
        //fgets(buffer, 256,f);
        //printf("%s \n" , buffer);
        CellChaine *cha = (CellChaine *)malloc(sizeof(CellChaine));
        int num;
        int nbPoints;
        fscanf(f,"%d %d ",&num,&nbPoints); //utilisation de fscanf pour avancer dans le fichier 
        //printf("%d %d \n",num,nbPoints);
        cha->numero = num;
        int i;
        CellPoint *liste_point = NULL;
        for(i = 0 ; i < nbPoints; i++){
            CellPoint *point = (CellPoint*)malloc(sizeof(CellPoint));
            float x, y;
            //int test = fscanf(f,"%f %f ",&x,&y);
            fscanf(f,"%f %f ",&x,&y);
            //pour debug
            //printf("%f %f  %d \n",x,y , test); 
            point->x=x;
            point->y = y;
            point->suiv = liste_point;
            liste_point=point;
            
        }
        cha->points = liste_point;
        cha->suiv = liste_cha;
        liste_cha = cha;


    }
    res->chaines = liste_cha;
    return res;
}




void ecrireChaines(Chaines *C, FILE *f){
    //fonction d'ecriture de la chaine dans un fichier
    fprintf(f,"NbChain : %d \nGamma : %d\n",C->nbChaines,C->gamma);
    int i ;
    CellChaine *liste_chaine = C->chaines;
    for(i = 0 ; i <C->nbChaines ; i++){
        
        int nb_points = 0; 
        CellPoint *liste_point = liste_chaine->points;
        while(liste_point){
            nb_points +=1;
            //printf("%d \n ", nb_points);
            liste_point=  liste_point->suiv;
        }

        fprintf(f,"%d %d ",liste_chaine->numero, nb_points);
        liste_point = liste_chaine->points;
        while(liste_point){
            //printf("%f %f ",liste_point->x,liste_point->y);
            fprintf(f, "%.2f %.2f ",liste_point->x,liste_point->y);
            liste_point = liste_point->suiv;
        }
        
        fprintf(f,"\n");
        liste_chaine = liste_chaine->suiv;
    }
}








double longueurChaine(CellChaine *c)
{ //calcule la longueur d'une chaine 


    if(c==NULL)
    {
        return 0;
    }
    double res = 0;
    CellPoint* temp_point = c->points;
    CellPoint* temp_point_suiv = temp_point->suiv;

    while(temp_point_suiv!=NULL)
    {
        res +=sqrt(pow(temp_point_suiv->y - temp_point->y,2)+pow(temp_point_suiv->x - temp_point->x, 2));
        temp_point = temp_point_suiv;
        temp_point_suiv = temp_point_suiv->suiv;
    }

    return res;
}


double longueurTotale(Chaines* C)  
{//compte la longuer totale 
    if(C==NULL)
    {
        return 0;
    }
    double res = 0.0;
    CellChaine* temp = C->chaines;
    
    while(temp!=NULL){
        res+=longueurChaine(temp);
        temp = temp->suiv;
    }

    return res;
}

int comptePointsTotal(Chaines* C)
{ //compte le nombre de points totale de la chaine 

    if(C==NULL){
        return 0;
    }
    
    int nb_points = 0;

    CellChaine* temp = C->chaines;

    while(temp!=NULL){
        CellPoint* point = temp->points;
        while(point!=NULL){
            nb_points+=1;
            point = point->suiv;
        }
        temp = temp->suiv;
    }
    return nb_points;
}


void afficheChainesSVG(Chaines *C, char* nomInstance){ //fonction d'affichage fournit 
    //int i;
    double maxx=0,maxy=0,minx=1e6,miny=1e6;
    CellChaine *ccour;
    CellPoint *pcour;
    double precx,precy;
    SVGwriter svg;
    ccour=C->chaines;
    while (ccour!=NULL){
        pcour=ccour->points;
        while (pcour!=NULL){
            if (maxx<pcour->x) maxx=pcour->x;
            if (maxy<pcour->y) maxy=pcour->y;
            if (minx>pcour->x) minx=pcour->x;
            if (miny>pcour->y) miny=pcour->y;  
            pcour=pcour->suiv;
        }
    ccour=ccour->suiv;
    }
    SVGinit(&svg,nomInstance,500,500);
    ccour=C->chaines;
    while (ccour!=NULL){
        pcour=ccour->points;
        SVGlineRandColor(&svg);
        SVGpoint(&svg,500*(pcour->x-minx)/(maxx-minx),500*(pcour->y-miny)/(maxy-miny)); 
        precx=pcour->x;
        precy=pcour->y;  
        pcour=pcour->suiv;
        while (pcour!=NULL){
            SVGline(&svg,500*(precx-minx)/(maxx-minx),500*(precy-miny)/(maxy-miny),500*(pcour->x-minx)/(maxx-minx),500*(pcour->y-miny)/(maxy-miny));
            SVGpoint(&svg,500*(pcour->x-minx)/(maxx-minx),500*(pcour->y-miny)/(maxy-miny));
            precx=pcour->x;
            precy=pcour->y;    
            pcour=pcour->suiv;
        }
        ccour=ccour->suiv;
    }
    SVGfinalize(&svg);
}