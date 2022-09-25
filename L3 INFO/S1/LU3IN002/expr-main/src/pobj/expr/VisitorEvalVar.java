package pobj.expr;

import java.util.Map;

public class VisitorEvalVar  extends VisitorEval  {
	private Map<String, Integer> map;

	public VisitorEvalVar(Map<String, Integer> map) {
		super();
		this.map = map;
	}
	
	
	@Override
	public Integer visit(Var v) {
		// TODO Auto-generated method stub
		return map.get(v.getName());
	}
	
}
