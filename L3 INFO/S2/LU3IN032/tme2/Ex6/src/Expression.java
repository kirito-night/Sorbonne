
public interface  Expression {
	int eval();
	public <T> T accept(IVisitor<T> v);
}
