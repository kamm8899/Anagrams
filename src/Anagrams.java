//@author Jessica Kamman

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

 
//computes a list of words that gives anagrams
public class Anagrams {
	
	//prime numbers
	final Integer[] primes={2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
	
	
	  //define Lettertable that holds dictionary for letters
	Map<Character, Integer> letterTable;
	
	   //define anagramTable that holds dictionary of the hash word
	Map<Long, ArrayList<String>> anagramTable;
	
	
	
	//Constructor that will initialize letterTable and anagramTable and that will call BuildLetterTable
	public Anagrams() {
		buildLetterTable(); 
		anagramTable = new HashMap<Long, ArrayList<String>>();
	}
	
// Build the letter table for hashing
	private void buildLetterTable() {
		letterTable= new HashMap<Character,Integer>();
		Character[] alphabets= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		

		for(int i = 0; i < 26; i++) {
			letterTable.put(alphabets[i], primes[i]);
		}
	}
// compute the hash code and add word in hash table
	
	private void addWord(String s) {
		
	
		Long hashcode = this.myHashCode(s);
		if(anagramTable.get(hashcode) == null) {
			ArrayList<String> aArrayList = new ArrayList<String>();
			aArrayList.add(s);
			anagramTable.put(hashcode, aArrayList);
		}
		else {
			anagramTable.get(hashcode).add(s);
		}
	}
	
	   // computes a string's hash code
	private Long myHashCode(String s) {
		
		// result will hold hash code set to 1
		long result=1;
		
		for(int i=0; i<s.length(); i++) {
			result*= (long)letterTable.get(s.charAt(i));
		}
		 // return hash code result
		return result;
	}
	
	
	//Method 3 Process File
	
	private void processFile(String s) throws IOException {
		
	//provided by File
		FileInputStream fStream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
		String strLine;
		
		while((strLine = br.readLine())!= null) {
			this.addWord(strLine);
		}
		
		br.close();
	}
	
	
// This function will return entries with largest number of anagram
	
	private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
		
	//list will hold all hash codes for largest numbers of anagrams
		ArrayList<Map.Entry<Long, ArrayList<String>>> lists = new ArrayList<>();
		//set max
		int max = 0;

		
		for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
			
		
			if(entry.getValue().size() > max) {
				lists.clear();
				lists.add(entry);
				max = entry.getValue().size();
			}
			
			else if(entry.getValue().size() == max) {
				lists.add(entry);
			}
		}
		
 return lists;
}
	
	
	public static void main(String[] args) {
// TODO Auto-generated method stub
		Anagrams a = new Anagrams();
		
		final long startTime = System.nanoTime();
		
		try {
			
			a.processFile ("words_alpha.txt");
			
		}catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries =a.getMaxEntries ();
		
		long key = maxEntries.get(0).getKey();
		int length = maxEntries.get(0).getValue().size();
		
		final long estimatedTime = System.nanoTime() - startTime ;
		final double seconds = ((double)estimatedTime/1000000000);
		
		//Get the values and print to screen
		System.out.println("Elapsed Time : "+ seconds);
		System.out.println("Key of maximum anagrams: "+ key);
		System.out.println("List of max anagrams: " + maxEntries.get(0).getValue());
		System.out.println("Length of list of max anagrams : "+ length);
}
}



 