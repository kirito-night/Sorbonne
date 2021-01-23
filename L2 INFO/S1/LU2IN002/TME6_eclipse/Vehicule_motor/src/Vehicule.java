
public class Vehicule {
	public static int num;
	private double distance_parcourue;
	
	
	public Vehicule() {
		
		this.distance_parcourue = 0;
		num++;
	}


	@Override
	public String toString() {
		return "Vehicule [distance_parcourue=" + distance_parcourue + "]";
	}
	
	public void rouler(double distance) {
		this.distance_parcourue += distance;
	}
	
	public void transporter (String depart, String arrivee) {}
	public void approvisionner( double nb_Litre) {}
	public void transporter(int n , int km ) {}
	public void transporter(String materiau, int km) {}


	
	
}
