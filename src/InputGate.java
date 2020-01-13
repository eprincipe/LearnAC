
public class InputGate implements Gate {

	private double p;
	private int v;
	private int name;
	
	public InputGate(double p, int n) {
		this.p = p;
		this.name = n;
	}
	
	public void setValue(int v) {
		this.v = v;
	}
	@Override
	public double output() {
		if(v ==0)
			return p;
		else
			return 1-p;
	}

	@Override
	public int getNE() {
		return 0;
	}

	@Override
	public int getNP() {
		return 1;
	}

	public String toString() {
		return ""+name;
	}
}
