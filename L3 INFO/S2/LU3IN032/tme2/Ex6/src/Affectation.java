
public class Affectation implements Instruction{
	private Expression expr;
	private Var var;
	
	
	public Affectation(Expression expr , Var var) {
		super();
		this.expr = expr;
		this.var = var;
	}

	@Override
	public void eval() {
		// TODO Auto-generated method stub
		Constant c = new Constant(expr.eval());
		Var.mapVar.put(var, c);
		return ; 
	}

	@Override
	public void accept(IVisitorIns v) {
		// TODO Auto-generated method stub
		v.visit(this);

	}
	
	
}
