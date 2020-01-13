
public class LearnAC {
	
	private static int data[][];
	private static double marg[];
	private static double ke, kp;
	private static double freq[], results[];

	public static void main(String[] args) {
		
		data = IO.getData(args[0]);
		ke = IO.getKe();
		kp = IO.getKp();
		
		initFreq();
		
		AC ac = learnAC();
		
		getResults(ac);
		
		IO.printMarginals(marg);
		IO.printResults(freq, results, ac.toString());
		
		
	}

	private static void initFreq() {
		freq = new double[(int)Math.pow(2, data[0].length)];
		for(int i = 0; i < data.length; i++)
			freq[getIndex(data[i])]++;
		
		
		for(int i = 0; i < freq.length; i++) 
			freq[i] = freq[i]/data.length; 
	}

	public static int getIndex(int[] sample) {
		int index = 0;
		for(int i = 0; i < sample.length; i++) {
			index = index << 1;
			index += sample[i]; 
		}
		return index;
	}
	
	public static int[] getValues(int index) {
		int ret[] = new int[data[0].length];
		for(int i = data[0].length-1; i >= 0; i--) {
			ret[i] = index % 2;
			index = index >> 1;
		}
		
		return ret;
	}

	private static AC learnAC() {
		//initialize C as product of marginals
		AC c = initialize();
		boolean check = false;
		//It was opted to linearly add in a tree for complexity concerns
		while(c.hasUnusedNodes() && !check) {
			check = true;
			for(int i = 1; i < data[0].length; i++) {
				if(c.setTest(i, true)) {
					double ss = score(c, true);
					c.setTest(i, false);
					double ps = score(c, true);
					
					if(ss > ps)
						c.setTest(i, true);
					
					//successful test, add the edge
					if(score(c, true) > score(c, false)) {
						c.addGate();
						check = false;
					}
				}
			}
		}
		
		return c;
	}

	private static double score(AC c, boolean t) {
		double likf = 0;
		for(int i = 0; i < data.length; i++) {
			likf += Math.log(t? c.getOutputTest(data[i]):
				c.getOutputValue(data[i])); 
		}
		
		return likf - c.getNE()*ke - c.getNP()*kp;
	}

	private static AC initialize() {
		marg = new double[data[0].length];
		for(int i = 0; i < data[0].length; i++) {
			marg[i] = getFreq(i, 0);
		}
		
		return new AC(marg);
	}

	private static double getFreq(int variable, int value) {
		double count = 0;
		for(int i = 0; i < data.length; i++)
			count += data[i][variable] == value? 1: 0; 
		return count/data.length;
	}
	
	private static void getResults(AC ac) {
		results = new double[(int)Math.pow(2, data[0].length)];
		double total = 0;
		for(int i = 0; i < freq.length; i++) {
			results[i] = ac.getOutputValue(getValues(i));
			total += results[i];
		}
		
		for(int i = 0; i < results.length; i++)
			results[i] = results[i]/total;
	}
}
