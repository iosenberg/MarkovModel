/**
 * @Ike Osenberg
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextGenerator {

	//Takes in args int k (in Markov Model), int characters to generate, and file to read
	public static void main(String[] args) {
		int K = Integer.parseInt(args[0]);
		String start = "";
		MarkovModel model = new MarkovModel(K,args[2]);
		try {
			//Reads and prints the first K letters
			FileReader R = new FileReader(args[2]);
			for (int i=0;i<K;i++) start += (char)R.read();
			R.close();
		} 
		catch (FileNotFoundException e) {System.out.println(e);}
		catch (IOException e) {System.out.println(e);}
		
		//Generates and prints rest of the file, using the first K letters as a basis
		System.out.println(model.generateText(Integer.parseInt(args[1]),start));
	}

}
