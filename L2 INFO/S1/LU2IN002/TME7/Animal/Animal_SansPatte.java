public  class Animal_SansPatte extends Animal {
    public Animal_SansPatte (String nom, int age){ 
        super(nom, age);

    }
     public void crier(){
            System.out.println(super.nom + " cries");
        }
    
    @Override
    public String toString() {
        return super.toString() +"Animal_SansPatte []";
    }

    
}

