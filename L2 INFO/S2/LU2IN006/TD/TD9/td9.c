#include<stdlib.h>
//ex 1 
/*
1.1
graphe non oriente
ville les sommet 
route les aretes, + distance 

1.2
les listes d'adjacecence ou matrice 
mais on va choisir les listes


*/
//1.3
typedef struct arete {
    char * nom_route; 
    float distance; 
    int i;
    int j;

}Arete;

typedef struct liste_arete{
    Arete *a;
    struct liste_arete *suiv;
    
}ListeArete;

typedef struct sommet{
    char* nom_ville; 
    int num;
    float x, y; 
    ListeArete *L_adj; //les arete de ce sommet
}Sommet; 

typedef struct graph
{
    int n; //nb ville 
    int m; //nb route
    Sommet **tabSommet ; //tableau de pionteur vers sommet
} Graph;


//1.5
Graph *initierGraph(int n){
    Graph *g  = (Graph*) malloc(sizeof(Graph)) ; 
    g->n = n ;
    g->m = 0;
    g->tabSommet = (Sommet **)malloc(sizeof(Sommet*));

    for(int i = 0  ; i< n ; i ++){
        g->tabSommet[i] = (Sommet*)malloc(sizeof(Sommet));
        g->tabSommet[i] ->num = i;

        g->tabSommet[i]->L_adj = NULL;
    }

    return g;

}
//1.6
void majSommet(Graph*  g, int num, char *nom ,float x ,float y){
    int i = num;
    g->tabSommet[i]->nom_ville = nom;   
    g->tabSommet[i]->x = x;
    g->tabSommet[i]->y = y;
    return ;
    

}
//1.8
void AjoutArete(Sommet *s1 , Sommet*s2){
    Arete *a = (Arete *)malloc(sizeof(Arete));
    a->i = s1->num;
    a->j = s2->num;
    a->distance = sqrt( pow(s1->x - s2->x , 2) +pow(s1->y - s2->y , 2));
    ListeArete *l1  = (ListeArete *)malloc(sizeof(ListeArete));
    ListeArete *l2  = (ListeArete *)malloc(sizeof(ListeArete));
    l1->a =a;
    l2->a = a;
    l1->suiv = s1->L_adj;
    s1->L_adj = l1;

    l2->suiv = s2->L_adj;
    s2->L_adj  =l2;


}
Arete * creerArete( int u , int v , float dist){
    Arete *a = (Arete *)malloc(sizeof(Arete));
    a->i = u;
    a->j = v;
    a->distance = dist;
    return a;
}

/* correction

void insererTete_ListeA(ListeA* LA, Arete* a)
{
    ElementListA* ela = malloc(sizeof(ElementListA));
    ela->a    = a;
    ela->suiv = *LA; // tete actuelle devient le suivant
    *LA       = ela  // nouvel element devient la tete
}
void ajouterArete(Graphe* g, int u, int v, double dist)
{
    Arete *a = creerArete(u, v, dist);
    insererTete_ListeA(&(g->tab[u]->L_adj), a);
    insererTete_ListeA(&(g->tab[v]->L_adj), a);
    // Graphe non oriente : il faut inserer l'arete pour les deux sommets
}
*/
//1.7
int main(){
    Graph *g = initierGraph(4);

    for (int i = 0; i < g->n; ++i){
        majSommet(g,i,"test",i*1.0,i*2.0);
    }
    return 0;
}

//1.9
void afficher_ListeA(ListeArete * l){
    ListeArete *tmp = l;
    while (tmp){
        printf("[%d , %d , %.2f] ", tmp->a->i , tmp->a->j , tmp->a->distance);
        tmp = tmp->suiv;
    }
}

void afficher_graph(Graph *g){
    for(int i = 0 ; i < g->n ; i++){
        printf("%d : ", i);
        afficher_ListeA(g->tabSommet[i]->L_adj);
        putchar('\n');
    }
}

void libere_listeA(ListeArete * l){
    ListeArete * tmp = l; 
    ListeArete *tmp_suiv;
    while (tmp){
        if(tmp->a ->i != -1){
            
            tmp->a->i  = -1;
        }else {
            free(tmp->a);
        }
        tmp_suiv = tmp->suiv;
        free(tmp);
        tmp = tmp_suiv;
    }
}

void libere_graph(Graph *g){
    for(int i = 0 ; i < g->n ; i++){
        libere_listeA(g->tabSommet[i]->L_adj);
        free(g->tabSommet[i]->nom_ville);
        free(g->tabSommet[i]);

    }
    free(g->tabSommet);
    free(g);
}

//ex 2 
/*
void degres_matrice(int **M, int n , int u , int * deg_e , int *deg_s){
    *deg_e = 0;
    *deg_s = 0;
    for(int i = 0 ; i<n ; i++){
       if( M[u][i] == 1){
           *deg_s +=1;
       } 
    }
    for(int j = 0 ; j < n ; j++){
        if( M[j][u] == 1){
           *deg_e +=1;
       } 
    }
}*/
/*
void degres_matrice(int **M, int n , int u , int * deg_e , int *deg_s){
    int e = 0 ; 
    int s = 0 ;
    for (int i = 0 ; i< n ; i++){
        if(M[u][i] == 1){
            s++;
        }
        if(M[i][u] == 1){
            e++;
        }
    }

    *deg_e = e ; 
    *deg_s  =s;
}*/

//2.1
void degre_matrice(int n, int** M, int u, int* deg_e, int* deg_s)
{
    int e = 0;
    int s = 0;
    
    for(int i=0; i<n ; i++) {
        if(M[u][i] == 1) {
            s++;
        }
        if(M[i][u] == 1) e++;
    }
    
    *deg_e = e;
    *deg_s = s;
}

//2.2
int degre_liste_sortant(Graph* g, int u)
{
    int deg = 0;
    ListeArete* cur = g->tabSommet[u]->L_adj;
    while(cur != NULL) {
        deg++;
        cur = cur->suiv;
    }
    
    return deg;
}
//2.3
int degre_liste_entrant(Graph* g, int u)
{
    int deg = 0;
    for(int i = 0; i< g->n ; i++) {
        ListeArete* cur = g->tabSommet[i]->L_adj;
        while(cur != NULL && cur->a->j != u) {
            cur = cur->suiv;
        }
        if(cur != NULL) deg++;
    }
    
    return deg;
    
}
//2.4

