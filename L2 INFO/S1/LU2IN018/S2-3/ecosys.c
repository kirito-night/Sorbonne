#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include "ecosys.h"

float p_ch_dir=0.01;
int gain_energie_proie=6;
int gain_energie_predateur=20;
float p_reproduce_proie=0.4;
float p_reproduce_predateur=0.5;
int temps_repousse_herbe=-15;
float p_manger = 1;
/* PARTIE 1*/
/* Fourni: Part 1, exercice 3, question 2 */
Animal *creer_animal(int x, int y, float energie) {
  Animal *na = (Animal *)malloc(sizeof(Animal));
  assert(na);
  na->x = x;
  na->y = y;
  na->energie = energie;
  na->dir[0] = rand() % 3 - 1;
  na->dir[1] = rand() % 3 - 1;
  na->suivant = NULL;
  return na;
}


/* Fourni: Part 1, exercice 3, question 3 */
Animal *ajouter_en_tete_animal(Animal *liste, Animal *animal) {
  assert(animal);
  assert(!animal->suivant);
  animal->suivant = liste;
  return animal;
}

/* A faire. Part 1, exercice 5, question 1 */
void ajouter_animal(int x, int y,  float energie, Animal **liste_animal) {
  /*A Completer*/
  assert(x<SIZE_X && x>=0);
  assert(y < SIZE_Y && y >= 0);
  Animal *na = creer_animal(x,y,energie);
  Animal *tmp = ajouter_en_tete_animal(*liste_animal,na);
  *liste_animal = tmp; 
}

/* A Faire. Part 1, exercice 5, question 5 */
void enlever_animal(Animal **liste, Animal *animal) {

  Animal *na;
  if(*liste == NULL || animal == NULL){
    return ;
  }
  while((na = *liste) != NULL  && *liste != animal){
    liste = &na->suivant;
  }
  *liste = na->suivant;
  free(na);
}





/* A Faire. Part 1, exercice 5, question 2 */
Animal* liberer_liste_animaux(Animal *liste) {
   /*A Completer*/
   Animal *na = liste;
   while(na){
   Animal *tmp = na ->suivant;
   free(na);
   na = tmp;
  }

  return NULL;
}

/* Fourni: part 1, exercice 3, question 4 */
unsigned int compte_animal_rec(Animal *la) {
  if (!la) return 0;
  return 1 + compte_animal_rec(la->suivant);
}

/* Fourni: part 1, exercice 3, question 4 */
unsigned int compte_animal_it(Animal *la) {
  int cpt=0;
  while (la) {
    ++cpt;
    la = la->suivant;
  }
  return cpt;
}



/* Part 1. Exercice 4, question 1 */



void afficher_ecosys(Animal *liste_proie, Animal *liste_predateur) {
  unsigned int i, j;
  char ecosys[SIZE_X][SIZE_Y];
  int nbpred=0,nbproie=0;
  Animal *pa=NULL;

  /* on initialise le tableau */
    /*A Completer*/
  for(i=0;i<SIZE_X; i++){
  	for(j =0; j< SIZE_Y ; j++)
  	{	
  		ecosys[i][j]= ' ';
  	}
  }

  /* on ajoute les proies */
    /*A Completer*/
  pa = liste_proie; 
  while(pa){
  	nbproie++;
  	ecosys[pa->x][pa->y] = '*';
  	pa = pa->suivant;
  }
  /* on ajoute les predateurs */
  /*A Completer*/
  /*pa = liste_predateur;
  while(pa){
  	nbpred++;
  	if((ecosys[pa->x][pa->y] == '@') || (ecosys[pa->x][pa->y] = '*')){
  		ecosys[pa->x][pa->y] = '@';
  	}
  	else{
  		ecosys[pa->x][pa->y] = 'O';
  	}
  	pa = pa->suivant;
  	
  }
  "hmm, je sais pas pourquoi le code commentee ci-dessus n'affiche que des
  '@' donc j'ai fait la version ci-dessus qui affiche correctement"
*/
  pa = liste_predateur;
  while(pa){
    nbpred++;
    if(ecosys[pa->x][pa->y] == ' ' || ecosys[pa->x][pa->y] == 'O')
      {ecosys[pa->x][pa->y] = 'O';}
    else
      {ecosys[pa->x][pa->y] = '@';}
   
    pa = pa->suivant;
  }

  /* on affiche le tableau */
  /*A Completer*/
    printf("+");
  for(j = 0; j<SIZE_Y; j++){
    printf("-");
  }
  printf("+\n");

  for(i = 0; i<SIZE_X; i++){
    printf("|");
    for(j = 0; j<SIZE_Y; j++){
      printf("%c",ecosys[i][j]);
    }
    printf("|\n");
  }

  printf("+");
  for(j = 0; j<SIZE_Y; j++){
    printf("-");
  }
  printf("+\n");

}

