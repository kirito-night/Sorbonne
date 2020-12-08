public class WMarchandise extends Wagon{
    
    protected int poidsMax;

    public WMarchandise(String marque, int poidsMax) {
        super(marque);
        this.poidsMax = poidsMax;
    }

    public WMarchandise(String marque) {
        super(marque);
        this.poidsMax = 200;
    }

    @Override
    public String toString() {
        return "WMarchandise [poidsMax=" + poidsMax + "]"  + super.toString();
    }
    
}
