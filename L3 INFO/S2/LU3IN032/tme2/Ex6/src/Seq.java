
public class Seq implements Instruction {
	private Instruction i1;
	private Instruction i2;
	
	public Seq(Instruction i1, Instruction i2) {
		super();
		this.i1 = i1;
		this.i2 = i2;
	}
	@Override
	public  void eval() {
		// TODO Auto-generated method stub
		i1.eval();
		i2.eval();
	}
	@Override
	public void accept(IVisitorIns v) {
		// TODO Auto-generated method stub
		 v.visit(this);
	}
	
	
}
