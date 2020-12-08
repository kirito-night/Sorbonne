public class Motrice extends ElemTrain{
    protected double puissance;

    public Motrice(String marque, double puissance) {
        super(marque);
        this.puissance = puissance;
    }

    public Motrice(String marque) {
        super(marque);
        this.puissance  = 100.0;
    }

    @Override
    public String toString() {
        return "Motrice [puissance=" + puissance + "]" + super.toString();
    }
    
    
    
}
