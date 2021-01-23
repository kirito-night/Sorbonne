public class WVoyageur extends Wagon{
    protected int nb_place;

    public WVoyageur(String marque, int nb_porte, int nb_place) {
        super(marque, nb_porte);
        this.nb_place = nb_place;
    }

    public WVoyageur(String marque) {
        super(marque);
        this.nb_place = 80;
    }

    @Override
    public String toString() {
        return "WVoyageur [nb_place=" + nb_place + "]" + super.toString();
    }
    
    
}
