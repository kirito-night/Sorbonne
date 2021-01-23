
public class Velo extends SansMoteur {
	private int nb_vitesse;
	public Velo(int nb_vitesse) {	
		super();
		this.nb_vitesse = nb_vitesse;
	}

	@Override
	
	public void transporter (String depart, String arrivee) {
		System.out.println("le velo num "+ Vehicule.num +" a roule de "+ depart+ " a " + arrivee);
	}
	

	@Override
	public String toString() {
		return super.toString() + "Velo [nb_vitesse=" + nb_vitesse + "]";
	}
	
	
}
