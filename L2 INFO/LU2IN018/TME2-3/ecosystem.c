#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "ecosystem.h"

Animal *createAnimal(unsigned int x, unsigned int y, float stamina) {
  Animal *animal = (Animal *)malloc(sizeof(Animal));
  assert(animal);

  animal->x = x;
  animal->y = y;
  animal->stamina = stamina;
  animal->direction[0] = rand() % 3 - 1;
  animal->direction[1] = rand() % 3 - 1;
  animal->next = NULL;

  return animal;
}

Animal *pushAnimal(Animal *head, Animal *animal) {
  assert(animal);
  assert(!animal->next);

  animal->next = head;
  return animal;
}

unsigned int countAnimals(Animal *head) {
  unsigned int result = 0;
  for (; head; head = head->next, result++);
  return result;
  // Recursive method, but not tail-recursive it seems so probably terrible performance compared to the iterative one :(
  /* if (!head) */
  /*   return 0; */
  /* return 1 + countAnimals(head->next); */
}

void printCurrentState(Animal *predatorList, Animal *preyList, int grass[SIZE_X][SIZE_Y], FILE *fd) {
  char world[SIZE_X][SIZE_Y];

  for (int i = 0; i < SIZE_X; i++)
    for (int j = 0; j < SIZE_Y; j++)
      /* world[i][j] = grass[i][j] > 0 ? '#' : ' '; */
      world[i][j] = ' ';

  int numPrey = countAnimals(preyList);
  int numPredators = countAnimals(predatorList);

  printf("Number of prey (*):\t\t%d\n", numPrey);
  printf("Number of predators (O):\t%d\n", numPredators);
  if (fd)
    fprintf(fd, "%d %d", numPrey, numPredators);

  for (; predatorList; predatorList = predatorList->next) {
    world[predatorList->x][predatorList->y] = 'O';
  }
  for (; preyList; preyList = preyList->next)
    world[preyList->x][preyList->y] = world[preyList->x][preyList->y] == 'O' ? '@' : '*';

  printf("+");
  for (int i = 0; i < SIZE_Y; i++)
    printf("-");
  printf("+\n");
  for (int i = 0; i < SIZE_X; i++) {
    printf("|");
    for (int j = 0; j < SIZE_Y; j++)
      printf("%c", world[i][j]);
    printf("|\n");
  }
  printf("+");
  for (int i = 0; i < SIZE_Y; i++)
    printf("-");
  printf("+\n");
}

void addAnimal(unsigned int x, unsigned int y, Animal **head) {
  assert(x < SIZE_X && y < SIZE_Y);
  *head = pushAnimal(*head, createAnimal(x, y, 0));
}

void freeAnimalList(Animal *head) {
  Animal *temp;
  while (head) {
    temp = head;
    head = head->next;
    free(temp);
  }
}

void removeAnimal(Animal **head, Animal *animal) {
  Animal *current = *head;
  if (current == animal) {
    (*head) = (*head)->next;
    free(current);
    return;
  }
  for (; current; current = current->next)
    if (current->next == animal) {
      Animal *temp = current->next;
      current->next = current->next->next;
      free(temp);
      return;
    }
}

void moveAnimals(Animal *head) {
  for (; head; head = head->next) {
    head->x = modulo(head->x - head->direction[0], SIZE_X);
    head->y = modulo(head->y - head->direction[1], SIZE_Y);
    if (probability(probDirectionChange)) {
      head->direction[0] = rand() % 3 - 1;
      head->direction[1] = rand() % 3 - 1;
    }
  }
}

void breed(Animal **head, float probBreeding) {
  for (Animal *current = *head; current; current = current->next)
    if (probability(probBreeding)) {
      addAnimal(current->x, current->y, head);
      current->stamina /= 2.0;
      (*head)->stamina = current->stamina;
      assert((*head)->stamina != 0.0);
      assert(current->stamina != 0.0);
    }
}

void refreshWorld(int grass[SIZE_X][SIZE_Y]) {
  for (int i = 0; i < SIZE_X; i++)
    for (int j = 0; j < SIZE_Y; j++)
      grass[i][j]++;
}

void refreshPrey(Animal **preyHead, int grass[SIZE_X][SIZE_Y]) {
  moveAnimals(*preyHead);
  for (Animal *current = *preyHead; current; current = current->next) {
    current->stamina--;
    if (grass[current->x][current->y] >= 0) {
      current->stamina += preyEnergyGain;
      grass[current->x][current->y] = grassGrowingTime;
    }
  }
  for (Animal *current = *preyHead; current; current = current->next)
    if (current->stamina <= 0.0)
      removeAnimal(preyHead, current);
  breed(preyHead, probPreyBreeding);
}

Animal *preyAtCoords(Animal *preyHead, unsigned int x, unsigned int y) {
  for (; preyHead; preyHead = preyHead->next)
    if (preyHead->x == x && preyHead->y == y)
      return preyHead;
  return NULL;
}

void refreshPredators(Animal **predatorHead, Animal **preyHead) {
  moveAnimals(*predatorHead);
  for (Animal *current = *predatorHead; current; current = current->next) {
    current->stamina--;
    Animal *temp;
    if ((temp = preyAtCoords(*preyHead, current->x, current->y)) && probability(probFeast)) {
      removeAnimal(preyHead, temp);
      current->stamina += predatorEnergyGain;
    }
  }
  for (Animal *current = *predatorHead; current; current = current->next)
    if (current->stamina <= 0.0)
      removeAnimal(predatorHead, current);
  breed(predatorHead, probPredatorBreeding);
}
