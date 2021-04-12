#include<stdlib.h>
#include <stdio.h>

typedef struct {
    int* tab; // le vrai tableau
    int free; // première case libre
    int taille; // taille du tableau
} Tableau;
//1.1
int rang(Tableau* t, int x, int deb, int fin)
{
    if(deb==fin) {
        if(t->tab[deb] > x) {
            return deb;
        } else {
            return deb + 1;
        }
    }
    else if(deb == fin-1) {
        if(t->tab[fin] > x) {
            return fin;
        } else {
            return fin+1;
        }
    } else {
        int milieu = (deb+fin)/2;
        
        if(x < t->tab[milieu]) {
            return rang(t, x, deb, milieu-1);
        } else {
            return rang(t,x,milieu, fin);
        }
    }
        
}
/* compleixte O(log(n))
tableau de taille n, on divise par le deux le tableau a chaque appel 
jusqu'a ce que sa taille soit <= 1 
on peut definir p  qui est le nb d'appels a la fonction rang 
donc on peut dre que la taille du tableau est environ n/(2^p)
on veut p le plus petit possible pour atteindre un tableau de taille 1 
n/(2^p) <= 1 <==> p >=log(n)
autre facon - ; commme on divise tjr par 2 et que els sous tableaux ont comme taille n/(2^p) alors on fait log(n) appel 
*/

//1.2
Tableau * triInsertionDicho(Tableau *t){
    Tableau * tri = (Tableau*)malloc(sizeof(Tableau));
    tri->tab = (int*) malloc(sizeof(int)*(t->taille));
    tri->taille = t->taille;
    tri->free = 0;
    for(int i = 0 ; i < t->taille ; i++){
        int elt = t->tab[i];
        if(t->free == 0){ // si c'est le premier element qu'on insere
            tri->tab[0] = elt;
            tri->free++;
        
        }else{//on doit chercher le rang ou placer elt
            int indice = rang(tri, elt, 0, tri->free );
            for(int j = tri->free;  j> indice ; j--){
                tri->tab[j] = tri->tab[j-1];
            }
            tri->free++;
            tri->tab[indice] = elt;

        }
    }


    return tri;
}
/* complexite O(n^2) car deux boucles une qui parcourt tout le tableau et une qui dans le pire  */ 

//1.3
/*rechercher dans une liste O(i)
methode de tri -> O(n^2)
pas de possible d'utiliser recherche dicho dans les listes
*/

//exercice 2
//2.1
void triPostal (Tableau* t, int min , int max){
    int taille = max-min+1;
    int *T = (int *) malloc(sizeof(int)*taille);
    for(int i = 0 ; i < taille; i++){
        T[i] = 0 ;

    }
    for(int i= 0 ; i < t->free; i++){
        T[t->tab[i]-min]+=1;

    }
    int k = 0; 
    for(int i = 0 ; i<t->free; i++){
        for(int j = 0 ; j < T[j]; j++){
            t->tab[k] = i+min;
            k++;
        }
    }
    free(T);
}
//2.2
/*complexite O(n+ (max - min)) = O(n)
on peut pas avoir des valeurs quelconques, il faut qu'elles soient assimilees a des entiers 
(par exemple des char) avec une fonction d'hachage
pour calculeer l'indice)  et il faut que max - min soit petit pour tenir en memoire

*/
//2.3
/* au lieu de d'utiliser un tableau on peut utiliser une table de hachage 
avec gestion des collisions par chainage . 
on choisit un nombre N correspondant au nombre de cases de la table et on stocke de la maniere suivante :
case 0 on met toutes les valeurs sui sont entre min et min + (max-min) /N
case 1 toutes les valeurs entre (max - min )/N et 2*(max-min / N)
case 2  -------
on va trier chaque liste chainnee et on concatene les listes 
*/


//exercice 3 
//3.1
int tabsum(Tableau *tab, int b){
    for(int i ; i< tab->taille)
}
