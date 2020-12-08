public class Personne{
	private String nom;
	private boolean celibataire = true;
	
	public Personne(){
		this("pers");
		nom = nom + tirageLettre()+tirageLettre()+tirageLettre();
	}
	
	public Personne (String nom)
	{
		this.nom = nom; 
	}
	
	private char tirageLettre(){
		return (char)((int)(Math.random()*26)+'A');
	}
	
	public String toString()
	{
		if(!(celibataire)){
			return nom + " marie";
		}
		else{
			return nom + " celibataire";
		}
	}
	public void epouser(Personne p)
	{	
		if(this.celibataire && p.celibataire)
		{
			this.celibataire = false;
			p.celibataire = false;
			System.out.println(this.nom+" et "+p.nom+" s'est mairie");
		}
		else{
			System.out.println("Ce marriage est impossible");
		}
	}
	public void divorcer()
	{
		if(!(this.celibataire)){
			this.celibataire = true;
		}
	}
}
