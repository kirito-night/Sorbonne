
public class Animal {
	protected int x; 
	protected int y;
	protected String type;
	
	public Animal(int x, int y,String type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double distance(Animal a) {
		int i =  Math.abs(this.x-a.x);
		int j = Math.abs(this.y-a.y);
		return Math.sqrt(i*i+j*j);
	}
	public void move(int i, int j) {
		this.x +=i;
		this.y += j;
		
	}
	
	public void move (Animal a) {}
	
	public void afficher() {
		System.out.println("je suis un animal");
	}
	
}
