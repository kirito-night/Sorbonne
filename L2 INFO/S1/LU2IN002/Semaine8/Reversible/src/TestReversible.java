import java.util.ArrayList;

public class TestReversible {
    public static void main(String[] args) {
       StringReversible s1 = new StringReversible("abcde");
       String s2 =  s1.reverse();
       System.out.println(s2);
       Reversible s3 = s1;
       System.out.println(s3.toString());

       ArrayReversible i1 = new ArrayReversible();
        for(int i = 0 ; i < 10 ; i ++){
            i1.add(i);

        }
        System.out.println(i1.toString());

        ArrayList<Object> i2 =  i1.reverse();

        for (Object object : i2){
            System.out.println(object);
        }




    
    }
}
