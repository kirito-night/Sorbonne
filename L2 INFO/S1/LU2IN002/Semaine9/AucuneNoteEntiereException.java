public class AucuneNoteEntiereException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public AucuneNoteEntiereException (String s){
        super(s);
    }
    @Override
    public String toString(){
        return "aucune note n'est valide \n";
    }
    
}
