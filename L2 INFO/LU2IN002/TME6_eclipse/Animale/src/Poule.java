
public class Poule extends Animal{
	protected static int cpt = 0;
	protected int id;
	@Override
	
	public String toString() {
		return String.format("p%02d", id);
	}
	public Poule(int x, int y) {
		super(x, y,"Poule");
		id = cpt;
		cpt++;
	}
	public void afficher() {
		System.out.println(this.toString()+ " , x = "+ super.x+" , y ="+super.y);
	}
}
