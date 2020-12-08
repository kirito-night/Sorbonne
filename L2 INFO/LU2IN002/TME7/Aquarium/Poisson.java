public abstract class Poisson {
    protected Point position;

    protected  Poisson(){
        this.position = new Point();
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
    
    public abstract void move (Point cible);
    public void verifPosition(){
        if(position.x > 499 ){
            position.x = position.x % 500;

        }else if(position.x < 0 ){
            position.x = (-(-position.x % 500) + 500);
        }
        if( position.y>499 ){
            position.y = position.y % 500;
        }else if(position.y<0){
            position.y = (-(-position.y % 500) + 500);
        }
    }

}
