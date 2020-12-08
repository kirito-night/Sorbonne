#include <stdio.h>
#include <stdlib.h>
#include <sys/select.h>
#include <time.h>
#include "ecosystem.h"

/* #define NUM_PREY 20 */
/* #define NUM_PREDATORS 20 */

const float probDirectionChange = 1;
const int grassGrowingTime = -15;
const int predatorEnergyGain = 20;
const int preyEnergyGain = 6;
const float probFeast = 0.5;
const float probPreyBreeding = 0.4;
const float probPredatorBreeding = 0.5;

int main(void) {
  srand(time(NULL));

  int grass[SIZE_X][SIZE_Y] = {0};
  Animal *predatorList = NULL;
  Animal *last = createAnimal(0, 0, 0);
  predatorList = pushAnimal(predatorList, last);
  for (int i = 0; i < NUM_PREDATORS; i++)
    addAnimal(rand() % SIZE_X, rand() % SIZE_Y, &predatorList);
  Animal *preyList = NULL;
  for (int i = 0; i < NUM_PREY; i++)
    addAnimal(rand() % SIZE_X, rand() % SIZE_Y, &preyList);
  Animal *first = createAnimal(5, 5, 5);
  preyList = pushAnimal(preyList, first);

  printCurrentState(predatorList, preyList, grass, NULL);

  printf("Moving animals around\n");
  moveAnimals(preyList);
  moveAnimals(predatorList);
  printCurrentState(predatorList, preyList, grass, NULL);

  printf("Shrexy time\n");
  breed(&preyList, 1);
  breed(&predatorList, 1);
  printCurrentState(predatorList, preyList, grass, NULL);

  printf("Removing a predator at 0, 0\n");
  removeAnimal(&predatorList, last);
  printCurrentState(predatorList, preyList, grass, NULL);

  printf("Removing prey at 5, 5\n");
  removeAnimal(&preyList, first);
  printCurrentState(predatorList, preyList, grass, NULL);

  freeAnimalList(predatorList);
  freeAnimalList(preyList);
  
  return 0;
}
