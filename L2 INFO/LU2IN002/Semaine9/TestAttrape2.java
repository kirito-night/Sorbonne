public class TestAttrape2 {
    public static int moyenne(String[] tab) {
        int sum = 0;
        int nbnote = 0;
        for(int i = 0; i < tab.length ; i++){
            try{
                sum += Integer.parseInt(tab[i]);
                nbnote++;
            }
            catch(NumberFormatException e){
                
                System.out.println("la : " + (i+1) + " elem  n'et pas un entier");
            }
        }
        
        return sum / nbnote;
    }


    public static void main(String[] args) {
        try{
            System.out.println("la moyenne est : " + moyenne(args));

        }
        catch(NumberFormatException e){
            System.out.println("une des notes n'et pas un entier");
        }

    }
}
