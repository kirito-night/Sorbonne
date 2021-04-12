#ifndef B_H
#define B_H
typedef struct  livreh{
    int clef;
    int num ;
    char * titre ;
    char * auteur ;

    struct livreh * suivant ;

}LivreH;

typedef struct table{
    int nE; //nombre d'elements contenus dans la table de hachage
    int m ; /*taille de la table de hachage */
    LivreH** T ; /*table de hachage avec resolution des collisions par chainage */

}BiblioH;

int fonctionClef(char* auteur);
LivreH * creer_livre( int num , char * titre, char * auteur);

void liberer_livre(LivreH *l);
BiblioH * creer_biblio(int m);
void liberer_biblio(BiblioH * b);
int fonctionHachage(int cle, int m);
void inserer(BiblioH * b, int num, char* titre , char* auteur);
LivreH * recherche_ouvrage_num(BiblioH *b, int num);
LivreH * recherche_ouvrage_titre(BiblioH *b, char * titre);
BiblioH * recherche_ouvrage_auteur(BiblioH *b, char * auteur);
void afficher_livre(LivreH *l);
void afficher_biblio(BiblioH *b);
void suppression_ouvrage(BiblioH * b , int num,  char * titre,  char* auteur);

BiblioH* recherche_meme_ouvrage(BiblioH* b);
BiblioH* fusion_biblio(BiblioH  *b1 , BiblioH *b2);


#endif