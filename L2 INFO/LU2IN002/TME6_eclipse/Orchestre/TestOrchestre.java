public class TestOrchestre{
	public static void main(String[] args){	
		Instrument i1 = new Piano(100,5000);
		Instrument i2 = new Guitar(30,500);
		Instrument i3 = new Trompette(10,50);
		
		Orchestre o1 = new Orchestre(0,3);
		o1.ajouteInstrument(i1);
		o1.jouer();
		o1.ajouteInstrument(i2);
		o1.jouer();
		o1.ajouteInstrument(i3);
		o1.jouer();
	}
}
