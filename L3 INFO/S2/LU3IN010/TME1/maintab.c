#include "tab.h"
#include <time.h>
#define NMAX 1000000

void PrintMem()
{
    struct rusage usage;
    getrusage(0, &usage);
    printf("%ld \n",usage.ru_maxrss);
}

int main(int argc, char **argv)
{
    //time_t t;
    srand(time(NULL));
    printf("memoire residente avant malloc  : ");
    PrintMem();
    int *tab = malloc(sizeof(int) * NMAX);
    printf("memoire residente avant init tab : ");
    PrintMem();
    InitTab(tab, NMAX);
    //PrintTab(tab, NMAX);

    int sum = SumTab(tab, NMAX);
    printf("valuer de SumTab : %d \n", sum);

    int min = 10;
    printf("valeur min avant l'appel de MinSumTab : %d \n", min);
    MinSumTab(&min , tab, NMAX);
    printf("valeur min apres l'appel de MinSumTab : %d \n", min);
    printf("memoire residente apres init tab : ");
    PrintMem();
    free(tab);
    printf("memoire residente apres free : ");
    PrintMem();

    tab = malloc(sizeof(int) * NMAX);
    printf("memoire residente avant 2eme init tab avec free : ");
    PrintMem();
    InitTab(tab, NMAX);
    printf("memoire residente apres 2eme init tab avec free : ");
    PrintMem();
    return 0;
}