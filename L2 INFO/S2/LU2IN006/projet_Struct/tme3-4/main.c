#include "entreeSortieLC.h"
#include "biblioLC.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
void menu(){
    putchar('\n');
    printf("##########\n");
    putchar('\n');
    printf("entrer 0 pour sortir du programme \n");

    printf("entrer 1 pour afficher la bibliotheque \n");
    printf("entrer 2 pour inserer un ouvrage a la tete de la bibliotheque  \n");
    printf("entrer 3 pour supprimer un ouvrage avec son num son titre et son auteur\n");
    printf("entrer 4 pour rechercher un ouvrage dans la bibliotheque \n");
    printf("entrer 5 pour enregistrer la bibliotheque dans un nouveau fichier \n");
    printf("entrer 6 pour renouveller la bibliotheque avec un nouveau nombre de ligne (les element dont vous avez inserer ou supprimer va etre supprimer) \n");
    putchar('\n');


}
int main(int argc, char* argv[]){
    if(argc != 3){
        printf("veuilez entrer le nom du fichier a lire et le nb de ligne a lire\n");
        return 0;
    }
    char *nomfic = argv[1];
    int nbligne_alire = atoi(argv[2]);

    Biblio *b  = charger_n_entrees(nomfic,nbligne_alire);
   
    int rep;

    do{
        menu();
        printf("entrer votre choix : ");
        scanf("%d", &rep);
        
        switch (rep){
        case 1:
            /* code */
            putchar('\n');
            printf("##########\n");
            putchar('\n');
            afficherBiblio(b);

            break;
        case 2: 
            putchar('\n');
            printf("##########\n");
            putchar('\n');
            //char buffer[256];
            //buffer[255]='\0';
            int num;
            char titre[100], auteur[100];
            printf(" veuillez entre le num , le titre et l'auteur de l'ouvrage : ");
            //fgets(buffer, 256, stdin);
            //sscanf(buffer, "%d %s %s",&num ,titre, auteur);
            
            
            if(scanf( "%d %s %s",&num ,titre, auteur) == 3){
                inserer_en_tete(b,num,titre,auteur);
                 printf("ajout fait");
            }
            else{
                printf("erreur formatage");
            }
            

            break;
        
       

        case 3 :
            //int num;
            //char titre[100], auteur[100];
            printf("veuillez entre le num , le titre et l'auteur de l'ouvrage : ");
            if(scanf( "%d %s %s",&num ,titre, auteur) == 3){
                //printf("%d %s %s\n", num, titre, auteur);
                suppression_ouvrage(b,num,titre,auteur);
            }
            else{
                printf("erreur formatage");
            }
           
            
            break;
        case 4:
            /* code */
            putchar('\n');
            printf("##########\n");
            putchar('\n');
            printf("entrez 1 pour chercher par numero \nentrez 2 pour chercher par titre \nentrez 3 pour chercher par auteur\nentre votre choix :");
            int recherche;
            scanf("%d",&recherche);
            switch (recherche)
            {
            case 1: ;
                /* code */
                printf("entrez le numero du livre que vous  rechercher :");
                int ouvrage_rechercher;
                scanf("%d",&ouvrage_rechercher);
                afficherLivre(recherche_ouvrage_num(b, ouvrage_rechercher));
                break;
            case 2: ;
                /* code */
                printf("entre le titre du livre que vous  rechercher :");

                char titre_rechercher[50];
                scanf("%s",titre_rechercher);
                afficherLivre(recherche_ouvrage_titre(b,titre_rechercher));

                break ;
            case 3: ;

                printf("entre l'auteur du livre que vous  rechercher :");
                char auteur_rechercher[50];
                Biblio *b_auteur = NULL;
                scanf("%s", auteur_rechercher);
                b_auteur = recherche_ouvrage_auteur(b,auteur_rechercher);
                afficherBiblio(b_auteur);

                break;
            default:
                printf("saisie invalide, retapper 4 pour refaire votre rechereche :");
                break;
            }


            break;
            
        case 5: 
            printf("entrez le nom du fichier que vous voulez enregister le bibliotheque \n");
            char nom_enregistrer[50];
            scanf("%s", nom_enregistrer);
            enregistrer_biblio(b,nom_enregistrer);
            break;
        case 6:
            printf("entrez le nb de ligne que vous voulez lire \n");
            scanf("%d",&nbligne_alire);
            b=charger_n_entrees(nomfic,nbligne_alire);


            break;
        default:
            printf("saisie invalide\n");
            break;
        }
    }while (rep!=0);
    printf("vous etes sorti de la boucle\n");
    liberer_biblio(b);
    return 0;
}