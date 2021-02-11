#include<stdlib.h>
#include<stdio.h>

int **alloue_matrice(int n, int m){
    int** T=(int **) malloc(sizeof(int*)*n);
    int i;
    for(i = 0 ; i< n; i++ ){
         T[i]= (int *)malloc(sizeof(int)*m);
    }
    return T;

}

int alloue_matrice_2(int *** T, int n, int m){
    *T=(int **) malloc(sizeof(int*)*n);
    if(*T == NULL){
        return 0;
    }
    int i;
    for(i = 0 ; i< n; i++ ){
         (*T)[i]= (int *)malloc(sizeof(int)*m);
         if((*T)[i] == NULL){
             return 0;
         }
    }
    
    return 1;
}

//ex.2

int alloue_matrice_tri(int *** T, int n, int m){
    *T=(int **) malloc(sizeof(int*)*n);
    if(*T == NULL){
        return 0;
    }
    int i;
    
    for(i = 0 ; i< n; i++ ){
         (*T)[i]= (int *)malloc(sizeof(int)*(i+1));
         if((*T)[i] == NULL){
             return 0;
         }
    }
    
    return 1;
}
typedef struct Mat_tri {
    int orientation;
    int ligne;
    int **mat; 
}Mat_tri;

Mat_tri *alloue_tri(int ligne , int orientation, int **mat){
    Mat_tri *res = (Mat_tri *)malloc(sizeof(Mat_tri));
    res->ligne = ligne;
    res->orientation =orientation;
    res->mat = alloue_matrice_tri(&mat,ligne);

}
void affiche_matrice_tri(Mat_tri *mat){
    if(mat->orientation){
        int i, j; 
        for()
    }
}
/*pour different orientation, n-i , i+1*/

//ex.3

typedef struct personne{
    char* nom;
    long tel;
} Personne;
typedef struct repertoire{
    Personne* tab;
    int taille;
} Repertoire;

void creer_fichier(char* nom, Repertoire *rep){
    FILE *f = fopen(nom,"w");
    int i; 
    for(i=0 ; i< rep->taille; i++){
        fprintf(f,"%s : %d \n",rep->tab[i].nom,rep->tab[i].tel);
    }
    fclose(f);

}
void creer_fichier_suite(char* nom, Repertoire *rep){
    FILE *f = fopen(nom,"a");
    int i; 
    for(i=0 ; i< rep->taille; i++){
        fprintf(f,"%s : %d \n",rep->tab[i].nom,rep->tab[i].tel);
    }
    fclose(f);
}
Repertoire * lire_repertoire(char* nom){
    FILE *f = fopen(nom,"r");
    Repertoire *rep = malloc(sizeof(Repertoire));
    memset(rep,0,sizeof(Repertoire));
    char buffer[256];
    int i =0;
    while(fgets(buffer,256,f)!= NULL){
        i++;
    }
    rewind(f);

    rep->tab = ( Personne*) malloc(sizeof(Personne)*i);
    rep->taille = i;
    while(fgets(buffer,256,f)!= NULL){
        char * nom;
        
        int tel;
        sscanf(buffer," %s %ld",nom, tel);
        rep->tab[i].nom = strdup(nom);
        rep->tab[i].tel = tel;
        

    }
    

    return rep;

}