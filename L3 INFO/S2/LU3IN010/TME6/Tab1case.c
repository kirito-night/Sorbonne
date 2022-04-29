/* Diffusion tampon 1 case */

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <libipc.h>

/************************************************************/

/* definition des parametres */

#define NE 2 /*  Nombre d'emetteurs         */
#define NR 5 /*  Nombre de recepteurs       */

/************************************************************/

/* definition des semaphores */

#define EMET 0
#define MUTEX 1
int RECEP[NR];

/************************************************************/

/* definition de la memoire partagee */

typedef struct
{
  int nb_recepteurs;
  char t;

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
int emetteur()
{
  while (1)
  {
    printf("Emetteur");
   

    P(EMET);
    sp->t = '_';
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
int recepteur(int i)
{
  while (1)
  {
    printf(" recepteur %d \n" , i);
    P(RECEP[i]);
    printf("%c \n", sp->t);

    P(MUTEX);
    sp->nb_recepteurs++;
    if (sp->nb_recepteurs == NR)
    {
      sp->nb_recepteurs = 0;
      V(EMET);
    }

    V(MUTEX);
  }
}

/************************************************************/

int main()
{
  struct sigaction action;
  /* autres variables (a completer) */

  setbuf(stdout, NULL);

  /* Creation du segment de memoire partagee */

  if ((sp = (t_segpart *)init_shm(sizeof(t_segpart))) == NULL)
  {
    perror("init_shm");
    exit(1);
  }
  sp->t = ' ';

  /* creation des semaphores */

  if (creer_sem(NR+2) == -1)
  {
    perror("creer_sem");
    exit(1);
  }

  /* initialisation des semaphores */

  init_un_sem(EMET, 1);
  init_un_sem(MUTEX, 1);
  for (int i = 0; i < NR; i++)
  { 
    RECEP[i] = i+2;
    init_un_sem(RECEP[i], 0);
  }

  /* creation des processus emetteurs */
  for (int i = 0; i < NE; i++)
  {
    emet_pid[i] = fork();
    if (emet_pid[i] == 0)
    {
      emetteur();
    }
  }

  /* creation des processus recepteurs */

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

  pause(); /* attente du Ctrl-C  */
  return EXIT_SUCCESS;
}
