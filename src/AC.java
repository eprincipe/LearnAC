
public class AC {

	private Gate root;
	private InputGate input[];
	private Gate test;
	private int tindex;
	private boolean used[];
	
	public AC(double[] p) {
		input = new InputGate[p.length];
		used = new boolean[p.length];
		for(int i = 0; i < p.length; i++)
			input[i] = new InputGate(p[i], i);
		root = input[0];
		used[0] = true;
	}

	public int getNE() {
		return root.getNE();
	}

	public double getNP() {
		return root.getNP();
	}
	
	public double getOutputValue(int[] values) {
		for(int i = 0; i < input.length; i++)
			input[i].setValue(values[i]);
		return root.output();
	}
	
	public Gate getRoot() {
		return root;
	}
	
	public void setRoot(Gate root) {
		this.root = root;
	}

	public double getOutputTest(int[] values) {
		for(int i = 0; i < input.length; i++)
			input[i].setValue(values[i]);
		return test.output();
	}

	public boolean setTest(int gate, boolean sum) {
		if(used[gate])
			return false;
		this.test = sum? new SumGate(root, input[gate]):
			new ProdGate(root, input[gate]);
		tindex = gate;
		return true;
	}
	
	public void addGate() {
		this.root = test;
		used[tindex] = true;
	}

	public boolean hasUnusedNodes() {
		for(int i = 0; i < used.length; i++)
			if(!used[i])
				return true;
		return false;
	}
	
	public String toString() {
		return root.toString();
	}
}
