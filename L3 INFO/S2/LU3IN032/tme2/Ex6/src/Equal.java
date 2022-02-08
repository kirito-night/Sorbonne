
public class Equal extends BinOp{

	public Equal(Expression left, Expression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "( "+ super.getLeft().toString()+" = " + super.getRight().toString()+ " )";
	}

	@Override
	public int eval() {
		// TODO Auto-generated method stub
		if (super.getLeft().eval() == super.getRight().eval()) {
			return 1;
		}
		return 0;
	}

	@Override
	public <T> T accept(IVisitor<T> v) {
		// TODO Auto-generated method stub
		return v.visit(this);
	}

}
