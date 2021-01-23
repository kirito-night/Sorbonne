
public class Instrument{
	private double poids;
	private double prix; 
	
	public Instrument(double poids, double prix){
		this.poids = poids;
		this.prix = prix;
	}
	
	public String toString(){
		// toString est une herite si on ne la redefinie pas dans les classes filles
		return "Instrument [poids=" + poids + ", prix=" + prix + "]";
	}
	
	public void  jouer(){}
}









		
	
