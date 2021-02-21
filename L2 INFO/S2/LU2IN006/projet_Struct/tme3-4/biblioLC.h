#ifndef BIBLIO_H
#define BIBLIO_H
typedef struct livre {
    int num ;
    char * titre ;
    char * auteur ;
    struct livre * suiv ;
} Livre ;

typedef struct{ /* Tete fictive */
    Livre * L ; /* Premier element */
} Biblio ;


Livre* creer_livre(int num,char* titre,char* auteur);
void liberer_livre(Livre* l);
Biblio* creer_biblio();
void liberer_biblio(Biblio* b);
void inserer_en_tete(Biblio* b, int num, char* titre, char* auteur);
void afficherLivre(Livre* l);
void afficherBiblio(Biblio* b);
Biblio * recherche_ouvrage_auteur(Biblio *b, char* auteur);
Livre * recherche_ouvrage_titre (Biblio *b, char* titre);
Livre * recherche_ouvrage_num (Biblio *b, int num);

void suppression_ouvrage(Biblio * b , int num,  char * titre,  char* auteur);
Biblio *fusion_biblio_2(Biblio *b1, Biblio *b2);
Biblio *fusion_biblio_1(Biblio *b1, Biblio *b2);
#endif