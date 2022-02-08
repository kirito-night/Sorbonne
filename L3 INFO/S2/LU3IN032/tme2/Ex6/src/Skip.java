
public class Skip implements Instruction{
	private Instruction ins;
	
	public Skip(Instruction ins) {
		super();
		this.ins = ins;
	}

	@Override
	public void eval() {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void accept(IVisitorIns v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}
	
}
