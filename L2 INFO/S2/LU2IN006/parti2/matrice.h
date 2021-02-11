#ifndef MATRICE_H
#define MATRICE_H

#include <stdio.h>
#include<stdlib.h>
#include<time.h>

int **alloue_matrice(int ** T, int n);
void alloue_matrice_2(int *** T, int n);
void desalloue_matrice(int **T, int n);
void remplir_matrice(int **T, int n, int V);
void afficher_matrice(int **T, int n);
int all_diff_1(int **Mat, int n);
int all_diff_2(int **Mat, int n , int V);
int **produit_matrice(int **mat1, int ** mat2 , int n);

#endif