#include<stdlib.h>
#include<stdio.h>

typedef struct _cellule_t cellule_t;
struct _cellule_t
{
	int donnee;
	cellule_t *suivant;
};



int len(cellule_t *list)
{
	int compt = 0;
	while(list->suivant !=NULL)
	{ 
		compt +=1;
		list = list->suivant;
	}
	return compt+1;
}








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
	cellule_t * tete = NULL;
	cellule_t * cell1 = NULL, *cell2 = NULL , *cell3 = NULL;
	cell1 = creer_cellule(1);
	cell2 = creer_cellule(2);
	cell3 = creer_cellule(3);
	tete = cell1;
	cell1 -> suivant = cell2;
	cell2 ->suivant = cell3;
	Afficher_liste_int(tete);
	
	printf("%d\n", len(tete));
}
