
public class Ite implements Instruction {
	private Instruction ins1;
	private Instruction ins2; 
	private Expression expr;
	public Ite(Instruction ins1, Instruction ins2, Expression expr) {
		super();
		this.ins1 = ins1;
		this.ins2 = ins2;
		this.expr = expr;
	}
	@Override
	public void eval() {
		// TODO Auto-generated method stub
		if (expr.eval()== 0){
			 ins2.eval();
		}
		else {
			ins1.eval();
		}
	}
	@Override
	public void accept(IVisitorIns v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}
	
	
	
	
}
