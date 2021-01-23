package Entierborne;



public class EntierBorne {
    private int val ; 
    public static final int BORNESUP= 10000;
    public static final int BORNEINF= -10000;

    public EntierBorne (int i) throws HorsBornesException{
        if(i > BORNESUP || i < BORNEINF){
            throw new HorsBornesException("la valeur" + i + " est en dehors de la borne ");
        }
        this.val = i;
        
    }
    
    public EntierBorne somme (EntierBorne i) throws HorsBornesException{
        int sum = this.val + i.val ;
        if(sum > BORNESUP || sum < BORNEINF){
            throw new HorsBornesException("la valeur : " + sum + " est en dehors de la borne, la somme est trop grande");
        }

        return  new EntierBorne(sum);
         
    }

    public EntierBorne divPar(EntierBorne i) throws HorsBornesException, DivisionParZeroException {
        if ( i.val == 0 ){
            throw new DivisionParZeroException("division par 0");
        }
        int div = this.val / i.val ;
        if(div > BORNESUP || div < BORNEINF){
            throw new HorsBornesException("la valeur " + div + " est en dehors de la borne");
        }
        return new EntierBorne(div);
    }
    public EntierBorne factorielle() throws HorsBornesException, IllegalArgumentException {
        if(this.val < 0){
            throw new IllegalArgumentException("factrorielle ne peut pas etre negatif");
        }
        int fac = 1; 
        int n = this.val;
        while(n > 0){
            fac *= n;
            System.out.println(fac);
            System.out.println(n);


            n-=1;
        }


        if(fac > BORNESUP || fac < BORNEINF){
            throw new HorsBornesException("la valeur" + fac + " est en dehors de la borne, la factorielle est trop grande");
        }
        return new EntierBorne(fac);

    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
    
    

    

}
