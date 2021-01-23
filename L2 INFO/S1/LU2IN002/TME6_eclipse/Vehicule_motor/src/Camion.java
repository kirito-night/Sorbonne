
public class Camion extends AMoteur {
	protected double vol;

	public Camion(double capacite, double vol) {
		super(capacite);
		this.vol = vol;
	}

	@Override
	public String toString() {
		return super.toString() + "Camion [vol=" + vol + "]";
	}
	
	public void transporter(String materiau, int km) {
		if(super.enPanne()) {
			System.out.println("plus d'essence");
		}
		else {
			System.out.println("le camion num " + Vehicule.num+" a transporte des "+ materiau+" sur "+km);
		}
	}
}
