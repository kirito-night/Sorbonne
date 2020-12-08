#include<stdio.h>
#include<stdlib.h>

typedef struct coordonnee_
	{
		int x;
		int y;
		char *b
	}coordonnee;
	

void test(coordonnee *n)
{
	n->x = 3;
	n->y = 4;
}
int main()
{
	
	/*coordonnee ptn = {1,1};
	printf("%d\t%d\n",ptn.x,ptn.y);
	test(&ptn);
	printf("%d\t%d\n",ptn.x,ptn.y);*/

	int a[6]={1,2,3,4,5,6};
	int *ptr;
	ptr = a+1;
	printf("%d\n",*(ptr+1));
	return 0;
	
}
