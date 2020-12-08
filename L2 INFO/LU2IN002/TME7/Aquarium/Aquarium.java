public class Aquarium {
    protected PoissonList pl;
    public Aquarium(int nbThons, int nbRequins){
        pl = new PoissonList();
        for(int i = 0 ; i < nbThons; i++){

            pl.list.add(new Thon());
        }
        for(int i = 0 ; i < nbRequins; i++){
            pl.list.add(new Requin());
        }
    }

    @Override
    public String toString() {
        String s ="";
        for( Poisson p: pl.list){
            s = s + p.toString() + "    ";
        }
        return s;
         
    }
    

    
}
