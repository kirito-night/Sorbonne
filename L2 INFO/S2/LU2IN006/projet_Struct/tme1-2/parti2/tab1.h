#ifndef TAB_H
#define TAB_H

#include <stdio.h>
#include<stdlib.h>
#include<time.h>

void alloue_tableau(int ** T, int n);
void desalloue_tableau(int *T);
void remplir_tableau(int **T, int n, int V);
void afficher_tableau(int *T, int n);
int sum_carre_1(int *tab, int n);
int sum_carre_2(int *tab, int n);

#endif