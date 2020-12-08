// nb jour de colonne 
// nb amies de lignes 
#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#define NB_JOURS 7
#define NB_AMIS 4


void init_tab( float tab[][NB_JOURS])
{
	int i, j;
	for(i = 0; i < NB_AMIS; i++)
	{
		for (j = 0; j < NB_JOURS; j++)
		{
			tab[i][j] = 0.0;
		}
	}
}
void alea_montant(float tab[NB_AMIS][NB_JOURS], int jour )
{
	int montant, payeur;
	montant = rand()%(50+1-30)+30;
	payeur = rand()%NB_AMIS;
	
	int i;
	for(i = 0; i < NB_AMIS; i++)
	{
		tab[i][jour]=-montant / (float)NB_AMIS;

	}
	tab[payeur][jour]= montant - montant / (float)NB_AMIS;
}
void affiche_tab(float tab[NB_AMIS][NB_JOURS])
{
	int i,j; 
	printf("\tj1\tj2\tj3\tj4\tj5\tj6\tj7\n");
	for( i = 0 ; i < NB_AMIS ; i++)
	{
		printf("%d\t",i);
		for(j = 0; j < NB_JOURS; j++)
		{
			printf("%.2f\t", tab[i][j]);
		}
		printf("\n");
	}
}
float solde_tot(float tab[NB_AMIS][NB_JOURS], int membre)
{
	/* membre < NB_AMIS*/
	int j;
	float som = 0.0; 
	for(j = 0; j < NB_JOURS; j++)
	{
		som += tab[membre][j];
	}
	return som;
}
		 
int main()
{
	srand(time(NULL));
	float tab[NB_AMIS][NB_JOURS];
	init_tab(tab); 
	affiche_tab(tab);
	printf("\n\n");
	int j;
	for(j = 0; j <NB_JOURS; j++)
	{
		alea_montant(tab , j );
	}
	affiche_tab(tab);
	printf("\n\n");
	int i; 
	for(i = 0; i < NB_AMIS; i++)
	{
		float n = solde_tot(tab,i);
		printf("%.2f\t", n);
	}
	printf("\n");
	
	return 0;
	
}

// LU1IN002@esimon.eu

