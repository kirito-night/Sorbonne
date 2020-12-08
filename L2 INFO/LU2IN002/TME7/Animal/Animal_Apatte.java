public class Animal_Apatte extends Animal {
    protected int nb_patte;

    public Animal_Apatte(String nom , int age ,int nb_patte) {
        super(nom,age);
        this.nb_patte = nb_patte;
    }
    
    public void crier(){
            System.out.println(super.nom + " cries");
        }
    


    @Override
    public String toString() {
        return super.toString() + "Animal_Apatte []";
    }
    

}
