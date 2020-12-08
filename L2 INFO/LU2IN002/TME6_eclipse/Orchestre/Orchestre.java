public class Orchestre{
	private Instrument [] tab_inst;
	private int nb_ins;
	
	
	public Orchestre(int nb_ins, int nb_tot){
		this.nb_ins = nb_ins;
		tab_inst = new Instrument[nb_tot];
		
	}
	public void ajouteInstrument(Instrument i){
		if(nb_ins < tab_inst.length){
			tab_inst[nb_ins] = i;
			nb_ins++;
		}		 
	}
	public void jouer(){
		for(int i =0 ; i < nb_ins; i++)
		{
			tab_inst[i].jouer();
		}
	}
}

