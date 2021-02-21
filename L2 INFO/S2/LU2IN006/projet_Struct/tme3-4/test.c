#include <stdio.h>
int main(int argc, char* argv[]){
    char buffer[256];
    buffer[255]='\0';
    int num;
    char titre[100], auteur[100];
    printf(" veuillez entre le num , le titre et l'auteur de l'ouvrage : ");
    fgets(buffer, 256, stdin);
    sscanf(buffer, "%d %s %s",&num ,titre, auteur);
    printf("%d %s %s \n",num, titre, auteur);
}