public class TestAttrapePas1 {

    public static int moyenne(String[] tab) {
        int sum = 0;
        int nbnote = 0;
        for(int i = 0; i < tab.length ; i++){
            sum += Integer.parseInt(tab[i]);
            nbnote++;
        }
        
        return sum / nbnote;
    }


    public static void main(String[] args) {
        System.out.println("la moyenne est : " + moyenne(args));
        System.out.println("la valeur est " + args[0]);
    }



}



