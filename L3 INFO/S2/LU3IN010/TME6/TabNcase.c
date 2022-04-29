/* Diffusion tampon N case */

#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <libipc.h>

/************************************************************/

/* definition des parametres */

#define NE 2   /*  Nombre d'emetteurs         */
#define NR 5   /*  Nombre de recepteurs       */
#define NMAX 3 /*  Taille du tampon           */

/************************************************************/

/* definition des semaphores */

#define EMET 0
#define MUTEXP 1
#define MUTEXC 2
int CASES[NMAX];
int RECEP[NR];

/************************************************************/

/* definition de la memoire partagee */

typedef struct
{
        int id;
        int nb_recepteurs[NMAX];
        int t[NMAX];

} t_segpart;

t_segpart *sp;

/************************************************************/

/* variables globales */
int emet_pid[NE], recep_pid[NR];

/************************************************************/

/* traitement de Ctrl-C */

void handle_sigint(int sig)
{
        int i;
        for (i = 0; i < NE; i++)
                kill(emet_pid[i], SIGKILL);
        for (i = 0; i < NR; i++)
                kill(recep_pid[i], SIGKILL);
        det_sem();
        det_shm((char *)sp);
}

/************************************************************/

/* fonction EMETTEUR */

// A completer - contient les instructions executees
// par un emetteur

int emetteur(int j)
{
        while (1)
        {
                printf("Emetteur");

                P(EMET);

                P(MUTEXP);
                int tmp = sp->id;
                sp->id = (sp->id+1) % NMAX;

                P(CASES[tmp]);

                V(MUTEXP);

                sp->t[tmp] = j;
                V(CASES[tmp]);
                for (int i = 0; i < NR; i++)
                {
                        V(RECEP[i]);
                }
        }
        return 0;
}

/************************************************************/

/* fonction RECEPTEUR */

// A completer - contient les instructions executees
// par un recepteur

int recepteur(int j)
{
        int i = 0;
        while (1)
        {
                printf(" recepteur %d \n", j);
                P(RECEP[j]);
                P(CASES[i]);
                printf("%d \n", sp->t[i]);
                V(CASES[i]);
                P(MUTEXC);

                sp->nb_recepteurs[i]++;
                if (sp->nb_recepteurs[i] == NR)
                {
                        sp->nb_recepteurs[i] = 0;
                        V(EMET);
                }
                i = (i + 1) % NMAX;

                V(MUTEXC);
        }
}
/************************************************************/

int main()
{
        struct sigaction action;
        /* autres variables (a completer) */

        setbuf(stdout, NULL);

        /* Creation du segment de memoire partagee */

        // A completer
        if ((sp = (t_segpart *)init_shm(sizeof(t_segpart))) == NULL)
        {
                perror("init_shm");
                exit(1);
        }
        
        sp->id = 0;
        for (int i = 0; i < NMAX; i++)
        {
                sp->t[i] = ' ';
                sp->nb_recepteurs[i] = 0;
        }

        /* creation des semaphores */

        // A completer
        if (creer_sem(NMAX + NR + 3) == -1)
        {
                perror("creer_sem");
                exit(1);
        }

        /* initialisation des semaphores */

        // A completer
        init_un_sem(EMET, NMAX);
        init_un_sem(MUTEXP, 1);
        init_un_sem(MUTEXC, 1);
        for (int i = 0; i < NMAX; i++)
        {
                CASES[i] = i + 3;
                init_un_sem(CASES[i], 1);
        }
        for (int i = 0; i < NR; i++)
        {
                RECEP[i] = i + 3 + NMAX;
                init_un_sem(RECEP[i], 0);
        }

        /* creation des processus emetteurs */

        // A completer - les pid des processus crees doivent
        // etre stockes dans le tableau emet_pid
        for (int i = 0; i < NE; i++)
        {
                emet_pid[i] = fork();
                if (emet_pid[i] == 0)
                {
                        emetteur(i);
                }
        }

        /* creation des processus recepteurs */

        // A completer - les pid des processus crees doivent
        // etre stockes dans le tableau recep_pid
        for (int i = 0; i < NR; i++)
        {
                recep_pid[i] = fork();
                if (recep_pid[i] == 0)
                {
                        recepteur(i);
                }
        }

        /* redefinition du traitement de Ctrl-C pour arreter le programme */

        sigemptyset(&action.sa_mask);
        action.sa_flags = 0;
        action.sa_handler = handle_sigint;
        sigaction(SIGINT, &action, 0);

        pause(); /* attente du Ctrl-C */
        return EXIT_SUCCESS;
}
