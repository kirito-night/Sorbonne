package pobj.expr;

public class VisitorSimplify implements IVisitor<Expression>{

	@Override
	public Expression visit(Constant c) {
		// TODO Auto-generated method stub
		
		return c;
	}

	@Override
	public Expression visit(Add e) {
		// TODO Auto-generated method stub
		
		if (Question10.isConstant(e)) {
			
			Expression c = new Constant(Question10.evalConstantExpression(e));
			return c;
		}
		
		Expression e1 = e.getLeft().accept(this);
		Expression e2 = e.getRight().accept(this);
		if(Question10.isConstant(e1) && Question10.evalConstantExpression(e1) ==0) {
			return e2;
		}
		if(Question10.isConstant(e2) && Question10.evalConstantExpression(e2) == 0) {
			return e1;
		}
		
		return e;
	}

	@Override
	public Expression visit(Mult e) {
		// TODO Auto-generated method stub
		
		
	if (Question10.isConstant(e)) {
			
			Expression c = new Constant(Question10.evalConstantExpression(e));
			return c;
		}
		
		Expression e1 = e.getLeft().accept(this);
		Expression e2 = e.getRight().accept(this);
		if(Question10.isConstant(e1) && Question10.evalConstantExpression(e1) == 0) {
			return new Constant();
		}
		if(Question10.isConstant(e2) && Question10.evalConstantExpression(e2) == 0) {
			return new Constant();
		}
		if(Question10.isConstant(e2) && Question10.evalConstantExpression(e2) == 1 ) {
			return e1;
		}
		if(Question10.isConstant(e1) && Question10.evalConstantExpression(e1) == 1) {
			return e2;
		}
		
		
		return e;
	}

	@Override
	public Expression visit(Var v) {
		// TODO Auto-generated method stub
		return v;
	}
	
}
