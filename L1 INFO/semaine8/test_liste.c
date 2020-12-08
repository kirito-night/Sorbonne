#include "liste_entiers.h"
#include<stdio.h>

int main() {
  cellule_t *ma_liste=creerListe(5);
  //cellule_t *it_liste = creerListe(5);
  
  AfficherListeInt(ma_liste);
 // AfficherListeInt(it_liste);
 printf("%d\n", nb_occurences(3, ma_liste));
 
 printf("%d\n",tous_plus_grand(3, ma_liste));
 printf("%p\n",Max(ma_liste));
 printf("%d\n",renvoyer_val_element_pos(3,ma_liste));
 
 //AfficherListeInt(Concatener_it(ma_liste,it_liste));
 printf("%d\n",nb_maximum(ma_liste));
  return 0;
}
