
public class While implements Instruction {
	private Instruction ins;
	private Expression expr;
	public While(Instruction ins, Expression expr) {
		super();
		this.ins = ins;
		this.expr = expr;
	}
	@Override
	public void eval() {
		// TODO Auto-generated method stub
		while(expr.eval() !=0) {
			ins.eval();
			
		}
	}
	@Override
	public void accept(IVisitorIns v) {
		// TODO Auto-generated method stub
		 v.visit(this);
	}
	
	
}
