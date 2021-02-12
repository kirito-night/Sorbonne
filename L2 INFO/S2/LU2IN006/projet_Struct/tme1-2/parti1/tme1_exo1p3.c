#include<stdio.h>
#include<stdlib.h>

typedef struct tableau{
	int* tab;
	int maxTaille;
	int position;
}Tableau;

void ajouterElement(int a,Tableau *t){
	t->tab[t->position]=a;
	t->position++;
}

Tableau* initTableau(int maxTaille){
	Tableau* t = (Tableau*)malloc(sizeof(Tableau));
	t->position=0;
	t->maxTaille=maxTaille;
	t->tab=(int*)malloc(sizeof(int)*maxTaille);
	return t;
}

void affichageTableau(Tableau* t){
	printf("t->position = %d\n",t->position);
	printf("[ ");	
	for (int i=0;i<(t->position);i++){
		printf("%d ",t->tab[i]);	
	}
	printf("]\n");
}

int main(){
	Tableau* t;
	t = initTableau(100);
	ajouterElement(5,t);
	ajouterElement(18,t);
	ajouterElement(99999,t);
	ajouterElement(-452,t);
	ajouterElement(4587,t);
	affichageTableau(t);	
	free(t);
}
/*1.6 
ce programme est cence de creer un tableau allouer dynamiquement , initialiser avenc la fonction initTableau()
afficher avec la fonction afficherTableau, les element sont ajouter par la fonction ajourElement()
la structure Tableau contient un tableau avec la taille maximal du tableau maxtaille et la position qui indique a quel point le tableau est remplit

en compilant et executant ce programme nous voyons l'affichage de :
t->position = 5
[ 5 18 99999 -452 4587 ]
ce qui est correcte

1.7 le probleme de ce programme est enfait une fuite de memoire.

1.8
en excutant le programme avec Valgrind nous remarquons une fuite de memoire de 400bytes
cette fuite correspond au tableau  t->tab (un int c'est 4 bytes, et ici la taille de t->tab est de 100, donc une fuite de memooire de 400 bytes)

1.9 pour resoudre ce probleme il faut justre faire : 
free (t->tab); avant free(t); et comme ca toute la memoire est correctement libere.