package pobj.expr;

public interface Expression {
    // initialement vide
	int eval();
	public<T> T accept(IVisitor<T> v);
}
