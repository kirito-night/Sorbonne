#include<stdio.h>
#include<stdlib.h>
#define K 5
#define N 2
// question 4
int val_pos(int n, int t[],int len)
{

	int i,k=0;
	for(i=0; i<len; i++)
	{
		if(t[i] > 0)
		{
			k += 1;
		}
	}
	if(k >= n)
	{
		return 1;
	}
	return 0;
}

// question 5 
//premier occurence

int indice_min(int t[], int len)
{
	int k = t[0];
	int i;
	int j;
	for(i=0; i<len; i++)
	{
		if(k >t[i])
		{
			k = t[i];
			j = i;
		}
	}
	
	return j;
}

//question 6
void replace_min_if_sup(int n, int t[], int len)
{
	int i = indice_min(t,len);
	if(n>t[i])
	{
		t[i] = n;
	}
}

//question 7
int *t_best(int n , int t[], int len)
{
	int k = t[0];
	int *tab = malloc(n * sizeof(int));
	int i; 
	for(i =0; i < len; i++)
	//trouver un element qui est superieur au plus grand element du tableau
	{
	 	if(k< t[i])
	 	{
	 		k = t[i];
	 	}
	 }
	 k += 1; // frocement exclus du tableau et superieur au plus grand elem du tableau
	int j;
	for(j = 0; j < (len - n); j++)
	{
		replace_min_if_sup(k,t,len);
	}	
	
	int p, z = 0;
	for(p=0; p<len ; p++)
	{
		if(t[p] != k)
		{
			*(tab+z) = t[p];
			z +=1;
			
		}
	}
	
	return tab;
}
		
		
void affiche_tab(int t[], int len)
{
	int i;
	for(i=0; i< len; i++)
	{
		printf("%d\t", t[i]);
	}
	printf("\n");
}

int main()
{
	
	int t[K]= {4,-1,2,8,6};
	printf("%d\n",indice_min(t,K));
	
	
	affiche_tab(t,K);
	affiche_tab(t_best(3,t,K), 3);
	
	return 0;
	
}
	
