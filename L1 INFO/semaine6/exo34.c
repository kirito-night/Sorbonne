#include<stdio.h>
#include<stdlib.h>
#include<time.h>

#define N 3



void init(char plateau[N][N])
{
	int i,j;
	for(i=0; i<N;i++)
	{
		for(j=0; j<N; j++)
		{
			plateau[i][j] ='_';
		}
	}
}

void afficher(char plateau[N][N])
{
		int i,j;
	for(i=0; i<N;i++)
	{
		for(j=0; j<N; j++)
		{
			printf("%c\t", plateau[i][j]);
		}
		printf("\n");
	}
}


int choisir_menu()
{
	printf("1. partie a deux joueurs\n\n" );
	printf("2. partie contre l'ordinateur\n\n");
	printf("3. quitter\n\n");
	int choix;
	scanf("%d", &choix);
	printf("\n");
	return choix;
	
}



void jouer(char plateau[N][N], int joueurs)
{

	int ligne,colonne; 
	printf("-choisir la ligne que tu veux deposer le pion (entre 1 et %d)\n\n",N);
	scanf("%d",&ligne);
	printf("-choisir le colonne que tu veux deposer le pion(entre 1 et %d) \n\n", N);
	scanf("%d", &colonne);
	printf("\n");
	
	ligne -= 1;
	colonne -=1;
	if(plateau[ligne][colonne] == '_' && ligne < N && colonne < N)
	{
		if( joueurs == 0)
		{
			plateau[ligne][colonne] = 'O';
		}
		if(joueurs == 1)
		{
			plateau[ligne][colonne] = 'X';
		}
	}
	else
	{
		printf("refaites votre choix\n");
		jouer(plateau, joueurs);
	}
}


void jouer_ordi(char plateau[N][N])
{
	int ligne,colonne;
	ligne = rand()%3;
	colonne = rand()%3;
	if(plateau[ligne][colonne] == '_')
	{
		plateau[ligne][colonne] = 'X';
	}
	else
	{
		jouer_ordi(plateau);
	}
}

int plateau_nonvide(char plateau[N][N])
{
	int i,j;
	for(i=0; i<N; i++)
	{
		for(j=0; j<N; j++)
		{
			if(plateau[i][j]=='_')
			{
				return 1;
			}
		}
	}
	return 0;
}

int partie_gagnee(char plateau[N][N])
{


	
	int ligne = 0,colonne = 0;
	
	while (ligne<N) 
	{
		if (plateau[ligne][colonne] =='_' )
		{
			ligne++;
		}
		if(plateau[ligne][colonne] == 'X')
		{
			int j, nx1=0;
			for(j=0;j<N;j++)
			{
			
				if (plateau[ligne][j] == 'X')
				{
	
					nx1++;
				}
				
			}
			if(nx1 == N)
			{
				printf("\nX a gagne\n");
				return 0;
			}
			else
			{
				ligne++;
			}
		}
			
		
		if (plateau[ligne][colonne] =='O')
		{
			int j, n1=0;
			for(j=0;j<N;j++)
			{
			
				if (plateau[ligne][j] == 'O')
				{
	
					n1++;
				}
				
			}
			if(n1 == N)
			{
				printf("\nO a gagne\n");
				return 0;
			}
			else
			{
				ligne++;
			}
		}
	}
	
	ligne=0;
	colonne=0;
	while (colonne<N) 
	{
		if (plateau[ligne][colonne] =='_' )
		{
			colonne++;
		}
		
		
		if(plateau[ligne][colonne] == 'X')
		{
			int i , nx2 = 0;
			for(i=0;i<N;i++)
			{
				
				if (plateau[i][colonne] == 'X')
				{
	
					nx2++;
				}
				
			}
			if(nx2 == N)
			{
				printf("\nX a gagne\n");
				return 0;
			}
			else
			{
				colonne++;
			}
		}
			
		
		if (plateau[ligne][colonne] =='O')
		{
			int i = 0 , n2 = 0;
			while(i<N)
			{
				
				if (plateau[i][colonne] == 'O')
				{
	
					n2++;
				}
				i++;
			}
			if(n2 == N)
			{
				printf("\nO a gagne\n");
				return 0;
			}
			else
			{
				colonne++;
			}
		}
	}		
	ligne=0;
	colonne=0;
	if (plateau[ligne][colonne] =='O')
	{
		int n3=0, i = 0;
		while(i<N)
		{
			
			if (plateau[ligne][colonne] == 'O')
			{
				n3++;
				ligne++;
				colonne++;
			}
			i++;
		}
		if(n3==N)
		{
			printf("\nO a gagne\n");
			return 0;
		}
	}
	
	
	
	ligne=0;
	colonne=0;
	if (plateau[ligne][colonne] =='X')
	{
		int n3=0, i = 0;
		while(i<N)
		{
			
			if (plateau[ligne][colonne] == 'X')
			{
				n3++;
				ligne++;
				colonne++;
			}
			i++;
		}
		if(n3==N)
		{
			printf("\nX a gagne\n");
			return 0;
		}
	}
	
	ligne=2;
	colonne=0;
	if (plateau[ligne][colonne] =='O')
	{
		int n4=0, i = 0;
		while(i<N)
		{
			
			if (plateau[ligne][colonne] == 'O')
			{
				n4++;
				ligne--;
				colonne++;
			}
			i++;
		}
		if(n4==N)
		{
			printf("\nO a gagne\n");
			return 0;
		}
	}
	
	
	ligne=2;
	colonne=0;
	if (plateau[ligne][colonne] =='X')
	{
		int n4=0, i = 0;
		while(i<N)
		{
			
			if (plateau[ligne][colonne] == 'X')
			{
				n4++;
				ligne--;
				colonne++;
			}
			i++;
		}
		if(n4==N)
		{
			printf("\nX a gagne\n");
			return 0;
		}
	}
	
	int i,j;
	for(i=0; i<N; i++)
	{
		for(j=0; j<N; j++)
		{
			if(plateau[i][j]=='_')
			{
				
				return 1;
			}
		}
	}
	printf("\negalite\n");
	return 0;
		
}

void jouer_a(char plateau[N][N], int nb_joueurs)
{
	//nb_joueurs <= 2 && nb_joueurs !=0
	if(nb_joueurs == 1)
	{
		while(partie_gagnee(plateau) == 1)
		{	
		
			
			jouer(plateau,0);
			afficher(plateau);
			
			printf("\n\n");
			if(partie_gagnee(plateau))
			{
				jouer_ordi(plateau);
				afficher(plateau);
			}
		}
	}
	if(nb_joueurs == 2)
	{
		int n = 0, m = 1, tmp;
		while(partie_gagnee(plateau) == 1)
		{	
			
			jouer(plateau,n);
			
			
			afficher(plateau);
			tmp = n;
			n = m;
			m = tmp;
			
			printf("\n\n");
			
		}
	}
}
	
		
		

int main()
{
	srand(time(NULL));
	char plateau[N][N];
	init(plateau);
	int n = choisir_menu();
	
	if(n == 1)
	{
		jouer_a(plateau,2);
	}
	if(n == 2)
	{
		jouer_a(plateau,1);
	
	}
	if(n==3)
	{
		return 0;
	}
	return 0;
	
	
	
}
