#include "tab.h"

void InitTab(int *tab, int size)
{
    for (int i = 0; i < size; i++)
    {
        tab[i] = rand() % 10;
    }
}

void PrintTab(int *tab, int size)
{
    for (int i = 0; i < size; i++)
    {
        printf(" %d \t", tab[i]);
    }
    printf("\n");
}

int SumTab(int *tab, int size)
{
    int sum = 0;
    for (int i = 0; i < size; i++)
    {
        sum += tab[i];
    }
    return sum;
}


int MinSumTab(int *min , int *tab , int size){
    int tmp = tab[0];
    for(int i = 0 ; i < size ; i++){
        if (tmp> tab[i]){

            tmp = tab[i];
        }
    }
    *min = tmp;

    return SumTab(tab, size);
}