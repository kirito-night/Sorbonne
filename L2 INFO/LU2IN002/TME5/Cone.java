public class Cone{
	private double r;
	private double h;
	private final static double PI = 3.1415926;
	public static int nbCone = 0;
	
	public Cone (double r, double h){
		this.r = r;
		this.h = h;
		nbCone++;
	}
	public Cone (){
		this(Math.random()*10, Math.random()*10);
	
	}
	
	public double getVolume(){
		return (PI*r*r*h)/2;
	}
	
	public String toString(){
		return "Cone r="+r+" h="+h+" V="+ getVolume();
	}
	
	public static int getNbCone(){
		return nbCone;
	}
}
	
