#include<stdlib.h>
#include<stdio.h>

typedef struct _cellule_t cellule_t;
struct _cellule_t
{
	int donnee;
	cellule_t *suivant;
};




cellule_t *creer_cellule(int d) 
{
	cellule_t *cell = malloc(sizeof(cellule_t));
	cell->donnee = d;
	cell->suivant = NULL;
	return cell;
}



void Afficher_liste_int(cellule_t *liste)
{
	/*Affiche les champs donnee des elements de la liste*/
	cellule_t *cell = liste;
	while(cell != NULL) 
	{
		printf("%d ", cell->donnee);
		cell = cell->suivant;
	}
	printf("\n");
}




int main()
{
	cellule_t * tete;
	cellule_t * cell1 = NULL, *cell2 = NULL , *cell3 = NULL;
	cell1 = creer_cellule(1);
	cell2 = creer_cellule(2);
	cell3 = creer_cellule(3);
	tete = cell1;
	cell1 -> suivant = cell2;
	cell2 ->suivant = cell3;
	Afficher_liste_int(tete);
	return 0;
}
	

	
/*
question 4 

ce programme va afficher les valeurs des donnee de la listes :
1 2 3 
en modifiant afficher(nCell1) en afficher ligne (nCell2) va faire que les donnee de la cell1 ne sera pas afficher donc on aura du :
2 3 

*/

/* 
question 5 

le programme va juste sauter la cellule deux est directement passer a la cellule 3 

*/ 

/* 
question 6
cava creer une boucle infinit dans la fonction affichage car plus aucun cellule se termine avec NULL
*/




