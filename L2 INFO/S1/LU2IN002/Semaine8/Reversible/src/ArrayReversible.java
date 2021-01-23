import java.util.ArrayList;

public class ArrayReversible extends ArrayList implements Reversible{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void ajout(Object o) {
        super.add(o);
    }

    public ArrayList<Object> reverse(){
        int j =this.size();
        ArrayList<Object> tmp= new ArrayList<Object>();
        for(int i = j - 1 ; i >= 0 ; i-- ){
            tmp.add(this.get(i));


        }
        return tmp;



    }

    @Override   
    /*public Object get(int index){
        return super.get(index);
    }*/

    public String toString() {
        String s = "";
        for (Object object : this) {
            s += object.toString() + "\n";
        }
        return s;
    }

    

}
