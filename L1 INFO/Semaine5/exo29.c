#include<stdio.h>
#include<stdlib.h>
#include<string.h>


void aff_char(char * s)
{
	int i = 0; 
	while( s[i]!= '\0')
	{
		if((s[i]<='Z' && s[i]>='A') || (s[i]<='z' && s[i] >= 'a'))
			{ 
				printf("%c",s[i]);
			}
		i++;
	} 
	
	printf("\n");
}


char *lettre(char *s)
{
	int i = 0, j=0; 
	char *tab = malloc( (strlen(s)+1) * sizeof(char));
	while( s[i]!= '\0')
	{
		if((s[i]<='Z' && s[i]>='A') || (s[i]<='z' && s[i] >= 'a'))
			{ 
				tab[j] = s[i];
				j++;
			}
		i++;
	} 
	return tab;
}

//question 3: oui
	

int main()
{
	char *s = "Hello inchallah ù$$ù$$ù$ù$ù$$ù$ùù";
	aff_char(s);
	aff_char(lettre(s));
	
	
	return 0;
}

