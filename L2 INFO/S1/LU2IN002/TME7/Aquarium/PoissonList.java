import java.util.ArrayList;
public class PoissonList {
    ArrayList<Poisson> list;

    public PoissonList(){
        list = new ArrayList<Poisson>();
    }
    public PoissonList(PoissonList p1){
        this.list = p1.list;
    }
    public int nbThons(){
        int cpt =0;
        for(Poisson p : list){
            if(p instanceof Thon){
                cpt++;

            }
        }
        return cpt;
    }

    public int rangPoissonProche(int index){
        int min = (int)list.get(index).position.distanceTo(list.get(0).position);
        int rtn = 0;
        for(int i = 0 ; i < list.size(); i++){
            if(min > list.get(index).position.distanceTo(list.get(i).position)){
                rtn = i;
            }
        }
        return rtn;  
    }
    public void bougeTousPoisson(){
        Point centre = new Point(250,250);

        for(int i = 0 ; i <list.size(); i++){
            if(list.get(rangPoissonProche(i)) instanceof Thon){
                Point cible= list.get(rangPoissonProche(i)).position;
                list.get(i).move(cible);

            }else if(list.get(rangPoissonProche(i)) instanceof Requin){

                list.get(i).move(centre);
            }
        }
    }

    


}
