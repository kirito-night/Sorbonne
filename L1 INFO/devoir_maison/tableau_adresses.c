#include<stdio.h>
#include<stdlib.h>
//question 8
void echange(int t1[], int t2[], int len)
{
	int i;
	for(i=0; i< len; i++)
	{
		if(t1[i]<t2[i])
		{
			int tmp;
			tmp = t1[i];
			t1[i] = t2[i];
			t2[i] = tmp;
		}
	}
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
	int t1[6] = { 7, 3, 9, 4, 5, 7 };
	int t2[6] = { 6, 4, 8, 3, 6, 5 };
	int t3[12] = { 7, 3, 9, 4, 5, 7, 6, 4, 8, 3, 6, 5 };
	affiche_tab(t1,6);
	affiche_tab(t2,6);
	echange(t1,t2,6);
	printf("\n");
	affiche_tab(t1,6);
	affiche_tab(t2,6);
	printf("\n");
	
	//question 9
	
	affiche_tab(t3,12);
	echange(t3,&t3[6],6);
	printf("t3 maintenant vaut\n");
	affiche_tab(t3,12);
	/*
	
	en executant echange(t3,&t3[6],6); nous comparons la premiere partie du tableau, du 0 eme element au 5 eme element ,et la deuxieme partie du tableau, du 6 eme element au 11eme element. Car &t3[6] correspond a l'adresse du 6 eme element. et ensuite la fonction va echanger les valeurs de t3, si t3[i]<t3[i+6].
	
	*/
	
	return 0;
	
}
