#include <stdio.h>
#include<stdlib.h>


int f(int m, int *n) {
  *n = m+1;
  return m;
}

int boucle(int a, int b) {
  int sum = 0;
  int i = 1;

  do {
    sum = sum + i;
    i++;    
  } while ((sum < a) || (i < b));
  return sum;
}
int main() {
 	printf("%d\n",boucle(4,5));
 	return 0;
}

