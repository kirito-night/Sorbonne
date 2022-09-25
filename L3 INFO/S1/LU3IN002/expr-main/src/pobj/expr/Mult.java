package pobj.expr;

public class Mult extends BinOp {

	public Mult(Expression left, Expression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return  getLeft().toString()+" * " + getRight().toString();
	}

	@Override
	public int eval() {
		// TODO Auto-generated method stub
		return getLeft().eval() * getRight().eval();
	}
	
	@Override
	public <T> T accept(IVisitor<T> v) {
		// TODO Auto-generated method stub
		return v.visit(this);
	}
}
