#include "Reseau.h"
#include "Chaine.h"
#include <stdlib.h>
#include "SVGwriter.h"
#include<math.h>
#include"Hachage.h"
#include "ArbreQuat.h"
Noeud* rechercheCreeNoeudListe(Reseau *R, double x, double y)
{
    CellNoeud* temp_noeud = R->noeuds; //on prend la liste des noeud du reseau 

    while(temp_noeud!=NULL) //on fait la recherche dans le noeud 
    {
        if((temp_noeud->nd->x == x) && (temp_noeud->nd->y == y))
        {
            //printf("point dedans\n");
            return temp_noeud->nd; //si trouver on retourne le resultat et on sort de la fonction 
        }        
        temp_noeud = temp_noeud->suiv;
    }
    //non trouver, on creer le noeud simplement et on insere en tete 
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
    //printf("point n'est pas dedans \n");
    return nouveau;


}

CellNoeud * insererNoeud(CellNoeud * liste_nd, Noeud *insere){ //fonction elementaire pour gerer les noeud voisins
    CellNoeud * tmp = liste_nd;
    while(tmp && tmp->nd != insere){
        
        tmp = tmp->suiv;
        
    }
    if(tmp == NULL){
        CellNoeud *new  = (CellNoeud *)malloc(sizeof(CellNoeud)); 
        new->nd = insere;
        new->suiv = liste_nd;
        liste_nd = new;

    }
    //printf("element est insere\n");
    return liste_nd; 
}


