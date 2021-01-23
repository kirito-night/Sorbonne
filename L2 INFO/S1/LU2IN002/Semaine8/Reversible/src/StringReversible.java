public class StringReversible implements Reversible {
    private String string;
    public StringReversible(String s){
        string = s;
    }

    public String  reverse(){
        int i = string.length();
        String s = "";
        for(int j = i-1 ; j >= 0 ; j--){
            s+= string.charAt(j);
        }
        return s;

    }


    public int length (){
        return string.length();
    }
    
    public char charAt(int i){
        return string.charAt(i);
    }

    @Override
    public String toString() {
        return "StringReversible [string=" + string + "]";
    }
    
}
