
public class Renard extends Animal{
	protected static int cpt = 0;
	protected int id;
	@Override
	
	public String toString() {
		return String.format("r%02d", id);
	}
	public Renard(int x, int y) {
		super(x, y,"Renard");
		id = cpt;
		cpt++;
	}
	public void afficher() {
		System.out.println(this.toString()+ " , x = "+ super.x+" , y ="+super.y);
	}
}
