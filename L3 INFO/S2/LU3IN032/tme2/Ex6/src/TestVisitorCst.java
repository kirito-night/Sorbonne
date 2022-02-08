
public class TestVisitorCst {
	public static void main (String[] args) {
		Constant c1 = new Constant(3);
		
		Constant c2 = new Constant(4);
		
		Add a = new Add(c1, c2);
		VisitorConstant vc = new VisitorConstant();
		
		Integer i = c1.accept(vc);
		System.out.println(i);
		Integer r1 = a.accept(vc);
		System.out.println( a +" = " + r1);
		
	}
}
