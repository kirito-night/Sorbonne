#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int compte_mots_chaine(char *chaine) {
  /* a completer exercice 3, question 1 */
  int n = 0;
  char *c = chaine;
  while(*c!='\0')
  {
  	if(*c== ' ' && *c != '\0')
  	{
  		c++;
  		continue;
  	}
  	n++;
  	while(*c!=' ')
  	{
  		c++;
  	}
  }
  return n;

}

char **decompose_chaine(char *chaine)
{

  /* Fonction vue en TD, exercice 2, question 7 */

  char *pc= chaine;
  int nb_mots=0;
  char **ptab;
  char *psrc_mot;
  int ind_mot=0;

  //comptages des mots
  nb_mots=compte_mots_chaine(chaine);
	
  if (nb_mots == 0)
    return NULL;
  // allocation du tableau

  ptab = malloc((nb_mots + 1) * sizeof(char *));
  ptab[nb_mots] = NULL;
	
  // copie des mots
	
  pc=chaine;
  while (*pc)
    {
      if(*pc == ' ')
        {
          pc++;
          continue;
        }

      psrc_mot = pc;

      while((*pc != ' ') && (*pc)) pc++;

      //allocation du mot
      ptab[ind_mot] = malloc((pc - psrc_mot + 1)* sizeof(char));
      //copie du mot
      memcpy(ptab[ind_mot], psrc_mot, pc - psrc_mot);
      //insertion du marqueur de fin de chaine
      *(ptab[ind_mot] + (pc - psrc_mot)) = '\0';

      ind_mot++;
    }
	

  return ptab;
}

char *compose_chaine(char **ptab_mots)
{
  /* a completer exercice 3, question 4 */
}
		
void detruit_tab_mots(char **ptab_mots)
{

  /* Fonction vue en TD, exercice 2, question 5 */

  int i=0;

  if (ptab_mots)
    while(ptab_mots[i])
      free(ptab_mots[i++]);

  free(ptab_mots);
}

int compte_mots(char **ptab_mots)
{
  /* a completer exercice 3, question 2 */
  int n =0;
  char **c = NULL;
  c=ptab_mots;
  while(*c!=NULL)
  {
  	
  		n++;
  		c++;
  	
  }
  return n;	
}

void affiche_tab_mots(char **ptab_mots)
{
  /* a completer exercice 3, question 3 */
}

char **reduit_tab_mots(char **ptab_mots)
{
  /* a completer exercice 4, question 1 */
}

int main() {

  /* a completer:
   * exercice 3, question 3, 5 
   * exercice 4, question 1
   */
	char *s = "je suis bla bla   yes ";
	int n1 = compte_mots_chaine(s);
	
	printf("%d\n",n1);
	
	 char **tab_s = {"mot1"};
	 int n2 = compte_mots(tab_s);
	 printf("%d\n",n2);
	 
  return 1;
}
