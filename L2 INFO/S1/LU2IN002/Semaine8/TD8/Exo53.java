/*exercice 53 
1)
ligne 8 declaration de la variable p1 type Point 
ligne 8 


ligne 12 : affichaged p1 = p2 : false
ligne 13 :Affichage p1 = p3 :true 
ligne 14 : affichage p1 = p4 : false 


*/
//2)

public boolean equals(Objet o){
    Point p = (Point) o ; 
    return this. x == p.x && this.y == p.y);
}

/*
3) 
a l'executijon on aura une erreur car on ne peut pas appliquer un cast sur un 
objet string pour obtenir un Point
il y aura une erreur java.lang.ClassCastExeption

