import java.util.Scanner;
import java.io.*;

public class hashTablePartTwo {
	
// H = Hash Table Size, N = Number of AUID's
public static final int H = 50, N = 31;
  
	// Hash calculation
	static int hash(int x) {
		return (x % N);
	}
  
	// Key insertion
	static int insert(int hashTable[], int x, int i) {
  
		int pos = (hash(x) + i) % H;
		if(hashTable[pos] == 0) {
			hashTable[pos] = x;
			return 0;
		}
		
		return 1 + insert(hashTable, x, i + 1);
	}
  
	// Search for the keys from the AUID text file
	static int search(int hashTable[], int key, int i) {
  
		int pos = (hash(key) + i) % H;
  
		// If a key inputed is missing from the AUID text file, the program
		// will display that the inputed key is not part of the AUID list.
		if(i == H || hashTable[pos] == 0) {
			System.out.println(key + " is not in AUID list.");
			return 0;
		}
		
		// If an inputed key is found in the AUID text file, the program
		// will display the inputed key and its location, or index, in
		// the hash table. 
		if(hashTable[pos] == key) {
			System.out.println(key + " found at index: " + pos);
			return 0;
		}
		
		// Add each key to the hash table after review. 
		return 1 + search(hashTable, key, i+1);
	}
  
  	// Driver Code
	public static void main(String[] args) {
  
		// hash table size of 50
		int hashTable[] = new int[50];
		double avg = 0.0;
  
		// Find the AUID text file and read it through. 
		try {
			Scanner sc = new Scanner(new File("C:\\Users\\jeffz\\Downloads\\2020 Fall\\Algorithms and Data Structures\\HWK\\HWK6-JeffZhang\\AUID.txt"));
  
			for(int i=1; i<=32; i++) {
				int x = sc.nextInt();
				avg += insert(hashTable, x, 0);
			}
  
		} 
		
		// If the AUID text file is missing, tell the user. 
		catch(FileNotFoundException e) {
			System.out.println("AUID.text file not found.");
		}
  
		// Calculate average by dividing by N = 32 (number of AUID's)
		avg = avg / 32;
		System.out.println("The average number of probing in creating hash table: " + avg);

		// Print out the hash table
		System.out.println("The Hash Table: ");
		for(int i=0; i<H; i++) {
			System.out.print(hashTable[i] + " ");
		}
		
		System.out.println();
  
		// Add keys here for the program to search
		int searchKeys[] = {97091, 12345, 64301, 68815};
		avg = 0.0;
  
		for(int i=0; i<searchKeys.length; i++) {
			avg += search(hashTable, searchKeys[i], 0);
		}
  
		avg = avg / searchKeys.length;
		System.out.println("Average number of probing in searching hash table: " + avg);
	}
	
}