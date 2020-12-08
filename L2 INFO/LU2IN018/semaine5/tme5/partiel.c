#include <stdlib.h>
typedef struct_article {unsigned intref;char*nom;unsigned intquantite;floatpu;/*prix unitaire*/} Article;


floatchiffre_daffaires (Article* articles,unsigned int n){
    

    int i,sum = 0; 

    for(i=0 ; i < n ; i++){
       sum +=  articles->pu;
    }
    return sum;
}

void liberer_articles_(Article * article , unsigned int n){
    int i;
    for(i = 0 ; i < n ; i ++){
        free(article[i].nom);
    }
    free(article);
}