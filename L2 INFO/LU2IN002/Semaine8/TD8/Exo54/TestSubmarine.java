package Exo54;

public class TestSubmarine {
    public static void main(String[] args) {
        Mer m = new Mer();
        m.ajoutElem(new Merlu();
        m.ajoutElem((new Dauphin()));
        m.ajoutElem(new Sousmarin());
        m.deplacer();


    }
/*
9) Non, on ne peut pas ajouter un chat dans Mer, car Char n'implemente pas Submarine

10)
pour definir la voubelle espece de chat on autra une definition proche de celle ci - apres

public class ChatSub extends Chat implements Submarine{
    public void seDeplacer(){
        sysout( "le chat nage")
    }
}


pas besoin de modifier la classe Mer 
on pourra ajouter un chat de type ChatSub en utilisant la methode ajouter,de la class Mer
}
}

