#include <stdio.h>
#define NB_PLANETES 8


typedef struct planete
{
	char nom[10];
	float density;
	float sunkm;
	int satellite;
}planete;

void affichePlanete( planete k)
{
	printf("%s\n",k.nom);
	printf("%.2f\n",k.density);
	printf("%.2f\n",k.sunkm);
	printf("%d\n",k.satellite);
}

void afficherToutesPlanetes(planete k[NB_PLANETES])
{
	int i;
	for(i=0; i<NB_PLANETES;i++)
	{
		affichePlanete(k[i]);
		printf("\n\n");
	}
}

void densite(planete *k, float dmodif)
{
	k->density = dmodif;
}


int main(){
    planete systemeSolaire[NB_PLANETES] ={{"Mercure", 5.42, 58, 0},{"Venus", 5.25, 108.2, 0},{"Terre", 5.52,149.6,1},{"Mars",3.94,227.9,2},{"Jupiter",1.314,778.3,16},{"Saturne",0.69,1427,17},{"Uranus",1.19,2869,15},{"Neptune",1.6,4496,2}};
    densite(&(systemeSolaire[0]),6.66);
    afficherToutesPlanetes(systemeSolaire);
    
            
    return 0;
}
