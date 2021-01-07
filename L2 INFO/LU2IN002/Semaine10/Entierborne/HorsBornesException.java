package Entierborne;

public class HorsBornesException  extends Exception{

    public HorsBornesException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "HorsBornesException : "  + this.getMessage();
    }

    
    
    
}
