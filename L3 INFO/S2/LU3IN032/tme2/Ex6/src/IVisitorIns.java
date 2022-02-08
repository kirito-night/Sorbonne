
public interface IVisitorIns<T>{
	 
    void visit(Affectation skip);
    void visit(Seq e);
    void visit(Ite e);
    void visit(While v);
	void visit(Skip skip);
	
}
