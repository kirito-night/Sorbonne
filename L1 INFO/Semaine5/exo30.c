#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#define MAX 16
#define MIN 1




void init_tab(int tab[], int taille)
{	
	int i; 
	for(i=0; i < taille-1; i++)
	{
		tab[i] = rand()%2;
	}
	tab[taille - 1] = -1;


}


void affiche_tab(int tab[])
{
	int i; 
	while(tab[i]!= -1)
	{
		printf("%d ", tab[i]);
		i++;
	}
	printf("%d ", tab[i]);
	printf("\n");
}
int compress_tab(int tab_brut[], int tab_compress[])
{
	int i = 0,j = 0,compt; 
	while(tab_brut[i] != -1)
	{
		compt = 1;
		while(tab_brut[i] == tab_brut[i + compt])
		{
			compt += 1;
		}
		if (compt != 1)
		{
			tab_compress[j] =compt;
			j+=1;
		}
		tab_compress[j] = tab_brut[i];
		j +=1;
		i += compt;
	}
	return j;
	
}
	
	
int main()
{
	srand(time(NULL));
	int tab_brut[MAX];
	int tab_compress[MAX];
	init_tab(tab_brut, MAX); 
	
	affiche_tab(tab_brut);
	compress_tab(tab_brut[MAX],tab_compress[MAX]);
	affiche_tab(tab_compress);

	
	
	
	return 0;
	
}


