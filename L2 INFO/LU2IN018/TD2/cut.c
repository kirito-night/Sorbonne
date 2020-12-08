void afficher_ecosys(Animal *liste_proie, Animal *liste_predateur) {
  unsigned int i, j;
  char ecosys[SIZE_X][SIZE_Y];
  int nbpred=0,nbproie=0;
  Animal *pa=NULL;

  /* on initialise le tableau */
    /*A Completer*/
  for(i=0;i<SIZE_X; i++){
  	for(j =0; j< SIZE_Y ; j++)
  	{	
  		ecosys[i][j]= ' ';
  	}
  }

  /* on ajoute les proies */
    /*A Completer*/
  pa = liste_proie; 
  while(pa){
  	nbproie++;
  	ecosys[pa->x][pa->y] = '*';
  	pa = pa->suivant;
  }
  /* on ajoute les predateurs */
  /*A Completer*/
  pa = liste_predateur;
  while(pa){
  	nbpred++;
  	if((ecosys[pa->x][pa->y] == '@') || (ecosys[pa->x][pa->y] = '*')){
  		ecosys[pa->x][pa->y] = '@';
  	}
  	else{
  		ecosys[pa->x][pa->y] = 'O';
  	}
  	pa = pa->suivant;
  	
  }

  /* on affiche le tableau */
  /*A Completer*/
  for(i=0;i<SIZE_X;i++){
  	for(j=0;j<SIZE_Y;j++){
  		printf("%c\t",ecosys[i][j]);
  	}
  	printf("\n");
  }
  

}
