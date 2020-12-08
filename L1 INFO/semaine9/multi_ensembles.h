typedef struct _element_t element_t;
struct _element_t{
  int valeur;
  int frequence;
  element_t *suivant;
};

element_t *Recherche_val(element_t *ensemble, int val);
element_t *Ajout_tete_ensemble(element_t *ensemble, int val, int freq);
void Affiche_ensemble(element_t *ensemble);
element_t * Creation_ensemble(int n);