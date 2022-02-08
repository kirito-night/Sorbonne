#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int max_list(char **list)
{
    int i = 1;
    int res = atoi(list[1]);

    while (list[i] != NULL)
    {
        if (res < atoi(list[i]))
        {
            res = atoi(list[i]);
        }
        i++;
    }
    return res;
}



int main(int argc, char **argv)
{

    int max = max_list(argv);
    printf("%d \n", max);
    return 0;
}