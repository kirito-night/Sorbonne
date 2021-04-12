typedef struct assoc Assoc;
typedef struct membre Membre;
typedef struct MaisonAsso{
    Assoc *a; 
    struct MaisonAsso *suiv;
}MaisonAsso;


struct assoc{
    char * nom_assoc;
    Membre *members;
};

struct membre{
    char* nom_membre;
    Membre * suiv ; 
};