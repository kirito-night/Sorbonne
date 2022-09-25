package pobj.expr;

public class VisitorDerive implements IVisitor<Expression>{
	
	
	private Var var;
	
	
	
	public VisitorDerive(Var var) {
		super();
		this.var = var;
	}

	@Override
	public Expression visit(Constant c) {
		// TODO Auto-generated method stub
		return new Constant();
	}

	@Override
	public Expression visit(Add e) {
		// TODO Auto-generated method stub
		if (Question10.isConstant(e)) {
			return new Constant();
		}
		Expression e1 = e.getLeft().accept(this);
		Expression e2 = e.getRight().accept(this);
		Add res = new Add(e1, e2);
		
		return res;
	}

	@Override
	public Expression visit(Mult e) {
		// TODO Auto-generated method stub
		Expression e1 = e.getLeft();
		Expression e2 = e.getRight();
		Expression e3 = e.getLeft().accept(this);
		Expression e4 = e.getRight().accept(this);
		
		Mult gauche  = new Mult(e1, e4);
		Mult droite = new Mult(e3, e2);
		Add res = new Add(gauche, droite);
		return res;
	}

	@Override
	public Expression visit(Var v) {
		// TODO Auto-generated method stub
		if(v.equals(var)) {
			return new Constant(1);
		}
		return new Constant();

	}
 
}
