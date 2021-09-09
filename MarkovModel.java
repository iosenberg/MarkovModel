/**
 * @Ike Osenberg
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MarkovModel {

	HashMap<String,State> model;
	int K;
	
	public MarkovModel(int k, String fileName) {
		K = k;
		model = new HashMap<String,State>();
		train(fileName);
	}
	
	//train() fills in the HashMap with the working character as a String s being the key, and the state associated with s being the value
	void train(String fileName) {
		try {
			FileReader R = new FileReader(fileName);
			String s = "";
			for (int i=0;i<K;i++) s += (char) R.read();
			int c = R.read();
			while(c!=-1) {
				if (model.containsKey(s)) 
					model.get(s).add((char) c);
				else {
					model.put(s, new State(s));
					model.get(s).add((char) c);
				}
				s = s.substring(1) + (char) c;
				c = R.read();
			}
			R.close();
		}
		catch (FileNotFoundException e) {System.out.println(e);}
		catch (IOException e) {System.out.println(e);}
	}
	
	//Only for debug purposes
	public void printModel() {
		System.out.printf("%d distinct states:\n", model.size());
		for (String s : model.keySet()) 
			System.out.printf("   %s\n",model.get(s));
	}
	
	//Generates the number of characters initially input (M), choosing the next characters randomly based
	//on the percentage chance they would follow the working character (c)
	public String generateText(int M, String start) {
		String text = start;
		String s = start;
		
		for (int i=0;i<M;i++) {
			if (!(model.containsKey(s))) s = start;
			char c = model.get(s).generate();
			text += c;
			s = s.substring(1) + c;
		}
		
		return text;
	}
}
