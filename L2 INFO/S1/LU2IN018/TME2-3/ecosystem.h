#include <stdio.h>
#define SIZE_X 20
#define SIZE_Y 50
#define NUM_PREY 20
#define NUM_PREDATORS 20

#define modulo(a, b) (((a) % (b) + (b)) % (b))
#define probability(p) ((float)rand()/(float)RAND_MAX < (p))

// Global parameters
extern const float probDirectionChange;
extern const int preyEnergyGain;
extern const int predatorEnergyGain;
extern const float probPreyBreeding;
extern const float probPredatorBreeding;
extern const float probFeast;
extern const int grassGrowingTime;

typedef struct animal {
  int x;
  int y;
  float stamina;
  int direction[2];
  struct animal *next;
} Animal;

Animal *createAnimal(unsigned int x, unsigned int y, float stamina);
Animal *pushAnimal(Animal *head, Animal *animal);
void addAnimal(unsigned int x, unsigned int y, Animal **head);
unsigned int countAnimals(Animal *head);
void freeAnimalList(Animal *head);
void removeAnimal(Animal **head, Animal *animal);

void printCurrentState(Animal *predatorList, Animal *preyList, int grass[SIZE_X][SIZE_Y], FILE *fd);

void moveAnimals(Animal *head);
void breed(Animal **head, float probBreeding);
void refreshPrey(Animal **preyHead, int world[SIZE_X][SIZE_Y]);
Animal *preyAtCoords(Animal *preyHead, unsigned int x, unsigned int y);
void refreshPredators(Animal **predatorHead, Animal **preyHead);
void refreshWorld(int grass[SIZE_X][SIZE_Y]);
