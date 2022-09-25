package pobj.expr;

public class Constant implements Expression{
	private int  value;

	public Constant(int entier) {
		super();
		this.value = entier;
	}

	public Constant() {
		super();
		this.value = 0;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int entier) {
		this.value = entier;
	}

	@Override
	public String toString() {
		return ""+value;
	}

	@Override
	public int eval() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public <T> T accept(IVisitor<T> v) {
		// TODO Auto-generated method stub
		return v.visit(this);
	}
	
	
	
}
