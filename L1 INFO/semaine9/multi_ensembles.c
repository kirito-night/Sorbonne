#include <stdio.h>
#include <stdlib.h>
#include "multi_ensembles.h"

  
/* Retourne un pointeur sur le premier element de valeur val, retourne NULL si aucun élément n'a la valeur val */
element_t * Recherche_val(element_t *ensemble, int val) 
{
	element_t *cell = ensemble;
	while(cell!=NULL)
	{
		if( cell->valeur = val)
		{
			return cell;
		}
		cell = cell->suivant  ;
	}
	return NULL;
}

/* Ajoute l'element val en tete de l'ensemble s'il n'apparait pas dans l'ensemble, augmente sa frequence de 1 sinon */
element_t * Ajout_tete_ensemble(element_t *ensemble, int val, int freq) 
{
	element_t *cell = malloc(sizeof(element_t));
	cell->valeur = val;
	cell->frequence = freq;
	if(Recherche_val(ensemble,val)->valeur = val)
	{
		cell->frequence = freq+=1;
		
	}
	cell->suivant = NULL;
	return cell;	
	
}


/* Affche tous les elements d'un ensemble avec leur frequence */
void Affiche_ensemble(element_t *ensemble) {
  element_t *ptr = ensemble;
  
  while (ptr != NULL) {
    printf("val : %d, frequence : %d\n",ptr->valeur,ptr->frequence);
    ptr=ptr->suivant;
  }
}

/* Saisie des n elements d'un ensemble */
element_t * Creation_ensemble(int n) {
  element_t *ensemble=NULL;
  
  int i = 0;
  int val;
  
  for (i=0; i < n; i++) {
    printf("Saisie d'un entier: ");
    scanf("%d",&val);
    ensemble=Ajout_tete_ensemble(ensemble,val,1);
  }
  return ensemble;
} 
