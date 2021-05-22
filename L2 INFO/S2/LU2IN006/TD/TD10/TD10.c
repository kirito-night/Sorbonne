#include <stdlib.h>
#include <stdio.h>

//2.1 
//G1 arborescence 
//G2 c'est une arbre qui a pour racine le sommet 0 (connexe et acyclique )
//G1 c'est l'orientation de l'arbre G2 (avec 0 comme racine )


//G3 graphe oriente avec un unique circuit 
//G4 est non oriente connexe avec plusieur cycle : ( 3,4,5) (1,3,5)(0,1,5,4)



//2.2
typedef struct cellule {
    int v ;
    /* indice d’un sommet de tabS */
    struct cellule * suiv ; /* pointeur sur le lien suivant */
} Cellule ;

typedef struct {
    int nbsom ; 
    Cellule ** tabS ; //tab liste chainnees de sommets 
} GrapheSimple;



void cree_graphe ( GrapheSimple *G , int n ) {
    int i ;
    G -> nbsom = n ;
    G -> tabS =( Cellule **) malloc ( n * sizeof ( Cellule *) ) ;
    for ( i =0; i < n ; i ++){
        G -> tabS [ i ]= NULL ;
    }
   
}


void ajoute_lien ( GrapheSimple *G , int i , int j ) { // ajoute le lien orient’e (i,j)
    Cellule * nouv =( Cellule *) malloc ( sizeof ( Cellule ) ) ;
    nouv -> v = j ;
    nouv -> suiv =G -> tabS [ i ];
    G -> tabS [ i ]= nouv ;
}


void aff_graphe ( GrapheSimple * G ) {
    printf ( " \n\nGraphe : \n" ) ;
    int i ;
    for ( i =0; i <G -> nbsom ; i ++) {
        printf ( "%d : " ,i ) ;
        Cellule * cour =G -> tabS [ i ];
        while ( cour != NULL ) {
            printf ( "%d " , cour -> v ) ;
            cour = cour -> suiv ;
        }
    printf ( " \n" ) ;
    }
}

//1.2
/*
Graphe G1:
0 : 4 1
1 : 5 3 2
2 : 
3 : 
4 : 
5 :

Graphe G2:
0 : 4 1
1 : 5 3 2 0
2 : 1
3 : 1
4 : 0
5 : 1

Graphe G3:
0 : 4 1
1 : 5 3 2
2 : 3
3 : 4
4 : 5
5 : 3

Graphe G4:
0 : 4 1
1 : 5 3 2 0
2 : 3 1
3 : 2 5 4 1
4 : 5 3 0
5 : 3 4 1

*/



void aff_parcours_1 ( GrapheSimple * G , int r ) {
    printf ( "%d" ,r ) ;
    Cellule * cour = G -> tabS [ r ];
    while ( cour != NULL ) {
        int v = cour -> v ;
        aff_parcours_1 (G , v ) ;
        cour = cour -> suiv ;
    }
}


/*
//parcours 1 : 0 4 1 5 3 2
//Parcours 1 avec G2 donne une boucle 
infinie (la fonction boucle sur 0 4 )
aff parcours 1 c'est l'affichage d'une arborescence 

aff parcours 2 permet d'afficher un arbre 
Parcours 2  : 0 4 1 5 3 2 
donc la fonction aff aprcours 2 peut aussi avoir des probleme de bouccles infinies avec des graphes orientes 

*/

void aff_parcours_2(GrapheSimple* G, int r, int u) {
    printf("%d \n", r);
    Cellule* cour = G->tabS[r];
    while(cour != NULL) {
        int v = cour->v;
        if(u!=v) aff_parcours_2(G,v,r);
        cour = cour->suiv;
    }
}


void aff_parcours_3(GrapheSimple* G, int r, int* visit) {
    visit[r] = 1;
    printf("%d ", r);
    Cellule* cour = G->tabS[r];
    while(cour!=NULL) {
        if(visit[cour->v] == 0){
            
            aff_parcours_3(G, cour->v, visit);
        }
        
        cour = cour->suiv;
    }
}

/*1.6
parcours 3 : 0 4 5 3 1 
aff parcours 3 permet de trouver un sous parcours dasn une graphe oriente quelconques 
*/





//partie 2 detection de circuit 

/*
vist puet prendre les valeurs 
0 non visite 
1 recontre mais encoer des descendants non visites 
2 visite et tous les descendants visite 
*/

typedef enum _test {oui, non , zero} test;

int detecte_circuit_descendant(GrapheSimple* G, int r, int* visit) {
    int detect = 0;
    visit[r] = 1;
    
    Cellule* cour = G->tabS[r];
    while( cour!=NULL && !detect) {
        int v = cour->v;
        if(visit[v] == 0 ){
            detect = detecte_circuit_descendant(G,v,visit);
        }
        if(visit[v] == 1 ){
            detect =  1;
        }
        //if (visit[v] ==  2 ) on fait rien 
    }
    visit[r] = 2;

    return detect;
}



int detecte_circuit(GrapheSimple* G) {
    int* visit = malloc(sizeof(int) * G->nbsom);

    int detect = 0;
    
    for(int i=0;i<G->nbsom;i++) {
        
        detect = detecte_circuit_descendant(G, i, visit);
        if(detect) break;
    }
    
    free(visit);
    return detect;
}

int main (){

    GrapheSimple * G = (GrapheSimple*) malloc(sizeof(GrapheSimple)); 
    cree_graphe(G, 5);
    ajoute_lien(G, 0, 1 );
    ajoute_lien(G,0,4);

    ajoute_lien(G,1,5);
    ajoute_lien(G,1,3);
    ajoute_lien(G,1,2);


    aff_parcours_1(G, 0);
    return 0;
}


