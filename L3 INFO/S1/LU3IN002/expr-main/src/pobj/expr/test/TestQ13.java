package pobj.expr.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pobj.expr.Add;
import pobj.expr.Constant;
import pobj.expr.Expression;
import pobj.expr.IVisitor;
import pobj.expr.Mult;
import pobj.expr.Question13;
import pobj.expr.Var;
import pobj.expr.VisitorDerive;
import pobj.expr.VisitorEvalVar;
import pobj.expr.VisitorSimplify;
import pobj.expr.VisitorToString;

public class TestQ13 {

    class VisitorNodeCount implements IVisitor<Integer> {
	@Override
	public Integer visit(Constant c) {
		return 1;
	}

	@Override
	public Integer visit(Add e) {
		return e.getLeft().accept(this) + e.getRight().accept(this) + 1;
	}

	@Override
	public Integer visit(Mult e) {
		return e.getLeft().accept(this) + e.getRight().accept(this) + 1;
	}

	@Override
	public Integer visit(Var v) {
		return 1;
	}
}


	@Test
	public void testQ13() {
		int rese, resd;
		Expression dix = new Constant(10);
		Expression vingt = new Constant(20);
		Var x = new Var("x");
		Var z = new Var("z");

		VisitorDerive vdz = new VisitorDerive(z);

		VisitorSimplify vs1 = new VisitorSimplify();
		VisitorNodeCount vnn1 = new VisitorNodeCount();
		VisitorToString vts1 = new VisitorToString();
		Map<String, Integer> env4 = new HashMap<>();
		env4.put("x", 3);
		env4.put("z", 7);
		VisitorEvalVar vev4 = new VisitorEvalVar(env4);

		System.out.println(
				"TRACE: expr \n!expr_derivee_simplifee ==> (eval expr, eval expr_derivee_Simplifye) nb = [nbNoeud expr, nbNoeud expr_derivee_Simplifye]");

		Expression e1 = new Mult(new Add(z, dix), new Mult(new Add(z, x), new Add(vingt, z)));
		Expression e4 = new Mult(z, new Mult(z, new Mult(z, z)));

		Expression eds1 = Question13.compose(vs1, vdz, e1);
		rese = e1.accept(vev4);
		resd = eds1.accept(vev4);
		System.out.println("e1=" + e1.accept(vts1) + ", eds1=" + eds1.accept(vts1) + "\n!"
				+ eds1.accept(vs1).accept(vts1) + " ==> (" + rese + " " + resd + ")" + "nb = [" + e1.accept(vnn1) + ","
				+ eds1.accept(vnn1) + "]");
		assertTrue(e1.accept(vnn1) <= eds1.accept(vnn1));
		assertTrue(899 == resd);

		Expression eds4 = Question13.compose(vs1, vdz, e4);
		rese = e4.accept(vev4);
		resd = eds4.accept(vev4);
		System.out.println("TRACE: e4=" + e4.accept(vts1) + ", eds4=" + eds4.accept(vts1) + "\n!"
				+ eds4.accept(vs1).accept(vts1) + " ==> (" + rese + " " + resd + ")" + "nb = [" + e4.accept(vnn1) + ","
				+ eds4.accept(vnn1) + "]");
		assertTrue(e4.accept(vnn1) <= eds4.accept(vnn1));
		assertTrue(1372 == resd);

	}

}
