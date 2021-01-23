#include<stdio.h>

void f (int n){
    printf("n = %d", n );

}
int main(){
    void (*pf)(int);
    pf = &f;
    pf(3);
    return 0;
}