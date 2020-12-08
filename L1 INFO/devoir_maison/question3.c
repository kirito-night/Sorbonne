#include<stdlib.h>
#include<stdio.h>

int points(int e1, int e2)
{
	int ptn, dif;
	dif = e1-e2;
	if(dif >= 4)
	{
		ptn = 0;
	}
	else
	{
		if(dif==3)
		{
			ptn =15;
		}
		else
		{
			if(dif==2)
			{
				ptn = 20;
			}
			else
			{
				if(dif == 1)
				{
					ptn = 30;
				}
				else
				{
					if(dif == 0)
					{
						ptn = 60;
					}
					else
					{
						if(dif == -1)
						{
							ptn = 90;
						}
						
						if(dif<=-2)
						{
							ptn = 120;
						}
					}
				}
			}
		}
	}
	printf("%d\n", ptn);
	return ptn;
}

int main()
{
	int e1 = 3;
	int e2 = 9;
	points(e1,e2);
	points(e2,e1);
	points(1,1);
	points(1,3);
	points(1,2);
	return 0;
}
	
