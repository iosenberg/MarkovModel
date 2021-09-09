/**
 * @Ike Osenberg
 */

import java.util.Random;
import java.util.TreeMap;

public class State {
	//State holds the information for each letter which is used to generate the text following that letter
	//s is the letter, suffixes is the Map of strings K long which follow s and the number of times they follow s, 
	//and count is the number of times s appears in the text
	TreeMap<Character,Integer> suffixes;
	String str;
	int count;
	Random rand;
	
	public State(String s) {
		suffixes = new TreeMap<Character,Integer>();
		str = s;
		count = 0;
		rand = new Random();
	}
	
	//Adds a suffix to the state, either as a new key/value pair if it's not in the map, or adding to its count if it is in the map
	public void add(char c) {
		if (suffixes.containsKey(c)) suffixes.put(c,suffixes.get(c)+1);
		else suffixes.put(c,1);
		count++;
	}
	
	//Translates the state to a String, for debug purposes
	public String toString() {
		String s = String.format("%d %s",count,str);
		for (Character ch : suffixes.keySet())
			s += String.format(" (%c %d) ", ch, suffixes.get(ch));
		return s;
	}
	
	char generate() {
		int r = rand.nextInt(count);
		for (char c : suffixes.keySet()) {
			r -= suffixes.get(c);
			if (r<0) return c;
		}
		return ' ';
	}
}
