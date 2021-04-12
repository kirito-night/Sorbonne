//1.1 table d'hachage  , l'empreinte c'est la clé 

/*1.2
un tableau de 10^5, et donc une fonction d'hachage de %10^5
donc on a un tableau de taille max 100000 = 10^5 donc on veut une fonction qui retourne une valeur %10^5

*/
//1.3
/*
( (x1+1) * (x2+1) * ... )%100000

*/
int fonctionHachage(int tab[], int taille){
    int i ;
    int res = 1;
    for(i = 0 ; i < taille ; i ++){
        res = res * (tab[i]+1);
    }
    return res;
}

/*1.4
 les trois retourne la meme clef
pour resoudre ce pb : un table hachage qui contient des structure de liste chaine
1.4 : même clé donc besoin de gérer les collisions : utiliser des listes ou un adressage ouvert
*/



//exercice 2 comparaison de differents types de table

/*2.1
R.Q :comme c'est modulo 16 on prend que le dernier caractere 
0x indique que l'on parle d’hexadécimale


 g(0xFF2E) = 0xE = 14
 g(0x178DD38) = 0x8 = 8
c'est le modulo 16 des dernier chiffre 

*/
/*2.2

adressage ouvert: stocker directement dans la table 

adressage ouvert probing lineaire ? 
adressage ouvert et probing quadratique ?

probing lineaire: utilise la premiere case dispo 
probing quadratique : 

*/




//exercice 3 


//tme 2.6 fonction affichage  recherche par auteur  ,recherche par titre , suppresion d'un livre , la fusion et la fonction qui recherche les doublons 