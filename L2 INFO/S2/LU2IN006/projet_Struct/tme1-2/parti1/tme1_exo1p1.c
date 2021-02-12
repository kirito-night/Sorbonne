#include <stdio.h>
#include<stdlib.h>

const static int len = 10 ;

int main (void){
    int * tab;
    int i ;

    tab = (int *) malloc (len * sizeof(int));

    for(i = len-1 ; i>= 0; i--){
        tab[i] = i;
        
    }

    free(tab);
    return 0;
}

/*
1.1 le programme est cense de remplir le tableau, avec la valeur de la n-ieme case est n, 
apres avoir lancer le programme nous rencontrons une segmentation fault

1.2 cette segmentation fault s'explique par
 nous avons essayer d'acceder une case qui n'existait pas dans le tableau 
 car avec gbd en affichant la valeur de i, i a pour valeur 4294967295, qui n'est clairement par une valeur dans tab,
 et nous avont essayer a acceder cette case, car cette valeur est >= a 0

1.3
pour resoudre cette erreur il suffit de enlever le unsigned de la variable i pour 
qu'elle puisse devenir negatif, pour pouvoir sortir de la boucle 
ou bien de enlever le egale de la boucle for car , par defaut quand c initialise le tableau avec tous les valeurs a 0

*/
