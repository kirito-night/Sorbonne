

//exercice 1 
//1.1
typedef struct form Formation;

typedef struct element {
    Formation *f;
    struct element * suivant;
}Element;

typedef struct form {
    char * nom; 
    int nbHeure; 
    Element *l;

}; 

//1.2
typedef struct cata{
    unsigned int nb_formations;
    unsigned nb_max_formations ;
    Formation **formation;

}Catalog;

//1.4
void affichage_Form(Formation *f){
    if(f->nbHeure == 0){
        printf(" Formation : %s heure : %d", f->nom, f->nbHeure);
        Element *tmp = f->l;
        while(tmp){
        printf("%s %d", tmp->f->nom, tmp->f->nbHeure);
        tmp = tmp->suivant;
        }
    }else {
        printf("cours %s",f->nom);
    }
}

void affichage_catalog(Catalog *c){
    int i ; 
    for(i=0 ; i< c->nb_max_formations ; i++){
        affichage_Form(c->formation[i]);
    }
}
   
int nbheures_formation(Formation *f){
    Element *tmp = f->l;
    int nbh = f->nbHeure;
    while(tmp){
        nbh += nbheures_formation(tmp->f);
        tmp = tmp->suivant;
        
    }
    return nbh ;
}

//1.7 : une boucle infinie 
//1.8
void ajouter_formation_dans_formation(Formation *f, Formation *nouvelle){
    //rechercher sur nouvelle est deja incluse dans f (ou inversement )
    //  on cherce a empecher al cration de cycle 
    //si la recherche indique que les formations sont deja liees alors 
    // on annule ajout
}


//exercice 2 
typedef struct membre Membre;
typedef struct assoc Assoc;
typedef struct MaisonAsso{
    Assoc *a; 
    struct MaisonAsso *suiv;
}MaisonAsso;

typedef struct membre{
    char* nom_membre;
    struct membre* suivant;
}Membre;

typedef struct assoc{
    char * nom_assoc;
    Membre * members;
}Assoc;



Assoc *creerAssociation(char * nomA){
    Assoc *a = (Assoc*)malloc(sizeof(Assoc));
    a->nom_assoc = strdup(nomA);
    a->members = =NULL;

    return a;
}
void ajouterPersonne(Assoc *a, char * nom ){
    Membre* m = (Membre *)malloc(sizeof(Membre));
    m->nom_membre = strdup(nom);
    m->suivant = a->members;
    a->members = m;
}

void supprimerPersonne(Assoc * a , char * nom){
    Membre * cur = a->members;
    Membre *prev = NULL;
    while(cur!=NULL && strcmp(nom,cur->nom) != 0){
        prev = cur;
        cur = cur->suivant;
    }
    if(cur!= NULL){
        prev -> suivant = cur->suivant;
        free(cur->nom);
        free(cur);
    }
}

void ajouterAssociation(MaisonAsso ** m , Assoc *a){
    MaisonAsso * new = creerMaison();
    new->a = a;
    new->suiv = *m;
    *m = new;

}
/*void enleverAssociation(MaisonAsso **m, Assoc *a){
    MaisonAsso * cur = *m ; 
    if(strcmp(a->nom_assoc, cur->a->nom_assoc)==0){
        *m = cur->suiv;
    }
}*/

void enleverAssociation(Maison** m, Association* a)
{
    Maison* cur = *m;
    if(strcmp(a->nomAssoc, cur->assoc->nomAssoc)==0) // si cest le meme nom
    {
        *m = cur->suiv;
        free(cur);
    } else {
        Maison* prev = cur;
        cur = cur->suiv;
        
        while(cur!=NULL && strcmp(a->nomAssoc, cur->assoc->nomAssoc) != 0) {
            prev=cur;
            cur=cur->suiv;
        }
        
        if(cur != NULL) {
            prev->suiv=cur->suiv;
            free(cur);
        }
    }


