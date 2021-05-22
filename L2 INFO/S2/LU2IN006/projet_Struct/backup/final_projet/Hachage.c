#include "Hachage.h"
#include"Reseau.h"

int fonctionCle(double x, double y)
{
    //int x = nd->x, y = nd->y;
    return (int) y+((x+y)*(x+y+1))/2; 
}


int fonctionHachage(int cle, int m)
{
    float a = (sqrt(5)-1)/2;
    return (int)(m*(cle*a - (int)(cle*a)));
}

Noeud* rechercheCreeNoeudHachage(Reseau* R, TableHachage *H, double x ,double y){
    int i = fonctionHachage(fonctionCle(x,y),H->taille);
    CellNoeud *tmp = H->tab[i];
    while(tmp && tmp->nd->x!=x && tmp->nd->y !=y ){
    
        tmp = tmp->suiv;
    }
    if(tmp!= NULL){
        return tmp->nd;
    }
    Noeud *new = (Noeud *) malloc(sizeof(Noeud));
    new->x = x;
    new->y = y;
    new->num = R->nbNoeuds +1;
    //new->clef = fonctionCle(x,y);
    new->voisins = NULL;
    CellNoeud *res = (CellNoeud*) malloc(sizeof(CellNoeud));
    res->nd = new;
    res->suiv = R->noeuds;
    R->noeuds = res;
    R->nbNoeuds++;

    CellNoeud *resH = (CellNoeud*) malloc(sizeof(CellNoeud));

    resH->nd = new;
    resH->suiv = H->tab[i];
    H->nE++;
    H->tab[i] = resH;

    return new;

}

CellNoeud * insererNoeud(CellNoeud * liste_nd, Noeud *insere){
    CellNoeud * tmp = liste_nd;
    while(tmp && tmp->nd != insere){
        /*if(tmp->nd== insere){
            printf("deja dans la liste\n");
            return liste_nd;
        }*/
        tmp = tmp->suiv;
        
    }
    if(tmp == NULL){
        CellNoeud *new  = (CellNoeud *)malloc(sizeof(CellNoeud)); 
        new->nd = insere;
        new->suiv = liste_nd;
        liste_nd = new;

    }
    printf("element est insere\n");
    return liste_nd; 
}

Reseau * reconstitueReseauHachage(Chaines *C, int M){
    TableHachage *tabH = (TableHachage *)malloc(sizeof(TableHachage));
    tabH->taille = M;
    tabH->nE = 0;
    tabH->tab = (CellNoeud**)malloc(sizeof(CellNoeud*)*M);
    Reseau * res = (Reseau *)malloc(sizeof(Reseau));
    res->gamma = C->gamma;
    res->nbNoeuds = 0;
    res->noeuds = NULL;
    res->commodites = NULL;

    CellCommodite *liste_commo = NULL;

    CellChaine *tmp_chaine = C->chaines; 
    for(int i  = 0 ; i < C->nbChaines ; i++){
        CellPoint *tmp_ptn  =tmp_chaine->points;    
        Noeud *premier = NULL;
        Noeud *prec = NULL;
        while(tmp_ptn){
            Noeud *nouveau = rechercheCreeNoeudHachage(res,tabH, tmp_ptn->x , tmp_ptn->y);
            if(premier ==NULL){
                premier = nouveau;
            }
            if(prec != NULL){
                prec->voisins = insererNoeud(prec->voisins,nouveau);
                nouveau->voisins = insererNoeud(nouveau->voisins , prec);

            }
            prec = nouveau;
            tmp_ptn = tmp_ptn->suiv;
        }
        CellCommodite *commo = (CellCommodite*)malloc(sizeof(CellCommodite));
        commo->extrA = premier ;   
        commo->extrB = prec ; //rechercheCreeNoeudListe(res,prec->x,prec->y)
        commo->suiv =liste_commo;
        liste_commo= commo;

        tmp_chaine =tmp_chaine->suiv;

    }

    res->commodites =liste_commo;
    return res;

    


}




