#include "Reseau.h"
#include "Chaine.h"
#include <stdlib.h>
#include "SVGwriter.h"
#include<math.h>
#include"Hachage.h"
#include "ArbreQuat.h"
Noeud* rechercheCreeNoeudListe(Reseau *R, double x, double y)
{
    CellNoeud* temp_noeud = R->noeuds;

    while(temp_noeud!=NULL)
    {
        if((temp_noeud->nd->x == x) && (temp_noeud->nd->y == y))
        {
            printf("point dedans\n");
            return temp_noeud->nd;
        }        
        temp_noeud = temp_noeud->suiv;
    }
    Noeud* nouveau = (Noeud*) malloc(sizeof(Noeud));
    nouveau->num = R->nbNoeuds + 1;
    nouveau->x = x;
    nouveau->y = y;
    nouveau->voisins = NULL;
    CellNoeud* new_cell = (CellNoeud*) malloc(sizeof(CellNoeud));
    new_cell->nd =nouveau;
    new_cell->suiv = R->noeuds;
    R->noeuds = new_cell;
    R->nbNoeuds += 1;
    printf("point n'est pas dedans \n");
    return nouveau;


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
/*
Reseau* reconstitueReseauListe(Chaines *C){
    //double LtotC = longueurTotale(C);
    //int nbPointTot = comptePointsTotal(C);

    Reseau * res = (Reseau*) malloc(sizeof(Reseau));
    res->gamma = C->gamma;
    res->nbNoeuds = 0 ;
    res->noeuds = NULL;
    res->commodites = NULL;
    CellChaine *tmp_chaine = C->chaines;
    
    CellCommodite *liste_commo = NULL;
    for(int i = 0 ;  i < C->nbChaines ; i++){
        CellPoint *tmp_ptn = tmp_chaine->points;
        CellPoint *dernierPoint = NULL;
        while(tmp_ptn){
            Noeud* nouveau = rechercheCreeNoeudListe(res,tmp_ptn->x,tmp_ptn->y);
            nouveau->voisins = NULL; // pas sur pour la liste des voisin d'un nd
            
            if(tmp_ptn->suiv == NULL){
                dernierPoint = tmp_ptn;
            }
            

            tmp_ptn = tmp_ptn ->suiv;

        }
        CellCommodite *commo = (CellCommodite*)malloc(sizeof(CellCommodite));
        commo->extrA = rechercheCreeNoeudListe(res,tmp_chaine->points->x, tmp_chaine->points->y);
        commo->extrB = rechercheCreeNoeudListe(res,dernierPoint->x,dernierPoint->y);
        commo->suiv =liste_commo;
        liste_commo= commo;


        tmp_chaine =tmp_chaine->suiv;
    } 
    res->commodites =liste_commo;
    

    return res;
}
*/

Reseau* reconstitueReseauListe(Chaines *C){
    //double LtotC = longueurTotale(C);
    //int nbPointTot = comptePointsTotal(C);

    Reseau * res = (Reseau*) malloc(sizeof(Reseau));
    res->gamma = C->gamma;
    res->nbNoeuds = 0 ;
    res->noeuds = NULL;
    res->commodites = NULL;
    CellChaine *tmp_chaine = C->chaines;
    
    CellCommodite *liste_commo = NULL;
    for(int i = 0 ;  i < C->nbChaines ; i++){
        CellPoint *tmp_ptn = tmp_chaine->points;
        Noeud *premier =NULL;
        Noeud *prec = NULL;
        while(tmp_ptn){
            Noeud* nouveau = rechercheCreeNoeudListe(res,tmp_ptn->x,tmp_ptn->y);
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

int nbCommodites(Reseau *R){
    CellCommodite *commo = R->commodites; 
    int res = 0;
    while(commo){
        res+=1;
        commo = commo->suiv;
    }
    return res;
}




int nbLiaisons(Reseau *R){
    CellNoeud *l = R->noeuds;
    int res = 0;
    while(l){
        
        CellNoeud *liste_voisin =  l->nd->voisins;
        while (liste_voisin){
            res+=1;
            liste_voisin = liste_voisin->suiv;
        }
        l= l->suiv;
    }
    return res /2 ;
}


void ecrireReseau(Reseau *R, FILE *f){
    int nbl = nbLiaisons(R);
    int nbc = nbCommodites(R);
    fprintf(f,"NbNoeud :  %d\n NbLiaisons :  %d\n Nbcommodites :  %d\nGamma :  %d\n\n", R->nbNoeuds , nbl ,nbc, R->gamma);
    CellNoeud *liste_nd = R->noeuds;
    

    //boucle pour les nb noeud 
    for(int  i = 0 ; i< R->nbNoeuds ; i++){
        fprintf(f,"v %d %.2f %.2f \n", liste_nd->nd->num,liste_nd->nd->x, liste_nd->nd->y);
        liste_nd = liste_nd->suiv;
    }
    fprintf(f, "\n");


    liste_nd = R->noeuds;
    
    //CellNoeud * liste_nd_suiv = liste_nd->suiv;
    CellNoeud * liste_voisin = NULL;
    for(int j = 0 ; j < nbl ;){
        liste_voisin = liste_nd->nd->voisins;
        
        
        while(liste_voisin){
            if(liste_voisin->nd->num < liste_nd->nd->num){
                fprintf(f,"l %d %d \n ", liste_voisin->nd->num, liste_nd->nd->num );
                j++;
            }
            
            liste_voisin = liste_voisin->suiv;
        }
        liste_nd = liste_nd->suiv;
         
    }
    
    
    fprintf(f, "\n");
    CellCommodite *liste_commo = R->commodites;
    for(int k = 0 ; k < nbc ; k++){
        fprintf(f, "k %d %d \n", liste_commo->extrA->num,liste_commo->extrB->num);
        liste_commo = liste_commo->suiv;
    }

    return ;
}

void afficheReseauSVG(Reseau *R, char* nomInstance){
    CellNoeud *courN,*courv;
    SVGwriter svg;
    double maxx=0,maxy=0,minx=1e6,miny=1e6;

    courN=R->noeuds;
    while (courN!=NULL){
        if (maxx<courN->nd->x) maxx=courN->nd->x;
        if (maxy<courN->nd->y) maxy=courN->nd->y;
        if (minx>courN->nd->x) minx=courN->nd->x;
        if (miny>courN->nd->y) miny=courN->nd->y;
        courN=courN->suiv;
    }
    SVGinit(&svg,nomInstance,500,500);
    courN=R->noeuds;
    while (courN!=NULL){
        SVGpoint(&svg,500*(courN->nd->x-minx)/(maxx-minx),500*(courN->nd->y-miny)/(maxy-miny));
        courv=courN->nd->voisins;
        while (courv!=NULL){
            if (courv->nd->num<courN->nd->num)
                SVGline(&svg,500*(courv->nd->x-minx)/(maxx-minx),500*(courv->nd->y-miny)/(maxy-miny),500*(courN->nd->x-minx)/(maxx-minx),500*(courN->nd->y-miny)/(maxy-miny));
            courv=courv->suiv;
        }
        courN=courN->suiv;
    }
    SVGfinalize(&svg);
}


//les code pour la table d'hachage


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


//les codes pour les arbres 

void chaineCoordMinMax(Chaines* C, double* xmin, double* ymin, double* xmax, double* ymax)
{
    if (C==NULL || C->chaines==NULL){
        printf("Pas de chaines");
        return;
    }
    CellChaine* CellTemp = C->chaines;
    *xmin = INFINITY, *xmax = -INFINITY, *ymax = -INFINITY, *ymin = INFINITY; 

    while(CellTemp->suiv!=NULL)
    {
        CellPoint* p = CellTemp->points;
        while(p!=NULL)
        {
            if(*xmin > p->x){
                *xmin = p->x;
            }
            if(*xmax<p->x){
                *xmax = p->x;
            }
            if(*ymin>p->y){
                *ymin=p->y;
            }
            if(*ymax<p->y){
                *ymax=p->y;
            }
            p = p->suiv;
        }
        CellTemp = CellTemp->suiv;
    }
}

ArbreQuat* creerArbreQuat(double xc, double yc, double coteX,double coteY)
    {
        ArbreQuat* new = (ArbreQuat*) malloc(sizeof(ArbreQuat));

        new->xc = xc;
        new->yc = yc;
        new->coteX = coteX;
        new->coteY = coteY;
        new->noeud = NULL;
        new->so = NULL;
        new->se = NULL;
        new->ne = NULL;
        new->no = NULL;

        return new;
    }


void insererNoeudArbre(Noeud *n, ArbreQuat** a, ArbreQuat* parent){
    
    if(*a == NULL){
        double new_coteX = (parent->coteX) / 2;
        double new_coteY = (parent->coteY) / 2;
        double new_x , new_y;
        double xc = parent->xc;
        double yc = parent->yc;
        /*
        if(n->x < parent->xc && n->y < parent->yc){ //le cas sud ouest

            //new_x = xc - xc/2;
            ArbreQuat* son =  creerArbreQuat(xc - xc/2, yc - yc/2, new_coteX, new_coteY);
            son->noeud = n;
            parent->so = son;
        }
        if(n->x > parent->xc && n->y > parent->yc){//nord est 
            ArbreQuat* son =  creerArbreQuat(xc + xc/2, yc + yc/2, new_coteX, new_coteY);
            son->noeud = n;
            parent->ne = son;
        }
        if(n->x < parent->xc && n->y > parent->yc){//nord ouest 
            ArbreQuat* son =  creerArbreQuat(xc - xc/2, yc + yc/2, new_coteX, new_coteY);
            son->noeud = n;
            parent->no = son;
        }
        else{ //sud est
            ArbreQuat* son =  creerArbreQuat(xc + xc/2, yc - yc/2, new_coteX, new_coteY);
            son->noeud = n;
            parent->se = son;
        }
        */


       if(n->x < parent->xc){
           new_x = xc - new_coteX/2 ;
       }else{
           new_x  = xc+ new_coteX/2 ;
       }
       
       if(n->y < parent->yc){
           new_y = yc - new_coteY/2;
       }else{
           new_y = yc +new_coteY/2;
       }

       ArbreQuat* son =  creerArbreQuat(new_x, new_y, new_coteX, new_coteY);
       son->noeud = n;
       *a = son;


    }
    else{
        if((*a)->noeud != NULL ){ // une modification dans le if
            Noeud * tmp  = (*a)->noeud ; 

            if(tmp->x < (*a)->xc){
                if(tmp->y < (*a)->yc){
                    insererNoeudArbre(tmp, &(*a)->so,*a );
                }   
                else{
                    insererNoeudArbre(tmp, &(*a)->no,*a );
                }   
            }else{
                if(tmp->y < (*a)->yc){
                    insererNoeudArbre(tmp, &(*a)->se,*a );
                }
                else{
                    insererNoeudArbre(tmp, &(*a)->ne, *a);
                }   
            }

            if(n->x < (*a)->xc){
                if(n->y < (*a)->yc){
                    insererNoeudArbre(n, &(*a)->so,*a );
                }
                else{
                    insererNoeudArbre(n, &(*a)->no,*a );
                }
            }else{
                if(n->y < (*a)->yc){
                    insererNoeudArbre(n, &(*a)->se,*a );
                }
                else{
                    insererNoeudArbre(n, &(*a)->ne, *a);
                }   
            }
            (*a)->noeud = NULL;
        }

    //if((*a) -> noeud == NULL && *a != NULL)
        else {
            if(n->x < parent->xc && n->y < parent->yc){
                insererNoeudArbre(n, &(*a)->so,*a);
            }

            if(n->x > parent->xc && n->y > parent->yc){
                 insererNoeudArbre(n, &(*a)->ne,*a);
            }
            if(n->x < parent->xc && n->y > parent->yc){
                insererNoeudArbre(n, &(*a)->no,*a);
            }
            if(n->x > parent->xc && n->y < parent->yc){
                insererNoeudArbre(n, &(*a)->se,*a);
            }

        }
    }
}






Noeud* rechercheCreeNoeudArbre(Reseau* R, ArbreQuat** a, ArbreQuat* parent, double x, double y){


    if((*a) == NULL){
        Noeud *new  = (Noeud *) malloc (sizeof(Noeud));
        new->x = x;
        new->y = y;
        new->num = R->nbNoeuds + 1;
        new->voisins = NULL;

        CellNoeud *new_cell = (CellNoeud *) malloc(sizeof(CellNoeud));
        new_cell ->nd = new; 
        new_cell ->suiv = R->noeuds ;
        R->noeuds  =new_cell;
        R->nbNoeuds +=1 ;

        insererNoeudArbre(new, a,parent);
        return new; 
    }
    else{

    

    if((*a)-> noeud != NULL){
        Noeud *tmp_noeud = (*a)-> noeud; 
        if(tmp_noeud -> x == x && tmp_noeud ->y == y){ //le cas ou le noeud est dans l'arbre
            return tmp_noeud;
        }
        /*else{
            Noeud *new  = (Noeud *) malloc (sizeof(Noeud));
            new->x = x;
            new->y = y;
            new->num = R->nbNoeuds + 1;
            new->voisins = NULL;

            CellNoeud *new_cell = (CellNoeud *) malloc(sizeof(CellNoeud));
            new_cell ->nd = new; 
            new_cell ->suiv = R->noeuds ;
            R->noeuds  =new_cell;
            R->nbNoeuds +=1 ;

            insererNoeudArbre(new, a,parent);
            return new; 
        }*/
        
        

        
        if(x < (*a) ->xc){ //le cas ou le noeud n'est pas dans l'arbre
            if(y < (*a) ->yc){
                return rechercheCreeNoeudArbre(R, &(*a)->so, *a, x, y );
            }else{
                return rechercheCreeNoeudArbre(R, &(*a)->no, *a, x, y );
            }
        }else{
            if(y < (*a) ->yc){
                return  rechercheCreeNoeudArbre(R, &(*a)->se, *a, x, y );
            }
            else{
                return rechercheCreeNoeudArbre(R, &(*a)->ne, *a, x, y );
            }
        }

    }

    if((*a)->noeud == NULL && *a != NULL){
         if(x < (*a) ->xc){ //le cas ou le noeud n'est pas dans l'arbre
            if(y < (*a) ->yc){
                return rechercheCreeNoeudArbre(R, &(*a)->so, *a, x, y );
            }else{
                return rechercheCreeNoeudArbre(R, &(*a)->no, *a, x, y );
            }
        }else{
            if(y < (*a) ->yc){
                return  rechercheCreeNoeudArbre(R, &(*a)->se, *a, x, y );
            }
            else{
                return rechercheCreeNoeudArbre(R, &(*a)->ne, *a, x, y );
            }
        }
    }
    }
    return NULL;

}

Reseau * reconstitueReseauArbre(Chaines *C){
    double xmin,ymin,xmax,ymax; 
    chaineCoordMinMax(C,&xmin,&ymin,&xmax,&ymax);
    ArbreQuat *abr = creerArbreQuat(xmax + xmin / 2, ymax+ymin / 2,xmax - xmin , ymax-ymin);
    Reseau * res = (Reseau *)malloc(sizeof(Reseau));
    res->gamma = C->gamma;
    res->nbNoeuds = 0;
    res ->commodites = NULL;
    res->noeuds = NULL;

    CellChaine *tmp_chaine = C->chaines;
    CellCommodite *liste_commo  =NULL ; 

    for(int i = 0 ;  i < C->nbChaines ; i++){
        CellPoint *tmp_ptn = tmp_chaine->points;
        Noeud *premier =NULL;
        Noeud *prec = NULL;
        while(tmp_ptn){
            Noeud * nouveau = NULL;
            if(tmp_ptn->x < abr->xc){
                if(tmp_ptn ->y < abr->yc){
                   nouveau = rechercheCreeNoeudArbre(res,&abr->so,abr,tmp_ptn->x, tmp_ptn->y);
                }
                else{
                    nouveau = rechercheCreeNoeudArbre(res,&abr->no,abr,tmp_ptn->x, tmp_ptn->y);
                }
            }else{
                if(tmp_ptn ->y < abr->yc){
                   nouveau = rechercheCreeNoeudArbre(res,&abr->se,abr,tmp_ptn->x, tmp_ptn->y);
                }else{
                   nouveau = rechercheCreeNoeudArbre(res,&abr->ne,abr,tmp_ptn->x, tmp_ptn->y);
                }
            }

            if(premier ==NULL){
                premier = nouveau;
            }
            if(prec != NULL){
                prec->voisins = insererNoeud(prec->voisins,nouveau);
                nouveau->voisins = insererNoeud(nouveau->voisins , prec);

            }

            prec =nouveau;

            tmp_ptn = tmp_ptn->suiv;

        }
        CellCommodite *commo = (CellCommodite *)malloc (sizeof(CellCommodite));
        commo->extrA = premier;
        commo->extrB = prec;
        commo->suiv = liste_commo;
        liste_commo = commo;
    }

    res->commodites = liste_commo;

    return res;
}