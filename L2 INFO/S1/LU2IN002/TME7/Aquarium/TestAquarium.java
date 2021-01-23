public class TestAquarium {
    public static void main(String[] args) {
        int nb_thon = Integer.parseInt(args[0]);
        int nb_requin =Integer.parseInt(args[1]);
        Aquarium a1 = new Aquarium(nb_thon, nb_requin);
        System.out.println(a1.toString());
        a1.pl.bougeTousPoisson();
        for(int i = 0 ; i < 3; i++){
            System.out.println("######Separation#####");
        }
        System.out.println(a1.pl.nbThons());
        for(int i = 0 ; i < 3; i++){
            System.out.println("######Separation#####");
        }
        System.out.println(a1.toString());

    }
}
