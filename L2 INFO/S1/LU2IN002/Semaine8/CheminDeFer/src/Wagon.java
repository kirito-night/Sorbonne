public abstract class Wagon extends ElemTrain{
    protected int nb_porte;

    public Wagon(String marque, int nb_porte) {
        super(marque);
        this.nb_porte = nb_porte;
    }

    public Wagon(String marque) {
        super(marque);
        this.nb_porte = 8;
    }

    @Override
    public String toString() {
        return "Wagon [nb_porte=" + nb_porte + "]" + super.toString();
    }
    
    
}
