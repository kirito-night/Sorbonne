package pobj.expr;

public interface IVisitor<T> {
    T visit(Constant c);
    T visit(Add e);
    T visit(Mult e);
    T visit(Var v);
}
