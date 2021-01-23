public class Requin extends Poisson {

    public Requin() {
        super();
    }

    @Override
    public String toString() {
        return "Requin : " + super.position.toString();
    }

    public void move (Point cible){
        super.position.x += cible.x /2  ; 
        super.position.y += cible.y /2 ; 
        super.verifPosition();

    }

    
    
}
