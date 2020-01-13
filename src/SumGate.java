
public class SumGate extends SPGate {
	
	public SumGate(Gate c1, Gate c2) {
		super(c1, c2);
	}

	@Override
	public double output() {
		return child1.output()+child2.output();
	}
	
	public String toString() {
		return "(" + child1.toString() + " + " + child2.toString() + ")"; 
	}

}
