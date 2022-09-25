package pobj.expr;

public class Add extends BinOp{

	public Add(Expression left, Expression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "( "+ super.getLeft().toString()+" + " + super.getRight().toString()+ " )";
	}

	@Override
	public int eval() {
		// TODO Auto-generated method stub
		return getLeft().eval() + getRight().eval();
	}
	
	
	@Override
	public <T> T accept(IVisitor<T> v) {
		// TODO Auto-generated method stub
		return v.visit(this);
	}
}
