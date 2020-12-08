
public class TestVehicule {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicule v1 = new Velo(3);
		System.out.println(v1.toString());
		v1.transporter("ici", "laba");
		v1.rouler(100);
		System.out.println(v1.toString());
		
		Vehicule v2 = new Voiture(100, 5);
		System.out.println(v2.toString());

		v2.transporter(2,100);
		v2.approvisionner(50);
		v2.transporter(2,100);
		
		Vehicule v3 = new Camion(100, 1000);
		System.out.println(v3.toString());

		v3.approvisionner(50);
		v3.transporter("fer", 50);


		
		
	}

}
