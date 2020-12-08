#include<stdlib.h>
#include<stdio.h>
#define X 5
#define Y 3
int iterative(int tab[], int taille , int elem)
{ 
	int i;
	for(i = 0; i < taille ; i++)
	{ 
		if (tab[i] == elem)
		{
			return 1;
		}
	}
	return 0;
}


int recursive(int *tab, int taille , int elem)
{
	
	if (*tab == elem)
		return 1;
	if(taille == 0)
		return 0;
	return recursive(tab+1 , taille -1 ,elem);
}
	

int main()
{
	int tab[6] = {1,2,3,4,7,8};
	if(iterative(tab, 6, X))
	{
		printf(" il est dedans\n");
	}
	else 
	{
		printf( " non il n'est pas\n");
	}
	
	if(recursive(tab, 6, Y))
	{
		printf(" il est dedans");
	}
	else 
	{
		printf( " non il n'est pas");
	}
	printf("\n");
	
	
	
	
	return 0;
}
