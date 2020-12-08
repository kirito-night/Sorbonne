#include <stdio.h>
#include<stdlib.h>

typedef struct cellule_t cellule_t;
struct cellule_t
{
	int donnne;
	cellule_t *suivant;
};

cellule_t *cons(int n, cellule_t *liste)
{
	cellule_t *cell;
	int i;
	for(i=0;i<n;i++)
	{
		cell = malloc(sizeof(cellule_t));
		int don;
		printf("entrer les valeurs de votrer liste dans l'ordre inverse\n");
		scanf("%d", &don);
		cell->donnne= don;
		cell->suivant = liste;
		liste = cell;
	}
	return cell;
}
void aff_list(cellule_t *liste)
{
	if (liste==NULL)
	{
		printf("ya pas de liste\n");
	}
	cellule_t *cell = liste;
	while(cell != NULL)
	{
		printf("%d\t",cell->donnne);
		cell = cell->suivant;
	}
	printf("\n");
}
	
cellule_t *f(int v1, int v2, cellule_t *liste)
{
	cellule_t *n;
	if(liste == NULL || v2 == 0)
	{
		n = malloc(sizeof(cellule_t));
		n->donnne = v1;
		n->suivant = liste;
		return n;
	}
	liste->suivant = f(v1,v2-1,liste->suivant);
	return liste;
}

/*void chaine(char s[], int tab[])
{
	int i=97,j=0;
	while(i<123)
	{
		int cpt = 0;
		while(s[j]!='\0')
		{
			if(s[j]== i)
			{
				cpt+=1;
			}
			j+=1;
		}
		tab[i]=cpt;
		printf("%d\n",tab[i]);
		i+=1;
	}
	
}*/
void affiche_tab(int tab[],int taille)
{
	int i;
	for(i=0; i<taille; i++)
	{
		printf("%d\n",tab[i]);
	}
}



cellule_t * f3(int val,cellule_t *liste) {
  cellule_t *ptr=liste;
  cellule_t *pred=NULL;
  
  while ((ptr != NULL) && (ptr->donnne <= val)){
    pred=ptr;
    ptr=ptr->suivant;
  }
  
  pred->suivant = malloc(sizeof(cellule_t));
  pred->suivant->donnne = val;
  pred->suivant->suivant = ptr;
  return liste;
} 	

void permute(char ch[],int taille)
{
	int i;
	char tmp = ch[taille-1];
	char tmp1= ch[0];
	for(i=0;i<taille;i++)
	{ 
		ch[i]= tmp1;
		tmp1 = ch[i+1];
		ch[i+1]= ch[i];
		
	}
	ch[0]=tmp;
}
void affiche_char(char tab[],int taille)
{
	int i;
	for(i=0; i<taille; i++)
	{
		printf("%c\n",tab[i]);
	}
}

	
int main()
{
	/*cellule_t *liste=NULL;
	liste = cons(3,liste);
	aff_list(liste);
	liste = f3(12,liste);
	aff_list(liste);*/
	char *s = "abcde";
	permute(s,6);
	affiche_char(s,6);
	
	
	
	

	return 0;
	
	
}
		
	
