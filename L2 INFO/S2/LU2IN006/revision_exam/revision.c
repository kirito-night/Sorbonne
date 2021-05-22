/*liste */
typedef struct list {
    int u;
    struct list *suiv ; 

    
}List;

List * creerList(int u){
    List* res = (List * )malloc(sizeof(List));
    res->u = u; 
    res->suiv = NULL;
}
List * insererEntete(List *l, int u ){
    List * new = creerList(u);
    new->suiv = l;
    l->suiv = new;

}

void insererEntete_2 (List **l , int u ){
    List * new = creerList(u);
    new ->suiv = *l;
    (*l)->suiv = new;

}

/*hachage*/ 

typedef struct point {
    float x; 
    float y; 
    struct point * suiv;  

}Point ;

typedef struct hachage{
    int taille; 
    int nb_elem; 
    Point **tabH;
}Hachage;

int fonction_cle (Point *p){
    return p->x+ p-> y * 2 * p-> y  / p->x ;
}
int fonction_hachage(int cle , int taille ){
    float A= (sqrt(5 ) -1)  / 2 ;
    return (int) (taille  * (cle*A - (int)(cle*A));
}

Hachage creerHachage(int m ){
    Hachage *res = (Hachage* )malloc (sizeof(Hachage));
    res->nb_elem = 0 ; 
    res->taille = m; 
    res->tabH = (Point **)malloc(sizeof(Point*));
    
}