package Exo54;

public abstract class Poisson implements Submarine {
    int x,y;
    public Poisson(int x ,int y){
        this.x = x;
        this .y = y;
    };
    /*public void seDeplacer(){
        thix.x +=(int) Math.random()*  15 - 15  
        thix.y +=(int) Math.random()*  15 - 15  
    }*/
    public abstract void seDepacer();
    
}
