#include<stdlib.h>
#include<stdio.h>


int est_deb(char *s1 , char *s2)
{
	if(*s1 == '\0')
		{return 1;}
	if(*s1!=*s2)
		{return 0;}
	
	return est_deb(s1 +1 , s2+1);
}


int est_incluse ( char *s1 , char *s2)
{
	if( est_deb(s1, s2))
	{
		return 1;
	}
	if (*s1 == '\0' && *s2 == '\0')
	{ 
		return 0;
	
	}

	return est_incluse(s1, s2);
}



int main()
{
	char *s1 = "alpha";
	char *s2 = "alphago";
	if(est_deb(s1,s2))
		printf("c'est un prefixe");
	else
		printf("ce ne l'ai pas");
	printf("\n");
	return 0;
}

	

