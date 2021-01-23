#include <stdio.h>
#include <stdlib.h>
/* #include <signal.h> */
#include <assert.h>
#include <time.h>
#include <unistd.h>
/* #include <strings.h> */
#include "ecosystem.h"

#define WAIT_TIME 50000
#define NUM_ITERATIONS 500

// Global parameters, defined
const float probDirectionChange = 0.01;
const int preyEnergyGain = 6;
const int predatorEnergyGain = 20;
const float probPreyBreeding = 0.4;
const float probPredatorBreeding = 0.5;
const float probFeast = 1;
const int grassGrowingTime = -15;

int main(int argc, const char *argv[]) {
  if (argc != 2) {
    fprintf(stderr, "Usage: %s <log file>\n", argv[0]);
    return 1;
  }

  FILE *filename = fopen(argv[1], "w");

  srand(time(NULL));
  int grass[SIZE_X][SIZE_Y] = {0};
  Animal *preyList = NULL;
  for (int i = 0; i < NUM_PREY; i++) {
    addAnimal(rand() % SIZE_X, rand() % SIZE_Y, &preyList);
    preyList->stamina = preyEnergyGain;
  }
  Animal *predatorList = NULL;
  for (int i = 0; i < NUM_PREDATORS; i++) {
    addAnimal(rand() % SIZE_X, rand() % SIZE_Y, &predatorList);
    predatorList->stamina = predatorEnergyGain;
  }

  printf("Initial state, starting simulation in 3 seconds\n");
  printCurrentState(predatorList, preyList, grass, NULL);
  assert(!sleep(3));
  for (int i = 0; i < NUM_ITERATIONS; i++) {
    printf("\nIteration %d\n", i);
    fprintf(filename, "%d ", i);
    refreshWorld(grass);
    refreshPrey(&preyList, grass);
    refreshPredators(&predatorList, &preyList);
    printCurrentState(predatorList, preyList, grass, filename);
    fprintf(filename, "\n");
    if (predatorList == NULL) {
      if (preyList == NULL)
        printf("Everyone dies(tm)\n");
      else
        printf("Well this isn't fun anymore\n");
      break;
    }
    assert(!usleep(WAIT_TIME));
  }

  fclose(filename);
  return 0;
}
