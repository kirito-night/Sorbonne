#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef struct adresse {
    int numero;
     char * rue;
     int code_postal;

}Adresse;


Adresse* creer_adressse(int n , char * r , int c){
    Adresse * new = (Adresse *) malloc (sizeof(Adresse));
    new ->numero = n;
    strcpy(new->rue, r);
    new->code_postal = c;
    return new;
}

int main(void){
    Adresse *maison = creer_adressse(12, "manoeuvre", 15670);
    printf("Adresse_courante  :  %d rue : %s  %d France \n", maison->numero, maison-> rue, maison->code_postal);


    return 0;
}

/*
1.4 le code est cense d'afficher le numero , le nom de la rue et le code postal de la maison creer
il y a une segmentation fault
1.5 
nous voyons que il n'y a pas d'espace memoir allouer pour la chaine de caractere rue, nour copions donc la chaine vers une destination non existante ce qui cause une erreur de segmentation
l'erreur survient a la copie de la chaine de caractere
pour resoudre l'erreur nous pouvons allouer dynamiquement l'espace avec strdup(); avec new->rue = strdup(r)
*/

