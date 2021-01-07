package Entierborne;

public class DivisionParZeroException extends Exception{

    public DivisionParZeroException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DivisionParZeroException []" + this.getMessage();
    }
    
}
    
