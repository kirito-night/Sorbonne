package pobj.expr;

public class Question4 {
	public static Expression e1() {
		Expression f1 = new Constant(2);
		Expression f2 = new Constant(3);
		Expression f3 = new Constant(4);
		
		Expression add = new Add(f1, f2);
		
		Expression mult = new Mult(add, f3);
		return mult;
	}
	
	
	public static Expression e2(){
		Expression f1 = new Var("x");
		Expression f2 = new Constant(3);
		Expression f3 = new Constant(4);
		
		Expression add1 = new Add(f1, f2);
		Expression add2 = new Add(f1, f3);
		Expression mult = new Mult(add1,add2);
		return mult;
	}
	
	
	
	public static Expression e3(){
		Expression f1 = new Var("x");
		Expression f4 = new Var("y");
		Expression f2 = new Constant(10);
		Expression f3 = new Constant(-8);
		
		Expression add1 = new Add(f1, f2);
		Expression add2 = new Add(f4, f3);
		Expression mult = new Mult(add1,add2);
		return mult;
	}
}
