#include <stdio.h>
#include <stdlib.h>
#include "liste_entiers.h"

cellule_t * creerListe(int n) {
/* cree une liste de n entiers saisi par l'utilisateur
  renvoie l'adresse du premier element de la liste */
  int i;
  int val;
  cellule_t *tete=NULL;
  cellule_t *ptr;
  
  printf("Saisie des %d elements de la liste\n",n);
  for (i=0; i < n; i++) {
    printf("Element %d :",i+1);
    scanf("%d",&val);
    ptr=malloc(sizeof(cellule_t));
    ptr->donnee = val;
    ptr->suivant = tete;
    tete = ptr;
  }
  return tete;
}


void AfficherListeInt(cellule_t *liste)
{
	cellule_t *cell=liste;
	while( cell != NULL)
	{
		printf("%d\t", cell->donnee);
		cell = cell->suivant;
	}
	printf("\n");
} 


int nb_occurences(int val, cellule_t *liste)
{
	int n = 0;
	cellule_t *cell = liste;
	while ( cell != NULL)
	{
		if( cell->donnee == val)
		{
			n += 1;
		}
		cell = cell->suivant;
	}
	return n;
}


int tous_plus_grand(int val, cellule_t *liste)
{
	int n = 1;
	cellule_t *cell = liste;
	while ( cell != NULL)
	{
		if( cell->donnee < val)
		{
			n = 0;
		}
		cell = cell->suivant;
	}
	return n;
}

cellule_t* Max(cellule_t* liste)
{
	
	cellule_t *cell = liste;
	cellule_t *max = cell;
	while ( cell != NULL)
	{
		if( max->donnee < cell->donnee)
		{
			max = cell;
		}
		cell = cell->suivant;
	}
	return max;
}


int renvoyer_val_element_pos(int pos,cellule_t* liste)
{
	cellule_t *cell = liste;
	int i;
	for(i =1; i<=pos;i++)
	{
		cell = cell->suivant;
	}
	return cell->donnee;
}

cellule_t *Concatener_it( cellule_t* liste1, cellule_t* liste2)
{
	cellule_t *cell = liste1;
	if(liste1 == NULL)
	{
		return liste2;
	}
	if(liste2 == NULL)
	{
		return liste1;
	}
	
	while( cell->suivant != NULL)
	{
		cell = cell->suivant;
	}
	cell->suivant = liste2;
	return liste1;
}

int nb_maximum(cellule_t *liste)
{
	int n = 0;
	

	cellule_t *cell = liste;
	while(cell != NULL)
	{
		if (Max(liste)->donnee == cell->donnee)
		{
			n+=1;
		}
		cell= cell->suivant;
	}
	return n;
}
		

		
