public class Thon extends Poisson{

    public Thon() {
        super();
    }
    public void move(Point cible){
        if(super.position.distanceTo(cible)> 60){
            super.position.x += (int)Math.random()*30 -15+1;
            super.position.y += (int)Math.random()*30 -15+1;
        }
        else{
            super.position.x += cible.x /2  ; 
            super.position.y += cible.y /2 ; 
        }
        verifPosition();
    }

    @Override
    public String toString() {
        return "Thon : " + super.position.toString();
    }
    

}
