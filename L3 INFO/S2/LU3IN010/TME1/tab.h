#ifndef TAB_H
#define TAB_H
#include <stdio.h>
#include <stdlib.h>
#include <math.h>


void InitTab(int *tab, int size);
void PrintTab(int *tab, int size);
int SumTab(int *tab, int size);
int MinSumTab(int *min , int *tab , int size);
#endif