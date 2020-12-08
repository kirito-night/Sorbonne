
public class Voiture extends AMoteur{
	protected int nb_place;

	public Voiture(double capacite, int nb_place) {
		super(capacite);
		this.nb_place = nb_place;
	}

	@Override
	public String toString() {
		return "Voiture [nb_place=" + nb_place + "]";
	}
	
	public void transporter(int n , int km ) {
	 if(super.enPanne())
	 	System.out.println("plus d'essence");
	 else {
			System.out.println("la voiture num "+Vehicule.num+"a transporte "+ n+" personne sur " + km);

	 }
	}
	
	
}
