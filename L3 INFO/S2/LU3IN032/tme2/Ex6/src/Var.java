import java.util.HashMap;
import java.util.Map;

public class Var implements Expression {
	private final String name;
	public static Map<Var, Constant> mapVar = new HashMap<>();
	public Var(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Var other = (Var) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int eval() {
		// TODO Auto-generated method stub
		
		return Var.mapVar.get(name).eval();
		
	}

	@Override
	public <T> T accept(IVisitor<T> v) {
		// TODO Auto-generated method stub
		return v.visit(this);
	}
}