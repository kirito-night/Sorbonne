#include "entreeSortieH.h"
#include "biblioH.h"
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
    
    printf("entrer 7 pour fusionner le bibliotheque present avec un autre bibliotheque qui dans un fichier   \n");
    putchar('\n');
}

int main(int argc, char* argv[]){
    if(argc != 3){
        printf("veuilez entrer le nom du fichier a lire et le nb de ligne a lire\n");
        return 0;
    }
    char *nomfic = argv[1];
    int nbligne_alire = atoi(argv[2]);

    BiblioH *b  = charger_n_entrees_h(nomfic,nbligne_alire);

    int rep;
    do{

        menu();
        printf("entrer votre choix : ");
        if(scanf("%d", &rep)!= 1){
            printf("erreur formatage \n");
            exit(1);
        }
        switch (rep)
        {
        case 1:
            /* code */
            putchar('\n');
            printf("##########\n");
            putchar('\n');
            afficher_biblio(b);

            
            break;
        case 2:
            putchar('\n');
            printf("##########\n");
            putchar('\n');
            int num;
            char titre[100], auteur[100];
            printf(" veuillez entre le num , le titre et l'auteur de l'ouvrage : ");


            if(scanf( "%d %s %s",&num ,titre, auteur) == 3){
                inserer(b,num,titre,auteur);
                printf("ajout fait");
            }
            else{
                printf("erreur formatage");
            }

            break;
        case 3: 
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
                afficher_livre(recherche_ouvrage_num(b, ouvrage_rechercher));
                break;
            case 2: ;
                /* code */
                printf("entre le titre du livre que vous  rechercher :");

                char titre_rechercher[50];
                scanf("%s",titre_rechercher);
                afficher_livre(recherche_ouvrage_titre(b,titre_rechercher));

                break ;
            case 3: ;

                printf("entre l'auteur du livre que vous  rechercher :");
                char auteur_rechercher[50];
                BiblioH *b_auteur = NULL;
                scanf("%s", auteur_rechercher);
                b_auteur = recherche_ouvrage_auteur(b,auteur_rechercher);
                afficher_biblio(b_auteur);

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
            enregistrer_biblio_h(b,nom_enregistrer);
            break;
        

        case 6:
            printf("entrez le nb de ligne que vous voulez lire \n");
            scanf("%d",&nbligne_alire);
            liberer_biblio(b);
            
            b=charger_n_entrees_h(nomfic,nbligne_alire);


            break;
        case 7 : 
            printf("entrez le nom du fichier qui contient le bibliotheque a fusionner et le nb de ligne que vous voulez lire :  ");
            char nom_fichier_f[50];
            int nbfusion ;
            scanf("%s %d", nom_fichier_f , &nbfusion);

            BiblioH *bf = charger_n_entrees_h(nom_fichier_f,nbfusion);

            b = fusion_biblio(b,bf);

            break;

        default:
            printf("saisie invalide\n");
            /*printf("entrer 0 pour etre sur de sortir du programme\n entrer un nb quelconque pour rester dans le programme");
            int def;
            scanf("%d",&def);
            if(def == 0){
                printf("vous etes sorti de la boucle\n");
                exit(1);
                
            }
            */
            
            break;
        }




    }while(rep!=0);
    liberer_biblio(b);
    printf("c'est le main table hachage\n");
    printf("vous etes sorti de la boucle\n");








    return 0;

}
