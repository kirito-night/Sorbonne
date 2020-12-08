public abstract class ElemTrain {
    protected static int cpt= 10000; 
    protected int num_serie;
    protected String marque;

    public ElemTrain(String marque) {
        this.marque = marque;
        num_serie = cpt;
        cpt++;

    }

    @Override
    public String toString() {
        return "ElemTrain [marque=" + marque + ", num_serie=" + num_serie + "]";
    }

    

    


}
