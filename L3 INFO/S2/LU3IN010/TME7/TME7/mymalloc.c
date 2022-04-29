#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <string.h>
#include "affiche_tas.h"

int first_fit(int taille, int *pred)
{
    int i = libre;
    while ((tas[i + 1] != -1) || (tas[i] < taille))
    {
        *pred = i;
        i = tas[i] + i + 1;
    }
    libre = i;
    return i;
}

char *tas_malloc(unsigned int taille)
{
    int pred = 0;
    int i = first_fit(taille, &pred);
    if (tas[i + 1] == -1)
    {
        if (libre + taille + 1 < TAILTAS)
        {
            tas[libre + taille + 1] = tas[i] - taille - 1;
            tas[libre + taille + 2] = -1;
            libre = libre + taille + 1;
        }
    }
    tas[i] = taille;
    return &tas[i + 1];
}

int tas_free(char *ptr)
{
    libre = ptr - tas - 1;
    tas[libre + 1] = -1;
    return 0;
}

int main(int argc, char **argv)
{
    tas_init();
    char *p1, *p2, *p3, *p4 , *p5;
    p1 = (char *)tas_malloc(10);
    p2 = (char *)tas_malloc(9);
    p3 = (char *)tas_malloc(5);
    strcpy(p1, "tp 1");
    strcpy(p2, "tp 2");
    strcpy(p3, "tp 3");
    tas_free(p1);
    p4 = (char *)tas_malloc(10);
    p5 = (char *) tas_malloc(3);

    strcpy(p4, "je suis");
    strcpy(p5 , "fi");
    afficher_tas();
}