Reseau* reconstitueReseauListe(Chaines *C){
    
    //on initialise le reseau 
    Reseau * res = (Reseau*) malloc(sizeof(Reseau));
    res->gamma = C->gamma;
    res->nbNoeuds = 0 ;
    res->noeuds = NULL;
    res->commodites = NULL;

    CellChaine *tmp_chaine = C->chaines; //on prend la liste des chaines 
    
    CellCommodite *liste_commo = NULL; //la liste de commodite que nous allons inserer 

    for(int i = 0 ;  i < C->nbChaines ; i++){ //parcours des chaines 
        CellPoint *tmp_ptn = tmp_chaine->points; //on prend la liste des points de la chaine 

        Noeud *premier =NULL; // premier pour sauvegarder le premier point de la chaine pour le mettre dans la commodite 
        Noeud *prec = NULL; //un pointeur sur le precedent pour gerer les voisins

        while(tmp_ptn){
            Noeud* nouveau = rechercheCreeNoeudListe(res,tmp_ptn->x,tmp_ptn->y); // on cree  un noeud si elle n'est pas cree sinon on la trouve 
            if(premier ==NULL){
                premier = nouveau; // le premier point 
            }
            if(prec != NULL){ //si il a un precedent, on inserer le noeud dans la liste voisin du noeud precedent et dans la liste voisin du noeud present 
                prec->voisins = insererNoeud(prec->voisins,nouveau);
                nouveau->voisins = insererNoeud(nouveau->voisins , prec);

            }
            prec = nouveau;
            

            tmp_ptn = tmp_ptn->suiv;
           
        }
        CellCommodite *commo = (CellCommodite*)malloc(sizeof(CellCommodite)); //pour chaque chaine on cree la commodite 
        commo->extrA = premier ;   
        commo->extrB = prec ; //a la fin de la boucle prec est le dernier element 
        commo->suiv =liste_commo; // insertion en tete
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
    return res /2 ; //les liaisons sont comptes en double donc division par 2 a la fin 
}


void ecrireReseau(Reseau *R, FILE *f){
    int nbl = nbLiaisons(R);
    int nbc = nbCommodites(R);
    fprintf(f,"NbNoeud :  %d\n NbLiaisons :  %d\n Nbcommodites :  %d\nGamma :  %d\n\n", R->nbNoeuds , nbl ,nbc, R->gamma);  //ecriture dans le fichier passer en parametre 
    CellNoeud *liste_nd = R->noeuds;
    

    //boucle pour les nb noeud 
    for(int  i = 0 ; i< R->nbNoeuds ; i++){
        fprintf(f,"v %d %.2f %.2f \n", liste_nd->nd->num,liste_nd->nd->x, liste_nd->nd->y);
        liste_nd = liste_nd->suiv;
    }
    fprintf(f, "\n");


    liste_nd = R->noeuds;
    
    //pour les liaisons 
    CellNoeud * liste_voisin = NULL;
    for(int j = 0 ; j < nbl ;){ //on fait j++ seulement quand on ecrit une liaison et non a chaque tour 
        if(liste_nd ==NULL){
            break;
        }
        liste_voisin = liste_nd->nd->voisins;
        
        while(liste_voisin){
            if(liste_voisin->nd->num < liste_nd->nd->num){ //il existe des doublons, on ecrit dans le fichier que quand celui qui est dans le voisin a un num plus petit 
                fprintf(f,"l %d %d \n ", liste_voisin->nd->num, liste_nd->nd->num );
                j++; //une liasion ecrite donc j++
            }
            
            liste_voisin = liste_voisin->suiv;
        }
        liste_nd = liste_nd->suiv;
         
    }
    
    
    fprintf(f, "\n");

    //pour les commodites
    CellCommodite *liste_commo = R->commodites;
    for(int k = 0 ; k < nbc ; k++){
        fprintf(f, "k %d %d \n", liste_commo->extrA->num,liste_commo->extrB->num);
        liste_commo = liste_commo->suiv;
    }

    return ;
}

void afficheReseauSVG(Reseau *R, char* nomInstance){ //fonction fournit 
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

//###################
//##################
//les code pour la table d'hachage


int fonctionCle(double x, double y)
{ //fonction de cle qui est assez efficace pour eviter les collisions 
    
    return  (int) y+((x+y)*(x+y+1))/2; 
}


int fonctionHachage(int cle, int m)
{//fonction d'hachage qui utilise le nombre d'or de Knuth
    float a = (sqrt(5)-1)/2;
    return (int)(m*(cle*a - (int)(cle*a)));
}

Noeud* rechercheCreeNoeudHachage(Reseau* R, TableHachage *H, double x ,double y){
    int i = fonctionHachage(fonctionCle(x,y),H->taille);
    CellNoeud *tmp = H->tab[i];
    while(tmp && tmp->nd->x!=x && tmp->nd->y !=y ){ //on sort de la boulce soit tmp ==NULL soit on a trouver le noeud 
    
        tmp = tmp->suiv;
    }
    if(tmp!= NULL){ //si c'est pas NULL alors on a trouver ce qu'il nous faut on retourne 
        return tmp->nd;
    }
    Noeud *new = (Noeud *) malloc(sizeof(Noeud)); // le cas que c'est NULL on a besoin de creer le noeud 
    new->x = x;
    new->y = y;
    new->num = R->nbNoeuds +1;
    new->voisins = NULL;


    // insertion en tete  dans reseau 
    CellNoeud *res = (CellNoeud*) malloc(sizeof(CellNoeud)); 
    res->nd = new;
    res->suiv = R->noeuds;
    R->noeuds = res;
    R->nbNoeuds++;

    // insertion en tete  dans table hachage 
    CellNoeud *resH = (CellNoeud*) malloc(sizeof(CellNoeud));

    resH->nd = new;
    resH->suiv = H->tab[i];
    H->nE++;
    H->tab[i] = resH;

    return new;

}



Reseau * reconstitueReseauHachage(Chaines *C, int M){ //code similaire au reconstitue reseau Liste 

    //initialisation du tableau d'hachage 
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

    CellChaine *tmp_chaine = C->chaines;  //on prend la channe pour faire le parcours 

    for(int i  = 0 ; i < C->nbChaines ; i++){
        CellPoint *tmp_ptn  =tmp_chaine->points;     //on prend la liste des points de la chaine pour faire le parcours 

        Noeud *premier = NULL;//poiteur pour sauvegarder le premier point de la chaine 
        Noeud *prec = NULL; //pointeur sur le point precedent pour gerer les voisins 
        while(tmp_ptn){
            Noeud *nouveau = rechercheCreeNoeudHachage(res,tabH, tmp_ptn->x , tmp_ptn->y);

            if(premier ==NULL){ 
                premier = nouveau;
            }
            if(prec != NULL){
                prec->voisins = insererNoeud(prec->voisins,nouveau); //inserer le noeud present dans la liste voisin du noeud precedent
                nouveau->voisins = insererNoeud(nouveau->voisins , prec); //inserer le noeud precedent  dans la liste voisin du noeud present 

            }
            prec = nouveau;
            tmp_ptn = tmp_ptn->suiv;
        }
        //creation et insertion en tete de la commodite 
        CellCommodite *commo = (CellCommodite*)malloc(sizeof(CellCommodite)); 
        commo->extrA = premier ;   
        commo->extrB = prec ; 
        commo->suiv =liste_commo;
        liste_commo= commo;

        tmp_chaine =tmp_chaine->suiv;

    }

    res->commodites =liste_commo;

    libererTable(tabH);
    return res;

    


}

void libererTable(TableHachage* tabH){
    
    for(int i=0 ; i < tabH->taille ; i++){
        free((tabH->tab)[i]) ; 
    }
    free(tabH->tab);
    free(tabH);
}

//###################
//##################


//les codes pour les arbres 

void chaineCoordMinMax(Chaines* C, double* xmin, double* ymin, double* xmax, double* ymax)
{
    if (C==NULL || C->chaines==NULL){ //pas de chaine on sort 
        printf("Pas de chaines");
        return;
    }
    CellChaine* CellTemp = C->chaines; //on prend la chaine pour faire le parcours 
    *xmin = INFINITY, *xmax = -INFINITY, *ymax = -INFINITY, *ymin = INFINITY;  //met a infinie pour etre sur que les valeurs sont des valeurs des chaines 

    while(CellTemp->suiv!=NULL)
    {
        CellPoint* p = CellTemp->points;
        while(p!=NULL)
        {
            //des comparaison pour determiner les min et les max
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

ArbreQuat* creerArbreQuat(double xc, double yc, double coteX,double coteY) //creation simple  de l'arbre quat 
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


void insererNoeudArbre(Noeud *n, ArbreQuat** a, ArbreQuat* parent){ //un double pointeur sur a, qui permet de modifier directerment a
    
    if(*a == NULL){ //le cas ou le parent est une feuille, on va creer un nouveau arbre quat

        double new_coteX = (parent->coteX) / 2; //les cotes 2x plus petit que les parents 
        double new_coteY = (parent->coteY) / 2;
        double new_x , new_y; //new_x et new_y pour determiner les coordonnees du centre de nouveau arbre quat cree 
        double xc = parent->xc;
        double yc = parent->yc;
        

    //les conditions de determinisation 
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

      (*a) = creerArbreQuat(new_x, new_y, new_coteX, new_coteY); //on cree l'arbre quat  avec les coordonnees centre et les cotes qu'on a trouve 
        (*a)->noeud = n; //on insere notre noeud dans l'arbre 

    }
    else{

        if((*a)->noeud != NULL ){// pour les feuille 
            Noeud * tmp  = (*a)->noeud ; 
            (*a)->noeud = NULL; //ce n'est plus une feuille apres l'insertion donc de mettre (*a)->noeud a NULL

            if(tmp->x < (*a)->xc){//inserer le noeud a en utilisant le cas de base 
                if(tmp->y < (*a)->yc){ 
                    insererNoeudArbre(tmp, &((*a)->so),*a );
                }   
                else{
                    insererNoeudArbre(tmp, &((*a)->no),*a );
                }   
            }else{
                if(tmp->y < (*a)->yc){
                    insererNoeudArbre(tmp, &((*a)->se),*a );
                }
                else{
                    insererNoeudArbre(tmp, &((*a)->ne), *a);
                }   
            }

            
           
        }

        
        if(n->x < (*a)->xc){ // inserer le noeud n dans tous les cas 
            if(n->y < (*a)->yc){
                insererNoeudArbre(n, &((*a)->so),*a );
            }
            else{
                insererNoeudArbre(n, &((*a)->no),*a );
            }
        }else{
            if(n->y < (*a)->yc){
                insererNoeudArbre(n, &((*a)->se),*a );
            }
            else{
                insererNoeudArbre(n, &((*a)->ne), *a);
            }   
        }
    }
}






Noeud* rechercheCreeNoeudArbre(Reseau* R, ArbreQuat** a, ArbreQuat* parent, double x, double y){ 


    if((*a) == NULL){// cas de base ou *a == NULL
        //on creer le noeud
        Noeud *new  = (Noeud *) malloc (sizeof(Noeud));
        new->x = x;
        new->y = y;
        new->num = R->nbNoeuds + 1;
        new->voisins = NULL;
        // on creer le CellNoued qui contient notre Noeud creer et fait une insetion en tete dans le reseau 
        CellNoeud *new_cell = (CellNoeud *) malloc(sizeof(CellNoeud));
        new_cell ->nd = new; 
        new_cell ->suiv = R->noeuds ;
        R->noeuds  =new_cell;
        R->nbNoeuds +=1 ;

        insererNoeudArbre(new, a,parent); //on insere le noeud dans l'arbre 
        return new; 
    }
    else{

    

        if((*a)-> noeud != NULL){ // pour une feuille
            Noeud *tmp_noeud = (*a)-> noeud; 
            if(fabs(tmp_noeud -> x -x) < 0.00001 && fabs(tmp_noeud ->y - y)< 0.00001){ //le cas ou le noeud est dans l'arbre
                return tmp_noeud;
            }
            else{ // le cas ou le noeud ne l'ai pas 

                //pareil que le cas de base 
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
            
            
            

        }else{
                // cas des noeud interne il faut qu'on aille dans le bon noeud 
                if(x < (*a) ->xc){ 
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
            //}
        }

        
    }
    return NULL;//jamais atteinte pour eviter les warning de compilation 

}








Reseau * reconstitueReseauArbre(Chaines *C){ //fonction similaire au deux autre fonction de reconstitution 

    //Initialisation  de l'arbre avec les coordonnees min max de la chaine 
    double xmin,ymin,xmax,ymax; 
    chaineCoordMinMax(C,&xmin,&ymin,&xmax,&ymax);
    ArbreQuat *abr = creerArbreQuat(xmin + ((xmax - xmin) / 2.0), ymin + ((ymax-ymin) / 2.0) ,xmax - xmin , ymax-ymin);

    //Initialisation  du reseau 
    Reseau * res = (Reseau *)malloc(sizeof(Reseau));
    res->gamma = C->gamma;
    res->nbNoeuds = 0;
    res ->commodites = NULL;
    res->noeuds = NULL;

    CellChaine *tmp_chaine = C->chaines;
    CellCommodite *liste_commo  =NULL ; 

    for(int i = 0 ;  i < C->nbChaines ; i++){ //similaire a deux autre fonction de reconstitution 
        CellPoint *tmp_ptn = tmp_chaine->points;
        Noeud *premier =NULL;
        Noeud *prec = NULL;
        while(tmp_ptn){
            Noeud * nouveau = rechercheCreeNoeudArbre(res,&abr,NULL,tmp_ptn->x, tmp_ptn->y);
            

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

        tmp_chaine= tmp_chaine->suiv;

        CellCommodite *commo = (CellCommodite *)malloc (sizeof(CellCommodite));
        commo->extrA = premier;
        commo->extrB = prec;
        commo->suiv = liste_commo;
        liste_commo = commo;
    }

    res->commodites = liste_commo;
    libererArbreQuat(abr);

    return res;
}


void libererArbreQuat(ArbreQuat* a){
    if(a){
        if(a->se){
            libererArbreQuat(a->se);
        }

        if(a->so){
            libererArbreQuat(a->so);
        }

        if(a->ne){
            libererArbreQuat(a->ne);
        }

        if(a->no){
            libererArbreQuat(a->no);
        }

    }
    free(a);
    
}