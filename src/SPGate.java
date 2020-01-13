
public abstract class SPGate implements Gate {

	protected Gate child1, child2;
	
	public SPGate(Gate c1, Gate c2) {
		this.child1 = c1;
		this.child2 = c2;
	}
	
	public abstract double output();
	
	public int getNE() {
		return 2 + child1.getNE() + child2.getNE();
	}
	
	public int getNP() {
		return 1 + child1.getNP() + child2.getNP();
	}
}
