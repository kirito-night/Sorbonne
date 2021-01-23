#include <stdlib.h>
#include <stdio.h>
#include "ecosys.h"



typedef struct _animal {
  int x;
  int y;
  int dir[2]; /* direction courante sous la forme (dx, dy) */
  float energie;
  struct _animal *suivant;
} Animal;


Animal *creer_animal(int x, int y, float energie)
{
	Animal *cell= malloc(sizeof(Animal));
	cell-> x = x;
	cell -> y = y;
	cell -> energie = energie;
	cell -> suivant = NULL;
	return cell;
}

int main ()
{
	Animal *chat = creer_animal(4,4,100.0);
	printf(" %d\t%d\t%f\n", chat->x,chat->y,chat->energie); 
	
	
	return 0;

}


