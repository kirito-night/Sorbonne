
public interface IVisitor<T> {
        T visit(Constant c);
        T visit(Add e);
        T visit(Equal e);
        T visit(Var v);
}