void clear_screen() {
  printf("\x1b[2J\x1b[1;1H");  /* code ANSI X3.4 pour effacer l'ecran */
}

/* PARTIE 2*/

/* Part 2. Exercice 5, question 1 */

void bouger_animaux(Animal *la) {
    /*A Completer*/
  while(la){
    if(rand() * 1.0 / RAND_MAX < p_ch_dir )
    {
      int a = la->dir[0];
      int b = la-> dir[1];
      while(a == la->dir[0] && b == la->dir[1]){
        la->dir[0] = (la -> dir[0])%3  -1;
        la->dir[1] = (la->dir[1] )%3 -1 ;
      }

    }
    la->x = la -> x + la-> dir [0];
    if(la->x >= SIZE_X)
    {
      la->x -= SIZE_X;
    }else if(la->x < 0) {
      la-> x += SIZE_X; 
    }
    la->y += la->dir[1];
    if(la->y >= SIZE_Y)
    {
      la->y -= SIZE_Y;
    }else if(la->y < 0) {
      la->y += SIZE_Y; 
    }
    la = la->suivant;

  }
}




/* Part 2. Exercice 5, question 3 */
void reproduce(Animal **liste_animal, float p_reproduce) {
   /*A Completer*/
   if(liste_animal){
    Animal *ani = *liste_animal;
    while(ani){
        if(rand() *1.0 / RAND_MAX < p_reproduce)
        {
          Animal *baby  = creer_animal(ani->x, ani->y,ani->energie / 2);
          ani->energie = ani->energie / 2 ;
          *liste_animal = ajouter_en_tete_animal(*liste_animal, baby);

        }
    ani = ani->suivant;
    } 
   }

}





/* Part 2. Exercice 7, question 1 */
void rafraichir_proies(Animal **liste_proie, int monde[SIZE_X][SIZE_Y]) {
    /*A Completer*/
    if(liste_proie){
      Animal *a = *liste_proie;
      bouger_animaux (a);
      a = *liste_proie;
      while(a){
          a->energie  -= 1;
          if(monde[a->x][a->y] >= 0){
              a->energie += gain_energie_proie;
              monde[a->x][a->y] = temps_repousse_herbe;
          
          }
          if(a->energie < 0){
              Animal *tmp = a;
              a= a->suivant;
              enlever_animal(liste_proie, tmp);

          }
          else{
              a = a->suivant;
          }
          
      }
    }

}

/* Part 2. Exercice 8, question 1 */
Animal *animal_en_XY(Animal *l, int x, int y) {
    /*A Completer*/
    while(l!= NULL){
        if(l->x ==x && l-> y ==y){
          return l;
        }
        l = l->suivant ;
    }

  return NULL;
} 

/* Part 2. Exercice 8, question 2 */
void rafraichir_predateurs(Animal **liste_predateur, Animal **liste_proie) {
   /*A Completer*/
   if(liste_predateur){
      Animal* a = *liste_predateur;
      bouger_animaux(a);
      a = *liste_predateur;
      Animal * b ;
      while (a){
          a->energie -= 1; 
          if (liste_proie){
            b = animal_en_XY(*liste_proie, a->x, a->y);
            if((b != NULL)&& rand()*1./RAND_MAX < p_manger){
              a->energie += gain_energie_predateur;
              enlever_animal(liste_proie,b);
            }


          }
          if(a->energie < 0){
              Animal *tmp = a;
              a = a -> suivant;
              enlever_animal(liste_predateur,tmp);
          }
          else
          {
            a = a->suivant;
          }
          
      }
    
      
   }

}

/* Part 2. Exercice 6, question 2 */
void rafraichir_monde(int monde[SIZE_X][SIZE_Y]){

   /*A Completer*/
  int i ,j ;
  for(i = 0 ; i< SIZE_X; i++){
    for(j = 0 ; j <SIZE_Y ; j++){
      monde[i][j]++;
    }
  }

}

