#include <stdio.h>
#include <stdlib.h>


int compte_mots_chaines(char *chaine)
{
	int cpt = 0;
	while(*chaine != '\0')
	{
		if(*chaine==' ' && *chaine != '\0')
		{
			chaine++;
			continue;
		}
		cpt +=1;
		while(*chaine != ' ' && *chaine !='\0')
		{
			chaine++;
		}
	}
	return cpt;
}


/*int compte_mots_tableau(char **ptr)
{
	int n = 0;
	while(*ptr != NULL)
	{
		n+=1;
		
		ptr++;
	}
	
	return n-1;
}*/

int compte_mots(char **ptab_mots)
{
  /* a completer exercice 3, question 2 */
  int n = 0;
  while(*ptab_mots != NULL){
    n++;
    ptab_mots++;
  }
  return n;
}

int main()
{
	char *s ="   dfd  dijf  idjfi  diididi i ";
	int nb_mot = compte_mots_chaines(s);
	printf("le nb_mot de mot est de %d \n", nb_mot);
	
	/*char **ptr = {"mot1","mot2","mot3","mot4s"};*/
	char **tab_s = {"mot1","et","mot2","et"," ","mot3"};
	int mot_tab = compte_mots(ptr);
	printf("le nb  de mot ds le tab est de %d \n", mot_tab);
	return 0;
	
}
