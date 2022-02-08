

public abstract class BinOp implements Expression {
	private final  Expression left;
	private final Expression right;
	public BinOp(Expression left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}
	
	
	public Expression getLeft() {
		return left;
	}
	public Expression getRight() {
		return right;
	}
	
	
}