import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class IO {
	
	private static String name;
	private static double ke, kp;

	public static int[][] getData(String fileName) {
		
		BufferedReader file = null;
		StringTokenizer st;
		try {
			file = new BufferedReader(new FileReader(fileName));
			st = new StringTokenizer(fileName, ".");
			name = st.nextToken();
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
			e.printStackTrace();
			System.exit(-1);
		}		
		
		int data[][] = null;
		try {
			st = new StringTokenizer(file.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(st.hasMoreTokens()) {
				//ke and kp are defined in file
				ke = Double.parseDouble(st.nextToken(" "));
				kp = Double.parseDouble(st.nextToken(" "));
			} else {
				ke = 0.5;
				kp = 0.5;
			}
			
			data = new int[r][c];
			
			for(int i = 0; i < r; i++) {
				st = new StringTokenizer(file.readLine());
				for(int j = 0; j < c; j++) {
					data[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading data file.");
			e.printStackTrace();
			System.exit(-1);
		}
		
		return data;
	}

	public static void printMarginals(double[] marg) {
		
		FileWriter file = null;
		
		try {
			file = new FileWriter(name + ".marg");
		} catch (IOException e) {
			System.err.println("Error trying to output the marginals file.");
			e.printStackTrace();
			System.exit(-1);
		}
		
		try {
			for(int i = 0; i < marg.length; i++)
				file.write(i + "\t" + marg[i] + "\t" + (1-marg[i]) + "\n");
			
			file.close();
		} catch (IOException e) {
			System.err.println("Error trying to output the marginals file.");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static double getKe() {
		return ke;
	}

	public static double getKp() {
		return kp;
	}

	public static void printResults(double[] freq, double[] results, String tree) {
		FileWriter file = null;
		
		try {
			file = new FileWriter(name + ".out");
		} catch (IOException e) {
			System.err.println("Error trying to output the results file.");
			e.printStackTrace();
			System.exit(-1);
		}
		
		try {
			file.write(tree + "\n");
			int indexes[];
			String conf;
			for(int i = 0; i < freq.length; i++) {
				conf = "";
				indexes = LearnAC.getValues(i);
				for(int j = 0; j < indexes.length; j++)
					conf += indexes[j] +" ";
				file.write(conf + String.format("%.16f", results[i]) + "\t" +
						String.format("%.16f", freq[i]) + "\n");
			}
			file.close();
		} catch (IOException e) {
			System.err.println("Error trying to output the results file.");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
