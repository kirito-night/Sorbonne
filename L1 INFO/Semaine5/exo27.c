#include<stdio.h>
#include<stdlib.h>


int compt(char *tab)
{
	int i = 0;
	int n = -1; 
	
	while (tab[i]!= '\0')
	{
		
		if((tab[i] == ' ') && (tab[i+1] != ' '))
		{
			n +=1;
		}
		i += 1;
		
	}
	if(tab[0] != ' ')
	{
		n +=1;
	}
	return n; 
}
		
// hello world lol

int main()
{
	char *s = "         lol lol lol lol          ";
	int n = compt(s);
	printf("nombre de mot :%d\n", n);
}
